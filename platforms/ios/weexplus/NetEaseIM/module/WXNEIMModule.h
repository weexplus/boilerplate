//
//  WXNEIMModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/9.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXEventModuleProtocol.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "farwolf.h"
#import <NIMSDK/NIMSDK.h>
@interface WXNEIMModule : NSObject<WXModuleProtocol,NIMLoginManagerDelegate>

@end

