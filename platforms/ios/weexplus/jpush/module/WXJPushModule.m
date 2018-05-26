//
//  WXJPushModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/24.
//

#import "WXJPushModule.h"

@implementation WXJPushModule

@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(getJPushId))


-(NSString*)getJPushId
{
    return @"";
}
@end
