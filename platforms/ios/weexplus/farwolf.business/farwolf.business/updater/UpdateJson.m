//
//  UpdateJson.m
//  farwolf.business
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "UpdateJson.h"

@implementation UpdateJson
-(NSInteger)getErrorCode
{
    
    //     NSDictionary *jsonDict=  [NSJSONSerialization JSONObjectWithData:self.data options:NSJSONReadingMutableLeaves error:nil];
    NSObject *o=  [self.data objectForKey:@"error"];
    return [[@"" stringByAppendingString:o] intValue];
    
}
-(NSString*)getErrorMsg
{
   
    NSString *s= [self.data objectForKey:@"msg"];
    return s;
}
@end
