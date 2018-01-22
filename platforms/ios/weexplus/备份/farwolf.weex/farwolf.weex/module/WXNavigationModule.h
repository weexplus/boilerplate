//
//  WXNavigationModule.h
//  farwolf.weex
//
//  Created by 郑江荣 on 2017/5/3.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "farwolf_weex.h"
#import "URL.h"
@interface WXNavigationModule : NSObject<WXModuleProtocol,UIGestureRecognizerDelegate>
-(void)getChildVc:(UIViewController *)vc array:(NSMutableArray*)array;
@end
