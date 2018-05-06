//
//  LMRightDatePicker.h
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^chooseTimerStr) (NSString *timerStr);
@interface LMRightDatePicker : UIView

@property (nonatomic ,copy) chooseTimerStr timerBlock;

@property (nonatomic ,copy) NSString *timerStr;


@property (nonatomic ,strong) UIDatePicker *datePicker;
@end
