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
#import "WXDebugger.h"
#import "WXDevTool.h"


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

   UINavigationController *nav=  (UINavigationController*)self.parentViewController;
   int k= [nav childViewControllers].count;
    
    ip=[Weex getDebugIp];
    self.url.text=_vc.sourceURL.absoluteString;
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

    [self updateCache];
//     [WXDevTool setDebug:YES];
    // Do any additional setup after loading the view.
}

-(void)updateCache
{
    float tmpSize =[[SDImageCache sharedImageCache] getSize]/1024/1024;
    NSString *ca=[[@"清除缓存(" addFloat:tmpSize] add:@"m)"];
    [self.cachebtn setTitle:ca forState:UIControlStateNormal];
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
        
//        if([s startWith:@"app"])
//        {
//            NSURL *nl=  [[NSBundle mainBundle] URLForResource:[s replace:@".js" withString:@""]  withExtension:@"js"];
//            [Weex setBaseUrl:nl.absoluteString];
//        }
//        else
//            [Weex setBaseUrl:s];
        [s save:@"url"];
        NSMutableDictionary *dic=[NSMutableDictionary new];
        [dic setValue:s forKey:@"url"];
        NSString *socketport=[self getSocketPort:s];
        if(socketport!=nil)
        {
            [socketport save:@"socketport"];
        }
        [self notifyDict:@"qrrefreshpage" value:dic];
        
        [self dismissViewControllerAnimated:true completion:nil];
        
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

     if(![WXDevTool isDebug])
     {
         NSString *ip=[Weex getDebugIp];
         [Weex startDebug:ip port:@"8088"];
          [self dismissViewControllerAnimated:true completion:nil];
 
         
     }
    else
    {
        [WXDevTool setDebug:false];
        [WXDebugger  setEnabled:false];
        [[WXDebugger defaultInstance] disconnect ];
        [WXSDKEngine restart];
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(200 * NSEC_PER_MSEC)), dispatch_get_main_queue(), ^{
            [self notify:@"RefreshInstance" value:nil];
        });
        [[Weex getDebugScocket] close];
        [WXDevTool setDebug:NO];
            [self dismissViewControllerAnimated:true completion:nil];
    }

    

 
        
        
 
    
}

- (IBAction)loadDefault:(id)sender {
    
     [self dismiss:true];
    [@"" save:@"url"];
    [self notify:@"loaddefault" value:nil];
 
    
}




- (IBAction)closeClick:(id)sender {
    
//    [self removeFromParentViewController];
//    [self.view removeFromSuperview];
    [self dismiss:true];
}
- (IBAction)clearCache:(id)sender {
    [[SDImageCache sharedImageCache] clearDisk];
    [self updateCache];
    

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
