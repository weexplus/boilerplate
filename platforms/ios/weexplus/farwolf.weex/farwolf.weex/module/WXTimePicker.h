//
//  WXTimerPicker.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/6.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WeexSDK.h>
#import "LMDisposeChooseTimeView.h"
@interface WXTimePicker : NSObject<WXModuleProtocol>
@property (nonatomic,strong) LMDisposeChooseTimeView *chooseTimeView;
@end
