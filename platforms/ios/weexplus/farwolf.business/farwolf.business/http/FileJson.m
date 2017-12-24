//
//  FileJson.m
//  oa
//
//  Created by 郑江荣 on 16/5/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "FileJson.h"

@implementation FileJson

-(NSInteger)getErrorCode
{
    
    //     NSDictionary *jsonDict=  [NSJSONSerialization JSONObjectWithData:self.data options:NSJSONReadingMutableLeaves error:nil];
    NSString *path=  [self.data objectForKey:@"path"];
    if(path!=nil)
    {
        return 0;
    }
    else
    {
        return 1;
    }
    
}
-(NSString*)getErrorMsg
{
    //    NSDictionary *jsonDict=  [NSJSONSerialization JSONObjectWithData:self.data options:NSJSONReadingMutableLeaves error:nil];
    NSString *s= [self.data objectForKey:@"message"];
    return s;
}
@end
