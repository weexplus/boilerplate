//
//  NSString+Farwolf.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface NSString (Farwolf)

-(UIColor*)toColor:(float)alpha;
-(UIColor*)toColor;
-(NSDictionary*)toJson;
-(BOOL)startWith:(NSString*)s;
-(BOOL)endWith:(NSString*)s;
-(BOOL)contains:(NSString*)s;
-(NSArray*)split:(NSString*)s;
-(NSString*)add:(NSObject*)o;
-(NSString*)addInt:(int)o;
-(NSString*)addFloat:(float)o;
-(NSString*)addFloat:(float)o format:(NSString*)f;
-(NSString*)addDouble:(double)o;
-(NSString*)replace:(NSString*)find withString:(NSString*)withString;
-(NSMutableArray<NSString*>*)toArray;
-(NSString*)dateFormat:(NSString*)from to:(NSString*)to;
-(NSDate*)toDate:(NSString*)format;
-(NSString*)findone:(NSString*)start end:(NSString*)end;
-(NSString*)findoneNoReplace:(NSString*)start end:(NSString*)end;
-(void)copyToPath:(NSString *)toPath;
-(void)delete;
-(BOOL)isExist;
-(BOOL)mkdir;
@end
