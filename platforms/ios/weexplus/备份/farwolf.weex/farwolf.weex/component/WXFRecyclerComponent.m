//
//  WXFRecyclerComponent.m
//  Pods
//
//  Created by 郑江荣 on 2017/8/15.
//
//

#import "WXFRecyclerComponent.h"
#import "farwolf.h"
@implementation WXFRecyclerComponent
 

- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if (self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance]) {
        if(attributes[@"backgroundColor"])
        {
              self.bgColor = [WXConvert NSString:attributes[@"backgroundColor"]]?:@"#ffffff";
        }
    }
    
    return self;
}

-(void)viewDidLoad
{
    [super viewDidLoad];
    if(self.bgColor==nil)
        self.bgColor=@"#ffffff";
     self.view.backgroundColor=[self.bgColor toColor];
}
-(void)updateAttributes:(NSDictionary *)attributes
{
    if(attributes[@"backgroundColor"])
    {
        self.bgColor = [WXConvert NSString:attributes[@"backgroundColor"]]?:@"#ffffff";
        self.view.backgroundColor=[self.bgColor toColor];
    }
}
@end
