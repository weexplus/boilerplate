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
WX_EXPORT_METHOD(@selector(makeBarCode:callback:))

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



-(void) makeBarCode:(NSMutableDictionary*)param  callback:(WXModuleCallback)callback
{
    NSString *source=param[@"str"];
    CGFloat size=[@"" add: param[@"size"]].floatValue;
    CGFloat height=-1;
    if(param[@"height"])
        height=[@"" add: param[@"height"]].floatValue;
    CGFloat space=0;
    if(param[@"space"])
        space=[@"" add: param[@"space"]].floatValue;
    
    space=[Weex length:space instance:weexInstance];
    height=[Weex length:height instance:weexInstance];
    size=[Weex length:size instance:weexInstance];
    　　// iOS 8.0以上的系统才支持条形码的生成，iOS8.0以下使用第三方控件生成
    　　if ([[UIDevice currentDevice].systemVersion floatValue] >= 8.0) {
        　　　　// 注意生成条形码的编码方式
        　　　　NSData *data = [source dataUsingEncoding: NSASCIIStringEncoding];
        　　　　CIFilter *filter = [CIFilter filterWithName:@"CICode128BarcodeGenerator"];
        　　　　[filter setValue:data forKey:@"inputMessage"];
        if(height>0)
            [filter setValue:@(height) forKey:@"inputBarcodeHeight"];
        　　　　// 设置生成的条形码的上，下，左，右的margins的值
        　　　　[filter setValue:[NSNumber numberWithInteger:space] forKey:@"inputQuietSpace"];
        CIImage *ciimage=filter.outputImage;
        　　　 UIImage *img= [self createNonInterpolatedUIImageFormCIImage:ciimage withSize:size] ;
        NSString *path=[WXPhotoModule saveImageDocuments:img];
        path=[@"sdcard:" add:path];
        callback(@{@"path":path});
        　　}
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
