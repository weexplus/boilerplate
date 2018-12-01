//
//  AppSysInfo.m
//  farwolf
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "AppSysInfo.h"

@implementation AppSysInfo
+(NSString*)appName
{
    NSString *app_Name = [[self info] objectForKey:@"CFBundleDisplayName"];
    return app_Name;
   
}
+(NSString*)versionName
{
    // app版本
    NSString *app_Version = [[self info]  objectForKey:@"CFBundleShortVersionString"];
    return app_Version;
}
+(NSString*)version
{
    // app build版本
    NSString *app_build = [[self info]  objectForKey:@"CFBundleVersion"];
    return app_build;
}
+(NSString*)bundleId
{
    // app bundleid
    NSString *app_bundleid = [[self info]  objectForKey:@"CFBundleIdentifier"];
    return app_bundleid;
}
+(NSDictionary*)info
{
     NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    return infoDictionary;
}
+(NSString*)uuid
{
   return  [KeyChainStore getUUID:[AppSysInfo bundleId]];
}
@end
