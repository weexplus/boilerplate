//
//  WXTracingViewController.h
//  TBWXDevTool
//
//  Created by 齐山 on 2017/7/4.
//  Copyright © 2017年 Taobao. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface WXTracingViewControllerManager : UIViewController
@property(nonatomic,strong)UITextView *textView;
@property(nonatomic,strong)NSMutableArray *messages;

+ (instancetype) sharedInstance;
+ (void)loadTracingView;
+ (BOOL)isLoadTracing;
@end
