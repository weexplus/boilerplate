//
//  Weex.h
//  Pods
//
//  Created by 郑江荣 on 2017/5/4.
//
//

#import <Foundation/Foundation.h>
#import "WXEventModule.h"
#import <WeexSDK/WXSDKInstance.h>
#import "WXNotifyModule.h"
#import "WXPhotoModule.h"
#import "WXNetModule.h"
#import "WXLoadingView.h"
#import "WXStaticModule.h"
#import "RefreshManager.h"
#import "DebugScocket.h"
static NSString *basedir;
//static NSString *baseurl;
static RefreshManager *refreshManager;
static DebugScocket *debugScocket;

@interface Weex : NSObject
+(void)initWeex:(NSString*)group appName:(NSString*)appName appVersion:(NSString*)appVersion;
+(void)startDebug:(NSString*)ip port:(NSString*)port;
+(void)setImageSource:(NSString*)url compelete:(void(^)(UIImage *img))compelete;
+(NSURL*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance;
+(NSString*)getBaseDir;
+(void)setBaseDir:(NSString*)url;
+(NSURL*)toURL:(NSString*)url;
//+(NSString*)getBaseUrl;
//+(void)setBaseUrl:(NSString*)url;
+(UIViewController*)start:(NSString*)image url:(NSString*)url;

+(NSMutableDictionary*)conifg;
+(NSMutableDictionary*)diskConifg;
+(NSMutableDictionary*)bundleConifg;

+(NSString*)socketPort;
+(CGFloat)length:(CGFloat)length instance:(WXSDKInstance*)instance;
+(CGFloat)deLength:(CGFloat)length instance:(WXSDKInstance*)instance;
+(CGFloat)fontSize:(CGFloat)fontsize instance:(WXSDKInstance*)instance;
+(RefreshManager*)getRefreshManager;
+(DebugScocket*)getDebugScocket;
+(NSString*)getBaseUrl:(WXSDKInstance*)instance;
+(NSString*)appBoardContent;
+(void)initAppBoardContent;

+(NSString*)getEntry;
+(NSString*)getDebugIp;
+(void)findComponent:(NSString *)elemRef instance:(WXSDKInstance*)instance block:(void (^)(WXComponent *))block;
+(NSMutableDictionary*)getRouterDic;
@end

