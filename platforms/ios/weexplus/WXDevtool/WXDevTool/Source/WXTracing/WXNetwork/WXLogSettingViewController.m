//
//  WXLogSettingViewController.m
//  Pods
//
//  Created by 齐山 on 2017/7/19.
//
//

#import "WXLogSettingViewController.h"
#import <WeexSDK/WXLog.h>

@interface WXLogSettingViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (strong,nonatomic) UITableView *table;
@property (strong,nonatomic) NSArray     *contents;

@end

@implementation WXLogSettingViewController

- (instancetype)initWithFrame:(CGRect )frame
{
    if ((self = [super init])) {
        self.view.frame = frame;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = @"日志级别";
    self.view.backgroundColor = [UIColor whiteColor];
    CGRect rect = [UIScreen mainScreen].bounds;
    self.view.frame =  CGRectMake(0, 0, rect.size.width, rect.size.height-64);
    self.contents = @[@"off",@"error",@"warn",@"info",@"log",@"debug",@"all"];
    [self cofigureTableview];
}

-(void)cofigureTableview
{
    self.table = [[UITableView alloc] initWithFrame:CGRectMake(self.view.bounds.origin.x, 0, self.view.bounds.size.width, self.view.bounds.size.height) style:UITableViewStylePlain];
    self.table.delegate = self;
    self.table.dataSource = self;
    [self.view addSubview:self.table];
    
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.contents count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"cellIdentifier";
    
    UITableViewCell *cell = [self.table dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if(cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
    }
    cell.textLabel.text = [self.contents objectAtIndex:indexPath.row];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if(self.delegate && [self.delegate respondsToSelector:@selector(selectLogLevel:)]){
        WXLogLevel level =  WXLogLevelLog;
        switch (indexPath.row) {
            case 0:
                level = WXLogLevelOff;
                break;
            case 1:
                level = WXLogLevelError;
                break;
            case 2:
                level = WXLogLevelWarning;
                break;
            case 3:
                level = WXLogLevelInfo;
                break;
            case 4:
                level = WXLogLevelLog;
                break;
            case 5:
                level = WXLogLevelDebug;
                break;
            case 6:
                level = WXLogLevelAll;
                break;
            default:
                break;
        }
        [self.delegate selectLogLevel:level];
    }
    [self.navigationController popViewControllerAnimated:YES];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 40;
}

@end
