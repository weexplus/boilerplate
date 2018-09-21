//
//  AlipayManager.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/9/20.
//

#import "AlipayManager.h"
#import "farwolf_weex.h"
#import <AlipaySDK/AlipaySDK.h>
#import "WXAlipayModule.h"
@implementation AlipayManager
+(instancetype)sharedManager {
    static dispatch_once_t onceToken;
    static AlipayManager *instance;
    dispatch_once(&onceToken, ^{
        instance = [[AlipayManager alloc] init];
    });
    return instance;
}

-(void)initHanler
{
    [self regist:APP_openURL method:@selector(handleOpenURL:)];
}
-(void)handleOpenURL:(NSNotification*)notify
{
    NSDictionary *d= notify.userInfo;
    NSURL *url=d[@"url"];
    
    if (![url.absoluteString contains:@"auth_code"]) {
        //跳转支付宝钱包进行支付，处理支付结果
        [[AlipaySDK defaultService] processOrderWithPaymentResult:url standbyCallback:^(NSDictionary *resultDic) {
            NSLog(@"result = %@",resultDic);
           WXCallback callback=  [WXAlipayModule getPayCallback];
            callback(resultDic);
            [WXAlipayModule setPayCallBack:nil];
        }];
    } else {
//        processAuth_V2Result
        [[AlipaySDK defaultService] processAuth_V2Result:url standbyCallback:^(NSDictionary *resultDic) {
            NSLog(@"result = %@",resultDic);
            WXCallback callback=  [WXAlipayModule getAuthCallback];
            callback(resultDic);
            [WXAlipayModule setAuthCallBack:nil];
        }];
    }
    
}


@end
