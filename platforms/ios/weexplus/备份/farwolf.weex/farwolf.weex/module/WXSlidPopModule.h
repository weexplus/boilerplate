//
//  WXSlidPopModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/9/21.
//
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXModuleProtocol.h>
#import "WXNormalViewContrller.h"
@interface WXSlidPopModule : NSObject<WXModuleProtocol>
@property(nonatomic)BOOL hasshow;
@property(nonatomic,strong) UIView *popView;
@property(nonatomic)CGFloat delt;
@property(nonatomic,strong)NSString *side;
@property(nonatomic,strong) WXNormalViewContrller *vc;
@property(nonatomic,strong) UINavigationController *nav;
@property (nonatomic, strong) UIView *backgroundView;
@property(nonatomic,strong)NSString*url;

@property(nonatomic)CGFloat left;
@property(nonatomic)CGFloat  top;
@property(nonatomic)CGFloat right;
@property(nonatomic)CGFloat bottom;

@end
