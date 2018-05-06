//
//  LMLeftDatePicker.m
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import "LMLeftDatePicker.h"
#import "FSCalendar.h"
#import "Constant.h"

#define kContainerFrame (CGRectMake(0, CGRectGetMaxY(calendar.frame), CGRectGetWidth(self.frame), 50))

@interface LMLeftDatePicker ()<FSCalendarDataSource,FSCalendarDelegate,FSCalendarDelegateAppearance>

@property (weak, nonatomic) FSCalendar *calendar;

@property (weak, nonatomic) UIView *bottomContainer;
@property (weak, nonatomic) UILabel *eventLabel;
@property (weak, nonatomic) UIButton *nextButton;
@property (weak, nonatomic) UIButton *prevButton;

@property (strong, nonatomic) NSCalendar *gregorian;
@property (strong, nonatomic) NSDateFormatter *dateFormatter;

@end

@implementation LMLeftDatePicker

-(instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        
        self.dateFormatter = [[NSDateFormatter alloc] init];
        self.dateFormatter.dateFormat = @"yyyy年MM月dd日";
        
        
        UIView *view;
        view = [[UIView alloc] initWithFrame:[UIScreen mainScreen].bounds];
        view.backgroundColor = [UIColor groupTableViewBackgroundColor];
        
        FSCalendar *calendar = [[FSCalendar alloc] initWithFrame:CGRectMake(0, 0, self.frame.size.width, Height(720/2))];
        calendar.backgroundColor = [UIColor whiteColor];
        calendar.dataSource = self;
        calendar.delegate = self;
        calendar.placeholderType = FSCalendarPlaceholderTypeFillSixRows;
        calendar.currentPage = [self.dateFormatter dateFromString:_nowDateStr];
        calendar.firstWeekday = 1;
        calendar.scrollDirection = FSCalendarScrollDirectionVertical;
        calendar.locale = [[NSLocale alloc]initWithLocaleIdentifier:@"zh_CHS_CN"];
        calendar.appearance.weekdayTextColor = [UIColor blackColor];
        calendar.headerHeight = 0.0f;
        calendar.appearance.todayColor = [UIColor lightGrayColor];
        
        [self addSubview:calendar];
        self.calendar = calendar;
    }
    return self;
}


- (NSDate *)minimumDateForCalendar:(FSCalendar *)calendar
{
    return [self.dateFormatter dateFromString:@"2016-01-07"];
}

- (NSDate *)maximumDateForCalendar:(FSCalendar *)calendar
{
    return [self.dateFormatter dateFromString:@"2018-10-08"];
}

- (void)setNowDateStr:(NSString *)nowDateStr
{
    _nowDateStr = nowDateStr;
    self.calendar.currentPage = [self.dateFormatter dateFromString:nowDateStr];
    
    NSDate *date =[self.dateFormatter dateFromString:_nowDateStr];
    
    [self.calendar selectDate:date];
    
    NSLog(@"-----  %@",[self.dateFormatter dateFromString:_nowDateStr]);
}

#pragma mark - <FSCalendarDelegate>

- (void)calendar:(FSCalendar *)calendar boundingRectWillChange:(CGRect)bounds animated:(BOOL)animated
{
    calendar.frame = (CGRect){calendar.frame.origin,bounds.size};
    self.bottomContainer.frame = kContainerFrame;
}

- (void)calendar:(FSCalendar *)calendar didSelectDate:(NSDate *)date {
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy年MM月dd日"];
    [dateFormatter setLocale:[[NSLocale alloc] initWithLocaleIdentifier:@"zh_CN"]];
    NSString *str = [dateFormatter stringFromDate:date];
    NSLog(@"---%@",str);
    if (self.dateBlock) {
        self.dateBlock(str);
    }
}

@end
