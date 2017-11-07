//
//  NetListView.m
//  oa
//
//  Created by 郑江荣 on 16/4/26.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "NetListView.h"
#import "farwolf.h"
#import "Json.h"
#import "JsonReader.h"

@implementation NetListView


-(void)_load
{
    
    [self load];
}


-(void)start
{
    self.isLoading=true;
    [self hideStateView];
    if(![self isShowingPullRefresh])
    {
        self.tableview.tableFooterView=[UIView new];
        if([self getDataSource].count==0&&self.tableview.tableHeaderView==nil)
        {
         
            
             [[self getProgress] show];
        }
        else
        {
            [self showProgressView];
        }
        
    }
    else
    {
        [self showProgressView];
    }
   
}

-(void)clear
{
    [super clear];
  
}

-(void)success:(Json*)j
{
 
 
    self.hasMore=[j hasMore];

    [self.data addObjectsFromArray:[j toList:[self getClass]]];
    [self.tableview reloadData];
    if(self.data.count==0)
    {
        [self showEmptyView];
//        [self showProgressView];
    }
    else
    {
        [self hideStateView];
    }
    
}



 

-(void)fail:(NSInteger)errcode errmsg:(NSString *)errmsg json:(Json *)j
{
    [self toast:errmsg];
    if(self.data.count==0)
    {
        [self showExceptionView];
    }
}




-(void)exception
{
     [self toast:@"网络异常!"];
    if(self.data.count==0)
    {
        [self showExceptionView];
    }
    
}
-(void)compelete
{
    self.isLoading=false;
    [[self getProgress] hide];
    [self hidePullIndicator];
    
    
   
}




-(void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    
    CGPoint offset =scrollView.contentOffset;
    CGRect bounds= scrollView.bounds;
    CGSize size=  scrollView.contentSize;
    UIEdgeInsets inset= scrollView.contentInset;
    
    CGFloat y = offset.y + bounds.size.height - inset.bottom;
    CGFloat reload_distance =10.0;
    CGFloat h = size.height+reload_distance;
    CGFloat height=self.frame.size.height;
    BOOL t=y > h&&h>height;
    if(t)
    {
        if(!self.isLoading&&self.hasMore)
        {
            [self _load];
        }
    }
    
}


-(id<ProgressProtocol>)getProgress
{
    if(self.progress==nil)
    {
//        UIWindow *w= [UIApplication sharedApplication].keyWindow;
        
        self.progress=[[LockScreenProgress alloc]initWith:self];
    }
    return self.progress;
}

@end
