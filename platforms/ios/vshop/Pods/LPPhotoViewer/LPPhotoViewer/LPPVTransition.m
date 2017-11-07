//
//  LPPVTransition.m
//  LPPhotoViewer
//
//  Created by litt1e-p on 16/4/5.
//  Copyright © 2016年 litt1e-p. All rights reserved.
//

#import "LPPVTransition.h"

@interface LPPVTransition ()

@property (nonatomic, weak) UIViewController *targetVc;

@end

@implementation LPPVTransition

static NSString * const kLPPVTransitionErr = @"__FAILED_LPPVTRANSITION__";

+ (instancetype)transitionWithTargetVc:(UIViewController *)vc transitionType:(LPPVTransitionType)type
{
    
    LPPVTransition *trans = [[self alloc] initWithTransitionType:type];
    trans.targetVc = vc;
    return trans;
}

- (instancetype)initWithTransitionType:(LPPVTransitionType)type
{
    self = [super init];
    if (self) {
        _type = type;
    }
    return self;
}

- (NSTimeInterval)transitionDuration:(id<UIViewControllerContextTransitioning>)transitionContext
{
    return 0.25;
}

- (void)animateTransition:(id<UIViewControllerContextTransitioning>)transitionContext
{
    switch (_type) {
        case LPPVTransitionTypePresent:
            [self presentAnimation:transitionContext];
            break;
            
        case LPPVTransitionTypeDismiss:
            [self dismissAnimation:transitionContext];
            break;
    }
}

- (void)dismissAnimation:(id<UIViewControllerContextTransitioning>)transitionContext
{
    self.targetVc.view.alpha = 1.0;
    [UIView animateWithDuration:[self transitionDuration:transitionContext] animations:^{
        self.targetVc.view.alpha = 0.0;
    } completion:^(BOOL finished) {
        BOOL wasCancel = [transitionContext transitionWasCancelled];
        [transitionContext completeTransition:!wasCancel];
        if (wasCancel) {
            self.targetVc.view.alpha = 1.0;
            NSLog(kLPPVTransitionErr);
        } else {
            [self.targetVc.view removeFromSuperview];
        }
    }];
}

- (void)presentAnimation:(id<UIViewControllerContextTransitioning>)transitionContext
{
    UIView *containerView = [transitionContext containerView];
    [containerView addSubview:self.targetVc.view];
    self.targetVc.view.alpha = 0.0;
    [UIView animateWithDuration:[self transitionDuration:transitionContext] animations:^{
        self.targetVc.view.alpha = 1.0;
    } completion:^(BOOL finished) {
        BOOL wasCancel = [transitionContext transitionWasCancelled];
        [transitionContext completeTransition:!wasCancel];
        if (wasCancel) {
            NSLog(kLPPVTransitionErr);
        }
    }];
}

@end
