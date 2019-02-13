//
//  App.m
//  farwolf.weex
//
//  Created by 郑江荣 on 2018/11/9.
//

#import "App.h"
#import "URL.h"
#import "Weex.h"
//#import "WXTracingManager.h"
@implementation App
+(void)initWeexPlus:(UIWindow*)window{
    [URL copyBundleToDisk];
    [Weex initAppBoardContent];
//    [WXTracingManager setTracingEnable:NO];
    [Weex setBaseDir:[Config schema]];
    [Weex initWeex:@"farwolf" appName:@"weexplus" appVersion:@"1.0.0"];
    
//    window = [[UIWindow alloc] init];
    window.frame = [UIScreen mainScreen].bounds;
    UIViewController *vc= [Weex start:[Config splash] url:[Weex getEntry]];
    window.rootViewController=vc;
    [window makeKeyAndVisible];
    if([Config isDebug])
        [[Weex getRefreshManager] open:[Weex getDebugIp] port:[Weex socketPort]];
}
@end
