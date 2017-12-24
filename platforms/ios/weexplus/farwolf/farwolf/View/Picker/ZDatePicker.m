//
//  ZDatePicker.m
//  oa
//
//  Created by 郑江荣 on 16/5/16.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "ZDatePicker.h"
#import "farwolf.h"
@implementation ZDatePicker



-(void)awakeFromNib
{
    [super awakeFromNib];
    NSLocale *locale = [[NSLocale alloc] initWithLocaleIdentifier:@"zh_CN"];//设置为中
    self.datePicker.locale = locale;
}

-(void)select:(NSDate*)d
{
    [self.datePicker setDate:d];
}

-(void)show
{
    [self setHidden:false];
}
-(void)hide
{
    [self setHidden:true];
}
- (IBAction)cancelClick:(id)sender {
    
    [self setHidden:true];
}


- (IBAction)doneClick:(id)sender {
    
    [self setHidden:true];
    self.onSelect(self.datePicker.date);
    
    
}

@end
