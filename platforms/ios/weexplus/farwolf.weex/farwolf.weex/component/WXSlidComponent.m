//
//  WXSlidComponent.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/5.
//

#import "WXSlidComponent.h"
 

#import "Weex.h"
#import "WXNormalViewContrller.h"

@implementation WXSlidComponent

-(instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if(self= [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance])
    {
 
        if (attributes[@"slidSrc"])
        {
            _slidSrc=attributes[@"slidSrc"];
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
 
    
    
    [WeexFactory renderNew:[Weex getFinalUrl:_src weexInstance:self.weexInstance] compelete:^(WXNormalViewContrller *vc)
     {
         vc.instance.parentInstance=self.weexInstance;
         [self.weexInstance addChildInstance:vc.instance];
         
         [WeexFactory renderNew:[Weex getFinalUrl:_slidSrc weexInstance:self.weexInstance] compelete:^(WXNormalViewContrller *vc1)
          {
              vc1.freeFrame=true;
              vc1.instance.frame=CGRectMake(0, 0, _leftWidth, [vc1 screenHeight]);
              vc1.instance.parentInstance=self.weexInstance;
              [self.weexInstance addChildInstance:vc1.instance];
              LGSideMenuController *sideMenuController = [LGSideMenuController sideMenuControllerWithRootViewController:vc leftViewController:vc1
                                                                       rightViewController:nil];
              
              sideMenuController.leftViewWidth = _leftWidth;
              sideMenuController.leftViewPresentationStyle = LGSideMenuPresentationStyleSlideAbove;
               [_host addSubviewFull:sideMenuController.view];
                [self.weexInstance.viewController addChildViewController:sideMenuController];
              
          } fail:^(NSString *msg) {
              
          }  frame:CGRectMake(0, 0, _leftWidth, [self.weexInstance.viewController screenHeight]) isPortrait:true];
     } fail:^(NSString *msg) {
         
     }  frame:r isPortrait:true];
//

   
}




-(UIView*)loadView
{
 
    self.host=[UIView new];
    return self.host;
}
@end
