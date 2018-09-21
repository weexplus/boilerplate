//
//  AlipayManager.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/9/20.
//

#import <Foundation/Foundation.h>

@interface AlipayManager : NSObject
+ (instancetype)sharedManager;
-(void)initHanler;
@end
