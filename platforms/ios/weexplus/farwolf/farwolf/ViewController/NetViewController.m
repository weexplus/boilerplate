//
//  NetViewController.m
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "NetViewController.h"
#import "UIViewController+Farwolf.h"
#import "Screen.h"
@interface NetViewController ()

@end

@implementation NetViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    CGFloat s=[self screenHeight];
    if(s==SCREEN3_5)
    {
        [self screen35];
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
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)screen35
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
-(void)screenUnknow:(double)height
{
    
}




-(void)start
{
    [[self getProgress] show];
}


-(void)success:(Json*)j
{
    
}



-(void)fail:(NSInteger)errcode errmsg:(NSString *)errmsg json:(Json *)j
{
     [self toast:errmsg];
}


-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    
    
}

-(void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
   
}

-(void)exception
{
    [self toast:@"网络异常"];
}

-(void)compelete
{
    [[self getProgress] hide];
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
