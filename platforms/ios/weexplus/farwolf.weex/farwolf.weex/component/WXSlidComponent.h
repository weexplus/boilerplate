//
//  WXSlidComponent.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/5.
//

#import <WeexSDK/WeexSDK.h>
//#import "REFrostedViewController.h"

#import "LGSideMenuController.h"
#import "UIViewController+LGSideMenuController.h"
@interface WXSlidComponent : WXComponent
@property(nonatomic,strong)  NSString *src;
@property(nonatomic,strong)  NSString *slidSrc;
@property(nonatomic)  BOOL isOpen;
@property(nonatomic)  CGFloat leftWidth;
@property(nonatomic,strong) UIView *host;
@property(nonatomic,strong) UIView *left;
@property(nonatomic,strong) UIView *root;
@property(nonatomic,strong) LGSideMenuController *slidcontrol;

//@property(nonatomic,strong)  REFrostedViewController *frostedViewController;
@end
