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
static NSString *basedir;
static NSString *baseurl;
@interface Weex : NSObject
+(void)initWeex:(NSString*)group appName:(NSString*)appName appVersion:(NSString*)appVersion;
+(void)startDebug:(NSString*)ip port:(NSString*)port;
+(void)setImageSource:(NSString*)url compelete:(void(^)(UIImage *img))compelete;
+(NSString*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance;
+(NSString*)getBaseDir;
+(void)setBaseDir:(NSString*)url;
+(NSString*)getBaseUrl;
+(void)setBaseUrl:(NSString*)url;
@end
