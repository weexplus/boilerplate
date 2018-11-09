//
//  WXAlipayModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/31.
//

#import "WXAlipayModule.h"
#import <AlipaySDK/AlipaySDK.h>
#import <WeexPluginLoader/WeexPluginLoader.h>
WX_PlUGIN_EXPORT_MODULE(alipay, WXAlipayModule)
static WXModuleCallback payCallback;
static WXModuleCallback authCallback;
@implementation WXAlipayModule
WX_EXPORT_METHOD(@selector(pay:callback:))
WX_EXPORT_METHOD(@selector(login:callback:))
-(void)pay:(NSDictionary*)param  callback:(WXModuleCallback)callback
{
    [WXAlipayModule setPayCallBack:callback];
    NSString* signstr=param[@"signstr"];
    NSString* iosscheme=param[@"iosscheme"];
    if (signstr != nil) {
        
        [[AlipaySDK defaultService] payOrder:signstr fromScheme:iosscheme callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);
            callback(resultDic);
            [WXAlipayModule setPayCallBack:nil];
        }];
    }
}

+(void)setPayCallBack:(WXModuleCallback)callback{
    payCallback=callback;
}
+(void)setAuthCallBack:(WXModuleCallback)callback{
    authCallback=callback;
}
+(WXModuleCallback)getPayCallback{
    return payCallback;
}
+(WXModuleCallback)getAuthCallback{
    return authCallback;
}

-(void)login:(NSDictionary*)param  callback:(WXModuleCallback)callback
{
     [WXAlipayModule setAuthCallBack:callback];
    NSString* signstr=param[@"signstr"];
    NSString* iosscheme=param[@"iosscheme"];
    if (signstr != nil) {
        
        [[AlipaySDK defaultService] auth_V2WithInfo:signstr fromScheme:iosscheme callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);
            callback(resultDic);
            [WXAlipayModule setAuthCallBack:nil];
        }];
    }
}
@end

