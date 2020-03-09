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
#import "WeexPlus.h"
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
//    [WeexPlus addDebugBtn];
  
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
    NSURL *url=url=[Weex toURL:self.url];
    

    if([Config isDebug])
    {
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
        vc.debug=[Config isDebug];
        vc.isLanscape=![Config isPortrait];
        UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
         nav.modalPresentationStyle=UIModalPresentationFullScreen;
        [self presentViewController:nav animated:false completion:^{
            
        }];
        return;
    }
    
    
    __weak typeof (self)weakSlef=self;
    [WeexFactory renderNew:url compelete:^(WXNormalViewContrller *vc) {
        [vc.view setHidden:true];
        [weakSlef addVc:vc];
    } fail:^(NSString *msg) {
        [weakSlef toast:@"启动异常"];
    }  frame:[UIApplication sharedApplication].keyWindow.frame isPortrait:isPortrait];
    
    

}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
