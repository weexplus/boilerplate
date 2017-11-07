//
//  CollectionControl.m
//  vshop
//
//  Created by 郑江荣 on 2017/5/25.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "CollectionControl.h"
#import "SplashControl.h"
@interface CollectionControl ()

@end

@implementation CollectionControl

- (void)viewDidLoad {
      self.page=[SplashControl getPage:@"关注"];
    [super viewDidLoad];
//     self.sourceURL = [[NSBundle mainBundle] URLForResource:@"app/recycler" withExtension:@"js"];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)loadCompelete
{
    [self notify:@"tabrender" value:nil];
    
}

-(void)viewWillAppear:(BOOL)animated
{
    self.navbarVisibility =@"hidden";
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}
-(UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleLightContent;
}
//-(void)viewWillAppear:(BOOL)animated
//{
//    [super viewWillAppear:animated];
//    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
//    [self.navigationController.navigationBar setTranslucent:false];
//}
//
//-(void)viewWillDisappear:(BOOL)animated
//{
//    [super viewWillDisappear:animated];
//    [self.navigationController.navigationBar setTranslucent:true];
//}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
