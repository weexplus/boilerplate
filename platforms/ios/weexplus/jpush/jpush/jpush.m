//
//  jpush.m
//  jpush
//
//  Created by 郑江荣 on 2018/5/24.
//  Copyright © 2018年 郑江荣. All rights reserved.
//

#import "jpush.h"
#import <AdSupport/AdSupport.h>
#import <WeexSDK/WXSDKInstance.h>
#import <WeexSDK/WXSDKEngine.h>
#import "WXJPushModule.h"
@implementation jpush




+(void)initPush
{
    //Required
    //notice: 3.0.0及以后版本注册可以这样写，也可以继续用之前的注册方式
    JPUSHRegisterEntity * entity = [[JPUSHRegisterEntity alloc] init];
    entity.types = JPAuthorizationOptionAlert|JPAuthorizationOptionBadge|JPAuthorizationOptionSound;
    [JPUSHService registerForRemoteNotificationConfig:entity delegate:self];
     [WXSDKEngine registerModule:@"jpush" withClass:[WXJPushModule class]];
}



-(void)afterLanching:(NSDictionary *)launchOptions appkey:(NSString*)appkey
{
    
    // Required
    // init Push
    // notice: 2.1.5版本的SDK新增的注册方法，改成可上报IDFA，如果没有使用IDFA直接传nil
    // 如需继续使用pushConfig.plist文件声明appKey等配置内容，请依旧使用[JPUSHService setupWithOption:launchOptions]方式初始化。
    [JPUSHService setupWithOption:launchOptions appKey:appkey
                          channel:@"appstore"
                 apsForProduction:true
            advertisingIdentifier:nil];
}


-(void)registToken:(NSData*)token
{
    [JPUSHService registerDeviceToken:token];
}


-(void)handNotification:(NSDictionary *)userInfo
{
      [JPUSHService handleRemoteNotification:userInfo];
}




- (void)jpushNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(NSInteger options))completionHandler
{
    
}
/*
 * @brief handle UserNotifications.framework [didReceiveNotificationResponse:withCompletionHandler:]
 * @param center [UNUserNotificationCenter currentNotificationCenter] 新特性用户通知中心
 * @param response 通知响应对象
 * @param completionHandler
 */
- (void)jpushNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void(^)())completionHandler
{
    
}
@end
