//
//  PFView.h
//  oa
//
//  Created by 郑江荣 on 2017/3/16.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PFView : UIView
@property (nonatomic, strong)  NSMutableArray<UIView*> *views;
-(void)addView:(UIView*)v;
-(void)layoutView;
@end
