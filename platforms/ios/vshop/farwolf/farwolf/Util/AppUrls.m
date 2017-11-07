//
//  AppUrls.m
//  oa
//
//  Created by 郑江荣 on 16/6/25.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "AppUrls.h"
#import "farwolf.h"
@implementation AppUrls

+(NSString*)getRealUrl:(NSString*)baseurl target:(NSString*)url
{
    NSString *s= baseurl;    //   NSLog([@"s:"add:s]);
    NSArray *n= [url split:@"/"];
    int c=0;
    NSString *weg=@"";
    for(NSString *sp in n)
    {
        if([sp isEqualToString:@".."])
        {
            c++;
        }
        else
        {
            if([n indexOfObject:sp]!=(n.count-1))
            {
                weg=[[weg add:sp]add:@"/"];
            }
            else
            {
                weg=[weg add:sp];
            }
            
        }
    }
    c++;
    //    NSLog([@"weg:"add:weg]);
    n= [s split:@"/"];
    NSString *head=@"";
    for(int i=0;i<n.count-c;i++)
    {
        NSString *sp=n[i];
        head=[[head add:sp]add:@"/"];
        
    }
    
    //    NSLog([@"head:"add:head]);
    return [head add:weg];
}

@end
