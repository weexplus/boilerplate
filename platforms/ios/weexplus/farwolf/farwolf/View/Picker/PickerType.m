//
//  PickerType.m
//  farwolf
//
//  Created by 郑江荣 on 2017/4/27.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "PickerType.h"

@implementation PickerType

-(id)initWith:(NSString*)code name:(NSString*)name
{
    self=[super init];
    self.code=code;
    self.name=name;
    return self;
}
@end
