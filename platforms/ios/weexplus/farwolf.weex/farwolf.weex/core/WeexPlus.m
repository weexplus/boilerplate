//
//  WeexPlus.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/9.
//

#import "WeexPlus.h"

@implementation WeexPlus
+(UIWindow*)init:(NSDictionary*)lanch{
    [URL copyBundleToDisk];
    [Weex initAppBoardContent];
//    [WXTracingManager setTracingEnable:NO];
    [Weex setBaseDir:[Config schema]];
    [Weex initWeex:@"farwolf" appName:@"weexplus" appVersion:@"1.0.0"];
    
     UIWindow   *window = [[UIWindow alloc] init];
    window.frame = [UIScreen mainScreen].bounds;
    UIViewController *vc= [Weex start:@"splash" url:[Weex getEntry]];
    window.rootViewController=vc;
    [window makeKeyAndVisible];
    if([Config isDebug])
        [[Weex getRefreshManager] open:[Weex getDebugIp] port:[Weex socketPort]];
    [WeexPluginManager initAllEntry:lanch];
    return window;
}
@end
