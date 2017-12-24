//
//  NSString+Farwolf.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "NSString+Farwolf.h"
#import "NSDate+Farwolf.h"
@implementation NSString (Farwolf)

-(UIColor*)toColor:(float)alpha
{
    
    NSString *cString = [[self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
    // String should be 6 or 8 charactersif ([cString length] < 6) return [UIColor blackColor];
    // strip 0X if it appearsif ([cString hasPrefix:@"0X"]) cString = [cString substringFromIndex:2];
    if ([cString hasPrefix:@"#"]) cString = [cString substringFromIndex:1];
    if ([cString length] != 6) return [UIColor blackColor];
    
    // Separate into r, g, b substrings
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    // Scan values
    unsigned int r, g, b;
    
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return [UIColor colorWithRed:((float) r / 255.0f)
                           green:((float) g / 255.0f)
                            blue:((float) b / 255.0f)
                           alpha:alpha];
    
}

-(NSDictionary*)toJson
{
    NSData* jsondata = [self dataUsingEncoding:NSUTF8StringEncoding];
    NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:jsondata
                                                        options:NSJSONReadingMutableContainers
                                                          error:nil];
    return dic;
}

-(UIColor*)toColor
{
    return [self toColor:1.0];
}

-(BOOL)startWith:(NSString *)s
{
    return [self hasPrefix:s];
    
}

-(BOOL)endWith:(NSString *)s
{
    return [self hasSuffix:s];
    
}


-(NSArray*)split:(NSString *)s
{
    return [self componentsSeparatedByString:s];
}

-(NSString*)replace:(NSString*)find withString:(NSString*)withString
{
    return [self stringByReplacingOccurrencesOfString:find withString:withString];
}
-(NSString*)add:(NSObject*)o
{
    
    if(o==nil)
    {
        return [self stringByAppendingString:@""];
    }
    return [self stringByAppendingString:o];
}

-(BOOL)contains:(NSString*)s
{
    //    NSArray *n=[self componentsSeparatedByString:s];
    NSRange n= [self rangeOfString:s];
    
    return     n.length>0;
}

-(NSString*)addInt:(int)o
{
    NSString *c = [NSString stringWithFormat:@"%d",o];
    return [self stringByAppendingString:c];
}
-(NSString*)addFloat:(float)o
{
    return  [self addFloat:o format:nil];
}
-(NSMutableArray<NSString*>*)toArray
{
    NSMutableArray<NSString*> *l=[[NSMutableArray<NSString*> alloc]init];
    if(self==nil)
    {
        return l;
    }
    
    
    for(int i=0;i<self.length;i++)
    {
        
        NSString *s = [self substringWithRange:NSMakeRange(i, 1)];
        [l addObject:s];
        
    }
    
    return l;
}
-(NSString*)addFloat:(float)o format:(NSString*)f
{
    if(f==nil)
    {
        f=@"0.0";
    }
    NSArray *n=[f split:@"."];
    NSString *fo=@"%.";
    int length=0;
    if(n.count>1)
    {
        NSString* s=  n[1];
        length=s.length;
        fo=[fo addInt:length];
    }
    else
    {
        fo=@"%";
    }
    fo=[fo add:@"f"];
    NSString *c = [NSString stringWithFormat:fo ,o];
    return [self stringByAppendingString:c];
}
-(NSString*)addDouble:(double)o
{
    NSString *c = [NSString stringWithFormat:@"%f",o];
    return [self stringByAppendingString:c];
}


-(NSString*)dateFormat:(NSString*)from to:(NSString*)to;
{
   
    NSDate *date = [self toDate:from];
    return [date format:to];
}

-(NSDate*)toDate:(NSString*)format
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:format];
    NSDate *date = [dateFormatter dateFromString:self];
    return date;
}



-(NSString*)findoneNoReplace:(NSString*)start end:(NSString*)end
{
    NSString *searchText =self;
    
    NSString *reg=[[start add:@"(.+?)"]add:end];
    NSRange range = [searchText rangeOfString:reg options:NSRegularExpressionSearch];
    
    if (range.location != NSNotFound) {
        NSLog(@"%@", [searchText substringWithRange:range]);
        return [searchText substringWithRange:range];
    }
     return self;
    
}

-(NSString*)findone:(NSString*)start end:(NSString*)end
{
    NSString *s=[self findoneNoReplace:start end:end];
    return [[s replace:start withString:@""] replace:end withString:@""];
    
}



@end
