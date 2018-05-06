//
//  LMRightDatePicker.m
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import "LMRightDatePicker.h"
#import "Constant.h"

@implementation LMRightDatePicker
- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        
        _datePicker = [[UIDatePicker alloc] initWithFrame:CGRectMake(0, 0, self.frame.size.width, Height(720/2))];
        _datePicker.datePickerMode = UIDatePickerModeTime;
        _datePicker.locale = [[NSLocale alloc]initWithLocaleIdentifier:@"zh_CHS_CN"];
        [_datePicker addTarget:self action:@selector(handleDate:) forControlEvents:UIControlEventValueChanged];
        _datePicker.backgroundColor = [UIColor whiteColor];
        [self addSubview:_datePicker];
    }
    
    return self;
}


/**
 选择完成后

 @param datePicker datePicker
 */
- (void)handleDate:(UIDatePicker *)datePicker
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"HH:mm"];
    NSString *currentDateString = [dateFormatter stringFromDate:datePicker.date];
    if (self.timerBlock) {
        self.timerBlock(currentDateString);
    }
}


- (void)setTimerStr:(NSString *)timerStr
{
    _timerStr = timerStr;
    
    if (_timerStr) {
        NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];//实例化一个NSDateFormatter对象
        
        [dateFormat setDateFormat:@"HH:mm"];//设定时间格式,这里可以设置成自己需要的格式
        
        _datePicker.date = [dateFormat dateFromString:_timerStr];
    }
}

@end
