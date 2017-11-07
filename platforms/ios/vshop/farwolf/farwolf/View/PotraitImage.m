//
//  PotraitImage.m
//  oa
//
//  Created by 郑江荣 on 16/3/11.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "PotraitImage.h"
#import "UIView+Farwolf.h"
@implementation PotraitImage

 -(void)awakeFromNib
{
    [self load];
}

-(void)load
{
    self.layer.masksToBounds=true;
    self.layer.cornerRadius=self.bounds.size.width*0.5;
}

@end
