//
//  WXSlidComponent.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/5.
//

#import "WXSlidComponent.h"


#import "Weex.h"
#import "WXNormalViewContrller.h"
#import "WXComponent+PseudoClassManagement.h"
#import "WXComponent+ViewManagement.h"

@implementation WXSlidComponent
WX_EXPORT_METHOD(@selector(toggle))
WX_EXPORT_METHOD(@selector(restyle:))


-(instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if(self= [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance])
    {
        
        if (attributes[@"slidSrc"])
        {
            _slidSrc=attributes[@"slidSrc"];
        }
        if (attributes[@"isOpen"])
        {
            
            _isOpen=attributes[@"isOpen"] ? [WXConvert BOOL:attributes[@"isOpen"]] : NO;
        }
        if (attributes[@"src"])
        {
            _src=attributes[@"src"];
        }
        if (attributes[@"leftWidth"])
        {
            _leftWidth=[WXConvert CGFloat:attributes[@"leftWidth"]]*self.weexInstance.pixelScaleFactor;
        }
        else
        {
            _leftWidth=280;
        }
        
    }
    

    return self;
}


-(void)viewDidLoad
{
    CGRect r=self.view.frame;
    
    
    self.root=[UIView new];
    self.left=[UIView new];
    
    self.root.frame= CGRectMake(0, 0, r.size.width, r.size.height);
    self.left.frame=CGRectMake(0, 0, _leftWidth,  r.size.height);
    //      self.root.backgroundColor=[UIColor redColor];
    //     self.left.backgroundColor=[UIColor blueColor];
    LGSideMenuController *sideMenuController=[LGSideMenuController sideMenuControllerWithRootView:_root leftView:_left rightView:nil];
    UIViewController *rootvc=[UIViewController new];
    UIViewController *leftvc=[UIViewController new];
    sideMenuController.rootView=self.root;
    sideMenuController.leftView=self.left;
    _slidcontrol=sideMenuController;
    sideMenuController.leftViewWidth = _leftWidth;
    sideMenuController.leftViewPresentationStyle = LGSideMenuPresentationStyleSlideAbove;
    
    [self.weexInstance.viewController addChildViewController:sideMenuController];
    [_host addSubviewFull:sideMenuController.view];
    
    
    //    [WeexFactory renderNew:[Weex getFinalUrl:_src weexInstance:self.weexInstance] compelete:^(WXNormalViewContrller *vc)
    //     {
    //         vc.instance.parentInstance=self.weexInstance;
    //         [self.weexInstance addChildInstance:vc.instance];
    //
    //         [WeexFactory renderNew:[Weex getFinalUrl:_slidSrc weexInstance:self.weexInstance] compelete:^(WXNormalViewContrller *vc1)
    //          {
    //              vc1.freeFrame=true;
    //              vc1.instance.frame=CGRectMake(0, 0, _leftWidth, [vc1 screenHeight]);
    //              vc1.instance.parentInstance=self.weexInstance;
    //              [self.weexInstance addChildInstance:vc1.instance];
    //              LGSideMenuController *sideMenuController = [LGSideMenuController sideMenuControllerWithRootViewController:vc leftViewController:vc1
    //                                                                       rightViewController:nil];
    //
    //              sideMenuController.leftViewWidth = _leftWidth;
    //              sideMenuController.leftViewPresentationStyle = LGSideMenuPresentationStyleSlideAbove;
    //               [_host addSubviewFull:sideMenuController.view];
    //                [self.weexInstance.viewController addChildViewController:sideMenuController];
    //              _slidcontrol=sideMenuController;
    //              if (self.isOpen) {
    //                  [_slidcontrol  showLeftViewAnimated:true completionHandler:nil];
    //              }
    //              else {
    //                  [_slidcontrol hideLeftViewAnimated:true completionHandler:nil];
    //              }
    //
    //          } fail:^(NSString *msg) {
    //
    //          }  frame:CGRectMake(0, 0, _leftWidth, [self.weexInstance.viewController screenHeight]) isPortrait:true];
    //     } fail:^(NSString *msg) {
    //
    //     }  frame:r isPortrait:true];
    //
    
    
}

 

-(void)updateAttributes:(NSDictionary *)attributes
{
    [super updateAttributes:attributes];
    if (attributes[@"isOpen"])
    {
        _isOpen=attributes[@"isOpen"] ? [WXConvert BOOL:attributes[@"isOpen"]] : NO;
    }
    
    if (self.isOpen) {
        [_slidcontrol  showLeftViewAnimated:true completionHandler:nil];
    }
    else {
        
        [_slidcontrol hideLeftViewPrepareWithGesture:NO];
        [_slidcontrol hideLeftViewAnimatedActions:true completionHandler:nil];
    }
}


-(void)toggle
{
    [_slidcontrol toggleLeftViewAnimated];
}


-(void)insertSubview:(WXComponent *)subcomponent atIndex:(NSInteger)index{
    
    UIView *v=subcomponent.view;
    if(index==0)
    {
        //        self.root.backgroundColor=[UIColor redColor];
        [self.left addSubviewFull:v];
        
        //        [self.root layoutSubviews];
    }
    if(index==1)
    {
        [self.root addSubviewFull:v];
        //         self.left.backgroundColor=[UIColor blueColor];
    }
    [subcomponent fireEvent:@"load" params:self.weexInstance.param];
}


-(UIView*)loadView
{
    
    self.host=[UIView new];
    self.host.backgroundColor=[UIColor blueColor];
    return self.host;
    //    UIView *v=[[UIView alloc]init];
    //    v.backgroundColor=[UIColor blueColor];
    //    return v;
}
@end

