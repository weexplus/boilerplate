//
//  UpdateJsonReader.m
//  farwolf.business
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "UpdateJsonReader.h"

@implementation UpdateJsonReader


-(Json*)getDecoder
{
    return [UpdateJson new];
}
-(NSString*)getUrl
{
    return self.url;
}

@end
