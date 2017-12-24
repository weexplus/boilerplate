//
//  ZDatePicker.h
//  oa
//
//  Created by 郑江荣 on 16/5/16.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ZDatePicker : UIView

@property (weak, nonatomic) IBOutlet UIDatePicker *datePicker;
@property (strong,nonatomic)   typeof(void(^)(NSDate*)) onSelect;
-(void)select:(NSDate*)d;
-(void)show;
-(void)hide;
@end
