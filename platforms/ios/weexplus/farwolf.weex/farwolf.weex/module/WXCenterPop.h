//
//  WXCenterPop.h
//  Pods
//
//  Created by 郑江荣 on 2017/12/6.
//

#import <UIKit/UIKit.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "WeexFactory.h"
#import "Weex.h"
#import "WXNormalViewContrller.h"
@interface WXCenterPop : NSObject <WXModuleProtocol>
@property (nonatomic, strong) UIViewController *vc;
@property (nonatomic, strong) UIView *backgroundView;
@property (nonatomic, strong) WXModuleKeepAliveCallback callback;
@end
