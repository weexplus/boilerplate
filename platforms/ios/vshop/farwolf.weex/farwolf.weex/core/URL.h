//
//  URL.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/11/6.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXSDKInstance.h>
@interface URL : NSObject
+(NSString*)getFinalUrl:(NSString*)url weexInstance:(WXSDKInstance*)weexInstance;
@end
