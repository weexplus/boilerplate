//
//  TestViewController.m
//  movie
//
//  Created by 郑江荣 on 2017/11/1.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "TestViewController.h"

@interface TestViewController ()

@end

@implementation TestViewController

- (void)viewDidLoad {
    [super viewDidLoad];
     [self.navigationController.navigationBar setHidden:false];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)viewWillAppear:(BOOL)animated
{
    [self.navigationController.navigationBar setHidden:false];
    [self.navigationController.navigationBar setBarTintColor:[UIColor redColor]];
    //创建一个高20的假状态栏
    UIView *statusBarView = [[UIView alloc] initWithFrame:CGRectMake(0, -20, self.view.bounds.size.width, 20)];
    //设置成绿色
    statusBarView.backgroundColor=[UIColor redColor];
    // 添加到 navigationBar 上
    [self.navigationController.navigationBar addSubview:statusBarView];
}

-(void)viewDidAppear:(BOOL)animated
{
    [self.navigationController.navigationBar setHidden:false];
    [self.navigationController.navigationBar setBarTintColor:[UIColor redColor]];
    //创建一个高20的假状态栏
    UIView *statusBarView = [[UIView alloc] initWithFrame:CGRectMake(0, -20, self.view.bounds.size.width, 20)];
    //设置成绿色
    statusBarView.backgroundColor=[UIColor redColor];
    // 添加到 navigationBar 上
    [self.navigationController.navigationBar addSubview:statusBarView];
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
