//
//  WXNEIMModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/9.
//

#import "WXNEIMModule.h"

#import <WeexPluginLoader/WeexPluginLoader.h>
@implementation WXNEIMModule
WX_PlUGIN_EXPORT_MODULE(im, WXNEIMModule)
WX_EXPORT_METHOD(@selector(regist:))
WX_EXPORT_METHOD(@selector(login:callback:))
WX_EXPORT_METHOD(@selector(autoLogin:callback:))
WX_EXPORT_METHOD(@selector(logout:))
WX_EXPORT_METHOD(@selector(sendText:))

-(void)regist:(NSDictionary*)param{
    NSString *appKey        = param[@"appkey"];
    NIMSDKOption *option    = [NIMSDKOption optionWithAppKey:appKey];
    option.apnsCername      = param[@"apnsCername"];
    option.pkCername        = param[@"pkCername"];
    [[NIMSDK sharedSDK] registerWithOption:option];
}

-(void)login:(NSDictionary*)param callback:(WXModuleKeepAliveCallback)callback{
    NSString *account = param[@"account"];
    NSString *token   = param[@"token"];
    [[[NIMSDK sharedSDK] loginManager] login:account
                                       token:token
                                  completion:^(NSError *error) {
                                      
                                      NSDictionary *err=@{@"err":@(0)};
                                      if(error!=0){
                                          err=@{@"err":@(-1),@"info":error.userInfo};
                                      }
                                      callback(err,true);
                                  }];
}

-(void)autoLogin:(NSDictionary*)param callback:(WXModuleKeepAliveCallback)callback{
    NSString *account = param[@"account"];
    NSString *token   = param[@"token"];;
    NIMAutoLoginData *loginData = [[NIMAutoLoginData alloc] init];
    loginData.account = account;
    loginData.token = token;
    [[[NIMSDK sharedSDK] loginManager] autoLogin:loginData];
}

- (void)onLogin:(NIMLoginStep)step{
    
}


-(void)logout:(WXModuleCallback)callback{
    [[[NIMSDK sharedSDK] loginManager] logout:^(NSError *error) {
        //jump to login page
        callback(@{});
        
    }];
}

-(void)sendText:(NSDictionary*)param callback:(WXModuleCallback)callback{
    NSString *sessionId=param[@"sessionId"];
     NSString *msg=param[@"msg"];
    // 构造出具体会话
    NIMSession *session = [NIMSession session:sessionId type:NIMSessionTypeP2P];
    // 构造出具体消息
    NIMMessage *message = [[NIMMessage alloc] init];
    message.text        = msg;
    // 错误反馈对象
    NSError *error = nil;
    // 发送消息
    [[NIMSDK sharedSDK].chatManager sendMessage:message toSession:session error:&error];
    if(error==0)
     {
            callback(@{@"err":@(0)});
    }else{
            callback(@{@"err":@(1),@"info": error.userInfo});
      }
    
 }


@end
