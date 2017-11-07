//
//  DebugViewControl.m
//  Pods
//
//  Created by 郑江荣 on 2017/9/30.
//
//

#import "DebugViewControl.h"

@interface DebugViewControl ()

@end

@implementation DebugViewControl

- (void)viewDidLoad {
   
    // Do any additional setup after loading the view.
    
    [self regist:@"refreshpage" method:@selector(onqr:)];
    NSString *url=  [self getSaveValue:@"url"];
    if(url==nil||[@"" isEqualToString:url])
    {
        
    }
    if([self getUrl]!=nil)
    {
        url=[self getUrl];
    }
    if([url startWith:@"http"])
    {
        self.sourceURL=[NSURL URLWithString:url];
    }
    else
    {
        if([url endWith:@".js"])
            url=[url replace:@".js" withString:@""];
        self.sourceURL = [[NSBundle mainBundle] URLForResource:url withExtension:@"js"];
    }
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
    [super viewDidLoad];
    [self add];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)onqr:(NSNotification*)n
{
    NSMutableDictionary *d=  n.userInfo;
    NSString *url=[d objectForKey:@"url"];
    self.sourceURL=[NSURL URLWithString:url];
    [self refreshWeex];
}

-(NSString*)getUrl
{
    return nil;
}


-(void)onCreateWeexView
{
    [self.view bringSubviewToFront:self.set];
    [self.view bringSubviewToFront:self.refresh];
}
-(void)add
{
     __weak __typeof(self)weakSelf = self;
     _set=[UIButton buttonWithType:UIButtonTypeCustom];;
    
//    [_set setBackgroundColor:[@"#4990E2" toColor]];
    [_set setTitle:@"设置" forState:UIControlStateNormal];
    [_set setTitleColor:[@"#4990E2" toColor] forState:UIControlStateNormal];
     [_set setTitleColor:[UIColor redColor] forState:UIControlStateHighlighted];
    [self.view addSubview:_set];
    [_set mas_makeConstraints:^(MASConstraintMaker *make) {

        make.left.equalTo(self.view).offset(10);
        make.bottom.equalTo(self.view).offset(-200);
//        make.height.mas_equalTo(@50);
//        make.width.mas_equalTo(@100);
    }];

    [_set addTarget:self
                action:@selector(gotoset)
      forControlEvents:UIControlEventTouchUpInside
     ];
    
    
    _refresh=[UIButton buttonWithType:UIButtonTypeCustom];;
    [_refresh setTitle:@"刷新" forState:UIControlStateNormal];
    [_refresh setTitleColor:[@"#4990E2" toColor] forState:UIControlStateNormal];
    [_refresh setTitleColor:[UIColor redColor] forState:UIControlStateHighlighted];

    [self.view addSubview:_refresh];
    [_refresh mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.right.equalTo(self.view).offset(-10);
        make.bottom.equalTo(self.view).offset(-200);
    }];

    
    
    [_refresh addTarget:self
             action:@selector(refreshWeex)
   forControlEvents:UIControlEventTouchUpInside
     ];
}

-(void)viewWillAppear:(BOOL)animated
{
    self.navbarVisibility =@"hidden";
    //     self.navbarVisibility=@"transparent";
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}

-(void)onaddWeexView
{
    [self.view bringSubviewToFront:self.set];
    [self.view bringSubviewToFront:self.refresh];
}

-(void)gotoset
{
     [self addVc:[self fromStoryBoard:@"weex/SetViewController"]];
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
