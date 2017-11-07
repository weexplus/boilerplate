//
//  SplashControl.h
//  vshop
//
//  Created by 郑江荣 on 2017/6/15.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Page.h"
@interface SplashControl : UIViewController
@property(nonatomic)int count;

+(Page*)getPage:(NSString*)key;
@end
