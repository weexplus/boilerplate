//
//  WXEnvModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/13.
//

#import "WXEnvModule.h"
#import "Config.h"

@implementation WXEnvModule
WX_EXPORT_METHOD_SYNC(@selector(versionCode))
WX_EXPORT_METHOD_SYNC(@selector(versionName))
WX_EXPORT_METHOD_SYNC(@selector(jsVersionCode))
WX_EXPORT_METHOD_SYNC(@selector(isFringeScreen))

-(NSString* ) versionCode
{
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *vcode = [infoDictionary objectForKey:@"CFBundleVersion"];
    return vcode;
}


-(NSString* ) versionName
{
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *vname = [infoDictionary objectForKey:@"CFBundleShortVersionString"];
    return vname;
}

-(bool) isFringeScreen
{
    if (!@available(iOS 11.0, *)) {
        return false;
    }
    UIWindow *window= [UIApplication sharedApplication].keyWindow;
    UIEdgeInsets eg=window.safeAreaInsets;
    return  eg.top>0&&eg.top!=20;
    //   return  window.safeAreaInsets!= UIEdgeInsetsZero;
    //    return  [UIApplication sharedApplication].keyWindow.safeAreaInsets != UIEdgeInsetsZero;
    //    return UIApplication.shared.windows[0].safeAreaInsets != UIEdgeInsets.zero
}




-(NSString* )jsVersionCode
{
    NSString *jsversion=[Config jsVersion];
    return jsversion;
}


@end
