//
//  WXFWebComponent.m
//  Pods
//
//  Created by 郑江荣 on 2017/7/26.
//
//

#import "WXFWebComponent.h"
#import "farwolf.h"
#import "Weex.h"
@implementation WXFWebComponent

- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if (self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance]) {
        _bounce = attributes[@"bounce"] ? [WXConvert BOOL:attributes[@"bounce"]] : YES;
    }
    return self;
}



-(void)viewDidLoad
{
    [super viewDidLoad];
    [self.view setBackgroundColor:[UIColor clearColor]];
    self.view.opaque=false;
    //    if(_s!=nil&&![@"" isEqualToString:_s])
    //    {
    //        [self loadURL:_s];
    //    }
    ((UIWebView*)self.view).scrollView.bounces=_bounce;
    
}



//- (void)setUrl:(NSString *)url
//{
//    if([url startWith:@"http"])
//    {
//        [super setUrl:url];
//    }
//    else
//    {
//        self.s=url;
//    }
//}
//
- (void)loadURL:(NSString *)url
{
    if([url startWith:@"root:"]){
        NSURL *ul=   url=[Weex getFinalUrl:url weexInstance:self.weexInstance];
        [super loadURL:ul.absoluteString];
    }else{
        [super loadURL:url];
    }
    
}

- (void) setSource:(NSString *)source{
    
    if(source!=nil)
    [((UIWebView*)self.view) loadHTMLString:source baseURL:nil];
}
@end
