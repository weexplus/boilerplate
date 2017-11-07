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

@end
