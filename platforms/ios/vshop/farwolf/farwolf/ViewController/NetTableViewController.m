//
//  NetTableViewController.m
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "NetTableViewController.h"

//#import "KoaPullToRefresh.h"
#import <QuartzCore/QuartzCore.h>
#import "UIViewController+Farwolf.h"
#import "Screen.h"
#import "NSString+Farwolf.h"
@interface NetTableViewController ()<UIGestureRecognizerDelegate>

@end

@implementation NetTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    CGFloat s=[self screenHeight];
    if(s==SCREEN3_5)
    {
        [self screen37];
    }
    else if(s==SCREEN4_0)
    {
        [self screen40];
    }
    else if(s==SCREEN4_7)
    {
        [self screen47];
    }
    else if(s==SCREEN5_5)
    {
        [self screen55];
    }
    else
    {
        [self screenUnknow:s];
    }
    if([self usePullToRefresh])
    {
//        [self.tableView addPullToRefreshWithActionHandler:^{
//
//            [self loadData];
//        } withBackgroundColor:[self getPullrefreshColor].toColor withPullToRefreshHeightShowed:0];
//        [self.tableView.pullToRefreshView setTitle:@"下拉以刷新" forState:KoaPullToRefreshStateStopped];
//        [self.tableView.pullToRefreshView setTitle:@"松开刷新" forState:KoaPullToRefreshStateTriggered];
//        [self.tableView.pullToRefreshView setTitle:@"加载中..." forState:KoaPullToRefreshStateLoading];
    }
    
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)screen37
{
    
}
-(void)screen40
{
    
}

-(void)screen47
{
    
}

-(void)screen55
{
    
}
-(void)screenUnknow:(double)width
{
    
}


-(NSString*)getPullrefreshColor
{
    return @"f4f3f2";
    
}
-(void)loadData
{
    
}

-(BOOL)usePullToRefresh
{
    return false;
}

-(void)start
{
    [[self getProgress] show];
}


-(void)success:(Json*)j
{
    
}
-(void)fail:(NSInteger)errcode:(NSString*)errmsg:(Json*)j
{
    [self toast:errmsg];
}

-(void)hidePullIndicator
{
//    [self.tableView.pullToRefreshView stopAnimating];
}

-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    //    [self addBackGesture];
}

-(void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
    //    [self clearBackGesture];
}


-(void)exception
{
    [self toast:@"网络异常"];
}
-(void)compelete
{
    [[self getProgress] hide];
    [self hidePullIndicator];
}

-(id<ProgressProtocol>)getProgress
{
    if(self.progress==nil)
    {
        self.progress=[[LockScreenProgress alloc]initWith:self.view];
    }
    return self.progress;
}
@end
