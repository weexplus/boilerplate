//
//  ListView.m
//  oa
//
//  Created by 郑江荣 on 16/3/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "ListView.h"
#import "ItemView.h"
#import "farwolf.h"
//#import "KoaPullToRefresh.h"
#import <QuartzCore/QuartzCore.h>
#import "EmptyView.h"
#import "ExceptionView.h"
#import "Screen.h"
@implementation ListView

CGRect c;
-(instancetype)initData:(NSMutableArray*)data
{
    self=[super init];
    self.data=data;
    return self;
}

-(BOOL)isShowingPullRefresh
{
//    KoaPullToRefreshView *k=self.tableview.pullToRefreshView;
//
//    return k.state!=KoaPullToRefreshStateStopped;
    return false;
    
}

-(void) awakeFromNib
{
    if(self.data==nil)
    {
        self.data=[[NSMutableArray alloc]init];
    }
    self.tableview=(UITableView*)[self findOneViewByType:[UITableView class]];
    self.tableview.delegate=self;
    self.tableview.dataSource=self;
//    [self.tableview addPullToRefreshWithActionHandler:^{
//        [self hideStateView];
//        [self clear];
//        [self _load];
//
//    } withBackgroundColor:[self getPullrefreshColor].toColor withPullToRefreshHeightShowed:0];
    
    self.emptyView=(EmptyView*)[self findOneViewByType:[EmptyView class]];
    
    self.exceptionView=(ExceptionView*)[self findOneViewByType:[ExceptionView class]];
    [self.exceptionView addClick:@selector(exceptionViewClick) host:self];
//    [self hideStateView];
 
//    [self.tableview.pullToRefreshView setTextColor:[self getPullrefreshTextColor].toColor];
 
//    [self.tableview.pullToRefreshView setFontAwesomeIcon:@"icon-refresh"];
    if(self.emptyView==nil)
    {
        self.emptyView=(EmptyView*)[self fromXib:[self getEmptyNibName]];
    }
    
    if(self.exceptionView==nil)
    {
        self.exceptionView=(ExceptionView*)[self fromXib:[self getExceptionNibName]];
    }
    if(self.progressView==nil)
    {
        self.progressView=(ListViewProgressView*)[self fromXib:[self getProgressNibName]];
        
    }
    
    //Set titles
//    [self.tableview.pullToRefreshView setTitle:@"下拉以刷新" forState:KoaPullToRefreshStateStopped];
//    [self.tableview.pullToRefreshView setTitle:@"松开刷新" forState:KoaPullToRefreshStateTriggered];
//    [self.tableview.pullToRefreshView setTitle:@"加载中..." forState:KoaPullToRefreshStateLoading];
    
    if(![self useCustomFooter])
    {
//        self.stateView=[self fromXib:@"StateView"];
        
//        [self.stateView addSubviewFull:self.emptyView];
//        [self.stateView addSubviewFull:self.exceptionView];
//        [self.stateView addSubviewFull:self.progressView];
        [self hideStateView];

    }
    else
    {
         self.customfooter= self.tableview.tableFooterView;
    }
   
    [self.emptyView setHeight:[self getStateFooterHeight]];
    [self.exceptionView setHeight:[self getStateFooterHeight]];
    [self.progressView setHeight:[self getProgressFooterHeight]];

    
    
    if([self ifLoadOnFirst])
     [self _load];
    
}

-(BOOL)useCustomFooter
{
    return false;
}
-(NSString*)getEmptyNibName
{
    return @"EmptyView";
}
-(NSString*)getExceptionNibName
{
    return @"ExceptionView";
}
-(NSString*)getProgressNibName
{
    return @"ListViewProgress";
}
-(void)refresh
{
    [self clear];
    [self _load];
}
-(void)setStateviewHeight:(CGFloat)height
{
    [self.emptyView setHeight:height];
    [self.exceptionView setHeight:height];
}

-(UIView*)fromXib:(NSString*)name
{
    NSArray *n=  [[NSBundle mainBundle]loadNibNamed:name owner:self options:nil];
    return (UIView*)n[0];
}
-(void)exceptionViewClick
{
    [self _load];
    [self setPulltoRefreshEnbale:true];
}

-(void)showEmptyView
{
    
    
    
    self.tableview.tableFooterView=self.emptyView;
 
    
    
}
-(void)showExceptionView
{
       self.tableview.tableFooterView=self.exceptionView;
//    if(![self useCustomFooter])
//    {
//        self.tableview.tableFooterView=self.stateView;
//        [self setStateviewHeight:[self getStateFooterHeight]];
//        [self.emptyView setHidden:true];
//        [self.progressView setHidden:true];
//        [self.exceptionView setHidden:false];
//        [self setPulltoRefreshEnbale:false];
//    }
//    else
//    {
//        self.tableview.tableFooterView=self.customfooter;
//    }

}


-(void)showProgressView
{
    
     self.tableview.tableFooterView=self.progressView;
//    if(![self useCustomFooter])
//    {
//        self.tableview.tableFooterView=self.stateView;
//        [self setStateviewHeight:[self getProgressFooterHeight]];
//        [self.emptyView setHidden:true];
//        [self.progressView setHidden:false];
//        [self.exceptionView setHidden:true];
//        [self setPulltoRefreshEnbale:false];
//    }
//    else
//    {
//        self.tableview.tableFooterView=self.customfooter;
//    }
}
-(CGFloat)getStateFooterHeight
{
    if( self.screenHeight==SCREEN3_5)
    {
        return 100;
    }
    return 130;
}

-(CGFloat)getProgressFooterHeight
{
   if( self.screenHeight==SCREEN3_5)
   {
        return 150;
   }
    return 150;
}

-(CGFloat)getYOffset
{
    return c.origin.y;
}

UIView *zeroheight;

-(void)hideStateView
{
   
 if(zeroheight==nil)
 {
     zeroheight=[UIView new];
 }
   self.tableview.tableFooterView=zeroheight;
   
}
-(void)setPulltoRefreshEnbale:(BOOL)enable
{
    [self.tableview setBounces:enable];
}

-(void)load
{
    
}
-(BOOL)ifLoadOnFirst
{
    return true;
}

-(void)_load
{
    [self load];
}
-(void)clear
{
    [self.data removeAllObjects];
    [self.tableview reloadData];
    
}

-(void)showPullIndicator
{
//    [self.tableview.pullToRefreshView startAnimating];
    
}
-(void)hidePullIndicator
{
//    [self.tableview.pullToRefreshView stopAnimating];
}

-(NSString*)getPullrefreshColor
{
    return @"f4f3f2";
    
}

-(NSString*)getPullrefreshTextColor
{
    return @"aaaaaa";
    
}



-(NSMutableArray *)getDataSource
{
    return  _data;
}


-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if(![self getDataSource])
        return  0;
    return [self getDataSource].count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if([self loadfromXib])
    {
        return [self loadTableViewCellfromXib:tableView cellForRowAtIndexPath:indexPath];
    }
    else
    {
        return  [self loadTableViewCellfromTableView:tableView cellForRowAtIndexPath:indexPath];
    }
}

-(UITableViewCell *)loadTableViewCellfromTableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    ItemView *cell=[tableView dequeueReusableCellWithIdentifier:[self getCellId] forIndexPath:indexPath];
    [self update:cell indexpath:indexPath];
    return cell;
}


-(void)update:(ItemView*)cell indexpath:(NSIndexPath*)indexPath
{
    NSMutableArray *data=[self getDataSource];
    if(indexPath.row<data.count)
    {
        [cell update:[self getBean:indexPath]];
    }
}
-(NSObject*)getBean:(NSIndexPath*)indexPath
{
    NSMutableArray *data=[self getDataSource];
    return data[indexPath.row];
}


-(UITableViewCell *)loadTableViewCellfromXib:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
//    NSString *cellid=[self getCellId];
    NSString *cellxibname=[self getCellXibName:indexPath];
    ItemView * cell = [tableView dequeueReusableCellWithIdentifier:cellxibname];
    if (!cell)
    {
          [tableView registerNib:[UINib nibWithNibName:cellxibname bundle:nil] forCellReuseIdentifier:cellxibname];
       
          cell = [tableView dequeueReusableCellWithIdentifier:cellxibname];
    }
    if(cell)
    {
        [self update:cell indexpath:indexPath];
        
    }
    return cell;
}

-(BOOL)loadfromXib
{
    return false;
}


-(NSString*)getCellId
{
    return @"";
}

-(NSString*)getCellXibName:(NSIndexPath*)indexPath
{
    return @"";
}

 


@end
