//
//  Config.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/1.
//

#import "Config.h"
#import "URL.h"

@implementation Config


+(NSString*)schema 
{
    return  [[Weex conifg] objectForKey:@"schema"];
}

+(NSString*)debugEntry
{
    return  [[Weex conifg] objectForKey:@"debugEntry"];
}

+(NSString*)releaseEntry
{
    return  [[Weex conifg] objectForKey:@"releaseEntry"];
}

+(NSString*)notifyEntry
{
    return  [[Weex conifg] objectForKey:@"notifyEntry"];
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

+(NSString*)appBoard
{
    return [[Weex conifg] objectForKey:@"appBoard"];
}

+(NSMutableDictionary*)routerTranslater
{
    NSString *str=[Config routerTranslaterStr];
   return [str toJson];
}
+(NSString*)routerTranslaterStr
{
     
    NSString *path = @"app/router-translator";
     NSURL *url=nil;
     if([URL isDiskExist])
     {
         url=[URL loadFromDisk:[path add:@".json"]];
     }else{
         url=[URL loadFromBundle:path ext:@"json"];
     }
  
   NSError *err;
   NSString *str =[NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:&err];
    return  str;
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
    
    return [[Weex bundleConifg][@"debug"] boolValue];
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
