//
//  UploadImage.m
//  oa
//
//  Created by 郑江荣 on 16/5/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "UploadImage.h"
#import "farwolf.h"
#import "FileReader.h"

#import "FileJson.h"


#define IOS8Later ([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0 ? YES:NO)
@implementation UploadImage




-(void)setAsp:(CGFloat)aspX aspY:(CGFloat)aspY
{
    self.aspX=aspX;
    self.aspY=aspY;
}
- (void)showPickImage
{
    [self showPickImage:[UIColor whiteColor] titleColor:[UIColor blackColor] cancelColor:[UIColor blackColor]];
}

- (void)showPickImage:(UIColor*)themecolor titleColor:(UIColor*) titleColor cancelColor:(UIColor*) cancelColor
{
    [self showPickImage:themecolor titleColor:titleColor cancelColor:cancelColor vc:[self getCurrentVc]];
}

- (void)showPickImage:(UIColor*)themecolor titleColor:(UIColor*) titleColor cancelColor:(UIColor*) cancelColor vc:(UIViewController*)vc{
 
    self.themeColor=themecolor;
    self.cancelColor=cancelColor;
    self.titleColor=titleColor;
    
    if (IOS8Later) {
        
        UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"选择照片" message:@"请选择照片上传" preferredStyle: UIAlertControllerStyleActionSheet];
       
        UIAlertAction *actionCancel = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel
                                                             handler:^(UIAlertAction *action) {
                                                                 
                                                             }];
        
        __weak __typeof(self) weakSelf = self;
        
        UIAlertAction *actionCamera = [UIAlertAction actionWithTitle:@"相机" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
            if( [vc checkCameraRights])
            {
                [weakSelf choseImageFromCamera];
            }
            
        }];
        
      
        
        UIAlertAction *actionPicture = [UIAlertAction actionWithTitle:@"图库" style:UIAlertActionStyleDestructive handler:^(UIAlertAction *action) {
            if( [vc checkPhotoRights])
            {
                [weakSelf choseImageFromPicture];
            }
            
        }];
        [alert addAction:actionCamera];
        //        [alert addAction:actionPhoto];
        [alert addAction:actionPicture];
        [alert addAction:actionCancel];
        
        [vc presentViewController:alert animated:YES completion:^{
            
        }];
    } else {
        UIActionSheet *actionsheet = [[UIActionSheet alloc] initWithTitle:@"拍照" delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:nil otherButtonTitles:@"相机", @"相册", nil];
        [actionsheet setTag:100];
        [actionsheet showInView:vc.view];
    }
    
}


-(void)openCamera
{
    if( [[self getCurrentVc] checkCameraRights])
    {
        [self choseImageFromCamera];
    }

}

-(void)openPhoto
{
    if( [[self getCurrentVc] checkPhotoRights])
    {
        [self choseImageFromPicture];
    }

    
}

- (void)choseImageFromCamera {
    if([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
        // 跳转到相机
        UIImagePickerController *imagePickerController = [[UIImagePickerController alloc] init];
        imagePickerController.delegate = self;
        imagePickerController.allowsEditing = NO;
        [imagePickerController.navigationBar setBarTintColor:self.themeColor];
        [imagePickerController.navigationBar setTintColor:self.titleColor];
   
        imagePickerController.sourceType = UIImagePickerControllerSourceTypeCamera;
        [[self getCurrentVc] presentViewController:imagePickerController animated:YES completion:^{
            
        }];
    } else {
        
    }
}

- (void)choseImageFromPhoto {
    if([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypePhotoLibrary]) {
        // 跳转到相册
        UIImagePickerController *imagePickerController = [[UIImagePickerController alloc] init];
        imagePickerController.delegate = self;
        imagePickerController.allowsEditing = NO;
        
        imagePickerController.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        [[self getCurrentVc] presentViewController:imagePickerController animated:YES completion:^{
            
        }];
    } else {
        
    }
}

- (void)choseImageFromPicture {
    if([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypePhotoLibrary]) {
        // 跳转到图库
        UIImagePickerController *imagePickerController = [[UIImagePickerController alloc] init];
        imagePickerController.delegate = self;
        imagePickerController.allowsEditing = NO;
        [imagePickerController.navigationBar setBarTintColor:self.themeColor];
        [imagePickerController.navigationBar setTintColor:self.cancelColor];
        [imagePickerController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:self.titleColor}];
        imagePickerController.sourceType = UIImagePickerControllerSourceTypeSavedPhotosAlbum;
        [[self getCurrentVc] presentViewController:imagePickerController animated:YES completion:^{}];
    } else {
        
    }
}



- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info {
    
    __weak __typeof(self) weakSelf = self;
    
    [picker dismissViewControllerAnimated:YES completion:^() {
        
        
        UIImage* original_image = [info objectForKey:@"UIImagePickerControllerOriginalImage"];
        TOCropViewController *cropViewController = [[TOCropViewController alloc] initWithImage:original_image];
            cropViewController.delegate = self;
            //cropViewController.aspectRatioPreset=TOCropViewControllerAspectRatioPresetCustom;
            cropViewController.aspectRatioLockEnabled=true;
        if(self.aspX==0)
        {
            self.aspX=320;
        }
        if(self.aspY==0)
        {
            self.aspY=320;
        }
        if(self.quality==0)
        {
            self.quality=0.2;
        }
            cropViewController.customAspectRatio=CGSizeMake(self.aspX, self.aspY);
        [cropViewController setAspectRatioPickerButtonHidden:true];
        [[self getCurrentVc] presentViewController:cropViewController animated:YES completion:nil];
        
        
        
      
    }];
    
}


- (void)cropViewController:(TOCropViewController *)cropViewController didCropToImage:(UIImage *)image withRect:(CGRect)cropRect angle:(NSInteger)angle
{
    [cropViewController dismiss:true];
    
      image = [self imageWithImage:image scaledToSize:CGSizeMake(_aspX, _aspY)];
      image = [UIImage imageWithData:UIImageJPEGRepresentation(image, self.quality)];
      [self.delegate imageSelect:image];
      if(self.autoUpload)
      [self submit:image];
}


-(void)submit:(UIImage*)img
{
    FileReader *f=[[FileReader alloc]initWith:UPLOAD_IMG];
    [f addParam:@"file" file:img];
    [f addParam:@"type" value:@"1"];
    [f excuteFile:[self getCurrentVc] success:^(Json *j) {
        FileJson *f=(FileJson*)j;
        [self.delegate imageUploadSuccess:[f getString:@"path"]];
    }];
    
}




- (UIImage*)imageWithImage:(UIImage*)image scaledToSize:(CGSize)newSize {
    CGFloat scale = newSize.width/image.size.width;
    CGRect rect = CGRectMake(0.0, 0.0, newSize.width, newSize.height);
    if (newSize.height/image.size.height > scale) {
        rect.origin.y = (newSize.height - image.size.height*scale)/2.0;
        rect.size.height = image.size.height*scale;
    }
    UIGraphicsBeginImageContextWithOptions(newSize, NO, image.scale);
    
    [image drawInRect:CGRectMake(rect.origin.x, rect.origin.y, rect.size.width, rect.size.height)];
    UIImage* newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return newImage;
}
@end
