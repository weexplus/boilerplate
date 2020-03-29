//
//  NSDate+Farwolf.m
//  oa
//
//  Created by 郑江荣 on 16/4/26.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "NSDate+Farwolf.h"

@implementation NSDate (Farwolf)

+(NSString*)format:(NSString*)format time:(NSDate*)time
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:format];
    return[dateFormatter stringFromDate:time];
}
-(NSString*)format:(NSString*)format
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:format];
    return[dateFormatter stringFromDate:self];
}

-(long)getMonth
{
    
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];//设置成中国阳历
    NSDateComponents *comps = [[NSDateComponents alloc] init];
    NSInteger unitFlags = NSYearCalendarUnit | NSMonthCalendarUnit | NSDayCalendarUnit | NSWeekdayCalendarUnit | NSHourCalendarUnit | NSMinuteCalendarUnit | NSSecondCalendarUnit;//这句我也不明白具体时用来做什么。。。
    comps = [calendar components:unitFlags fromDate:self];
//    long weekNumber = [comps weekday]; //获取星期对应的长整形字符串
//    long day=[comps day];//获取日期对应的长整形字符串
//    long year=[comps year];//获取年对应的长整形字符串
    long month=[comps month];//获取月对应的长整形字符串
    return month;
//    long hour=[comps hour];//获取小时对应的长整形字符串
//    long minute=[comps minute];//获取月对应的长整形字符串
//    long second=[comps second];//获取秒对应的长整形字符串
}

-(long)getYear
{
    
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];//设置成中国阳历
    NSDateComponents *comps = [[NSDateComponents alloc] init];
    NSInteger unitFlags = NSYearCalendarUnit | NSMonthCalendarUnit | NSDayCalendarUnit | NSWeekdayCalendarUnit | NSHourCalendarUnit | NSMinuteCalendarUnit | NSSecondCalendarUnit;//这句我也不明白具体时用来做什么。。。
    comps = [calendar components:unitFlags fromDate:self];
    //    long weekNumber = [comps weekday]; //获取星期对应的长整形字符串
    //    long day=[comps day];//获取日期对应的长整形字符串
        long year=[comps year];//获取年对应的长整形字符串
//    long month=[comps month];//获取月对应的长整形字符串
    return year;
    //    long hour=[comps hour];//获取小时对应的长整形字符串
    //    long minute=[comps minute];//获取月对应的长整形字符串
    //    long second=[comps second];//获取秒对应的长整形字符串
}


-(long)getWeek
{
    
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];//设置成中国阳历
    NSDateComponents *comps = [[NSDateComponents alloc] init];
    NSInteger unitFlags = NSYearCalendarUnit | NSMonthCalendarUnit | NSDayCalendarUnit | NSWeekdayCalendarUnit | NSHourCalendarUnit | NSMinuteCalendarUnit | NSSecondCalendarUnit;//这句我也不明白具体时用来做什么。。。
    comps = [calendar components:unitFlags fromDate:self];
        long weekNumber = [comps weekday]; //获取星期对应的长整形字符串
    return weekNumber;
    //    long day=[comps day];//获取日期对应的长整形字符串
//    long year=[comps year];//获取年对应的长整形字符串
    //    long month=[comps month];//获取月对应的长整形字符串
//    return year;
    //    long hour=[comps hour];//获取小时对应的长整形字符串
    //    long minute=[comps minute];//获取月对应的长整形字符串
    //    long second=[comps second];//获取秒对应的长整形字符串
}

-(long)getDay
{
    
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];//设置成中国阳历
    NSDateComponents *comps = [[NSDateComponents alloc] init];
    NSInteger unitFlags = NSYearCalendarUnit | NSMonthCalendarUnit | NSDayCalendarUnit | NSWeekdayCalendarUnit | NSHourCalendarUnit | NSMinuteCalendarUnit | NSSecondCalendarUnit;//这句我也不明白具体时用来做什么。。。
    comps = [calendar components:unitFlags fromDate:self];
//    long weekNumber = [comps weekday]; //获取星期对应的长整形字符串
//    return weekNumber;
        long day=[comps day];//获取日期对应的长整形字符串
    return day;
    //    long year=[comps year];//获取年对应的长整形字符串
    //    long month=[comps month];//获取月对应的长整形字符串
    //    return year;
    //    long hour=[comps hour];//获取小时对应的长整形字符串
    //    long minute=[comps minute];//获取月对应的长整形字符串
    //    long second=[comps second];//获取秒对应的长整形字符串
}
- (NSString *)getCurrentTimestamp {
    NSDate *date = [NSDate dateWithTimeIntervalSinceNow:0]; // 获取当前时间0秒后的时间
    NSTimeInterval time = [date timeIntervalSince1970]*1000;// *1000 是精确到毫秒(13位),不乘就是精确到秒(10位)
    NSString *timeString = [NSString stringWithFormat:@"%.0f", time];
    return timeString;
}
@end
