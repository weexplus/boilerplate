/**
 * Created by Weex.
 * Copyright (c) 2016, Alibaba, Inc. All rights reserved.
 *
 * This source code is licensed under the Apache Licence 2.0.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface WXPageDomainUtility : NSObject

@property (nonatomic, assign) BOOL  stopRunning;

+ (UIImage *)screencastDataScale:(float)scale;

+ (NSMutableDictionary *)screencastMetaDataWithScale:(float)scale;

+ (UIViewController *)getCurrentKeyController;
    
+(BOOL)isContainWebView:(UIView *)rootView;


/**
 * @abstract execute asynchronous action block on the main thread.
 *
 */
extern void WXPerformBlockOnScreencastThread(void (^block)(void));
@end
