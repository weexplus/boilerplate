//
//  JPushManager.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/6/6.
//

#import <Foundation/Foundation.h>
#import "JPUSHService.h"
#import "PushProtocol.h"
// iOS10注册APNs所需头文件
#ifdef NSFoundationVersionNumber_iOS_9_x_Max
#import <UserNotifications/UserNotifications.h>
#endif
@interface JPushManager : NSObject<JPUSHRegisterDelegate,PushProtocol>
+ (instancetype)sharedManager;
-(void)initHanler;
@end
