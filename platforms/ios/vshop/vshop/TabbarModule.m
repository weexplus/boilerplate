//
//  TabbarModule.m
//  vshop
//
//  Created by 郑江荣 on 2017/6/29.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "TabbarModule.h"
#import "farwolf.h"

@implementation TabbarModule
WX_EXPORT_METHOD(@selector(show:))


-(void)show:(NSString*)name
{
    NSMutableDictionary *d=[NSMutableDictionary new];
    [d setValue:name forKey:@"name"];
    [self notifyDict:@"tabselect" value:d];
}
@end
