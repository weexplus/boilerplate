//
//  HttpProtocol.h
//  farwolf
//
//  Created by 郑江荣 on 16/9/5.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol HttpProtocol <NSObject>
-(void)start;
-(void)success:(NSString*)res;
-(void)exception;
-(void)compelete;

@end
