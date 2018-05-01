//
//  Config.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/1.
//

#import "Config.h"


@implementation Config


+(NSString*)schema
{
    return  [[Weex conifg] objectForKey:@"schema"];
}

+(NSString*)entry
{
    return  [[Weex conifg] objectForKey:@"entry"];
}

+(NSString*)debugIp
{
    return  [[Weex conifg] objectForKey:@"debugIp"];
}

+(NSMutableArray*)preload
{
   return  [[Weex conifg] objectForKey:@"preload"];
}
+(NSString*)splash
{
    return  [[Weex conifg] objectForKey:@"splash"];
}

+(NSString*)jsVersion
{
    if([URL isDiskExist])
    {
        return [self diskJsVersion];
    }
    else
    {
         return [self bundleJsVersion];
    }
    
}

+(NSString*)diskJsVersion
{
    return  [[Weex diskConifg] objectForKey:@"jsVersion"];
}

+(NSString*)bundleJsVersion
{
    return  [[Weex bundleConifg] objectForKey:@"jsVersion"];
}


+(BOOL)isDebug
{
    NSMutableDictionary *d=  [Weex conifg];
  
   return [d[@"debug"] boolValue];
}
+(BOOL)showError
{
    NSMutableDictionary *d=  [Weex conifg];
    
    return [d[@"showerror"] boolValue];
}
+(BOOL)isPortrait
{
    NSMutableDictionary *d=  [Weex conifg];
    
    return [d[@"isPortrait"] boolValue];
}

+(NSString*)getUMAndroidKey
{
    
    return [[[[Weex conifg] objectForKey:@"umeng"] objectForKey:@"android"] objectForKey:@"appkey"];
}

+(NSString*)getUMIOSKey
{
     return [[[[Weex conifg] objectForKey:@"umeng"] objectForKey:@"ios"] objectForKey:@"appkey"];
}


+(Platform*)getPlatformWX
{
   return  [Platform yy_modelWithJSON: [[[Weex conifg] objectForKey:@"platform"] objectForKey:@"wx"]];
}
+(Platform*)getPlatformQQ
{
  return  [Platform yy_modelWithJSON: [[[Weex conifg] objectForKey:@"platform"] objectForKey:@"qq"]];
}
+(Platform*)getPlatformWeibo
{
 return  [Platform yy_modelWithJSON: [[[Weex conifg] objectForKey:@"platform"] objectForKey:@"weibo"]];
    
}

@end
