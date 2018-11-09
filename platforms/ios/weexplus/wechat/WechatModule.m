//
//  WXPayModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/3.
//

#import "WechatModule.h"
#import "WXApi.h"
#import "WXApiManager.h"
#import <WeexPluginLoader/WeexPluginLoader.h>
WX_PlUGIN_EXPORT_MODULE(wechat, WechatModule)
@implementation WechatModule
@synthesize weexInstance;


WX_EXPORT_METHOD(@selector(regist:))
WX_EXPORT_METHOD(@selector(pay:callback:))
WX_EXPORT_METHOD(@selector(login:callback:))



-(void)regist:(NSString*)appId
{
    [WXApi registerApp:appId enableMTA:YES];
}
-(void)pay:(NSDictionary*)param  callback:(WXModuleKeepAliveCallback)callback
{

    PayReq *request =[PayReq yy_modelWithDictionary:param];
    request.package=param[@"packageValue"];

    WXApiManager.sharedManager.payCallback=callback;
    [WXApi sendReq:request];
 
}

-(void)login:(NSDictionary*)param  callback:(WXModuleKeepAliveCallback)callback
{
    WXApiManager.sharedManager.loginCallback=callback;
    SendAuthReq *req=[SendAuthReq yy_modelWithDictionary:param];
    [WXApi sendAuthReq:req viewController:weexInstance.viewController   delegate:[WXApiManager sharedManager]];
}


@end
