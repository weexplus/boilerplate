//
//  LMDisposeChooseTimeView.h
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import <UIKit/UIKit.h>
typedef void(^chooseDateWithTimerStr) (NSString *dateWithTimerStr);

@interface LMDisposeChooseTimeView : UIView

/**
 当前日期
 */
@property (nonatomic ,copy)   NSString *nowDateStr;

/**
 当前时间
 */
@property (nonatomic ,copy)   NSString *nowTimerStr;

/***************************     **  ↑ -- 传来的时间   **      *************************/


/**
 整体 View
 */
@property (nonatomic ,strong) UIView *bgView;

/**
 上方显示时间区域
 */
@property (nonatomic ,strong) UIView *upView;

/**
 日期 : 年/月/日
 */
@property (nonatomic ,strong) UILabel *dateLabel;

/**
 时间 : 00:00
 */
@property (nonatomic ,strong) UILabel *timerLabel;

/**
 线
 */
@property (nonatomic ,strong) UILabel *longLineLabel;
@property (nonatomic ,strong) UILabel *shortLineLabel;


@property (nonatomic ,copy)   chooseDateWithTimerStr chooseDateWithTimerBlock;

/**
 展示

 @param view 在当前 View 上显示
 */
- (void)showInView:(UIView *)view;

/**
 隐藏
 */
- (void)disMissView;

@end
