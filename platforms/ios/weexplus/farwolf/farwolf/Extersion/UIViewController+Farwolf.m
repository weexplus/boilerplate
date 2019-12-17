//
//  UIViewController+Farwolf.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "UIViewController+Farwolf.h"
#import "NSString+Farwolf.h"
#import "Toast.h"
#import "UIView+Farwolf.h"
#import <CoreLocation/CoreLocation.h>
#import <Photos/Photos.h>
#import "AppSysInfo.h"
#import<AssetsLibrary/AssetsLibrary.h>
@import CoreTelephony;
@implementation UIViewController (Farwolf)


-(CGFloat)statusBarHeight
{
    CGRect status = [[UIApplication sharedApplication] statusBarFrame];
    
    return status.size.height;
}

-(CGFloat)navBarHeight
{
    CGRect nav = self.navigationController.navigationBar.frame;
    return nav.size.height;
}

-(CGFloat)titleHeight
{
    
    return  [self statusBarHeight]+[self navBarHeight];
}


-(void)closeKeyBoardOntouch
{
    UITapGestureRecognizer *tapGr = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(viewTapped:)];
    tapGr.cancelsTouchesInView = NO;
    [self.view addGestureRecognizer:tapGr];
}

-(void)viewTapped:(UITapGestureRecognizer*)tapGr
{
    [self closeKeyboard];
}



-(UIView*)fromXib:(NSString*)name
{
    
    NSArray *n=  [[NSBundle mainBundle]loadNibNamed:name owner:self options:nil];
    return (UIView*)n[0];
}

-(void) makeNavBarTransparent
{
    
    [self.navigationController.navigationBar setBackgroundImage:[UIImage new] forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setBackgroundColor: [UIColor clearColor]];
    self.navigationController.navigationBar.layer.masksToBounds = NO;
    self.navigationController.navigationBar.shadowImage = [UIImage new];
}

-(BOOL)checkLocationRights
{
    CLAuthorizationStatus status = [CLLocationManager authorizationStatus];    
    
    if (kCLAuthorizationStatusDenied == status || kCLAuthorizationStatusRestricted == status)
    {
        
         [self gotoSet:  [[ @"无法获取当前城市，请允许"add: [AppSysInfo appName]] add:@"你的位置！"]];
          return false;
    }
    return true;
}

-(BOOL)checkNetRights
{
    
    CTCellularData *cellularData = [[CTCellularData alloc]init];
    CTCellularDataRestrictedState state = cellularData.restrictedState;
    if(state==kCTCellularDataRestricted||state==PHAuthorizationStatusDenied)
    {
        
         [self gotoSet:  [[ @"无法访问网络，请允许"add: [AppSysInfo appName]] add:@"访问网络！"]];
         return false;
    }
    return true;
    
    
}
-(BOOL)checkPhotoRights
{
    
    
    PHAuthorizationStatus photoAuthorStatus = [PHPhotoLibrary authorizationStatus];
    if(photoAuthorStatus==PHAuthorizationStatusDenied||photoAuthorStatus==PHAuthorizationStatusRestricted)
    {
         [self gotoSet:  [[ @"无法访问相册，请允许"add: [AppSysInfo appName]] add:@"访问相册！"]];
        return false;
    }
     return true;
}

-(BOOL)checkCameraRights
{
    AVAuthorizationStatus AVstatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];//相机权限
    
    if(AVstatus==AVAuthorizationStatusDenied||AVstatus==AVAuthorizationStatusRestricted)
    {
        [self gotoSet:  [[ @"无法访问相机，请允许"add: [AppSysInfo appName]] add:@"访问相机！"]];
         return false;
    }
    return true;
    
}
-(void)gotoSet:(NSString*)s
{
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示" message:s preferredStyle: UIAlertControllerStyleAlert];
    
    UIAlertAction *actionCancel = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel
                                                         handler:^(UIAlertAction *action) {
                                                             [alert dismiss:true];
                                                         }];
    UIAlertAction *actionok = [UIAlertAction actionWithTitle:@"去设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
        
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
        
    }];
    
    
    
    [alert addAction:actionok];
    [alert addAction:actionCancel];
    
    [self presentViewController:alert animated:YES completion:^{
        
    }];
}


-(UIViewController*)push:(NSString*)Id anim:(BOOL) anim
{
    UIViewController *vc= [self fromStoryBoard:Id];
    [self.navigationController pushViewController:vc animated:anim];
    return vc;
    
}


-(UIViewController*)present:(NSString*)Id anim:(BOOL) anim
{
    UIViewController *vc= [self fromStoryBoard:Id];
    vc.modalPresentationStyle = UIModalPresentationFullScreen;
    [self presentViewController:vc animated:anim completion:^{
        
    }];
   
    
    return vc;
    
}


-(CGFloat) screen
{
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    return h;
}


-(CGFloat) screenHeight
{
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    return h;
}

-(CGFloat) screenWidth
{
    CGFloat h=[UIScreen mainScreen].bounds.size.width;
    return h;
}

-(UIViewController*)push:(NSString*)Id
{
    return [self push:Id anim:true] ;
}


-(void)pushVc:(UIViewController*)vc animated:(BOOL)animated
{
    
    [self.navigationController pushViewController:vc animated:animated];
    
}

-(void)popVc:(UIViewController*)vc animated:(BOOL)animated
{
    
    [self.navigationController popToViewController:vc animated:animated];
    
}

-(void)pushVc:(UIViewController*)vc
{
    
    [self pushVc:vc animated:true];
}
-(void)pop:(BOOL)animated
{
    [self.navigationController popViewControllerAnimated:animated];
}

-(void)pop
{
    [self pop:true];
}

-(void)setBackBar:(NSString*)txt color:(NSString*)color
{
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] init];
    if(txt==nil)
    {
        txt=@"返回";
    }
    if(color==nil)
    {
        color=@"ffffff";
    }
    backItem.title = txt;
    self.navigationController.navigationBar.tintColor = [color toColor];
    
    self.navigationItem.backBarButtonItem = backItem;
}

-(void)popVc:(Class)c
{
    
    UIViewController *vc=  [self getVc:c];
    [self popVc:vc animated:true];
    
}
-(void)closeKeyboard
{
    NSArray *n=  [self.view findAllViewByType:[UITextField class]];
    for(UITextField *t in n)
    {
        [t resignFirstResponder];
    }
    
    n=  [self.view findAllViewByType:[UITextView class]];
    for(UITextView *t in n)
    {
        [t resignFirstResponder];
    }
}

-(UIViewController*)getVc:(Class)c
{
    NSArray *n=self.navigationController.viewControllers;
    for (UIViewController* vc in n) {
        if([vc isKindOfClass:c])
        {
            return vc;
        }
    }
    return  nil;
}

-(UIViewController*)fromStoryBoard:(NSString *)name Id:(NSString *)Id
{
    UIStoryboard *u=[UIStoryboard storyboardWithName:name bundle:nil];
    return [u instantiateViewControllerWithIdentifier:Id];
}

- (UIViewController *)TopViewController {
    UIViewController *resultVC;
    resultVC = [self _topViewController:[[[UIApplication sharedApplication].delegate window] rootViewController]];
    while (resultVC.presentedViewController) {
        resultVC = [self _topViewController:resultVC.presentedViewController];
    }
    return resultVC;
}

- (UIViewController *)_topViewController:(UIViewController *)vc {
    if ([vc isKindOfClass:[UINavigationController class]]) {
        return [self _topViewController:[(UINavigationController *)vc topViewController]];
    } else if ([vc isKindOfClass:[UITabBarController class]]) {
        return [self _topViewController:[(UITabBarController *)vc selectedViewController]];
    } else {
        return vc;
    }
    return nil;
}


-(UIViewController*)fromStoryBoard:(NSString *)Id
{
    NSArray *n= [Id split:@"/" ];
    return [self fromStoryBoard:n[0] Id:n[1]];
}

-(void)addVc:(UIViewController *)vc
{
    [self addChildViewController:vc];
    [self.view addSubview:vc.view];
    [vc didMoveToParentViewController:self];
}

-(void)remove
{
    [self.view removeFromSuperview];
    [self removeFromParentViewController];
}

-(void)show:(UIViewController*)vc
{
    [vc remove];
    [self addVc:vc];
}


-(void)navigate:(UIViewController*)viewControllerToPresent:(BOOL*) animated competion:(void (^ __nullable)(void))completion
{
    [self presentViewController:viewControllerToPresent animated:animated completion:completion ];
    
}


-(void)back:(BOOL)flag
{
    [self.navigationController popViewControllerAnimated:flag];
}
-(void)dismiss:(BOOL)flag
{
    [self dismissViewControllerAnimated:flag completion:nil];
}
-(void)dismiss:(BOOL)flag completion:(void (^ __nullable)(void))completion
{
    [self dismissViewControllerAnimated:flag completion:completion];
}

-(void)toast:(NSString*)msg
{
    [[Toast create:[UIApplication sharedApplication].keyWindow] toast:msg];
}



@end
