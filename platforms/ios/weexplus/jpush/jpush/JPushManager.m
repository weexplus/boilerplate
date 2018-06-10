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
     [self regist:APP_didRegisterForRemoteNotificationsWithDeviceToken method:@selector(didFinishLaunchingWithOptions:)];
    
       [self regist:APP_didReceiveRemoteNotification method:@selector(didFinishLaunchingWithOptions:)];
}

-(void)didFinishLaunchingWithOptions:(NSNotification*)notify
{
    NSDictionary *d= notify.userInfo;
    NSDictionary *options=d[@"options"];
 
    NSString *appkey=@"";
    [JPUSHService setupWithOption:options appKey:appkey
                          channel:@"appstore"
                 apsForProduction:true
            advertisingIdentifier:nil];
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


