//
//  LanscapeViewContoller.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/3/2.
//

#import "LanscapeViewContoller.h"

@interface LanscapeViewContoller ()

@end

@implementation LanscapeViewContoller

- (void)viewDidLoad {
    [super viewDidLoad];
     [self interfaceOrientation:UIInterfaceOrientationLandscapeRight];
//    [self orientationChange:true];
    // Do any additional setup after loading the view.
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
 
-(void)viewWillDisappear:(BOOL)animated{
    [self interfaceOrientation:UIInterfaceOrientationPortrait];
}



//支持旋转
-(BOOL)shouldAutorotate{
    return YES;
}
//
//支持的方向
- (UIInterfaceOrientationMask)supportedInterfaceOrientations {
    return UIInterfaceOrientationMaskLandscapeLeft;
}

//一开始的方向  很重要
-(UIInterfaceOrientation)preferredInterfaceOrientationForPresentation{
    return UIInterfaceOrientationLandscapeLeft;
}



- (void)interfaceOrientation:(UIInterfaceOrientation)orientation
{
    if ([[UIDevice currentDevice] respondsToSelector:@selector(setOrientation:)]) {
        SEL selector             = NSSelectorFromString(@"setOrientation:");
        NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:[UIDevice instanceMethodSignatureForSelector:selector]];
        [invocation setSelector:selector];
        [invocation setTarget:[UIDevice currentDevice]];
        int val                  = orientation;
        [invocation setArgument:&val atIndex:2];
        [invocation invoke];
    }
}

 

@end
