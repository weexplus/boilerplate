//
//  NSDate+Farwolf.h
//  oa
//
//  Created by 郑江荣 on 16/4/26.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSDate (Farwolf)
+(NSString*)format:(NSString*)format time:(NSDate*)time;
-(NSString*)format:(NSString*)format;
-(long)getMonth;
-(long)getYear;
-(long)getWeek;
- (NSString *)getCurrentTimestamp;
 
@end
