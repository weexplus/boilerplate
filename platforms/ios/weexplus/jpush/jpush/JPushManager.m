//
//  JPushManager.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/6.
//

#import "JPushManager.h"
#import "farwolf.h"
#import "farwolf_weex.h"
#import <AdSupport/AdSupport.h>
#import "WeexFactory.h"
#import "WXNormalViewContrller.h"
@implementation JPushManager
#pragma mark - LifeCycle
+(instancetype)sharedManager {
    static dispatch_once_t onceToken;
    static JPushManager *instance;
    dispatch_once(&onceToken, ^{
        instance = [[JPushManager alloc] init];
    });
    return instance;
}


-(void)initHanler
{
      [self regist:APP_didFinishLaunchingWithOptions method:@selector(didFinishLaunchingWithOptions:)];
      [self regist:APP_didRegisterForRemoteNotificationsWithDeviceToken method:@selector(didRegisterForRemoteNotificationsWithDeviceToken:)];
    
       [self regist:APP_didReceiveRemoteNotification method:@selector(didReceiveRemoteNotification:)];
    
    [self regist:APP_didReceiveRemoteNotification_fetchCompletionHandler method:@selector(didReceiveRemoteNotification:)];
    
}

- (void)jpushNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(NSInteger options))completionHandler
{
    NSDictionary * userInfo = notification.request.content.userInfo;
    if([notification.request.trigger isKindOfClass:[UNPushNotificationTrigger class]]) {
        [JPUSHService handleRemoteNotification:userInfo];
    }
    completionHandler(UNNotificationPresentationOptionAlert);
    [self handleRecieve:userInfo[@"aps"] type:@"recieve"];
}
/*
 * @brief handle UserNotifications.framework [didReceiveNotificationResponse:withCompletionHandler:]
 * @param center [UNUserNotificationCenter currentNotificationCenter] 新特性用户通知中心
 * @param response 通知响应对象
 * @param completionHandler
 */
- (void)jpushNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void(^)())completionHandler
{
    NSDictionary * userInfo = response.notification.request.content.userInfo;
    if([response.notification.request.trigger isKindOfClass:[UNPushNotificationTrigger class]]) {
        [JPUSHService handleRemoteNotification:userInfo];
    }
    completionHandler();
    [self handleRecieve:userInfo[@"aps"] type:@"open"];
}


-(void)handleRecieve:(NSMutableDictionary*)data type:(NSString*)type
{
    
    NSMutableDictionary *d=[NSMutableDictionary new];
    d[@"msg"]=data;
    d[@"action"]=type;
    NSString *url=[Config notifyEntry];
    
    [WeexFactory renderNew:[Weex toURL:url] compelete:^(WXNormalViewContrller *vc) {
        
        [vc.view setHidden:true];
        vc.param=d;
        vc.view.frame=CGRectMake(0, 0, 0, 0);
        UIViewController *parent= [[UIApplication sharedApplication].keyWindow.rootViewController topViewController];
        [parent addVc:vc];
        [vc.instance firePageInit];
        //        [vc.instance fireGlobalEvent:@"onPageInit" params:vc.param];
        
    } fail:^(NSString *msg) {
        
       
    }  frame:[UIApplication sharedApplication].keyWindow.frame isPortrait:true];
}

-(void)didReceiveRemoteNotification_fetchCompletionHandler:(NSNotification*)notify
{
    NSDictionary *d= notify.userInfo;
    NSDictionary *userInfo=d[@"userInfo"];
    [JPUSHService handleRemoteNotification:userInfo];
}
-(void)didFinishLaunchingWithOptions:(NSNotification*)notify
{
    NSDictionary *d= notify.userInfo;
    NSDictionary *options=d[@"options"];
 
    NSString *appkey=options[@"jpushAppkey"];
    [JPUSHService setupWithOption:options appKey:appkey
                          channel:@"appstore"
                 apsForProduction:true
            advertisingIdentifier:nil];
    
    JPUSHRegisterEntity * entity = [[JPUSHRegisterEntity alloc] init];
    entity.types = JPAuthorizationOptionAlert|JPAuthorizationOptionBadge|JPAuthorizationOptionSound;
    [JPUSHService registerForRemoteNotificationConfig:entity delegate:self];
    [JPUSHService registrationIDCompletionHandler:^(int resCode, NSString *registrationID) {
        NSLog([@"registrationID="add: registrationID]);
    }];

}
-(void)didRegisterForRemoteNotificationsWithDeviceToken:(NSNotification*)notify
{
    NSDictionary *d= notify.userInfo;
    NSString *token=d[@"deviceToken"];
     [JPUSHService registerDeviceToken:token];
}

-(void)didReceiveRemoteNotification:(NSNotification*)notify
{
        NSDictionary *d= notify.userInfo;
        NSDictionary *userInfo=d[@"userInfo"];
       [JPUSHService handleRemoteNotification:userInfo];
}

  



@end


