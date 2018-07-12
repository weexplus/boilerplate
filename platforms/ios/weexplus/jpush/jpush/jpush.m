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
#import "JPushManager.h"
@implementation jpush



+(instancetype)sharedManager {
    static dispatch_once_t onceToken;
    static jpush *instance;
    dispatch_once(&onceToken, ^{
        instance = [[jpush alloc] init];
    });
    return instance;
}

-(void)initPush
{
    //Required
    //notice: 3.0.0及以后版本注册可以这样写，也可以继续用之前的注册方式
//    JPUSHRegisterEntity * entity = [[JPUSHRegisterEntity alloc] init];
//    entity.types = JPAuthorizationOptionAlert|JPAuthorizationOptionBadge|JPAuthorizationOptionSound;
//    [JPUSHService registerForRemoteNotificationConfig:entity delegate:self];
     [WXSDKEngine registerModule:@"jpush" withClass:[WXJPushModule class]];
    [[JPushManager sharedManager] initHanler];
}




@end
