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

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
//    [WXTracingManager setTracingEnable:NO];
//    [WXTracingManager setTracingEnable:YES];
     [URL copyBundleToDisk];
    [WXTracingManager setTracingEnable:NO];
    [Weex setBaseDir:[Config schema]];
    [Weex initWeex:@"farwolf" appName:@"vshop" appVersion:@"1.0.0"];
 
//    NSString *from=@"/Users/zhengjiangrong/Library/Developer/CoreSimulator/Devices/5319AD64-DD67-4D89-81B3-E39231481EB9/data/Containers/Bundle/Application/BF2A6AC0-8219-4C8F-9869-5B1C35203C61/weexplus.app/app/config.json";
//   NSString *to=@"/Users/zhengjiangrong/Library/Developer/CoreSimulator/Devices/5319AD64-DD67-4D89-81B3-E39231481EB9/data/Containers/Data/Application/385D0FC0-A665-4B7A-9B02-34CD37CDEE72/Documents/app/config.json";
//     NSError *err = nil;
//    [[NSFileManager defaultManager] copyItemAtPath:from toPath:to error:&err];
    
//     NSLog(@"xxx=%@",err);

   
    
    
   
    self.window = [[UIWindow alloc] init];
    self.window.frame = [UIScreen mainScreen].bounds;
   
     
    UIViewController *vc= [Weex start:[Config splash] url:[Weex getEntry]];
    
//    UpdateDialogControl *uvc=[[UpdateDialogControl alloc]initWithNibName:@"updater" bundle:nil];
    _window.rootViewController=vc;
    [_window makeKeyAndVisible];

//    [Weex startDebug:@"127.0.0.1" port:@"8088"];
    if([Config isDebug])
      [[Weex getRefreshManager] open:[Weex getDebugIp] port:[Weex socketPort]];
    
    
     return YES;
}



//-(UIInterfaceOrientationMask)application:(UIApplication *)application supportedInterfaceOrientationsForWindow:(UIWindow *)window{
//
//    if(![Config isPortrait])
//    {
//        return UIInterfaceOrientationMaskLandscape;
//    }
//    return UIInterfaceOrientationMaskPortrait;
//}

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
