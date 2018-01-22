//
//  WXStaticModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/6/14.
//
//

#import <WeexSDK/WeexSDK.h>
static NSMutableDictionary *map;
@interface WXStaticModule :  NSObject < WXModuleProtocol>
-(id)get:(NSString*)key;

@end
