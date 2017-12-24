//
//  ViewPager.m
//  oa
//
//  Created by 郑江荣 on 16/6/8.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "ViewPager.h"
#import "farwolf.h"

@implementation ViewPager



-(void)awakeFromNib
{
//    [self load];
}


+(ViewPager*)create
{
    
    NSArray *n=  [[NSBundle mainBundle]loadNibNamed:@"ViewPager" owner:[UIApplication sharedApplication] options:nil];
    return (UIView*)n[0];
//    [[self getCurrentVc]fromXib:_ViewPager];
}

-(void)load
{
    self.width.constant=self.screenWidth*self.data.count;
    [self.layout equalDivideHorizontally:self.data padding:0];
    self.scrollview.delegate=self;
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    NSInteger page = scrollView.contentOffset.x/self.screenWidth;
    [self.delegate pageChange:page view:self.data[page]];
    
}

-(void)addPageView:(UIView*)v
{
    if(self.data==nil)
    {
        self.data=[[NSMutableArray alloc]init];
        
    }
    [self.data addObject:v];
    [self.layout addSubview:v];
}

-(void)addPageViewController:(UIViewController*)vc
{
    if(self.data==nil)
    {
        self.data=[[NSMutableArray alloc]init];
        
    }
    [self.data addObject:vc.view];
    [[self getCurrentVc]addChildViewController:vc];
    [self.layout addSubview:vc.view];
}

-(void)selectPage:(int)index
{
    [self.scrollview setContentOffset:CGPointMake(index*self.screenWidth, 0.0) animated:YES];
}


@end
