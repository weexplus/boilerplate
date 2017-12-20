//
//  WXNormalViewContrller.m
//  farwolf.weex
//
//  Created by 郑江荣 on 2017/5/3.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "WXNormalViewContrller.h"
#import "farwolf_weex.h"
#import <WeexSDK/WXSDKInstance.h>
#import "WeexSDK/WXSDKEngine.h"
#import "WeexSDK/WXAppConfiguration.h"
#import "WeexSDK/WXUtility.h"
#import "WeexSDK/WXSDKManager.h"
#import "Masonry.h"
#import "Weex.h"
#import "Config.h"
#import "IQKeyboardManager.h"

@interface WXNormalViewContrller ()

@end

@implementation WXNormalViewContrller


- (void)dealloc
{
    
    [_instance destroyInstance];
    [self _removeObservers];
}

- (instancetype)initWithSourceURL:(NSURL *)sourceURL
{
    if ((self = [super init])) {
        self.sourceURL = sourceURL;
        self.hidesBottomBarWhenPushed = YES;
        
        [self _addObservers];
    }
    return self;
}



/**
 *  After setting the navbar hidden status , this function will be called automatically. In this function, we
 *  set the height of mainView equal to screen height, because there is something wrong with the layout of
 *  page content.
 */


- (void)webSocket:(SRWebSocket *)webSocket didReceiveMessage:(id)message
{
    if ([@"refresh" isEqualToString:message]) {
        [self refreshWeex];
    }
}

-(void)openWatch:(NSString*)ip
{
    
    
    NSString *wsport = @"8082";
    NSURL *socketURL = [NSURL URLWithString:[NSString stringWithFormat:@"ws://%@:%@", ip, wsport]];
    self.hotReloadSocket = [[SRWebSocket alloc] initWithURL:socketURL protocols:@[@"echo-protocol"]];
    self.hotReloadSocket.delegate = self;
    [self.hotReloadSocket open];
}
/**
 *  We assume that the initial state of viewController's navigtionBar is hidden.  By setting the attribute of
 *  'dataRole' equal to 'navbar', the navigationBar hidden will be NO.
 */
- (void)viewDidLoad
{
    [super viewDidLoad];
    
    
    self.navigationController.navigationBar.translucent=false;
    self.view.backgroundColor = [UIColor whiteColor];
    self.automaticallyAdjustsScrollViewInsets = NO;
    if(_debug)
    {
        [self add];
    }
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(refreshWeex) name:@"RefreshInstance" object:nil];
    if(self.page!=nil)
    {
        [self loadPage];
        return;
    }
    [self _renderWithURL:_sourceURL];
    
    if ([self.navigationController isKindOfClass:[WXNormalViewContrller class]]) {
        self.navigationController.navigationBarHidden = YES;
    }
    
    
#ifdef DEBUG
    [self.view addDoubleClick:^{
        
        [self refreshWeex];
    }];
    
#endif
    
    [self regist:@"notify" method:@selector(onNotify:)];
    
    
    
    //    self.returnKeyHandler=
    //    [[IQKeyboardReturnKeyHandler alloc] initWithViewController:self];
    //    self.returnKeyHandler.lastTextFieldReturnKeyType = UIReturnKeyDone;
    
    
    _textfields=[NSMutableArray new];
    [_textfields addObjectsFromArray:[self.view findAllViewByType:[UITextField class]]];
    [_textfields addObjectsFromArray:[self.view findAllViewByType:[UITextView class]]];
}

-(void)loadPage
{
    self.instance=self.page.instance;
    self.weexView=self.page.weexView;
    self.sourceURL=self.page.url;
    self.instance.viewController=self;
    
    [self resetFrame];
    
    self.instance.pageObject=self;
    self.instance.pageName=[@"" addInt:arc4random()];
    
    
    
    
    self.instance.renderFinish = ^(UIView *view) {
        
        [self.instance fireGlobalEvent:@"onPageInit" params:nil];
        
        
        [self loadCompelete];
    };
    [self.view addSubview:self.weexView];
    [self.instance fireGlobalEvent:@"onPageInit" params:nil];
    if(_debug)
    {
        [self.view bringSubviewToFront:self.set];
        [self.view bringSubviewToFront:self.refresh];
    }
    
    [self loadtextfields];
}

-(void)loadtextfields
{
    NSMutableArray *n= [self.view findAllViewByType:[UITextField class]];
    _textfields=[NSMutableArray new];
    [_textfields addObjectsFromArray:n];
    [_textfields addObjectsFromArray:[self.view findAllViewByType:[UITextView class]]];
}
-(void)loadCompelete
{
    [self onaddWeexView];
    [self loadtextfields];
    //    self.returnKeyHandler addTextFieldView:<#(nonnull UIView *)#>
    
}

-(void)onaddWeexView
{
    
}

-(void)resetFrame
{
    if(_freeFrame)
        return;
    [self.instance setFrame:CGRectMake(0.0f, 0.0f, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height)];
    
}
-(void)onNotify:(NSNotification*)n
{
    NSMutableDictionary *d=n.userInfo;
    d[@"key"];
    
}

BOOL isshowErr;
-(void)onWeexError:(NSNotification*)n
{
#ifdef DEBUG
    
    
    [self showError:n.userInfo[@"msg"]] ;
    
#endif
}
-(void)openScan
{
#ifdef DEBUG
    [self openQR];
#endif
    
}



-(void)addFailLayout
{
    self.fail_layout=  [UIView new];
    [self.view addSubviewFull:self.fail_layout];
    
    UIImageView *failimg=[UIImageView new];
    UILabel *lable=[UILabel new];
    lable.text=@"加载失败了,点击重新加载";
    lable.textColor=[@"dddddd" toColor];
    [self.fail_layout addSubview:failimg];
    [self.fail_layout addSubview:lable];
    [self.fail_layout mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.centerXWithinMargins.equalTo(self.view);
        make.size.mas_equalTo(CGSizeMake(250, 230));
        make.top.equalTo(self.view).offset(50);
        
        
    }];
    failimg.image=[UIImage imageNamed:@"fail.png"];
    [failimg mas_makeConstraints:^(MASConstraintMaker *make) {
        make.size.mas_equalTo(CGSizeMake(200, 173));
        make.center.equalTo(_fail_layout);
    }];
    [lable mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.centerXWithinMargins.equalTo(self.fail_layout);
        make.bottom.equalTo(self.fail_layout);
    }];
    
    [self.fail_layout addClick:@selector(refreshWeex) host:self];
    [self.fail_layout setHidden:true];
    //    [self.fail_layout addSubview:lable];
    
    
}



-(void)showError:(NSString*)msg
{
    if(isshowErr)
    {
        return;
    }
    [self.fail_layout setHidden:false];
    isshowErr=true;
    
    ErrorControl *vc=[ErrorControl new];
    vc.errmsg=msg;
    vc.onClose=^(){
        isshowErr=false;
        [self.fail_layout setHidden:false];
    };
    
    
    
    dispatch_sync(dispatch_get_main_queue(), ^{
        
        　 [self presentViewController:vc animated:true completion:^{
            
        }];
        
    });
    
    
    
    
    
    
    
    
    
    
    
}





- (void)viewWillDisappear:(BOOL)animated
{
    [self setBackBar:nil color:nil];
    [_instance fireGlobalEvent:@"viewWillDisappear" params:nil];
    [_instance fireGlobalEvent:WX_APPLICATION_WILL_RESIGN_ACTIVE params:nil];
    
}

-(void)viewWillAppear:(BOOL)animated
{
    [_instance fireGlobalEvent:@"viewWillAppear" params:nil];
    
    [self.navigationController setNavigationBarHidden:true animated:animated];
    [self resetFrame];
    self.view.backgroundColor=[@"#333333" toColor];
    
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    [_instance fireGlobalEvent:@"viewDidAppear" params:nil];
    [_instance fireGlobalEvent:WX_APPLICATION_DID_BECOME_ACTIVE params:nil];
    [self _updateInstanceState:WeexInstanceAppear];
    
    if(self.page.hasload)
        [_instance fireGlobalEvent:@"onPageInit" params:nil];
}

- (void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
    [_instance fireGlobalEvent:@"viewDidDisappear" params:nil];
    [self _updateInstanceState:WeexInstanceDisappear];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    [self _updateInstanceState:WeexInstanceMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)refreshWeex
{
    [self.fail_layout setHidden:true];
    [self _renderWithURL:_sourceURL];
}

- (void)addEdgePop
{
    self.navigationController.interactivePopGestureRecognizer.delegate = self;
}

#pragma mark- UIGestureRecognizerDelegate

- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer
{
    if (!self.navigationController || [self.navigationController.viewControllers count] == 1) {
        return NO;
    }
    return YES;
}

- (void)_renderWithURL:(NSURL *)sourceURL
{
    if (!sourceURL) {
        return;
    }
    if([Weex getBaseUrl] ==nil||[[Weex getBaseUrl] isEqualToString:@""])
        [Weex setBaseUrl:sourceURL.absoluteString];
    
    [_instance destroyInstance];
    _instance = [[WXSDKInstance alloc] init];
    
    
    [self resetFrame];
    _instance.pageObject = self;
    _instance.pageName = [[WXUtility urlByDeletingParameters:sourceURL] absoluteString];
    _instance.viewController = self;
    
    NSString *newURL = nil;
    
    if ([sourceURL.absoluteString rangeOfString:@"?"].location != NSNotFound) {
        newURL = [NSString stringWithFormat:@"%@&random=%d", sourceURL.absoluteString, arc4random()];
    } else {
        newURL = [NSString stringWithFormat:@"%@?random=%d", sourceURL.absoluteString, arc4random()];
    }
    [_instance renderWithURL:[NSURL URLWithString:newURL] options:@{@"bundleUrl":sourceURL.absoluteString} data:nil];
    
    __weak typeof(self) weakSelf = self;
    _instance.onCreate = ^(UIView *view) {
        [weakSelf.weexView removeFromSuperview];
        weakSelf.weexView = view;
        
        [weakSelf.view addSubview:weakSelf.weexView];
        UIAccessibilityPostNotification(UIAccessibilityScreenChangedNotification,  weakSelf.weexView);
        
        [self onCreateWeexView];
        
    };
    
    
    _instance.onFailed = ^(NSError *error) {
        
        NSString *msg=error.userInfo[@"NSLocalizedDescription"];
        [self showError:msg];
    };
    
    
    _instance.renderFinish = ^(UIView *view) {
        [weakSelf _updateInstanceState:WeexInstanceAppear];
        [_instance fireGlobalEvent:@"onPageInit" params:nil];
        if(_debug)
        {
            [self.view bringSubviewToFront:self.set];
            [self.view bringSubviewToFront:self.refresh];
        }
        [self loadCompelete];
    };
}


- (void)_updateInstanceState:(WXState)state
{
    if (_instance && _instance.state != state) {
        _instance.state = state;
        
        if (state == WeexInstanceAppear) {
            [[WXSDKManager bridgeMgr] fireEvent:_instance.instanceId ref:WX_SDK_ROOT_REF type:@"viewappear" params:nil domChanges:nil];
        } else if (state == WeexInstanceDisappear) {
            [[WXSDKManager bridgeMgr] fireEvent:_instance.instanceId ref:WX_SDK_ROOT_REF type:@"viewdisappear" params:nil domChanges:nil];
        }
    }
}

- (void)_appStateDidChange:(NSNotification *)notify
{
    if ([notify.name isEqualToString:@"UIApplicationDidBecomeActiveNotification"]) {
        [self _updateInstanceState:WeexInstanceForeground];
    } else if([notify.name isEqualToString:@"UIApplicationDidEnterBackgroundNotification"]) {
        [self _updateInstanceState:WeexInstanceBackground]; ;
    }
}

- (void)_addObservers
{
    for (NSString *name in @[UIApplicationDidBecomeActiveNotification,
                             UIApplicationDidEnterBackgroundNotification]) {
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(_appStateDidChange:)
                                                     name:name
                                                   object:nil];
    }
}

- (void)_removeObservers
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}


-(void)debugInit
{
    [self regist:@"refreshpage" method:@selector(onqr:)];
    NSString *url=  [self getSaveValue:@"url"];
    if(url==nil||[@"" isEqualToString:url])
    {
        
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

-(void)onqr:(NSNotification*)n
{
    NSMutableDictionary *d=  n.userInfo;
    NSString *url=[d objectForKey:@"url"];
    self.sourceURL=[NSURL URLWithString:url];
    [self refreshWeex];
}

-(void)onCreateWeexView
{
    
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



-(void)gotoset
{
    [self addVc:[self fromStoryBoard:@"weex/SetViewController"]];
}




@end

