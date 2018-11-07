//
//  WXKeyboardModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/3.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WeexSDK.h>


@interface WXKeyboardModule :  NSObject<WXModuleProtocol>
@property(nonatomic,retain)WXModuleKeepAliveCallback onshow;
@property(nonatomic,retain)WXModuleKeepAliveCallback onhide;
@end


