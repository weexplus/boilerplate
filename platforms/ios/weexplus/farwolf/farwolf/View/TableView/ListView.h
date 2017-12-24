//
//  ListView.h
//  oa
//
//  Created by 郑江荣 on 16/3/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "EmptyView.h"
#import "ExceptionView.h"
#import "ItemView.h"
#import "StateView.h"
#import "ListViewProgressView.h"
@interface ListView : UIView<UITableViewDelegate, UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableview;
@property (strong, nonatomic)   EmptyView *emptyView;
@property (strong, nonatomic)   ExceptionView *exceptionView;
@property (strong, nonatomic)   StateView *stateView;
@property (strong, nonatomic)   ListViewProgressView *progressView;

@property (strong, nonatomic)   UIView *customfooter;
-(NSMutableArray*)getDataSource;
-(BOOL)loadfromXib;
-(NSString*)getCellId;
-(NSString*)getCellXibName:(NSIndexPath*)indexPath;
-(NSObject*)getBean:(NSIndexPath*)indexPath;
-(void)update:(ItemView*)cell indexpath:(NSIndexPath*)indexPath;
@property (nonatomic) NSMutableArray *data;
-(void)load;
-(void)_load;
-(void)showPullIndicator;
-(void)hidePullIndicator;
-(void)clear;
-(void)refresh;
-(BOOL)ifLoadOnFirst;
-(BOOL)isShowingPullRefresh;
-(void)showEmptyView;
-(void)showExceptionView;
-(void)hideStateView;
-(void)setPulltoRefreshEnbale:(BOOL)enable;
-(void)exceptionViewClick;
-(CGFloat)getStateFooterHeight;
-(CGFloat)getProgressFooterHeight;

-(void)setStateviewHeight:(CGFloat)height;
-(void)showProgressView;
-(NSString*)getEmptyNibName;
-(NSString*)getExceptionNibName;
-(NSString*)getProgressNibName;
-(BOOL)useCustomFooter;



@end
