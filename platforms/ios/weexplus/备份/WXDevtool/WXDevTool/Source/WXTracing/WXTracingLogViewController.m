//
//  WXTracingLogViewController.m
//  Pods
//
//  Created by 齐山 on 2017/7/24.
//
//

#import "WXTracingLogViewController.h"
#import <UIKit/UIKit.h>
#import "WXDebugger.h"
#import "WXRenderTracingTableViewCell.h"
#import "WXTracingLogTableViewCell.h"
#import "WXTracingViewControllerManager.h"
#import "WXTracingUtility.h"

@interface WXTracingLogViewController ()<UITableViewDelegate,UITableViewDataSource,UISearchControllerDelegate,UISearchResultsUpdating>

@property (strong,nonatomic) UITableView *table;
@property (nonatomic ,strong)NSMutableArray *searchArr;
@property (nonatomic ,strong)UISearchController *searchVC;
@property (nonatomic ,strong)NSMutableArray *logArr;

@end

@implementation WXTracingLogViewController

- (instancetype)initWithFrame:(CGRect )frame
{
    if ((self = [super init])) {
        self.view.frame = frame;
    }
    return self;
}

-(void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    CGRect rect = [UIScreen mainScreen].bounds;
    self.view.frame =  CGRectMake(0, 0, rect.size.width, rect.size.height);
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(resetLogData) name:TracingResetLogDataNoti object:nil];
    self.extendedLayoutIncludesOpaqueBars = YES;
    [self cofigureTableview];
}

-(void)cofigureTableview
{
    self.table = [[UITableView alloc] initWithFrame:CGRectMake(self.view.bounds.origin.x, 0, self.view.bounds.size.width, self.view.bounds.size.height-64) style:UITableViewStylePlain];
    self.logArr= [NSMutableArray new];
    self.logArr = [[WXTracingViewControllerManager sharedInstance].messages mutableCopy];
    self.table.delegate = self;
    self.table.dataSource = self;
    [self.view addSubview:self.table];
    [self.table  setTableFooterView:[[UIView alloc] initWithFrame:CGRectZero]];
    self.table.tableHeaderView = self.searchVC.searchBar;

//    [self.table setContentOffset:CGPointMake(0, self.table.contentSize.height -self.table.bounds.size.height) animated:YES];
    
}

-(void)refreshData
{
    if (!self.searchVC.active)
    {
        if([self.logArr count] < [[WXTracingViewControllerManager sharedInstance].messages count]){
            NSInteger i = [self.logArr count];
            NSMutableArray *indexPaths = [[NSMutableArray alloc] init];
            for (; i < [[WXTracingViewControllerManager sharedInstance].messages count] ; i++) {
                [self.logArr addObject:[WXTracingViewControllerManager sharedInstance].messages[i]];
                NSIndexPath *indexPath = [NSIndexPath indexPathForRow:i inSection:0];
                [indexPaths addObject: indexPath];
            }
            [self.table beginUpdates];
            [self.table insertRowsAtIndexPaths:indexPaths withRowAnimation:UITableViewRowAnimationFade];
            [self.table endUpdates];
            [self.table scrollToRowAtIndexPath:
             [NSIndexPath indexPathForRow:[self.logArr count]-1 inSection:0]
                                      atScrollPosition: UITableViewScrollPositionBottom
                                              animated:YES];
        }
        
    }
}

-(void)resetLogData
{
    self.logArr = [NSMutableArray new];
    [self.table reloadData];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (self.searchVC.active) {
        return self.searchArr.count;//搜索结果
    }else
    {
        return [self.logArr count];//原始数据
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"cellIdentifier";
    
    WXTracingLogTableViewCell *cell = [self.table dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if(cell == nil) {
        cell = [[WXTracingLogTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
    }
    NSInteger row = [indexPath row];
    
    if (!self.searchVC.active) {
        [cell config: self.logArr[row] searchStr:@""];
    }else
    {
        [cell config:self.searchArr[row] searchStr:[self.searchVC.searchBar text]];
    }
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [self tableView:tableView cellForRowAtIndexPath:indexPath];
    return cell.frame.size.height;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath;
{
    //    NSLog(@"title of cell %@", [_content objectAtIndex:indexPath.row]);
}


- (void)updateSearchResultsForSearchController:(UISearchController *)searchController
{
//    if([self.searchVC.searchBar text].length ==0 && self.searchVC.isActive){
//        return;
//    }
    NSString *searchString = [self.searchVC.searchBar text];
    NSPredicate *preicate = [NSPredicate predicateWithFormat:@"SELF CONTAINS[c] %@", searchString];
    if (self.searchArr!= nil) {
        [self.searchArr removeAllObjects];
    }
    //过滤数据
    self.searchArr= [NSMutableArray arrayWithArray:[self.logArr filteredArrayUsingPredicate:preicate]];
    //刷新表格
    [self.table reloadData];
}

- (UISearchController *)searchVC
{
    if (!_searchVC) {
        _searchVC = [[UISearchController alloc]initWithSearchResultsController:nil];
        _searchVC.searchResultsUpdater = self;
        
        _searchVC.dimsBackgroundDuringPresentation = NO;
        
        _searchVC.hidesNavigationBarDuringPresentation = NO;
        
        _searchVC.searchBar.frame = CGRectMake(self.searchVC.searchBar.frame.origin.x, self.searchVC.searchBar.frame.origin.y, self.searchVC.searchBar.frame.size.width, 44.0);
        
    }
    return _searchVC;
}

@end
