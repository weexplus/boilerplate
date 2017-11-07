//
//  WXSlidPopModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/9/21.
//
//

#import "WXSlidPopModule.h"
#import "WXNormalViewContrller.h"
#import "WeexFactory.h"
#import "Weex.h"

@implementation WXSlidPopModule
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(show:param:delt:side:))
WX_EXPORT_METHOD(@selector(dismiss))

-(void)show:(NSString*)url param:(NSDictionary*)param  delt:(CGFloat)delt side:(NSString*)side
{
     if(_url==nil||![_url isEqualToString:url])
     {
         [_nav removeFromParentViewController];
         [_popView removeFromSuperview];
         _url=url;
         _popView=nil;
         _vc=nil;
    
     }
    if(_popView==nil)
    {
        [self prepare:url param:param delt:delt side:side compelete:^{
            [self com];
        }];
    }
    else
    {
         [self com];
    }
    
    
    
}

-(void)dismiss
{
    [self gone];
}



-(void)prepare:(NSString*)url param:(NSDictionary*)param  delt:(CGFloat)delt side:(NSString*)side  compelete:(void(^)())compelete
{
    
    if(side==nil)
        side=@"left";
    _side=side;
    _delt=delt;
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl]];
    }
    NSString *newURL = url;
    if ([url hasPrefix:@"//"]) {
        newURL = [NSString stringWithFormat:@"http:%@", url];
    } else if (![url hasPrefix:@"http"]) {
        newURL = [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL].absoluteString;
    }
    
    
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    
    [WeexFactory render:[NSURL URLWithString:newURL] compelete:^(Page *p) {
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:[NSURL URLWithString:url]];
        vc.navbarVisibility=@"hidden";
        vc.hidesBottomBarWhenPushed = YES;
        vc.page=p;
        vc.instance=p.instance;
        vc.param=param;
        _vc=vc;
        _vc.freeFrame=true;
        UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
          _popView=nav.view;
        _nav=nav;
        if([@"left" isEqualToString:side])
        {
        
             [_popView setFrame:CGRectMake(-1*delt, 0, delt, h)];
             [vc.instance setFrame:CGRectMake(0.0f, 0.0f, delt, h)];
        }
        else if([@"top" isEqualToString:side])
        {
            [_popView setFrame:CGRectMake(0, -1*delt, w, delt)];
            [vc.instance setFrame:CGRectMake(0.0f, 0.0f, w, delt)];
        }
        else if([@"right" isEqualToString:side])
        {
            [_popView setFrame:CGRectMake(w, 0, delt, h)];
            [vc.instance setFrame:CGRectMake(0.0f, 0.0f, delt, h)];
 
        }
        else if([@"bottom" isEqualToString:side])
        {
            [_popView setFrame:CGRectMake(0, h, w, delt)];
            [vc.instance setFrame:CGRectMake(0.0f, 0.0f, w, delt)];
        }
      
        [weexInstance.viewController.view addSubview:_popView];
        [weexInstance.viewController addChildViewController:nav];
        compelete();
        
    }];

}




- (void)com {
    
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    [weexInstance.viewController.view bringSubviewToFront:_popView];
    [UIView animateWithDuration:0.15 animations:^{
        // 设置view弹出来的位置
        if([@"left" isEqualToString:_side])
        {

             _popView.frame=CGRectMake(0, 0, _delt, h);
        }
        else if([@"top" isEqualToString:_side])
        {

             _popView.frame=CGRectMake(0, 0, w, _delt);
        }
        else if([@"right" isEqualToString:_side])
        {
            _popView.frame=CGRectMake(w-_delt, 0, _delt, h);
        }
        else if([@"bottom" isEqualToString:_side])
        {

             _popView.frame=CGRectMake(0, h-_delt, w, _delt);
        }
        _hasshow=true;
        
    }];
   
    
    
    
}
- (IBAction)btnClick:(id)sender {
    //     [self com];
    
    if(!_hasshow)
    {
        [self com];
    }
    else
    {
        [self gone];
    }
    _hasshow=!_hasshow;
}


- (void)gone {
    
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    [UIView animateWithDuration:0.30 animations:^{
        // 设置view弹出来的位置
        
        
        if([@"left" isEqualToString:_side])
        {
            
              _popView.frame=CGRectMake(-1*_delt, 0, _delt, h);
        }
        else if([@"top" isEqualToString:_side])
        {
             _popView.frame=CGRectMake(0, -1*_delt, w, _delt);
        }
        else if([@"right" isEqualToString:_side])
        {

             _popView.frame=CGRectMake(w, 0, _delt, h);
        }
        else if([@"bottom" isEqualToString:_side])
        {
             _popView.frame=CGRectMake(0,h, w, _delt);
        }
        _hasshow=false;
    }];
    
    
    
}


@end
