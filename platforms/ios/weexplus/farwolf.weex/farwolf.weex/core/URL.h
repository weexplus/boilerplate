//
//  URL.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/11/6.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXSDKInstance.h>
@interface URL : NSObject
+(NSURL*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance;
+(NSMutableArray*)getFinalUrls:(NSMutableArray*)urls weexInstance:(WXSDKInstance*)weexInstance;
+(NSString*)documentDirectory;
+(BOOL)isDiskExist;
+(NSURL*)loadFromBundle:(NSString*)path ext:(NSString*)ext;
+(NSURL*)loadFromDisk:(NSString*)path;
+(void)copyBundleToDisk;
+(NSURL*)loadLocal:(NSString*)url;
+(void)unzip:(NSString*)from to:(NSString*)to;
@end
