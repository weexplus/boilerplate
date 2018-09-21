//
//  WXAlipayModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/31.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXEventModuleProtocol.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "farwolf.h"
@interface WXAlipayModule : NSObject<WXModuleProtocol>
+(void)setPayCallBack:(WXModuleCallback)callback;
+(void)setAuthCallBack:(WXModuleCallback)callback;
+(WXModuleCallback)getPayCallback;
+(WXModuleCallback)getAuthCallback;
@end
