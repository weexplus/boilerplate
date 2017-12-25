//
//  SetViewController.m
//  xinao
//
//  Created by 郑江荣 on 2017/8/29.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "SetViewController.h"
#import "WXPage.h"
#import "farwolf.h"
#import "QRControl.h"
#import "Weex.h"
#import "WXDevTool.h"
#import "Config.h"
#import "RefreshManager.h"


@interface SetViewController ()

@end

@implementation SetViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    NSString *s= [self getSaveValue:@"url"];
//    NSString *ip= [s findone:@"http://" end:@":"];
      NSString *ip=[Config debugIp];
//    NSString *s= [self getSaveValue:@"url"];
//            NSString *ip= [s findone:@"http://" end:@":"];

    ip=[Weex getDebugIp];
    self.url.text=s;
    self.debugip.text=[@"debugip=" add:ip];
    if([WXDevTool isDebug])
    {
        [self.debugbtn setTitle:@"关闭debug" forState:UIControlStateNormal];
//        [self.debugbtn setTitle:@"关闭debug" forState:st]
    }
    else
    {
             [self.debugbtn setTitle:@"开启debug" forState:UIControlStateNormal];
    }
//     [WXDevTool setDebug:YES];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/
- (IBAction)qrClick:(id)sender {
    UINavigationController  *nav=[self present:_QRControl anim:true];
    QRControl *vc=nav.childViewControllers[0];
    [nav.navigationBar setHidden:false];
    vc.scanSuccess=^(NSString* s){
        
        [s save:@"url"];
        NSMutableDictionary *dic=[NSMutableDictionary new];
        [dic setValue:s forKey:@"url"];
        NSString *socketport=[self getSocketPort:s];
        if(socketport!=nil)
        {
            [socketport save:@"socketport"];
        }
        [self notifyDict:@"qrrefreshpage" value:dic];
        [self closeClick:nil];
        
        [RefreshManager reload];
       
     
    };
}

-(NSString*)getSocketPort:(NSString*)url
{
  NSMutableDictionary *m=  [self getParam:url];
   NSString *port= [m objectForKey:@"socketport"];
    if(port!=nil)
    {
        return port;
    }
    return nil;
}
-(NSMutableDictionary*)getParam:(NSString*)url
{
    NSMutableDictionary *m=[NSMutableDictionary new];
    if(![url contains:@"?"])
    {
        return m;
    }
    NSMutableArray *a= [url split:@"?"];
    if([a count]<2)
    {
        return m;
    }
    NSString *s= [a objectAtIndex:1];
    NSMutableArray *t=[s split:@"&"];
    for(NSString *ts in t)
    {
        NSMutableArray *ta= [ts split:@"="];
        if([ta count]==2)
        {
            [m setObject:[ta  objectAtIndex:1] forKey:[ta  objectAtIndex:0]];
        }
            
    }
    return m;
    
}
- (IBAction)openDebug:(id)sender {
  
    
    
//    if([WXDevTool isDebug])
//    {
//        [WXDevTool setDebug:false];
//
////        NSMutableDictionary *dic=[NSMutableDictionary new];
////        [dic setValue:[self getSaveValue:@"url"] forKey:@"url"];
////        [self notifyDict:@"refreshpage" value:dic];
//
//        NSString *s= [self getSaveValue:@"url"];
////        NSString *ip= [s findone:@"http://" end:@":"];
//        NSString *ip=[Config debugIp];
//
//
//        NSString *url=[[[[@"ws://" add:ip]add:@":"]add:@"8888"]add:@"/debugProxy/native"];
//
//
//
//        [WXDevTool launchDevToolDebugWithUrl:url];
//
//    }
//    else
//    {
//        NSString *s= [self getSaveValue:@"url"];
//        NSString *ip= [s findone:@"http://" end:@":"];
//        [Weex startDebug:ip port:@"8088"];
//    }
     NSString *ip=[Config debugIp];
     [Weex startDebug:ip port:@"8088"];
     [self closeClick:nil];
    
}
- (IBAction)closeClick:(id)sender {
    
//    [self removeFromParentViewController];
//    [self.view removeFromSuperview];
    [self dismiss:true];
}

-(void)viewWillAppear:(BOOL)animated
{
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}

-(void)viewWillDisappear:(BOOL)animated
{
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleDefault];
}
@end
