//
//  WXRenderTracingViewController.m
//  TBWXDevTool
//
//  Created by 齐山 on 2017/7/4.
//  Copyright © 2017年 Taobao. All rights reserved.
//

#import "WXTracingMethodViewController.h"
#import <UIKit/UIKit.h>
#import "WXDebugger.h"
#import "WXRenderTracingTableViewCell.h"
#import "WXTracingMethodTableViewCell.h"

@interface WXTracingMethodViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (strong,nonatomic) UITableView *table;

@end

@implementation WXTracingMethodViewController

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

-(void)cofigureTableview
{
    self.table = [[UITableView alloc] initWithFrame:CGRectMake(self.view.bounds.origin.x, 0, self.view.bounds.size.width, self.view.bounds.size.height-20) style:UITableViewStylePlain];
    self.table.delegate = self;
    self.table.dataSource = self;
    [self.view addSubview:self.table];
    [self.table  setTableFooterView:[[UIView alloc] initWithFrame:CGRectZero]];
    
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.methods count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"cellIdentifier";
    
    WXTracingMethodTableViewCell *cell = [self.table dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if(cell == nil) {
        cell = [[WXTracingMethodTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
    }
    NSInteger row = [indexPath row];
    [cell config:self.methods[row]];
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 44;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath;
{
//    NSLog(@"title of cell %@", [_content objectAtIndex:indexPath.row]);
}

@end
