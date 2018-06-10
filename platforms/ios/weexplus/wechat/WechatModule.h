//
//  WXPayModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/3.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXEventModuleProtocol.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "farwolf.h"
@interface WechatModule : NSObject<WXModuleProtocol>
+(void)initWechat;
@end
