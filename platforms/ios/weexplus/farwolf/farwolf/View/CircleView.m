//
//  CircleView.m
//  oa
//
//  Created by 郑江荣 on 16/3/12.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "CircleView.h"
#import "UIView+Farwolf.h"

@implementation CircleView



-(void)awakeFromNib
{

    CGFloat w=self.frame.size.width/2;
    NSLog(@"%f",w);
    self.CornerRadius=w;
}





@end
