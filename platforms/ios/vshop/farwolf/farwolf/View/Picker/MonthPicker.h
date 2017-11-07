//
//  MonthPicker.h
//  oa
//
//  Created by 郑江荣 on 2017/2/20.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "Picker.h"
#import "SRMonthPicker.h"
@interface MonthPicker : UIView
@property (weak, nonatomic) IBOutlet UIView *pickerLayout;

@property (strong, nonatomic)   SRMonthPicker *datePicker;
@property (strong,nonatomic)   typeof(void(^)(NSDate*)) onSelect;
-(void)show;
-(void)hide;
@end
