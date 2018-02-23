//
//  WXFEmbedComponent.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/26.
//
//

#import "WXFEmbedComponent.h"
#import "farwolf.h"
#import "Weex.h"

@implementation WXFEmbedComponent

- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    
    self =[super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance];
    self.param=attributes[@"param"];
    return self;
}


-(void)loadUrl:(NSString*)url instance:(WXSDKInstance*)instance sourceurl:(NSURL*)sourceURL{
    
     instance.param=self.param;
    [instance renderWithURL:[Weex getFinalUrl:url weexInstance:self.weexInstance] options:@{@"bundleUrl":[sourceURL absoluteString]} data:nil];

}

-(void)onRenderFinish{
  
    [[self getInstance] fireGlobalEvent:@"onPageInit" params:self.param];
}

@end
