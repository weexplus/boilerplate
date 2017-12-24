//
//  WXTracingLogImpl.m
//  Pods
//
//  Created by 齐山 on 2017/7/10.
//
//

#import "WXTracingLogImpl.h"
#import "WXTracingViewControllerManager.h"
#import "WXTracingUtility.h"
#import <WeexSDK/WXUtility.h>

@implementation WXTracingLogImpl
- (WXLogLevel)logLevel
{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    if([defaults objectForKey:@"wxloglevel"]) {
        return [[defaults objectForKey:@"wxloglevel"] integerValue];
    }
    return WXLogLevelLog;
}
- (void)log:(WXLogFlag)flag message:(NSString *)message
{
    dispatch_async(dispatch_get_main_queue(), ^{
        NSMutableArray *messages = [WXTracingViewControllerManager sharedInstance].messages;
        NSString *strMsg = [NSString stringWithFormat:@"%zd: %@ %@ %@",[[WXTracingViewControllerManager sharedInstance].messages count],[WXTracingUtility tracingTime] ,[[WXUtility getEnvironment] objectForKey:@"appName"],message];
        [messages addObject:strMsg];
    });
}

@end
