//
//  Config.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/1.
//

#import <Foundation/Foundation.h>
#import "Platform.h"
#import "Weex.h"

@interface Config : NSObject
+(NSString*)schema;
+(NSString*)debugEntry;
+(NSString*)releaseEntry;
+(NSString*)notifyEntry;
+(NSString*)splash;
+(NSString*)debugIp;
+(BOOL)isPortrait;
+(BOOL)showError;
+(NSString*)jsVersion;
+(NSString*)diskJsVersion;
+(NSString*)bundleJsVersion;
+(NSString*)appBoard;
+(NSString*)appBoardContent;
+(NSString*)routerTranslaterStr;
+(NSMutableDictionary*)routerTranslater;
+(NSMutableArray*)preload;
+(BOOL)isDebug;
+(NSString*)getUMAndroidKey;
+(NSString*)getUMIOSKey;
+(Platform*)getPlatformWX;
+(Platform*)getPlatformQQ;
+(Platform*)getPlatformWeibo;

@end
