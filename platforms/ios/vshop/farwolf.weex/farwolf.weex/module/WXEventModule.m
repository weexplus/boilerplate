/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import "WXEventModule.h"
#import "WXNormalViewContrller.h"
#import <WeexSDK/WXBaseViewController.h>

@implementation WXEventModule

@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(openURL:))

- (void)openURL:(NSString *)url
{
    NSString *newURL = url;
    if ([url hasPrefix:@"//"]) {
        newURL = [NSString stringWithFormat:@"http:%@", url];
    } else if (![url hasPrefix:@"http"]) {
        // relative path
        newURL = [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL].absoluteString;
    }
    
//    WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
//     vc.hidesBottomBarWhenPushed = YES;
//    [[self.weexInstance.viewController navigationController] pushViewController:vc animated:YES];
//    [vc preRender:newURL finish:^{
//        [[self.weexInstance.viewController navigationController] pushViewController:vc animated:YES];
//    }];
    
    
    [WeexFactory render:[NSURL URLWithString:newURL] compelete:^(Page *p) {
       
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
         vc.hidesBottomBarWhenPushed = YES;
        vc.page=p;
        [[self.weexInstance.viewController navigationController] pushViewController:vc animated:YES];
        
    }];

   
   
 
}

@end

