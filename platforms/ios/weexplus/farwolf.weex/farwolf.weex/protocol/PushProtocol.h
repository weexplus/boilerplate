//
//  PushProtocol.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/27.
//

#import <Foundation/Foundation.h>

@protocol PushProtocol<NSObject>

-(void)initPush;

-(void)afterLanching:(NSDictionary *)launchOptions appkey:(NSString*)appkey;

-(void)registToken:(NSData*)token;

-(void)handNotification:(NSDictionary *)userInfo;

@end
