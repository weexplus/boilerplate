//
//  StateView.m
//  oa
//
//  Created by 郑江荣 on 2017/3/30.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "StateView.h"

@implementation StateView



-(void)setHeight:(CGFloat)height
{
   CGRect c=  self.frame;
    self.frame=CGRectMake(c.origin.x, c.origin.y, c.size.width,height);
}


@end
