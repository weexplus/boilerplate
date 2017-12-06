//
//  TabController.m
//  vshop
//
//  Created by 郑江荣 on 2017/5/24.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "TabController.h"
#import "WXNormalViewContrller.h"
#import "TabSelector.h"
#import "WeexFactory.h"
#import "farwolf.h"
#import "WXStaticModule.h"
    static UIViewController *lastController = nil;
@interface TabController ()<UITabBarControllerDelegate,UINavigationControllerDelegate>

@end

@implementation TabController

- (void)viewDidLoad {
    [super viewDidLoad];
    UIWindow *window= [UIApplication sharedApplication].keyWindow;
      _splash=[[UIImageView alloc]initWithFrame:CGRectMake(0, 0, 375, 667)];
    [window addSubview:_splash];
    [_splash setImage:[UIImage imageNamed:@"splash1"]];
    _splash.contentMode=UIViewContentModeScaleAspectFill;
       self.edgesForExtendedLayout = UIRectEdgeNone;
//    [self setSelectedIndex:1];
    
//    [self load];
     [self setDelegate:self];
    for(UIViewController *n in self.viewControllers)
    {
//        UIViewController *vb= n.childViewControllers[0];
//         [vb viewDidLoad];
         [n viewDidLoad];
//         n.delegate=self;
//         [vb loadView];
 
    }
    [self loadTabBar];
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    [self.tabBar setShadowImage:[self imageWithColor:[@"#1296db" toColor] size:CGSizeMake(w,0.5)]];
    [[UIApplication sharedApplication]setStatusBarHidden:false];;
    [self regist:@"tabselect" method:@selector(tabselect:)];
    [self regist:@"tabrender" method:@selector(tabrender)];
//     _splash.layer.opacity=0;

}



-(void)viewWillAppear:(BOOL)animated
{
    for(UIViewController *n in self.viewControllers)
    {
        if([self.viewControllers indexOfObject:n]==self.selectedIndex)
        {
            [n viewWillAppear:animated];
        }
        
    }
     [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
}



- (void)navigationController:(UINavigationController *)navigationController willShowViewController:(UIViewController *)viewController animated:(BOOL)animated
{
    //每次当navigation中的界面切换，设为空。本次赋值只在程序初始化时执行一次

    
    //若上个view不为空
    if (lastController != nil)
    {
        //若该实例实现了viewWillDisappear方法，则调用
        if ([lastController respondsToSelector:@selector(viewWillDisappear:)])
        {
            [lastController viewWillDisappear:animated];
        }
    }
    
    //将当前要显示的view设置为lastController，在下次view切换调用本方法时，会执行viewWillDisappear
    lastController = viewController;
    
    [viewController viewWillAppear:animated];
}


-(void)tabrender
{
    self.loadcount++;
    if(self.loadcount>=self.viewControllers.count)
    {
        if(_splash!=nil)
        {
            [UIView animateWithDuration:0.45 animations:^{
                // 设置view弹出来的位置
            
                _splash.layer.opacity=0;
            }];
        }
       
    }
}
-(void)tabselect:(NSNotification*)n
{
   NSString *name=  n.userInfo[@"name"];
    
    if([@"推荐" isEqualToString:name])
    {
         [self setSelectedIndex:0];
    }
    else if([@"电视剧" isEqualToString:name])
    {
         [self setSelectedIndex:1];
         self.selectedViewController=self.viewControllers[1];
       
    }
    else if([@"电影" isEqualToString:name])
    {
         [self setSelectedIndex:2];
    }

    else if([@"关注" isEqualToString:name])
    {
         [self setSelectedIndex:3];
    }
    else if([@"我的" isEqualToString:name])
    {
         [self setSelectedIndex:4];
    }
    
    


}

-(void)setSelectedIndex:(NSUInteger)selectedIndex
{
    [super setSelectedIndex:selectedIndex];
//    [self.tabBar setSelectedItem:self.tabBar.items[selectedIndex]];
}

-(UIImage *)imageWithColor:(UIColor *)color size:(CGSize)size {
    if (!color || size.width <=0 || size.height <=0) return nil;
    CGRect rect = CGRectMake(0.0f, 0.0f, size.width, size.height);
    UIGraphicsBeginImageContextWithOptions(rect.size,NO, 0);
    CGContextRef context =UIGraphicsGetCurrentContext();
    CGContextSetFillColorWithColor(context, color.CGColor);
    CGContextFillRect(context, rect);
    UIImage *image =UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return image;
}


-(UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleLightContent;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}




-(UINavigationController*)createSub:(NSString*)url title:(NSString*)title img:(NSString*)img select:(NSString*)selectimg
{
    
    WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL: [[NSBundle mainBundle] URLForResource:url withExtension:@"js"]];
    vc.title=title;
    vc.tabBarItem.title=title;
    vc.tabBarItem.image=[UIImage imageNamed:img];
    vc.tabBarItem.selectedImage=[UIImage imageNamed:selectimg];
    vc.hidesBottomBarWhenPushed=false;
   
    UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
     [vc viewDidLoad];
    return nav;
}

-(void)loadTabBar
{
    //    UITabBarController *tabc=(UITabBarController*)self.window.rootViewController;
    NSArray *n=self.tabBar.items ;
  
    self.tabBar.tintColor=[@"#1296db" toColor];
    for (UITabBarItem *i in n) {
        
        TabSelector *ts=[self getSelector:i.title];
        UIImage *un=[[UIImage imageNamed:ts.unselect] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
        
        
        UIImage *sel=[[UIImage imageNamed:ts.select]imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
        
        i.image=un;
        i.selectedImage=sel;
        
        
        
        
        CGRect rect = [[UIScreen mainScreen] bounds];
        CGSize size = rect.size;
        CGFloat width = size.width;
        CGFloat height = size.height;
        CGFloat scale_screen = [UIScreen mainScreen].scale;
        NSLog(@"print %f,%f",width*scale_screen,height*scale_screen);
        
        
    }
    
    
    
    
    
    
    
}

- (BOOL)tabBarController:(UITabBarController *)tabBarController shouldSelectViewController:(UIViewController *)viewController NS_AVAILABLE_IOS(3_0)
{
    
 
    NSInteger index = [tabBarController.viewControllers indexOfObject:viewController];
    lastController=viewController;
 
    if(index==3)
    {
        NSArray *dex=@[@"推荐",@"电视剧",@"电影",@"关注"];
        WXStaticModule *n=[WXStaticModule new];
        if([n get:@"user"]!=nil)
        {
            return YES;
        }
        else{
        
            NSURL *url= [[NSBundle mainBundle] URLForResource:@"app/busi/account/login" withExtension:@"js"];;
            [WeexFactory render:url compelete:^(Page *p) {
                WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
                vc.hidesBottomBarWhenPushed = YES;
                vc.navbarVisibility=@"visiable";
                vc.page=p;
                UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
                [self presentViewController:nav animated:false completion:^{
                    
                }];
                
            }];
            
            return  NO;
        }
        
        
        
        
    }
    else
    {
        return YES;
    }
    
    
}










-(TabSelector*)getSelector:(NSString*)t
{
    TabSelector *ts=[TabSelector alloc];
    
    if([t isEqual:@"推荐"])
    {
        [ts initWithImg:@"menu_home_30" select:@"menu_home_30o"];
    }
    else if([t isEqual:@"电视剧"])
    {
        [ts initWithImg:@"serial_u" select:@"serial"];
    }
    else if([t isEqual:@"电影"])
    {
        [ts initWithImg:@"mv_un_30" select:@"mv_30"];
    }
    else if([t isEqual:@"关注"])
    {
        [ts initWithImg:@"co_u" select:@"co"];
    }
    else if([t isEqual:@"我的"])
    {
        [ts initWithImg:@"menu_my_30" select:@"menu_my_30o"];
    }
    
    return ts;
}



@end
