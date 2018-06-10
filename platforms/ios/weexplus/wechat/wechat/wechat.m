//
//  wechat.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/6.
//

#import "wechat.h"
#import "farwolf.h"
#import "farwolf_weex.h"
#import "WXApi.h"
#import "WXApiManager.h"
#import <AdSupport/AdSupport.h>
#import <WeexSDK/WXSDKInstance.h>
#import <WeexSDK/WXSDKEngine.h>
#import "WechatModule.h"
@implementation wechat


+(void)initWechat
{
  
     [WXApiManager.sharedManager initHandler];
     [WXSDKEngine registerModule:@"wechat" withClass:[WechatModule class]];
}



@end
