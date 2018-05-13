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

NSString* versionCode()
{
     NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
      NSString *vcode = [infoDictionary objectForKey:@"CFBundleVersion"];
    return vcode;
}


NSString* versionName()
{
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *vname = [infoDictionary objectForKey:@"CFBundleShortVersionString"];
    return vname;
}




 NSString* jsVersionCode()
{
     NSString *jsversion=[Config jsVersion];
    return jsversion;
}
@end
