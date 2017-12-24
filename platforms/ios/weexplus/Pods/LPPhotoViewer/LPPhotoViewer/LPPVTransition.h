//
//  LPPVTransition.h
//  LPPhotoViewer
//
//  Created by litt1e-p on 16/4/5.
//  Copyright © 2016年 litt1e-p. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, LPPVTransitionType) {
    LPPVTransitionTypePresent,
    LPPVTransitionTypeDismiss
};

@interface LPPVTransition : NSObject<UIViewControllerAnimatedTransitioning>

@property (nonatomic, assign) LPPVTransitionType type;
+ (instancetype)transitionWithTargetVc:(UIViewController *)pvc transitionType:(LPPVTransitionType)type;
- (instancetype)initWithTransitionType:(LPPVTransitionType)type;

@end