//
//  EnteryControl.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/11/30.
//

#import "EntryControl.h"
#import "farwolf.h"
#import "Masonry.h"
#import "WeexFactory.h"
#import "Config.h"
@interface EntryControl ()

@end

@implementation EntryControl

-(id)initWithImage:(NSString*)url img:(NSString*)img
{
    self=[super init];
    self.url=url;
    self.img=img;
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initView];
    [self gotoMain];
  
}
-(void)initView
{
    
    UIImageView *img=[UIImageView new];
    UIImage *mg=[UIImage imageNamed:self.img];
    img.contentMode=UIViewContentModeScaleAspectFill;
    img.image=mg;
    [self.view addSubview:img];
    [img mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
        make.top.equalTo(self.view);
        make.bottom.equalTo(self.view);
    }];
   
}



-(void)viewWillAppear:(BOOL)animated
{
    [self.navigationController.navigationBar setHidden:true];
    [[UIApplication sharedApplication] setStatusBarHidden:false];
//    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}


-(void)gotoMain
{
    
    BOOL isPortrait=[Config isPortrait];
    NSURL *url=[NSURL URLWithString:self.url];
    if([self.url startWith:@"http"])
    {
        url=[NSURL URLWithString:self.url];
       
    }
    else
    {
        
//        url= [[NSBundle mainBundle] URLForResource:[self.url replace:@".js" withString:@""]  withExtension:@"js"];
//        url=[URL loadFromDisk:self.url];
          url=[URL loadLocal:self.url];
        int k=0;
    }

    if([Config isDebug])
    {
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
        vc.debug=[Config isDebug];
        vc.isLanscape=![Config isPortrait];
        UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
        [self presentViewController:nav animated:false completion:^{
            
        }];
        return;
    }
    if([Config preload].count>0)
    {
        [WeexFactory preRenderAll:[Config preload] compelete:^{
            
            [WeexFactory renderNew:url compelete:^(WXNormalViewContrller *vc) {
                
//                vc.debug=[Config isDebug];
//                vc.isLanscape=![Config isPortrait];
//                UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
//                [self presentViewController:nav animated:false completion:^{
//
//                }];
                [vc.view setHidden:true];
                [self addVc:vc];
//                 [vc.instance fireGlobalEvent:@"onPageInit" params:vc.param];
                
            } fail:^(NSString *msg) {
            
                  [self failGo];
            }  frame:[UIApplication sharedApplication].keyWindow.frame isPortrait:isPortrait];
            
        } fail:^(NSString *s) {
            
//            [WeexFactory renderNew:url compelete:^(WXNormalViewContrller *vc) {
//
//                vc.debug=[Config isDebug];
//                vc.isLanscape=![Config isPortrait];
//                UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
//                [self presentViewController:nav animated:false completion:^{
//
//                }];
//
//            } fail:^(NSString *msg) {
//                  [self failGo];
//            }  frame:[UIApplication sharedApplication].keyWindow.frame isPortrait:isPortrait];
            
        }];
    }
    else
    {
        [WeexFactory renderNew:url compelete:^(WXNormalViewContrller *vc) {
            
             vc.debug=[Config isDebug];
            vc.isLanscape=![Config isPortrait];
            UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
            [self presentViewController:nav animated:false completion:^{
                
            }];
            
        } fail:^(NSString *msg) {
            [self failGo];
        }  frame:[UIApplication sharedApplication].keyWindow.frame isPortrait:isPortrait];
    }
    
    

}

-(void)failGo
{
    WXNormalViewContrller *vc=[WXNormalViewContrller new];
    vc.debug=[Config isDebug];
    vc.sourceURL=[NSURL URLWithString:[Config entry]];
    UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
//    [self.navigationController pushViewController:vc animated:true];
    [self presentViewController:nav animated:false completion:^{

    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
