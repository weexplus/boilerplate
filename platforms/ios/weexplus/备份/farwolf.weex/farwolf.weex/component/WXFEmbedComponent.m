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

-(void)loadUrl:(NSString*)url instance:(WXSDKInstance*)instance sourceurl:(NSURL*)sourceURL{
    
    
    
    [instance renderWithURL:[Weex getFinalUrl:url weexInstance:instance] options:@{@"bundleUrl":[sourceURL absoluteString]} data:nil];

}

-(void)onRenderFinish{
    
    [[self getInstance] fireGlobalEvent:@"onPageInit" params:nil];
}

@end
