//
//  Toast.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@interface Toast : NSObject

@property(nonatomic,strong)UIView *view;

-(id)initWithView:(UIView*)view;
+(Toast*)create:(UIView*)view;
-(void)toast:(NSString*)s time:(NSTimeInterval)time x:(float) x y:(float)y;
-(void)toast:(NSString*)s;
@end
