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
#import "RefreshManager.h"
#import "SetViewController.h"

static BOOL isshowErr;
@interface WXNormalViewContrller ()

@end

@implementation WXNormalViewContrller

+(BOOL)showError
{
    return isshowErr;
}

+(void)setShowError:(BOOL)show
{
    isshowErr=show;
}
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
    
    
    NSString *wsport = @"9897";
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
      self.naviIndex=self.topViewController.navigationController.childViewControllers.count;
    if(self.isLanscape)
    {
         [self interfaceOrientation:UIInterfaceOrientationLandscapeRight];
    }
     [self regist:@"refreshpage" method:@selector(scoketrefresh)];
    [self regist:@"qrrefreshpage" method:@selector(onqr:)];
    [self regist:@"weexError" method:@selector(onWeexError:)];
    self.navigationController.navigationBar.translucent=false;
    self.view.backgroundColor = [UIColor whiteColor];
    self.automaticallyAdjustsScrollViewInsets = NO;
    if(_debug)
    {
        [self debugInit];
    }
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(RefreshInstanc) name:@"RefreshInstance" object:nil];
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
//    [self.view addDoubleClick:^{
//
////        [self refreshWeex];
//    }];
    
#endif
    
    [self regist:@"notify" method:@selector(onNotify:)];
    
    
    
    //    self.returnKeyHandler=
    //    [[IQKeyboardReturnKeyHandler alloc] initWithViewController:self];
    //    self.returnKeyHandler.lastTextFieldReturnKeyType = UIReturnKeyDone;
    
    
    _textfields=[NSMutableArray new];
    [_textfields addObjectsFromArray:[self.view findAllViewByType:[UITextField class]]];
    [_textfields addObjectsFromArray:[self.view findAllViewByType:[UITextView class]]];
//    [self openWatch:@"192.168.199.248"];

//    RefreshManager *r=[RefreshManager new];
//    [r open:@"192.168.199.248" port:@"6969"];
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
    
    
     [UIView setAnimationsEnabled:true];
    
    self.instance.renderFinish = ^(UIView *view) {
        
//        [self.instance fireGlobalEvent:@"onPageInit" params:self.param];
        self.instance.param=_param;
        self.instance.isInit=true;
        [self.instance firePageInit];
        [self loadCompelete];
    };
    [self.view addSubview:self.weexView];
//    [self.instance fireGlobalEvent:@"onPageInit" params:self.param];
    self.instance.param=_param;
    self.instance.isInit=true;
    [self.instance firePageInit];
    if(_debug)
    {
        
        [self.view bringSubviewToFront:self.toolView];
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
 
-(void)onWeexError:(NSNotification*)n
{
    if([Config showError])
    {
        [self showError:n.userInfo[@"msg"]] ;
    }
}
-(void)openScan
{
#ifdef DEBUG
    [self openQR];
#endif
    
}

-(void)showError:(NSString*)msg
{
    if(isshowErr)
    {
        return;
    }
//    [self.fail_layout setHidden:false];
    isshowErr=true;
    
    ErrorControl *vc=[ErrorControl new];
    vc.errmsg=msg;
    vc.onClose=^(){
        isshowErr=false;
        [self.fail_layout setHidden:false];
        [vc dismiss:true completion:^{
            
        }];
    };
    
    
    
    
    [self.topViewController presentViewController:vc animated:true completion:^{
        
    }];
    
    
    
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
    
//    [failimg addClick:^{
//        [self refreshWeex];
//    }];
    [self.fail_layout addClick:@selector(refreshWeex) host:self];
    [self.fail_layout setHidden:true];
    //    [self.fail_layout addSubview:lable];
    
    
}








- (void)viewWillDisappear:(BOOL)animated
{
   
    [self setBackBar:nil color:nil];
    [_instance fireGlobalEvent:@"viewWillDisappear" params:nil];
    [_instance fireGlobalEvent:WX_APPLICATION_WILL_RESIGN_ACTIVE params:nil];
    
}

-(void)viewWillAppear:(BOOL)animated
{
    if(self.isLanscape)
    {
        [self interfaceOrientation:UIInterfaceOrientationLandscapeRight];
    }
    else
    {
         [self interfaceOrientation:UIInterfaceOrientationPortrait];
    }
    [_instance fireGlobalEvent:@"viewWillAppear" params:nil];
    [self.navigationController setNavigationBarHidden:true animated:animated];
    [self resetFrame];
    //    self.view.backgroundColor=[@"#ffffff" toColor];
    
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    [_instance fireGlobalEvent:@"viewDidAppear" params:nil];
    [_instance fireGlobalEvent:WX_APPLICATION_DID_BECOME_ACTIVE params:nil];
    [self _updateInstanceState:WeexInstanceAppear];
    
//    if(self.page.hasload)
//        [_instance fireGlobalEvent:@"onPageInit" params:nil];
}

- (void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
   
    [_instance fireGlobalEvent:@"viewDidDisappear" params:nil];
    [self _updateInstanceState:WeexInstanceDisappear];
    
    
    NSLog([@"url== " add: self.sourceURL.absoluteString]);
    NSLog([@"self== " addInt: self.naviIndex]);
    NSLog([@"count== " addInt: self.topViewController.navigationController.childViewControllers.count]);
    
    if(self.naviIndex>self.topViewController.navigationController.childViewControllers.count)
    {
        //        WXNormalViewContrller *vc= self.weexInstance.viewController;
        NSMutableDictionary *p=[NSMutableDictionary new];
        [p setValue:self.sourceURL.absoluteString forKey:@"url"];
        [self notifyDict:@"removeUrl" value:p];
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    [self _updateInstanceState:WeexInstanceMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)RefreshInstanc
{
//    NSDate *date      = NSDate.date;
//
//    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
//    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
//    NSString *dateString       = [formatter stringFromDate: date];
//    NSLog(@"服务器返回的时间戳对应的时间是:%@",dateString);
 
    [self refreshWeex];
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

    
    [_instance destroyInstance];
    _instance = [[WXSDKInstance alloc] init];
    
    _instance.param=self.param;
    [self resetFrame];
    _instance.pageObject = self;
    _instance.pageName = [[WXUtility urlByDeletingParameters:sourceURL] absoluteString];
    _instance.viewController = self;
    
    NSString *newURL = nil;
    
    

    
    NSURL *url=nil;
    if([sourceURL.absoluteString startWith:@"http"])
    {
            if ([sourceURL.absoluteString rangeOfString:@"?"].location != NSNotFound) {
                newURL = [NSString stringWithFormat:@"%@&random=%d", sourceURL.absoluteString, arc4random()];
            } else {
                newURL = [NSString stringWithFormat:@"%@?random=%d", sourceURL.absoluteString, arc4random()];
            }
        url=[NSURL URLWithString:newURL];
        
    }
    else
    {
        url= sourceURL;
    }
    
    [_instance renderWithURL:url options:@{@"bundleUrl":sourceURL.absoluteString} data:nil];
    
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
    
    };
    
    
    _instance.renderFinish = ^(UIView *view) {
        [weakSelf _updateInstanceState:WeexInstanceAppear];
//        [_instance fireGlobalEvent:@"onPageInit" params:self.param];
        self.instance.isInit=true;
        self.instance.param=self.param;
        [self.instance firePageInit];
        if(_debug)
        {
            [self.view bringSubviewToFront:self.set];
            [self.view bringSubviewToFront:self.refresh];
             [self.view bringSubviewToFront:self.fail_layout];
         
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
   
    NSString *url=  [self getSaveValue:@"url"];
    if(url==nil||[@"" isEqualToString:url])
    {
        
    }
    
    if(url!=nil&& ![@"" isEqualToString:url])
    {
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
    }
    
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleDefault];
    
    
  
    [self add];
}
-(void)scoketrefresh
{
    
    if (self.isViewLoaded && self.view.window!=nil) {
//        NSLog(@"屏幕上");
        
        [self refreshWeex];
        
    }

}

- (UIViewController *)getCurrentVC
{
    UIViewController *result = nil;
    
    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
    if (window.windowLevel != UIWindowLevelNormal)
    {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(UIWindow * tmpWin in windows)
        {
            if (tmpWin.windowLevel == UIWindowLevelNormal)
            {
                window = tmpWin;
                break;
            }
        }
    }
    
    UIView *frontView = [[window subviews] objectAtIndex:0];
    id nextResponder = [frontView nextResponder];
    
    if ([nextResponder isKindOfClass:[UIViewController class]])
        result = nextResponder;
    else
        result = window.rootViewController;
    
    return result;
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
    if(_debug)
    {
        
        [self.view bringSubviewToFront:self.toolView];
        [self.view bringSubviewToFront:self.set];
        [self.view bringSubviewToFront:self.refresh];
    }
}
-(void)add
{
    self.toolView = [[UIView alloc] initWithFrame:CGRectMake(0, CGRectGetHeight(self.view.bounds) / 2, 50, 80)];
    float width = CGRectGetWidth(self.toolView.bounds);
    float height = CGRectGetHeight(self.toolView.bounds);
    float y = CGRectGetHeight(self.view.bounds) / 2;
    self.toolView.backgroundColor=[@"#000000" toColor:0.3];
    self.set=[UIButton buttonWithType:UIButtonTypeCustom];
    self.set.frame = CGRectMake(0, y, width, (height - 20) / 2);
    [self.set setTitle:@"设置" forState:UIControlStateNormal];
    [self.set setTitleColor:[@"#ffffff" toColor] forState:UIControlStateNormal];
    [self.set setTitleColor:[UIColor redColor] forState:UIControlStateHighlighted];
    [self.view addSubview:self.set];
    
    [self.set addTarget:self
                 action:@selector(gotoset)
       forControlEvents:UIControlEventTouchUpInside
     ];
    
    
    self.refresh=[UIButton buttonWithType:UIButtonTypeCustom];
    self.refresh.frame = CGRectMake(0, y + (height - 20) / 2 + 20, width, (height - 20) / 2);
    [self.refresh setTitle:@"刷新" forState:UIControlStateNormal];
    [self.refresh setTitleColor:[@"#ffffff" toColor] forState:UIControlStateNormal];
    [self.refresh setTitleColor:[UIColor redColor] forState:UIControlStateHighlighted];
    [self.view addSubview:self.refresh];
    
    [self.refresh addTarget:self
                     action:@selector(refreshWeex)
           forControlEvents:UIControlEventTouchUpInside
     ];
    [self.view addSubview:self.toolView];
    [self.view bringSubviewToFront:self.toolView];
    [self.view bringSubviewToFront:self.set];
      [self.view bringSubviewToFront:self.refresh];
    
    [self regist:@"loaddefault" method:@selector(loaddefault)];
    [self addFailLayout];
    
     
}

-(void)loaddefault
{
    self.sourceURL= [NSURL URLWithString:[Config entry]];
    [self refreshWeex];
    
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [super touchesBegan:touches withEvent:event];
    UITouch *touch = [touches anyObject];
    BOOL isInToolView = [self.toolView.layer containsPoint:[touch locationInView:self.toolView]];
    BOOL isInSetView = [self.set.layer containsPoint:[touch locationInView:self.toolView]];
    BOOL isInRefreshView = [self.refresh.layer containsPoint:[touch locationInView:self.toolView]];
    self.isInView = isInToolView && !isInSetView && !isInRefreshView;
    
    _beginpoint = [touch locationInView:self.toolView];
}

- (void)touchesMoved:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [super touchesMoved:touches withEvent:event];
    if (!self.isInView)    // 仅当取到touch的view是小窗口时，我们才响应触控，否则直接return
    {
        return;
    }
    
    UITouch *touch = [touches anyObject];
    CGPoint currentPosition = [touch locationInView:self.toolView];
    //偏移量
    float offsetX = currentPosition.x - self.beginpoint.x;
    float offsetY = currentPosition.y - self.beginpoint.y;
    //移动后的中心坐标
    self.toolView.center = CGPointMake(self.toolView.center.x + offsetX, self.toolView.center.y + offsetY);
    
    //x轴左右极限坐标
    if (self.toolView.center.x > (self.toolView.superview.frame.size.width-self.toolView.frame.size.width/2))
    {
        CGFloat x = self.toolView.superview.frame.size.width-self.toolView.frame.size.width/2;
        self.toolView.center = CGPointMake(x, self.toolView.center.y + offsetY);
    }
    else if (self.toolView.center.x < self.toolView.frame.size.width/2)
    {
        CGFloat x = self.toolView.frame.size.width/2;
        self.toolView.center = CGPointMake(x, self.toolView.center.y + offsetY);
    }
    
    //y轴上下极限坐标
    if (self.toolView.center.y > (self.toolView.superview.frame.size.height-self.toolView.frame.size.height/2))
    {
        CGFloat x = self.toolView.center.x;
        CGFloat y = self.toolView.superview.frame.size.height-self.toolView.frame.size.height/2;
        self.toolView.center = CGPointMake(x, y);
    }
    else if (self.toolView.center.y <= self.toolView.frame.size.height/2)
    {
        CGFloat x = self.toolView.center.x;
        CGFloat y = self.toolView.frame.size.height/2;
        self.toolView.center = CGPointMake(x, y);
    }
    CGPoint toolCenter = self.toolView.center;
    self.set.center = CGPointMake(toolCenter.x, toolCenter.y - CGRectGetWidth(self.set.bounds) / 2);
    self.refresh.center = CGPointMake(toolCenter.x, toolCenter.y + CGRectGetWidth(self.refresh.bounds) / 2);
}


- (void)interfaceOrientation:(UIInterfaceOrientation)orientation
{
    if ([[UIDevice currentDevice] respondsToSelector:@selector(setOrientation:)]) {
        SEL selector             = NSSelectorFromString(@"setOrientation:");
        NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:[UIDevice instanceMethodSignatureForSelector:selector]];
        [invocation setSelector:selector];
        [invocation setTarget:[UIDevice currentDevice]];
        int val                  = orientation;
        [invocation setArgument:&val atIndex:2];
        [invocation invoke];
    }
}

-(void)gotoset
{
//    _setVc= [self fromStoryBoard:@"weex/SetViewController"];
//        [self addVc:_setVc];
    _setVc=  [self present:@"weex/SetViewController" anim:true];
    ((SetViewController*)((UINavigationController*)_setVc).childViewControllers[0]).vc=self;
//   _setVc= [self addVc:[self fromStoryBoard:@"weex/SetViewController"]];
}




@end
