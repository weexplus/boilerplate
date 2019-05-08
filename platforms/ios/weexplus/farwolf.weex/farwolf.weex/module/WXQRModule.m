//
//  WXQRModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/10/9.
//
//

#import "WXQRModule.h"
#import "farwolf.h"
#import "WXPage.h"

#import "QRControl.h"
#import "Weex.h"
@implementation WXQRModule
WX_EXPORT_METHOD(@selector(open:callback:))
WX_EXPORT_METHOD(@selector(makeQr:callback:))
@synthesize weexInstance;



-(void)open:(NSMutableDictionary*)param callback:(WXModuleKeepAliveCallback)callback
{
    NSString *color=param[@"color"];
    NSString *bgcolor=param[@"bgcolor"];
     NSString *statusbarcolor=param[@"statusbarcolor"];
    UINavigationController  *nav=[weexInstance.viewController present:_QRControl anim:true];
    QRControl *vc=nav.childViewControllers[0];
    vc.color=color;
    vc.bgcolor=bgcolor;
    vc.statusbarcolor=statusbarcolor;
    [nav.navigationBar setHidden:false];
    vc.scanSuccess=^(NSString* s){
        
       
//        NSMutableDictionary *dic=[NSMutableDictionary new];
//        [dic setValue:s forKey:@"res"];
        callback(s,true);
        
    };

    
}

-(void)makeQr:(NSMutableDictionary*)param  callback:(WXModuleCallback)callback{
    NSString *url=param[@"str"];
    CGFloat size=[@"" add: param[@"size"]].floatValue;
    size=[Weex length:size instance:weexInstance];
    //       NSString *url=param[@"url"];
    //创建过滤器
    CIFilter *filter = [CIFilter filterWithName:@"CIQRCodeGenerator"];
    
    //过滤器恢复默认
    [filter setDefaults];
    
    //给过滤器添加数据
    NSString *string = url;
    
    //将NSString格式转化成NSData格式
    NSData *data = [string dataUsingEncoding:NSUTF8StringEncoding allowLossyConversion:YES];
    
    [filter setValue:data forKeyPath:@"inputMessage"];
    
    //获取二维码过滤器生成的二维码
    CIImage *image = [filter outputImage];
    UIImage *img= [self createNonInterpolatedUIImageFormCIImage:image withSize:size];
    NSString *path=[WXPhotoModule saveImageDocuments:img];
    path=[@"sdcard:" add:path];
    callback(@{@"path":path});
    //将获取到的二维码添加到imageview上
    //    self.imageView.image =[UIImage imageWithCIImage:image];
}

- (UIImage *)createNonInterpolatedUIImageFormCIImage:(CIImage *)image withSize:(CGFloat) size
{
    CGRect extent = CGRectIntegral(image.extent);
    CGFloat scale = MIN(size/CGRectGetWidth(extent), size/CGRectGetHeight(extent));
    
    // 1.创建bitmap;
    size_t width = CGRectGetWidth(extent) * scale;
    size_t height = CGRectGetHeight(extent) * scale;
    CGColorSpaceRef cs = CGColorSpaceCreateDeviceGray();
    CGContextRef bitmapRef = CGBitmapContextCreate(nil, width, height, 8, 0, cs, (CGBitmapInfo)kCGImageAlphaNone);
    CIContext *context = [CIContext contextWithOptions:nil];
    CGImageRef bitmapImage = [context createCGImage:image fromRect:extent];
    CGContextSetInterpolationQuality(bitmapRef, kCGInterpolationNone);
    CGContextScaleCTM(bitmapRef, scale, scale);
    CGContextDrawImage(bitmapRef, extent, bitmapImage);
    
    // 2.保存bitmap到图片
    CGImageRef scaledImage = CGBitmapContextCreateImage(bitmapRef);
    CGContextRelease(bitmapRef);
    CGImageRelease(bitmapImage);
    return [UIImage imageWithCGImage:scaledImage];
}

@end
