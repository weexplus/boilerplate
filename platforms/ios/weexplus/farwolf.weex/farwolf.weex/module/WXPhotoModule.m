//
//  WXPhotoModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/19.
//
//

#import "WXPhotoModule.h"

@implementation WXPhotoModule

@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(open:aspY:themeColor:titleColor:cancelColor:callback:))
WX_EXPORT_METHOD(@selector(openCamera:aspY:themeColor:callback:))
WX_EXPORT_METHOD(@selector(openPhoto:aspY:themeColor:titleColor:cancelColor:callback:))
WX_EXPORT_METHOD(@selector(save:callback:))

-(void)open:(int)aspX aspY:(int)aspY themeColor:(NSString*)themeColor titleColor:(NSString*)titleColor cancelColor:(NSString*)cancelColor callback: (WXModuleKeepAliveCallback)callback
{
   
    [self initUploader:themeColor titleColor:titleColor cancelColor:cancelColor];
    [self.uploadImage setAsp:aspX aspY:aspY];
    self.callback =callback;
    [self.uploadImage showPickImage:[themeColor toColor] titleColor:[titleColor toColor] cancelColor:[cancelColor toColor]];
    
    
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



-(void)openCamera:(int)aspX aspY:(int)aspY themeColor:(NSString*)themeColor  callback:(WXModuleKeepAliveCallback)callback
{
    self.callback =callback;

    [self initUploader:@"#ffffff" titleColor:@"#ffffff" cancelColor:@"#ffffff"];
      [self.uploadImage setAsp:aspX aspY:aspY];
    [self.uploadImage openCamera];
}

-(void)openPhoto:(int)aspX aspY:(int)aspY themeColor:(NSString*)themeColor titleColor:(NSString*)titleColor cancelColor:(NSString*)cancelColor callback:(WXModuleKeepAliveCallback)callback
{
    self.callback =callback;
    [self initUploader:themeColor titleColor:titleColor cancelColor:cancelColor];
      [self.uploadImage setAsp:aspX aspY:aspY];
    [self.uploadImage openPhoto];

}
-(void)imageSelect:(UIImage*)img
{
    NSString *path= [self saveImageDocuments:img];
    path=[@"sdcard:" add:path];
//    NSString *encodedString = [self image2DataURL:img];
//    encodedString=[@"base64===" add:encodedString];
    NSMutableDictionary *d=[NSMutableDictionary new];
    [d setValue:path forKey:@"path"];
    self.callback(d,true);
}

//保存图片
-(NSString*)saveImageDocuments:(UIImage *)image{
    
    NSFileManager *fileManager = [NSFileManager defaultManager];
 
    NSArray * paths2 = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES);
     NSString * cachePath = [paths2 lastObject];
    NSString* temp=[cachePath add:@"/img"];
    
    
    
    if(![fileManager  fileExistsAtPath:[temp add:@"/1.x"] isDirectory:false])
    {
        [fileManager createDirectoryAtPath:temp withIntermediateDirectories:true attributes:nil error:nil];
        [fileManager createFileAtPath:[temp add:@"/1.x"] contents:nil attributes:nil];
//        [fileManager createDirectoryAtPath:[temp add:@"1.x"] withIntermediateDirectories:false attributes:nil error:nil];
    }
    else
    {
         NSArray *fileList = [fileManager contentsOfDirectoryAtPath:temp error:nil];
        for(NSString *p in fileList)
        {
            if(![@"1.x" isEqualToString:p])
                [fileManager removeItemAtPath:[[temp add:@"/"] add:p] error:nil];
        }
    
    }

    //拿到图片
    UIImage *imagesave = image;
   
    //设置一个图片的存储路径
       int value = arc4random() % 10000;
    temp=[temp add:@"/"];
    NSString* name=[[temp addInt:value] add:@".png"];
  
    //把图片直接保存到指定的路径（同时应该把图片的路径imagePath存起来，下次就可以直接用来取）
    [UIImagePNGRepresentation(imagesave) writeToFile:name atomically:YES];
    return name;
}


-(void)save:(NSString*)path callback:(WXModuleKeepAliveCallback)callback
{
    if([path startWith:PREFIX_SDCARD])
    {
        path=[path replace:PREFIX_SDCARD withString:@""];
    }
    UIImage *image=[[UIImage alloc]initWithContentsOfFile:path];
    //     图片保存相册
    NSDictionary *info = [NSBundle mainBundle].infoDictionary;
    if(!info[@"NSPhotoLibraryUsageDescription"]) {
        if (callback) {
            callback(@{
                             @"success" : @(false),
                             @"errorDesc": @"This maybe crash above iOS 10 because it attempted to access privacy-sensitive data without a usage description.  The app's Info.plist must contain an NSPhotoLibraryUsageDescription key with a string value explaining to the user how the app uses this data."
                             }, NO);
        }
        return ;
    }
    
    // iOS 11 needs a NSPhotoLibraryUsageDescription key for permission
    if (WX_SYS_VERSION_GREATER_THAN_OR_EQUAL_TO(@"11.0")) {
        if (!info[@"NSPhotoLibraryUsageDescription"]) {
            if (callback) {
                callback(@{
                                 @"success" : @(false),
                                 @"errorDesc": @"This maybe crash above iOS 10 because it attempted to access privacy-sensitive data without a usage description.  The app's Info.plist must contain an NSPhotoLibraryUsageDescription key with a string value explaining to the user how the app uses this data."
                                 }, NO);
            }
            return;
        }
    }
    
  
    if (!callback) {
        // there is no need to callback any result;
        UIImageWriteToSavedPhotosAlbum(image, nil, nil, NULL);
    }else {
        UIImageWriteToSavedPhotosAlbum(image, self, @selector(image:didFinishSavingWithError:contextInfo:), (void*)CFBridgingRetain(callback));
    }

}

- (void)image:(UIImage *)image didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo
{
    if (!contextInfo) {
        return;
    }
    NSMutableDictionary * callbackResult = [NSMutableDictionary new];
    BOOL success = false;
    if (!error) {
        success = true;
    } else {
        [callbackResult setObject:[error description] forKey:@"errorDesc"];
    }
    if (contextInfo) {
        [callbackResult setObject:@(success) forKey:@"success"];
        ((__bridge WXKeepAliveCallback)contextInfo)(callbackResult, NO);
        CFRelease(contextInfo);
    }
}


- (BOOL) imageHasAlpha: (UIImage *) image
{
    CGImageAlphaInfo alpha = CGImageGetAlphaInfo(image.CGImage);
    return (alpha == kCGImageAlphaFirst ||
            alpha == kCGImageAlphaLast ||
            alpha == kCGImageAlphaPremultipliedFirst ||
            alpha == kCGImageAlphaPremultipliedLast);
}
- (NSString *) image2DataURL: (UIImage *) image
{
    NSData *imageData = nil;
    NSString *mimeType = nil;
    
    if ([self imageHasAlpha: image]) {
        imageData = UIImagePNGRepresentation(image);
        mimeType = @"image/png";
    } else {
        imageData = UIImageJPEGRepresentation(image, 1.0f);
        mimeType = @"image/jpeg";
    }
    
    return [NSString stringWithFormat:@"data:%@;base64,%@", mimeType,
            [imageData base64EncodedStringWithOptions: 0]];
}

-(void)imageUploadSuccess:(NSString*)url
{
    
    NSMutableDictionary *d=[NSMutableDictionary new];
    [d setValue:url forKey:@"url"];
    self.callback(d,true);
}



@end
