//
//  AppUrls.h
//  oa
//
//  Created by 郑江荣 on 16/6/25.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#define QQ @"mqq://"
#define WeiXin @"weixin:///"
@interface AppUrls : NSObject
+(NSString*)getRealUrl:(NSString*)baseurl target:(NSString*)url;
@end
