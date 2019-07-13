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
WX_EXPORT_METHOD(@selector(excuteJs:))
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
    if([url isEqualToString:@""]){
        return;
    }
    if([url startWith:@"root:"]){
        NSString *path=@"";
        NSString *param=@"";
        if([url contains:@"?"]){
            path=[url split:@"?"][0];
            param=[url split:@"?"][1];
        }else{
            path=url;
        }
        NSURL *ul=[Weex getFinalUrl:path weexInstance:self.weexInstance];
        url=[[ul.absoluteString add:@"?"]add:param];
    }
    if([url startWith:@"http"]){
        [super loadURL:url];
    }else{
        NSString *path=@"";
        NSString *param=@"";
        if([url startWith:PREFIX_SDCARD]){
            url=[url replace:PREFIX_SDCARD withString:@""];
        }
        if([url contains:@"?"]){
            path=[url split:@"?"][0];
            param=[url split:@"?"][1];
        }else{
            path=url;
        }
        NSURL *fileUrl = [NSURL fileURLWithPath:path isDirectory:NO];//此部分没有?所以没有问题，isDirectory=YES会导致多一层目录。
        NSURLComponents *urlComponents = [NSURLComponents componentsWithURL:fileUrl resolvingAgainstBaseURL:NO];
        NSMutableArray *ary=[param split:@"&"];
        NSMutableArray *qrys=[NSMutableArray new ];
        if([param contains:@"&"]){
            for(NSString *p in ary){
                NSString *key=@"";
                NSString *v=@"";
                NSArray *kv= [p split:@"="];
                if(kv>0){
                    key=kv[0];
                }
                if(kv>1){
                    v=kv[1];
                }
                [qrys addObject:[NSURLQueryItem queryItemWithName:key value:v]];
            }
            [urlComponents setQueryItems:qrys];
        }
        [super loadURL:urlComponents.URL.absoluteString];
        
        
        
    }
    
    
}


- (void) setSource:(NSString *)source{
    
    if(source!=nil)
        [((UIWebView*)self.view) loadHTMLString:source baseURL:nil];
}
@end
