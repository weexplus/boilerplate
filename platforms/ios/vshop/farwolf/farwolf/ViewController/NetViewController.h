//
//  NetViewController.h
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "farwolf.h"
#import "JsonProtocol.h"
#import "LockScreenProgress.h"
@interface NetViewController : UIViewController<JsonProtocol>
-(id<ProgressProtocol>)getProgress;

@property(strong,nonatomic)LockScreenProgress* progress;
-(void)screen35;
-(void)screen40;
-(void)screen47;
-(void)screen55;
-(void)screenUnknow:(double)height;
@end
