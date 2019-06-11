//
//  WeexPlus.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/9.
//

#import "WeexPlus.h"
#import "MNFloatBtn.h"

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
    {
        [WeexPluginManager initAllEntry:lanch];
//        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
//            [WeexPlus addDebugBtn];
//             [window makeKeyAndVisible];
//        });
        [[Weex getRefreshManager] open:[Weex getDebugIp] port:[Weex socketPort]];
    }
   
    
    
    return window;
}

+(void)addDebugBtn{
    
    //    [MNFloatBtn showDebugModeWithType:MNAssistiveTypeNone];
    
    //设置不同环境下，要展示的不同title，以及当前的Host
    
    
    [MNFloatBtn showDebugModeWithType:MNAssistiveTypeNone];
    
    //点击事件 - 用'[MNFloatBtn sharedBtn].btnClick'触发
    [MNFloatBtn sharedBtn].btnClick = ^(UIButton *sender) {
        
        NSLog(@" btn.btnClick ~");
    };
}
@end
