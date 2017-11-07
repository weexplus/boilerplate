//
//  MonthPicker.m
//  oa
//
//  Created by 郑江荣 on 2017/2/20.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "MonthPicker.h"
#import "SRMonthPicker.h"
#import "farwolf.h"
@implementation MonthPicker


-(void)awakeFromNib
{
    [super awakeFromNib];
    self.datePicker=[[SRMonthPicker alloc]init];
    [self.pickerLayout addSubviewFull:self.datePicker];
}
- (IBAction)doneClick:(id)sender {
    
    
    [self setHidden:true];
    self.onSelect(self.datePicker.date);
   
 
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


@end
