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

+(BOOL)isDebug
{
    NSMutableDictionary *d=  [Weex conifg];
  
   return [d[@"debug"] boolValue];
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
