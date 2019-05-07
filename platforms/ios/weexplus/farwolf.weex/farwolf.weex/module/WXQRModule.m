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


@end
