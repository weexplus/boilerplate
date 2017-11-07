//
//  LockScreenProgress.h
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MBProgressHUD.h"
#import "ProgressProtocol.h"

@interface LockScreenProgress : UIView<ProgressProtocol>
-(id)initWith:(UIView*)v;
@property(strong,nonatomic) UIView* view;
@property(strong,nonatomic) MBProgressHUD* p;
-(void)show:(NSString*)s;

@end
