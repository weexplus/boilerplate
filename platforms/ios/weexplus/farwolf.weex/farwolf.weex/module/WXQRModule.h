//
//  WXQRModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/10/9.
//
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "LMDisposeChooseTimeView.h"
@interface WXQRModule : NSObject<WXModuleProtocol>
@property (nonatomic,strong) LMDisposeChooseTimeView *chooseTimeView;
@end
