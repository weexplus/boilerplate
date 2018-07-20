//
//  AppDelegate.m
//  vshop
//
//  Created by 郑江荣 on 2017/5/18.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "AppDelegate.h"
#import "farwolf_weex.h"
#import "RefreshManager.h"
#import "Config.h"
#import "UpdateDialogControl.h"
#import "ZipDownloader.h"
#import "jpush.h"
#import "wechat.h"
#import "alipay.h"
#import "Appdefine.h"


@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  
    
    
    [URL copyBundleToDisk];
    [Weex initAppBoardContent];
    [WXTracingManager setTracingEnable:NO];
    [Weex setBaseDir:[Config schema]];
    [Weex initWeex:@"farwolf" appName:@"weexplus" appVersion:@"1.0.0"];
 
    self.window = [[UIWindow alloc] init];
    self.window.frame = [UIScreen mainScreen].bounds;
    UIViewController *vc= [Weex start:[Config splash] url:[Weex getEntry]];
    _window.rootViewController=vc;
    [_window makeKeyAndVisible];
    if([Config isDebug])
      [[Weex getRefreshManager] open:[Weex getDebugIp] port:[Weex socketPort]];
    [wechat initWechat];
    [alipay initPay];
    [[jpush  sharedManager]initPush];
//    NSString *appkey=@"";
//    [self.pushProtocol afterLanching:launchOptions appkey:appkey];
    
    NSMutableDictionary *p=[NSMutableDictionary new];
    if(launchOptions==nil)
        launchOptions=[NSMutableDictionary new];
    [launchOptions setValue:JPUSHAppkey forKey:@"jpushAppkey"];
    p[@"options"]=launchOptions;
    [self notifyDict:APP_didFinishLaunchingWithOptions value:p];
    
    
    
     return YES;
}

 
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:(void (^)(UIBackgroundFetchResult result))completionHandler
{
    NSMutableDictionary *p=[NSMutableDictionary new];
    p[@"userInfo"]=userInfo;
    [self notifyDict:APP_didReceiveRemoteNotification_fetchCompletionHandler value:p];
    
    completionHandler(UIBackgroundFetchResultNewData);
}

- (void)application:(UIApplication *)application
didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    
    /// Required - 注册 DeviceToken
//    [self.pushProtocol registToken:deviceToken];
    NSMutableDictionary *p=[NSMutableDictionary new];
    p[@"deviceToken"]=deviceToken;
    [self notifyDict:APP_didRegisterForRemoteNotificationsWithDeviceToken value:p];
}


- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
    
    NSMutableDictionary *p=[NSMutableDictionary new];
    p[@"userInfo"]=userInfo;
    [self notifyDict:APP_didReceiveRemoteNotification value:p];
}

- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
    
    NSMutableDictionary *p=[NSMutableDictionary new];
    p[@"url"]=url;
    [self notifyDict:APP_handleOpenURL value:p];
    return   true;
}

- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {
    NSMutableDictionary *p=[NSMutableDictionary new];
    p[@"url"]=url;
    [self notifyDict:APP_openURL value:p];
    return   true;
}

- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}


- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


@end
