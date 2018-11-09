//
//  WXJPushModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/24.
//

#import "WXJPushModule.h"
#import "WXJPushModule.h"
#import "JPushManager.h"
#import <WeexPluginLoader/WeexPluginLoader.h>
WX_PlUGIN_EXPORT_MODULE(jpush, WXJPushModule)
@implementation WXJPushModule

@synthesize weexInstance;

WX_EXPORT_METHOD_SYNC(@selector(getJPushId))

 
-(NSString*)getJPushId
{
    
    NSString *jid=[JPUSHService registrationID];
     return jid;
   
}
@end
