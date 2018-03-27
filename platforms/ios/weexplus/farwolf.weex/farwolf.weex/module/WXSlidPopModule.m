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
#import "Page.h"

@implementation WXSlidPopModule
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(show:param:delt:offset:side:))
WX_EXPORT_METHOD(@selector(dismiss))
WX_EXPORT_METHOD(@selector(reset))


-(void)show:(NSString*)url param:(NSDictionary*)param  delt:(CGFloat)delt offset:(NSMutableDictionary*)offset  side:(NSString*)side
{
//    dispatch_sync(dispatch_get_main_queue(), ^(){
    
    [self initBackgroundView];
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

            [self prepare:url param:param delt:delt side:side offset:offset compelete:^{
                [self com:_popView time:0.15 compelete:^{
                    
                }];
            }];
        }
        else
        {
 
            [self com:_popView time:0.15 compelete:^{
                
            }];
        }
     [self regist:@"closeslidpop" method:@selector(close)];
 

    
    
    
}

- (void)initBackgroundView {
    
    if(_backgroundView==nil)
    {
        self.backgroundView = [[UIView alloc] initWithFrame:weexInstance.viewController.view.bounds];
        self.backgroundView.backgroundColor = [UIColor colorWithWhite:0 alpha:0.5];
        
        [_backgroundView addClick:^{
            [self close];
        }];
      
    }
    [_backgroundView removeFromSuperview];
    [weexInstance.viewController.view addSubview:self.backgroundView];
   
}
-(void)close
{
    [self gone:_popView time:0.30];
    
}
    
-(void)dismiss
{
 
       [self notify:@"closeslidpop" value:nil];
}



-(void)prepare:(NSString*)url param:(NSDictionary*)param  delt:(CGFloat)delt side:(NSString*)side  offset:(NSMutableDictionary*)offset compelete:(void(^)())compelete
{
    if(side==nil)
        side=@"left";
    _side=side;
    _delt=[Weex length:delt instance:weexInstance];
   
    [WeexFactory renderNew:[Weex getFinalUrl:url weexInstance:weexInstance] compelete:^(WXNormalViewContrller *vc) {
        
        vc.instance.param=param;
        [self render:vc param:param delt:_delt side:side offset:offset compelete:^(UINavigationController *n) {
            _popView=n.view;
            _nav=n;
            compelete();

        }];
        
    } fail:^(NSString *msg) {
        
    }  frame:[UIApplication sharedApplication].keyWindow.bounds isPortrait:true];
 
}



-(void)render:(WXNormalViewContrller*)vc   param:(NSDictionary*)param delt:(CGFloat)delt side:(NSString*)side offset:(NSMutableDictionary*)offset  compelete:(void(^)(UINavigationController*n))compelete
{
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    
    _vc=vc;
    _vc.freeFrame=true;
    UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
    _popView=nav.view;
    UISwipeGestureRecognizer *ges=  [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(panGestureRecognize:)];
    
    [nav.view addGestureRecognizer:ges];
    _nav=nav;
    _left=[self getOffset:offset key:@"left"];
    _right=[self getOffset:offset key:@"right"];
    _top=[self getOffset:offset key:@"top"];
    _bottom=[self getOffset:offset key:@"bottom"];
    
    if([@"left" isEqualToString:side])
    {
        [_popView setFrame:CGRectMake(-1*delt, _top, delt, h-_top-_bottom)];
        [vc.instance setFrame:CGRectMake(0.0f, 0.0f, delt, h-_top-_bottom)];
        ges.direction=UISwipeGestureRecognizerDirectionLeft;
    }
    else if([@"top" isEqualToString:side])
    {
        [_popView setFrame:CGRectMake(_left, -1*delt, w-_left-_right, delt)];
        [vc.instance setFrame:CGRectMake(0.0f, 0.0f, w-_left-_right, delt)];
        ges.direction=UISwipeGestureRecognizerDirectionUp;
    }
    else if([@"right" isEqualToString:side])
    {
        [_popView setFrame:CGRectMake(w, _top, delt, h-_top-_bottom)];
        [vc.instance setFrame:CGRectMake(0.0f, 0.0f, delt, h-_top-_bottom)];
        ges.direction=UISwipeGestureRecognizerDirectionRight;
        
    }
    else if([@"bottom" isEqualToString:side])
    {
        [_popView setFrame:CGRectMake(_left, h, w-_left-_right, delt)];
        [vc.instance setFrame:CGRectMake(0.0f, 0.0f, w-_left-_right, delt)];
        ges.direction=UISwipeGestureRecognizerDirectionDown;
    }
    [_popView addClick:^{
        
    }];
//    [weexInstance.viewController.view addSubview:_popView];
    [_backgroundView addSubview:_popView];
    [weexInstance.viewController addChildViewController:nav];
    
    
    compelete(nav);
    
}
-(CGFloat)getOffset:(NSMutableDictionary *)d key:(NSString*)key
{
    if(d==nil)
        return 0;
    if([d objectForKey:key]==nil)
    {
        return 0;
    }
    return [@"" add:[d objectForKey:key]].floatValue*self.weexInstance.pixelScaleFactor;
}
-(void)panGestureRecognize:(UISwipeGestureRecognizer*)res
{
    if([@"right" isEqualToString:_side])
    {
        if(res.direction==UISwipeGestureRecognizerDirectionRight) {
            
            
            if(self.nav.childViewControllers.count>1)
            {
                //            [self.nav back:true];
                [self.nav  popViewControllerAnimated:true];
            }
            else
            {
                [self dismiss];
            }
            
        }
    }
    
   else if([@"bottom" isEqualToString:_side])
    {
        if(res.direction==UISwipeGestureRecognizerDirectionDown) {
            
            
            [self dismiss];
            
        }
    }
   else if([@"left" isEqualToString:_side])
   {
       if(res.direction==UISwipeGestureRecognizerDirectionLeft) {
           
           
           if(self.nav.childViewControllers.count>1)
           {
               //            [self.nav back:true];
               [self.nav  popViewControllerAnimated:true];
           }
           else
           {
               [self dismiss];
           }
           
       }
   }

   else if([@"top" isEqualToString:_side])
   {
       if(res.direction==UISwipeGestureRecognizerDirectionUp) {
           
           
           [self dismiss];
           
       }
   }



}

-(void)reset
{
     [_popView removeFromSuperview];
    _popView=nil;
//    NSLog(@"传入的位置是：%@\nView的目标View=(%.2f,%.2f,%.2f,%.2f)", _side, view.frame.origin.x, view.frame.origin.y, view.frame.size.width, view.frame.size.height);
}
- (void)com:(UIView*)view time:(NSTimeInterval)time compelete: (void(^)())compelete {
    
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
    [weexInstance.viewController.view bringSubviewToFront:_popView];
    [UIView animateWithDuration:time animations:^{
        // 设置view弹出来的位置
        if([@"left" isEqualToString:_side])
        {
            
            view.frame=CGRectMake(_left, _top, _delt, h-_top-_bottom);
        }
        else if([@"top" isEqualToString:_side])
        {
            
            view.frame=CGRectMake(_left, _top, w-_left-_right, _delt);
        }
        else if([@"right" isEqualToString:_side])
        {
            view.frame=CGRectMake(w-_delt-_right, _top, _delt, h-_top-_bottom);
        }
        else if([@"bottom" isEqualToString:_side])
        {
            
            view.frame=CGRectMake(_left, h-_delt-_bottom, w-_left-_right, _delt);
        }

        _hasshow=true;
    } completion:^(BOOL finished) {
        compelete();
    }];
    
}


- (void)gone:(UIView*)view time:(NSTimeInterval)time {
    
    CGFloat w=[UIScreen mainScreen].bounds.size.width;
    CGFloat h=[UIScreen mainScreen].bounds.size.height;
     [UIView animateWithDuration:time animations:^{
        if([@"left" isEqualToString:_side])
        {
            
            view.frame=CGRectMake(-1*_delt, _top, _delt, h-_top-_bottom);
        }
        else if([@"top" isEqualToString:_side])
        {
            view.frame=CGRectMake(_left, -1*_delt, w-_left-_right, _delt);
        }
        else if([@"right" isEqualToString:_side])
        {
            
            view.frame=CGRectMake(w, _top, _delt, h-_top-_bottom);
        }
        else if([@"bottom" isEqualToString:_side])
        {
            view.frame=CGRectMake(_left,h, w-_left-_right, _delt);
        }
        _hasshow=false;
    } completion:^(BOOL finished) {
        
        [_backgroundView removeFromSuperview];
        
    }];
    
    
    
}


@end
