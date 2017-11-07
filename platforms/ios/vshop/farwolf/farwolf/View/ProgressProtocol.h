//
//  ProgressProtocol.h
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//


#import <Foundation/Foundation.h>
@protocol ProgressProtocol <NSObject>
@optional
-(void)show;
-(void)hide;
@end