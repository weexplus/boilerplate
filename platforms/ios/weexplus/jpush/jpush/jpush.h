//
//  jpush.h
//  jpush
//
//  Created by 郑江荣 on 2018/5/24.
//  Copyright © 2018年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "JPUSHService.h"
#import "PushProtocol.h"
// iOS10注册APNs所需头文件
#ifdef NSFoundationVersionNumber_iOS_9_x_Max
#import <UserNotifications/UserNotifications.h>
#endif
@interface jpush : NSObject
-(void)initPush;
+(instancetype)sharedManager;
@end
