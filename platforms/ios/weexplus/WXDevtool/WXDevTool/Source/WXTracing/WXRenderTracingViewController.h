//
//  WXRenderTracingViewController.h
//  TBWXDevTool
//
//  Created by 齐山 on 2017/7/4.
//  Copyright © 2017年 Taobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <WeexSDK/WXTracingManager.h>

@interface WXShowTracingTask : WXTracingTask
@property (nonatomic) NSTimeInterval begin;
@property (nonatomic) NSTimeInterval end;
@end

@interface WXRenderTracingViewController : UIViewController

- (instancetype)initWithFrame:(CGRect )frame;
-(void)refreshData;
@end
