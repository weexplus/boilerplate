//
//  WXPayModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/3.
//

#import "WechatModule.h"
#import "WXApi.h"

@implementation WechatModule

WX_EXPORT_METHOD(@selector(regist:))
WX_EXPORT_METHOD(@selector(pay:callback:))



-(void)regist:(NSString*)appId
{
    [WXApi registerApp:appId enableMTA:YES];
}
-(void)pay:(NSDictionary*)param  callback:(WXModuleCallback)callback
{

    PayReq *request =[PayReq yy_modelWithDictionary:param];
    [WXApi sendReq:request];
 
}


@end
