//
//  WXRenderTracingViewController.m
//  TBWXDevTool
//
//  Created by 齐山 on 2017/7/4.
//  Copyright © 2017年 Taobao. All rights reserved.
//

#import "WXRenderTracingViewController.h"
#import <UIKit/UIKit.h>
#import "WXDebugger.h"
#import "WXRenderTracingTableViewCell.h"
@implementation WXShowTracingTask
@end

@interface WXRenderTracingViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (strong,nonatomic) UITableView *table;
@property (strong,nonatomic) NSArray     *content;
@property (strong,nonatomic) NSMutableArray *tasks;
@property (nonatomic) NSTimeInterval begin;
@property (nonatomic) NSTimeInterval end;
@property (nonatomic,copy) NSString *sectionTitle;

@end

@implementation WXRenderTracingViewController

- (instancetype)initWithFrame:(CGRect )frame
{
    if ((self = [super init])) {
        self.view.frame = frame;
    }
    return self;
}
    
   
- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    CGRect rect = [UIScreen mainScreen].bounds;
    self.view.frame =  CGRectMake(0, 0, rect.size.width, rect.size.height);
    [self cofigureTableview];
    
    
    
}

-(void)refreshData
{
    self.tasks = [NSMutableArray new];
    NSMutableDictionary *taskData = [[WXTracingManager getTracingData] mutableCopy];
    NSArray *instanceIds = [[WXSDKManager bridgeMgr] getInstanceIdStack];
    if(instanceIds && [instanceIds count] >0){
        for (NSInteger i = 0; i< [instanceIds count]; i++) {
            NSString *instanceId =instanceIds[i];
            WXTracingTask *task = [taskData objectForKey:instanceId];
            WXShowTracingTask *showTask = [WXShowTracingTask new];
            showTask.counter = task.counter;
            showTask.iid = task.iid;
            showTask.tag = task.tag;
            showTask.bundleUrl = task.bundleUrl;
            showTask.bundleJSType = task.bundleJSType;
            NSMutableArray *tracings = [NSMutableArray new];
            for (WXTracing *tracing in task.tracings) {
                if(![WXTracingEnd isEqualToString:tracing.ph]){
                    [tracings addObject:tracing];
                    if(showTask.begin <0.0001){
                        showTask.begin = tracing.ts;
                    }
                    
                    if(tracing.ts < showTask.begin){
                        showTask.begin = tracing.ts;
                    }
                    if((tracing.ts +tracing.duration) > showTask.end){
                        showTask.end = tracing.ts +tracing.duration ;
                    }
                }
            }
            showTask.tracings = [NSMutableArray new];
            showTask.tracings = tracings;
            [self.tasks addObject:showTask];
        }
    }
    if(!self.tasks || [self.tasks count] == 0)
    {
        WXShowTracingTask *showTask = [WXShowTracingTask new];
        showTask.tracings = [NSMutableArray new];
        self.tasks = [NSMutableArray new];
        [self.tasks addObject:showTask];
    }
    [self.table reloadData];
}

-(void)cofigureTableview
{
    self.table = [[UITableView alloc] initWithFrame:CGRectMake(self.view.bounds.origin.x, 0, self.view.bounds.size.width, self.view.bounds.size.height-64) style:UITableViewStylePlain];
    self.table.delegate = self;
    self.table.dataSource = self;
    [self.view addSubview:self.table];
    
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return [self.tasks count];
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    WXShowTracingTask *task = self.tasks[section];
    return [task.tracings count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"cellIdentifier";
    
    WXRenderTracingTableViewCell *cell = [self.table dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if(cell == nil) {
        cell = [[WXRenderTracingTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
    }
    WXShowTracingTask *task = self.tasks[indexPath.section];
    [cell config:[task.tracings objectAtIndex:indexPath.row] begin:task.begin end:task.end];
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 80;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath;
{
    NSLog(@"title of cell %@", [_content objectAtIndex:indexPath.row]);
}

#pragma mark -
#pragma section
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 58;
}
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    WXTracingTask *task = [self.tasks objectAtIndex:section];
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, 18)];
    /* Create custom view to display section header... */
    UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(10, 5, tableView.frame.size.width, 18)];
    [label setFont:[UIFont boldSystemFontOfSize:14]];
    NSString *string = [NSString stringWithFormat:@"instanceId:%@",task.iid];
    if(!task.iid){
        string = @"暂时没有weex页面渲染";
    }
    /* Section header is in 0th index... */
    [label setText:string];
    [view addSubview:label];
    if(!task.iid){
        return view;
    }
    
    UILabel *subLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 22, tableView.frame.size.width, 30)];
    [subLabel setFont:[UIFont systemFontOfSize:12]];
    subLabel.lineBreakMode = NSLineBreakByWordWrapping;
    subLabel.numberOfLines = 0;
    NSString *subString = [NSString stringWithFormat:@"bundleUrl:%@",task.bundleUrl];
    /* Section header is in 0th index... */
    [subLabel setText:subString];
    [view addSubview:subLabel];
    
    [view setBackgroundColor:[UIColor colorWithRed:235/255.0 green:235/255.0 blue:235/255.0 alpha:1.0]];
    return view;
}

@end
