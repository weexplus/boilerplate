//
//  alipay.m
//  alipay
//
//  Created by 郑江荣 on 2018/5/31.
//  Copyright © 2018年 郑江荣. All rights reserved.
//

#import "alipay.h"
#import <AliyunOSSiOS/OSSService.h>
#import <WeexSDK/WXSDKInstance.h>
#import <WeexSDK/WXSDKEngine.h>
#import "WXAlipayModule.h"
@implementation alipay
+(void)initPay
{
    [WXSDKEngine registerModule:@"alipay" withClass:[WXAlipayModule class]];
}
@end
