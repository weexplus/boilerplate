//
//  WXPushComponent.h
//  farwolf.weex
//
//  Created by 郑江荣 on 2017/5/3.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WXComponent.h>
#import <UIKit/UIKit.h>
#import "farwolf_weex.h"
@interface WXPushComponent :  WXComponent <UIGestureRecognizerDelegate>
@property (nonatomic, strong) UITapGestureRecognizer *tap;
@property (nonatomic, strong) NSString *href;
@property (nonatomic,strong) NSString* navbarVisibility;
@end
