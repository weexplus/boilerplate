//
//  WXZoomImage.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/12/10.
//

#import "WXZoomImage.h"

#import "Masonry.h"
@implementation WXZoomImage
-(instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if(self= [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance])
    return self;
}
-(UIView*)loadView
{
  
    
    // add as a subview to another view
//    YBPhotoBrowserView *v=[YBPhotoBrowserView new];
    UIView *v=[UIView new];
//    [v addSubview:sv];
    return v;
}

-(void)viewDidLoad
{
    
   
    }



@end
