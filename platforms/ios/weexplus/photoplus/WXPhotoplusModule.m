//
//  WXPhotoplusModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/5/28.
//

#import "WXPhotoplusModule.h"
#import "TZImagePickerController.h"
#import <ZLPhotoBrowser/ZLPhotoBrowser.h>
#import "farwolf.h"
 
//#import <SDWebImage/SDAnimatedImageView.h>

//注册module，名字叫photoplus
WX_PlUGIN_EXPORT_MODULE(photoplus, WXPhotoplusModule)

@implementation WXPhotoplusModule
    
@synthesize weexInstance;
//异步方法
WX_EXPORT_METHOD(@selector(open:callback:))
//同步返回方法注册
WX_EXPORT_METHOD_SYNC(@selector(getData))
    
-(void)open:(NSMutableDictionary*)param  callback:(WXModuleCallback)callback{
    
    NSString *action=@"choose";
    if(param[@"action"]){
        action=param[@"action"];
    }
    NSString *type=@"photo";
    if(param[@"type"]){
        type=param[@"type"];
    }
    CGFloat aspX=0;
    CGFloat aspY=0;
    CGFloat maxSize=0;
    if(param[@"aspX"]){
          aspX=  [[@"" add:param[@"aspX"]] floatValue] ;
      }
    if(param[@"aspY"]){
             aspY=  [[@"" add:param[@"aspY"]] floatValue] ;
    }
    if(param[@"maxSize"]){
        maxSize=  [[@"" add:param[@"maxSize"]] floatValue] ;
     }

    if([@"choose" isEqualToString:action]){
        if([@"video" isEqualToString:type]){
            [self chooseVideo:callback];
        }else{
            int count=99;
            if(aspX>0&&aspY>0){
                count=1;
            }
            [self chooseImage:maxSize aspX:aspX aspY:aspY count:count callback:callback];
        }
       
    }else{
        self.maxSize=maxSize;
        [self openCamera:aspX aspY:aspY themeColor:@"#000000" callback:callback];
    }
    
    
}

-(void)chooseVideo:(WXModuleCallback)callback{
    TZImagePickerController *imagePickerVc = [[TZImagePickerController alloc] initWithMaxImagesCount:99 delegate:self];
    imagePickerVc.allowPickingVideo=true;
    imagePickerVc.allowTakeVideo=true;
    imagePickerVc.allowPickingImage = NO;
    
    [imagePickerVc setDidFinishPickingVideoHandle:^(UIImage *coverImage, PHAsset *asset) {
        NSMutableDictionary *data=[NSMutableDictionary new];
        NSMutableArray *ary=[NSMutableArray new];
        [[TZImageManager manager] getVideoOutputPathWithAsset:asset presetName:AVAssetExportPresetLowQuality success:^(NSString *outputPath) {
               // NSData *data = [NSData dataWithContentsOfFile:outputPath];
               NSLog(@"视频导出到本地完成,沙盒路径为:%@",outputPath);
               NSMutableArray *ary=[NSMutableArray new];
               NSMutableDictionary *item=[NSMutableDictionary new];
               [item setValue:[PREFIX_SDCARD add:outputPath] forKey:@"path"];
               [ary addObject:item];
               data[@"res"]=ary;
               callback(data);
               // Export completed, send video here, send by outputPath or NSData
               // 导出完成，在这里写上传代码，通过路径或者通过NSData上传
           } failure:^(NSString *errorMessage, NSError *error) {
               NSLog(@"视频导出失败:%@,error:%@",errorMessage, error);
           }];
 
    }];
     imagePickerVc.modalPresentationStyle=UIModalPresentationFullScreen;
//     NSMutableArray *mediaTypes = [NSMutableArray array];
     [weexInstance.viewController presentViewController:imagePickerVc animated:YES completion:nil];
}

-(void)chooseImage:(CGFloat)maxSize aspX:(CGFloat)aspX  aspY:(CGFloat)aspY count:(int)count callback:(WXModuleCallback)callback{

    TZImagePickerController *imagePickerVc = [[TZImagePickerController alloc] initWithMaxImagesCount:count delegate:self];
    CGFloat width=UIApplication.sharedApplication.keyWindow.frame.size.width;
    CGFloat height=UIApplication.sharedApplication.keyWindow.frame.size.height;
    imagePickerVc.allowCrop=true;
//    imagePickerVc.cropRect=CGRectMake(0, 0, aspX, aspY);
    imagePickerVc.allowPickingVideo=NO;
       imagePickerVc.allowPickingImage = true;
     CGFloat cropViewWH = width / 3 * 2;
      
      aspY=aspY*cropViewWH/aspX;
      imagePickerVc.cropRect = CGRectMake((width - cropViewWH) / 2, (height - aspY) / 2, cropViewWH, aspY);
    
    
     [imagePickerVc setDidFinishPickingPhotosHandle:^(NSArray<UIImage *> *photos, NSArray *assets, BOOL isSelectOriginalPhoto) {
         
          NSMutableDictionary *data=[NSMutableDictionary new];
          NSMutableArray *ary=[NSMutableArray new];
          __block int count=0;
          NSString *documentPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
          NSString *photoplus= documentPath=[documentPath add:@"photoplus"];
          if([photoplus isExist]){
              [photoplus delete];
          }
         if(aspX>0&&aspY>0){
               NSString *path= [photos[0] save:[[@"photoplus/imgs/origin/" add:[[NSDate new] getCurrentTimestamp]]add:@".png"]];
             [ary addObject:@{@"path":[PREFIX_SDCARD add:path] }];
             callback(@{@"res":ary});
             return;
         }
       
         for(PHAsset *ps in assets){
             [[PHImageManager defaultManager] requestImageDataForAsset:ps options:nil resultHandler:^(NSData * _Nullable imageData, NSString * _Nullable dataUTI, UIImageOrientation orientation, NSDictionary * _Nullable info) {
                 count++;
                
                 NSMutableDictionary *item=[NSMutableDictionary new];
                  UIImage *img=[UIImage imageWithData:imageData];
                 NSString *path= [img save:[[@"photoplus/imgs/origin/" add:[[NSDate new] getCurrentTimestamp]]add:@".png"]];
                 [item setValue:[PREFIX_SDCARD add:path] forKey:@"path"];
                 if(maxSize>0){
                     NSData *tdata = [img compress:maxSize];
                     UIImage *compressImg=[UIImage imageWithData:tdata];
                     NSString *compressPath= [compressImg save:[[@"photoplus/imgs/origin/" add:[[NSDate new] getCurrentTimestamp]]add:@".png"]];
                     [item setValue:[PREFIX_SDCARD add:compressPath] forKey:@"compressPath"];
                 }
      
                 [ary addObject:item];
                 if(count==assets.count){
                     data[@"res"]=ary;
                     callback(data);
                 }
              }];
         }
     }];
     imagePickerVc.modalPresentationStyle=UIModalPresentationFullScreen;
     NSMutableArray *mediaTypes = [NSMutableArray array];
     [weexInstance.viewController presentViewController:imagePickerVc animated:YES completion:nil];
    
}

-(void)openCamera:(int)aspX aspY:(int)aspY themeColor:(NSString*)themeColor  callback:(WXModuleCallback)callback
{
      self.callback =callback;
      [self initUploader:@"#ffffff" titleColor:@"#ffffff" cancelColor:@"#ffffff"];
      [self.uploadImage setAsp:aspX aspY:aspY];
      [self.uploadImage openCamera];
}

 -(void)initUploader:(NSString*)themeColor titleColor:(NSString*)titleColor cancelColor:(NSString*)cancelColor
 {
     if(self.uploadImage==nil)
     {
         self.uploadImage=[UploadImage new];
         self.uploadImage.delegate=self;
         self.uploadImage.frame=CGRectMake(0, 0, 0, 0);
         [self.weexInstance.viewController.view addSubview: self.uploadImage];
         self.uploadImage.themeColor=[themeColor toColor];
         self.uploadImage.titleColor=[titleColor toColor];
         self.uploadImage.cancelColor=[cancelColor toColor];
     }
 }

-(void)imageSelect:(UIImage*)img
{
    if(self.maxSize>0)
    img= [UIImage  imageWithData: [img compress:self.maxSize]];
    NSString *path= [WXPhotoModule saveImageDocuments:img];
    path=[@"sdcard:" add:path];
//    NSString *encodedString = [self image2DataURL:img];
//    encodedString=[@"base64===" add:encodedString];
    NSMutableDictionary *d=[NSMutableDictionary new];
    [d setValue:path forKey:@"path"];
    NSMutableArray *ary=[NSMutableArray new];
    [ary addObject:d];
    self.callback(@{@"res":ary});
}
-(int)getData{
    return 0;
}

 
    
 
@end
