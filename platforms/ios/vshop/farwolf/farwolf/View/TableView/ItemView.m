//
//  ItemView.m
//  oa
//
//  Created by 郑江荣 on 16/3/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "ItemView.h"

@implementation ItemView

//- (void)awakeFromNib {
//    // Initialization code
//}
//
//- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
//    [super setSelected:selected animated:animated];
//
//    // Configure the view for the selected state
//}


-(void)update:(NSObject*)n{
    
}

- (void)awakeFromNib {
    // Initialization code
    [super awakeFromNib];
    [self adjust];
}


-(void)adjust
{
    CGFloat s=[UIScreen mainScreen].bounds.size.height;
    if(s==SCREEN3_5)
    {
        [self screen35];
    }
    else if(s==SCREEN4_0)
    {
        [self screen40];
    }
    else if(s==SCREEN4_7)
    {
        [self screen47];
    }
    else if(s==SCREEN5_5)
    {
        [self screen55];
    }
    else
    {
        [self screenUnknow:s];
    }
    
}

-(void)screen35
{
    
}
-(void)screen40
{
    
}

-(void)screen47
{
    
}

-(void)screen55
{
    
}
-(void)screenUnknow:(double)width
{
    
}



@end
