//
//  WXTracingHomePageViewController.m
//  Pods
//
//  Created by 齐山 on 2017/7/17.
//
//

#import "WXTracingHomePageViewController.h"
#import "WXTagListView.h"
#import "WXRenderTracingViewController.h"
#import "WXLogViewController.h"
#import "WXApiTracingViewController.h"
#import "WXEnviromentViewController.h"
#import "WXNetworkHistoryTableViewController.h"
#import "WXTracingSettingViewController.h"
#import "WXNetworkSettingsTableViewController.h"
#import "WXTracingLogViewController.h"

@interface WXTracingHomePageViewController ()<WXTagListViewDelegate,ViewPagerDataSource, ViewPagerDelegate>
{
}
@property (nonatomic, strong) NSArray *titleArr;
@property (strong) WXTagListView *tagListView;
@property (nonatomic, strong) NSMutableArray *tableViewArr;
@property (nonatomic) NSInteger fundIndex;
@property (nonatomic) NSInteger slideIndex;
@property (nonatomic, strong) WXViewPagerController *productVC;

@property (nonatomic,strong)WXNetworkHistoryTableViewController *netVC;
@property (nonatomic,strong)WXTracingLogViewController *logVC;
@property (nonatomic,strong)WXApiTracingViewController *apiVC;
@property (nonatomic,strong)WXEnviromentViewController *envVC;
@property (nonatomic,strong)WXNetworkSettingsTableViewController *netSettingVC;
@property (nonatomic,strong)WXRenderTracingViewController *renderVC;

@end

@implementation WXTracingHomePageViewController


- (void)viewDidLoad {
    [super viewDidLoad];
    self.titleArr = @[@"性能",@"网络",@"日志",@"api",@"环境变量",@"设置"];
    [self config];
    // Do any additional setup after loading the view.
}

- (void)config
{
    [self loadProductListView];

    self.dataSource = self;
    self.delegate = self;
    // WXTagListView

    self.tagListView = [[WXTagListView alloc] initWithFrame:CGRectMake(0, 0, [UIScreen mainScreen].bounds.size.width, 44)];
    [self.tagListView bindData:self.titleArr];
    self.tagListView.delegate = self;
    [self.view addSubview:self.tagListView];
    // 默认高亮
//    [self performSelector:@selector(updateToIndex) withObject:nil afterDelay:1.0];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if(self.renderVC){
        [self.renderVC refreshData];
    }
    if(self.netVC){
        [self.netVC refreshData];
    }
    
    
    
}

-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    if(self.logVC){
        [self.logVC refreshData];
    }
}

- (void)loadProductListView
{
    self.tableViewArr = [NSMutableArray array];
    
    if(!self.renderVC){
        self.renderVC = [[WXRenderTracingViewController alloc] init];
        [self.tableViewArr addObject:self.renderVC];
    }
    
    if(!self.netVC){
        self.netVC = [[WXNetworkHistoryTableViewController alloc] init];
        [self.tableViewArr addObject:self.netVC];
    }
    if(!self.logVC){
        self.logVC = [[WXTracingLogViewController alloc] init];
        [self.tableViewArr addObject:self.logVC];
    }
    if(!self.apiVC){
        self.apiVC = [[WXApiTracingViewController alloc] init];
        [self.tableViewArr addObject:self.apiVC];
    }
    if(!self.envVC){
        self.envVC = [[WXEnviromentViewController alloc] init];
        [self.tableViewArr addObject:self.envVC];
    }
    if(!self.netSettingVC){
        self.netSettingVC = [[WXNetworkSettingsTableViewController alloc] init];
        [self.tableViewArr addObject:self.netSettingVC];
    }
}

- (void)updateToIndex
{
    [self.tagListView setHighLightIndex:self.fundIndex];
    [self selectTabAtIndex:self.fundIndex];
}


#pragma mark - WXTagListViewDelegate

- (void)selectedTag:(NSString *)tagName withIndex:(NSInteger)tagIndex
{
    [self.tagListView setHighLightIndex:tagIndex];
    [self selectTabAtIndex:tagIndex];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
#pragma mark - ViewPagerDataSource
- (NSUInteger)numberOfTabsForViewPager:(WXViewPagerController *)viewPager
{
    //    return 10;
    return [self.tableViewArr count];
}
- (UIView *)viewPager:(WXViewPagerController *)viewPager viewForTabAtIndex:(NSUInteger)index
{
    
    UILabel *label = [UILabel new];
    label.frame = CGRectMake(0.0, 0.0, 320, 44);
    label.backgroundColor = [UIColor whiteColor];
//    label.font = Font_L;
    label.text = [NSString stringWithFormat:@"%@", self.titleArr[index]];
    label.textAlignment = NSTextAlignmentCenter;
    label.textColor = [UIColor blackColor];
    [label sizeToFit];
    
    return label;
}

- (UIViewController *)viewPager:(WXViewPagerController *)viewPager
contentViewControllerForTabAtIndex:(NSUInteger)index
{
    WXViewPagerController *vc = [self.tableViewArr objectAtIndex:index];
    
//    vc.isApply = self.isApply;
    return vc;
}
#pragma mark - ViewPagerDelegate
- (void)viewPager:(WXViewPagerController *)viewPager didChangeTabToIndex:(NSUInteger)index
{
    self.productVC = [self.tableViewArr objectAtIndex:index];
//    [self.productVC updateTable];
    self.slideIndex = index;
    [self.tagListView.segmentedControl setSelectedSegmentIndex:index animated:YES];
    [self.tagListView setHighLightIndex:index];
}

- (CGFloat)viewPager:(WXViewPagerController *)viewPager
      valueForOption:(ViewPagerOption)option
         withDefault:(CGFloat)value
{
    switch (option) {
        case ViewPagerOptionStartFromSecondTab:
            return 0.0;
        case ViewPagerOptionCenterCurrentTab:
            return 0.0;
        case ViewPagerOptionTabLocation:
            return 1.0;
        case ViewPagerOptionTabHeight:
            return 44.0;
        case ViewPagerOptionTabOffset:
            return 40.0;
            
        default:
            return value;
    }
}

- (UIColor *)viewPager:(WXViewPagerController *)viewPager
     colorForComponent:(ViewPagerComponent)component
           withDefault:(UIColor *)color
{
    
    switch (component) {
        case ViewPagerIndicator:
            return [UIColor colorWithRed:210/255.0 green:0/255.0 blue:0/255.0 alpha:1.0];
        case ViewPagerTabsView:
            return [UIColor whiteColor];
        case ViewPagerContent:
            return [UIColor whiteColor];
        default:
            return color;
    }
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
