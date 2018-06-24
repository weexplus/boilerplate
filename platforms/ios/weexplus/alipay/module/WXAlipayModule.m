//
//  WXAlipayModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/31.
//

#import "WXAlipayModule.h"
#import <AlipaySDK/AlipaySDK.h>
@implementation WXAlipayModule
WX_EXPORT_METHOD(@selector(open:callback:))

-(void)open:(NSDictionary*)param  callback:(WXModuleCallback)callback
{
    
    NSString* signstr=param[@"signstr"];
    NSString* iosscheme=param[@"iosscheme"];
    if (signstr != nil) {
        
        [[AlipaySDK defaultService] payOrder:signstr fromScheme:iosscheme callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);
            callback(resultDic);
        }];
    }
}
@end
