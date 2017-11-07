//
//  JsonProtocol.h
//  pod
//
//  Created by 郑江荣 on 16/3/9.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Json.h"
@protocol JsonProtocol <NSObject>

-(void)start;
-(void)success:(Json*)j;
-(void)fail:(NSInteger)errcode errmsg:(NSString*)errmsg json:(Json*)j;
-(void)exception;
-(void)compelete;

@end
