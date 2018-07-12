//
//  WXApiManager.m
//  SDKSample
//
//  Created by Jeason on 16/07/2015.
//
//

#import "WXApiManager.h"
#import "farwolf.h"
#import "farwolf_weex.h"
@implementation WXApiManager

#pragma mark - LifeCycle
+(instancetype)sharedManager {
    static dispatch_once_t onceToken;
    static WXApiManager *instance;
    dispatch_once(&onceToken, ^{
        instance = [[WXApiManager alloc] init];
    });
    return instance;
}


-(void)initHandler
{
      [self regist:APP_handleOpenURL method:@selector(openUrl:)];
     [self regist:APP_openURL method:@selector(openUrl:)];
}
-(void)openUrl:(NSNotification*)notify
{
    NSDictionary *d= notify.userInfo;
    NSURL *url=d[@"url"];
    [WXApi handleOpenURL:url delegate:[WXApiManager sharedManager]];
}

#pragma mark - WXApiDelegate
- (void)onResp:(BaseResp *)resp {
    
    NSMutableDictionary *res=[NSMutableDictionary new];
    res[@"type"]=@"pay";
    res[@"errCode"]=@(resp.errCode);
    res[@"errMsg"]=resp.errStr;
    if([resp isKindOfClass:[SendAuthResp class]]){
        SendAuthResp *p=(SendAuthResp*)resp;
         res[@"authCode"]=p.code;
        _loginCallback(res,true);
        return;
    }
    _payCallback(res,true);
}

- (void)onReq:(BaseReq *)req {

}

@end
