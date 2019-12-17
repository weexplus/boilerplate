//
//  QRControl.m
//  hotjob
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "QRControl.h"
#import "CCQrCode.h"
#import "farwolf.h"
@interface QRControl ()

@end

@implementation QRControl

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    CCQrCode *code = [[CCQrCode alloc] initWithFrame:self.view.bounds];
    [self.view addSubview:code];
    __weak typeof (self) weakself=self;
    [code startReading:^(AVCaptureOutput *captureOutput, NSArray *metadataObjects, AVCaptureConnection *connection, AVMetadataMachineReadableCodeObject *metadataObj, NSString *stringValue) {
        
        dispatch_async(dispatch_get_main_queue(), ^
        {
              NSLog(@"%@",stringValue);
                     [code stopReading];
             //        [self dismiss:true];
             //        [self.TopViewController dismiss:true completion:nil];
                    
                     [weakself.TopViewController dismiss:true completion:^{
                         if(weakself.scanSuccess!=nil)
                         {
                             weakself.scanSuccess(stringValue);
                         }
                     }];
        });
        
     
    }];
    
    if(_color==nil)
        _color=@"#ffffff";
    if(_bgcolor==nil)
        _bgcolor=@"#000000";
    
    UINavigationBar *bar= self.navigationController.navigationBar;
    [bar setBarTintColor:[self.bgcolor toColor]];
    [bar setTitleTextAttributes:@{NSForegroundColorAttributeName : [_color toColor]}];
    if([@"black" isEqualToString:_statusbarcolor])
    {
        [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleDefault];
    }
    else
    {
        [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
    }
 
  
}

-(void)viewDidAppear:(BOOL)animated
{
    
 
}



- (IBAction)backClick:(id)sender {
    [self dismiss:true];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
