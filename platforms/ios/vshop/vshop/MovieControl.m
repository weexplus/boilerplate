//
//  MovieControl.m
//  vshop
//
//  Created by 郑江荣 on 2017/7/16.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "MovieControl.h"
#import "SplashControl.h"
@interface MovieControl ()

@end

@implementation MovieControl

- (void)viewDidLoad {
    self.page=[SplashControl getPage:@"电影"];
    [super viewDidLoad];
    
    //    self.sourceURL = [[NSBundle mainBundle] URLForResource:@"app/wechat" withExtension:@"js"];
    // Do any additional setup after loading the view.
}
-(void)loadCompelete
{
    [self notify:@"tabrender" value:nil];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)viewWillAppear:(BOOL)animated
{
    self.navbarVisibility =@"hidden";
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
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
