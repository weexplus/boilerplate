//
//  ScreenAdapter.h
//  oa
//
//  Created by 郑江荣 on 16/9/28.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol ScreenAdapter <NSObject>
@optional
-(void)screen37;
-(void)screen40;
-(void)screen47;
-(void)screen55;
-(void)screenUnknow:(double)width;

@end
