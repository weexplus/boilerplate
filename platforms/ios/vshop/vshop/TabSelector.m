//
//  TabSelector.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "TabSelector.h"

@implementation TabSelector

-(id)initWithImg:(NSString*) unselect select:(NSString*)select
{
    self=[super init];
    self.unselect=unselect;
    self.select=select;
    return self;
}


@end
