//
//  Indicator.h
//  oa
//
//  Created by 郑江荣 on 2017/3/16.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "IndicatorItem.h"
#import "PFView.h"
@protocol selectDelegate;
@interface Indicator : PFView

//@property (nonatomic, strong)  NSMutableArray<IndicatorItem*> *items;

 

@property(nullable,nonatomic,weak) id<selectDelegate>   delegate;
-(void)selectOne:(int)index;
@end
@protocol  selectDelegate<NSObject>
@optional
-(void)select:(int)index;


@end
