//
//  ViewController.m
//  vshop
//
//  Created by 郑江荣 on 2017/5/18.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "ViewController.h"
#import "SplashControl.h"
@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
 
//    [self openQR];
 
//     NSString *s=@"http://127.0.0.1:9898/argc/demo/util/net.js";
//    NSString *s=@"http://127.0.0.1:9898/mv/demo/demo/addressBook.js";
    
    
      NSString *s=@"http://127.0.0.1:9898/mv/demo/demo/looper.js";
//     self.page=[SplashControl getPage:@"主页"];
   
    self.sourceURL=[NSURL URLWithString:s];
    self.sourceURL = [[NSBundle mainBundle] URLForResource:@"app/demo/demo/net" withExtension:@"js"];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
    [super viewDidLoad];
    [[UIApplication sharedApplication]setStatusBarHidden:false];
    // Do any additional setup after loading the view, typically from a nib.
}


-(UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleLightContent;
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)viewWillAppear:(BOOL)animated
{
    self.navbarVisibility =@"hidden";
//     self.navbarVisibility=@"transparent";
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}
@end
