//
//  LMDisposeChooseTimeView.m
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import "LMDisposeChooseTimeView.h"
#import "LMLeftDatePicker.h"
#import "LMRightDatePicker.h"
#import "Constant.h"
#import "FSCalendar.h"
#import "UILabel+Extension.h"

@interface LMDisposeChooseTimeView ()<UIScrollViewDelegate>

@property (nonatomic ,assign) NSInteger selectIndex;

@property (nonatomic ,strong) UIScrollView *timeScrollView;

@property (nonatomic ,strong) LMLeftDatePicker *leftDatePicker;

@property (nonatomic ,strong) LMRightDatePicker *rightDatePicker;

@end

@implementation LMDisposeChooseTimeView
#pragma mark - UI
-(instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        
        self.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:0.2]; ;
        self.userInteractionEnabled = YES;
        [self addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(disMissView)]];
        
        _bgView = [[UIView alloc] init];
        [_bgView setFrame:CGRectMake(0, self.frame.size.height - Height(408), KScreenWidth, Height(408))];
        _bgView.backgroundColor = [UIColor whiteColor];
        [self addSubview:_bgView];
        
        _upView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, 48)];
        _upView.backgroundColor = UIColorFromRGB(0xf6f6f6);
        [_bgView addSubview:_upView];
        
        
        NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
        [dateFormatter setDateFormat:@"yyyy年MM月dd日"];
        _dateLabel = [[UILabel alloc] initWithFrame:CGRectMake(Width(12), 0, Width(126), Height(48))];
        _dateLabel.textColor = UIColorFromRGB(0x606060);
        _dateLabel.text = [dateFormatter stringFromDate:[NSDate date]];
        _dateLabel.font = Font(16);
        _dateLabel.textAlignment = NSTextAlignmentLeft;
        _dateLabel.userInteractionEnabled = YES;
        UITapGestureRecognizer *dateTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(dateLabelClick:)];
        [_dateLabel addGestureRecognizer:dateTap];
        [_upView addSubview:_dateLabel];
        
        
        NSDateFormatter *timeFormatter = [[NSDateFormatter alloc] init];
        [timeFormatter setDateFormat:@"HH:mm"];
        _timerLabel = [[UILabel alloc] initWithFrame:CGRectMake(CGRectGetMaxX(_dateLabel.frame) + Width(15), 0, Width(50), Height(48))];
        _timerLabel.textColor = UIColorFromRGB(0x606060);
        _timerLabel.text = [timeFormatter stringFromDate:[NSDate date]];
        _timerLabel.font = Font(16);
        _timerLabel.userInteractionEnabled = YES;
        UITapGestureRecognizer *timerTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(timerLabelClick:)];
        [_timerLabel addGestureRecognizer:timerTap];
        _timerLabel.textAlignment = NSTextAlignmentLeft;
        [_upView addSubview:_timerLabel];
        
        _longLineLabel = [[UILabel alloc] initWithFrame:CGRectMake(Width(12), CGRectGetMaxY(_upView.frame)-Height(1.5), [_dateLabel textWidth], Height(1.5))];
        _longLineLabel.backgroundColor = UIColorFromRGB(0x838381);
        [_upView addSubview:_longLineLabel];
        
        _shortLineLabel = [[UILabel alloc] initWithFrame:CGRectMake(CGRectGetMaxX(_dateLabel.frame) + Width(15), CGRectGetMaxY(_upView.frame)-Height(1.5), [_timerLabel textWidth], Height(1.5))];
        _shortLineLabel.backgroundColor = UIColorFromRGB(0x838381);
        _shortLineLabel.hidden = YES;
        [_upView addSubview:_shortLineLabel];
        
        UIButton *sureBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [sureBtn setTitle:@"确定" forState:(UIControlStateNormal)];
        [sureBtn setTitleColor:UIColorFromRGB(0x4d92d5) forState:UIControlStateNormal];
        sureBtn.titleLabel.font = Font(16);
        sureBtn.frame = CGRectMake(KScreenWidth - Width(56/2) - Width(35), 0, Width(35), Height(48));
        [sureBtn addTarget:self action:@selector(sureBtnClick:) forControlEvents:(UIControlEventTouchUpInside)];
        [_upView addSubview:sureBtn];
        
        
        [self customScrollerView];
    }
    return self;
}

- (void)customScrollerView
{
    _timeScrollView = [[UIScrollView alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(_upView.frame) + Height(10), KScreenWidth, Height(720/2))];
    _timeScrollView.contentSize = CGSizeMake(KScreenWidth *2, Height(720/2));
    _timeScrollView.pagingEnabled = YES;
    _timeScrollView.scrollEnabled = NO;
    _timeScrollView.bounces = NO;
    [_bgView addSubview:_timeScrollView];
    
    
    LMLeftDatePicker *leftDatePicker = [[LMLeftDatePicker alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, Height(720/2))];
    leftDatePicker.nowDateStr = self.nowDateStr;
    leftDatePicker.dateBlock = ^(NSString *dateStr) {
        _dateLabel.text = dateStr;
        _longLineLabel.frame = CGRectMake(Width(12), CGRectGetMaxY(_upView.frame)-Height(1.5), [_dateLabel textWidth], Height(1.5));
        _longLineLabel.hidden = YES;
        _shortLineLabel.hidden = NO;
        [UIView animateWithDuration:0.3 animations:^{
            CGPoint off = self.timeScrollView.contentOffset;
            off.x = KScreenWidth * 1;
            self.timeScrollView.contentOffset = off;
        }];
    };
    [_timeScrollView addSubview:leftDatePicker];
    
    
    
    LMRightDatePicker *rightDatePicker = [[LMRightDatePicker alloc] initWithFrame:CGRectMake(KScreenWidth, 0, KScreenWidth, Height(720/2))];
    rightDatePicker.timerBlock = ^(NSString *timerStr) {
        _timerLabel.text = timerStr;
        _shortLineLabel.frame = CGRectMake(CGRectGetMaxX(_dateLabel.frame) + Width(15), CGRectGetMaxY(_upView.frame)-Height(1.5), [_timerLabel textWidth], Height(1.5));
    };
    [_timeScrollView addSubview:rightDatePicker];
    
    
    self.leftDatePicker = leftDatePicker;
    self.rightDatePicker = rightDatePicker;
    
}

#pragma mark - 展示与隐藏
/**
 展示从底部向上弹出的UIView（包含遮罩）
 */
- (void)showInView:(UIView *)view
{
    if (!view) {
        return;
    }
    [view addSubview:self];
    [view addSubview:_bgView];
    [_bgView setFrame:CGRectMake(0, view.frame.size.height, KScreenWidth, Height(408))];
    
    [UIView animateWithDuration:0.3 animations:^{
        
        self.alpha = 1.0;
        
        [_bgView setFrame:CGRectMake(0, view.frame.size.height - Height(408), KScreenWidth, Height(408))];
        
    } completion:nil];
}

/**
 移除从上向底部弹下去的UIView（包含遮罩）
 */
- (void)disMissView
{
    [_bgView setFrame:CGRectMake(0, self.frame.size.height - Height(408), KScreenWidth, Height(408))];
    [UIView animateWithDuration:0.3f
                     animations:^{
                         
                         self.alpha = 0.0;
                         
                         [_bgView setFrame:CGRectMake(0, self.frame.size.height, KScreenWidth, Height(408))];
                     }
                     completion:^(BOOL finished){
                         
                         [self removeFromSuperview];
                         [_bgView removeFromSuperview];
                     }];
}




#pragma mark - BtnCilck
- (void)sureBtnClick:(UIButton *)sender
{
    NSString *chooseTime = [NSString stringWithFormat:@"%@ %@",_dateLabel.text,_timerLabel.text];
    [self disMissView];
    if (_chooseDateWithTimerBlock) {
        self.chooseDateWithTimerBlock(chooseTime);
    }
}


/**
 日期点击事件
 */
- (void)dateLabelClick:(UITapGestureRecognizer *)gesture
{
    _longLineLabel.hidden = NO;
    _shortLineLabel.hidden = YES;
    [UIView animateWithDuration:0.3 animations:^{
        CGPoint off = self.timeScrollView.contentOffset;
        off.x = KScreenWidth * 0;
        self.timeScrollView.contentOffset = off;
    }];
}

/**
 时间点击事件
 */
- (void)timerLabelClick:(UITapGestureRecognizer *)gesture
{
    _longLineLabel.hidden = YES;
    _shortLineLabel.hidden = NO;
    [UIView animateWithDuration:0.3 animations:^{
        CGPoint off = self.timeScrollView.contentOffset;
        off.x = KScreenWidth * 1;
        self.timeScrollView.contentOffset = off;
    }];
}

#pragma mark - Set
/**
 日期
 */
- (void)setNowDateStr:(NSString *)nowDateStr
{
    _nowDateStr = nowDateStr;
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy年MM月dd日"];
    
    _dateLabel.text = _nowDateStr;
    self.leftDatePicker.nowDateStr = _nowDateStr;
    
    _longLineLabel.frame = CGRectMake(Width(12), CGRectGetMaxY(_upView.frame)-Height(1.5), [_dateLabel textWidth], Height(1.5));
}
/**
 时间
 */
- (void)setNowTimerStr:(NSString *)nowTimerStr
{
    _nowTimerStr = nowTimerStr;
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"HH:mm"];
    
    _timerLabel.text = _nowTimerStr;
    self.rightDatePicker.timerStr = _nowTimerStr;
    
    _shortLineLabel.frame = CGRectMake(CGRectGetMaxX(_dateLabel.frame) + Width(15), CGRectGetMaxY(_upView.frame)-Height(1.5), [_timerLabel textWidth], Height(1.5));
}
@end
