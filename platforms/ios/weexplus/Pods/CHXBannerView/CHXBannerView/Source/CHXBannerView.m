//
//  CHXBannerView.m
//  CHXBannerView
//
//  Created by Moch Xiao on 2015-03-01.
//  Copyright (c) 2014 Moch Xiao (https://github.com/atcuan).
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
//

#import "CHXBannerView.h"

#pragma mark - WeakTarget

@interface _CHXWeakProxy : NSProxy

@property (nonatomic, weak, readonly) id target;
- (instancetype)initWithTarget:(id)target;
+ (instancetype)proxyWithTarget:(id)target;

@end

@implementation _CHXWeakProxy

- (instancetype)initWithTarget:(id)target {
    _target = target;
    return self;
}

+ (instancetype)proxyWithTarget:(id)target {
    return [[_CHXWeakProxy alloc] initWithTarget:target];
}

- (id)forwardingTargetForSelector:(SEL)selector {
    return _target;
}

- (void)forwardInvocation:(NSInvocation *)invocation {
    void *null = NULL;
    [invocation setReturnValue:&null];
}

- (NSMethodSignature *)methodSignatureForSelector:(SEL)selector {
    return [NSObject instanceMethodSignatureForSelector:@selector(init)];
}

- (BOOL)respondsToSelector:(SEL)aSelector {
    return [_target respondsToSelector:aSelector];
}

- (BOOL)isEqual:(id)object {
    return [_target isEqual:object];
}

- (NSUInteger)hash {
    return [_target hash];
}

- (Class)superclass {
    return [_target superclass];
}

- (Class)class {
    return [_target class];
}

- (BOOL)isKindOfClass:(Class)aClass {
    return [_target isKindOfClass:aClass];
}

- (BOOL)isMemberOfClass:(Class)aClass {
    return [_target isMemberOfClass:aClass];
}

- (BOOL)conformsToProtocol:(Protocol *)aProtocol {
    return [_target conformsToProtocol:aProtocol];
}

- (BOOL)isProxy {
    return YES;
}

- (NSString *)description {
    return [_target description];
}

- (NSString *)debugDescription {
    return [_target debugDescription];
}

@end

#pragma mark - NSTimer

@interface NSTimer (_CHXAddition)

- (void)pause;
- (void)pauseAfterDuration:(NSTimeInterval)interval;
- (void)resume;
- (void)resumeAfterDuration:(NSTimeInterval)interval;

@end

@implementation NSTimer (_CHXAddition)

- (void)pause {
    self.fireDate = [NSDate distantFuture];
}

- (void)pauseAfterDuration:(NSTimeInterval)interval {
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(interval * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [self pause];
    });
}

- (void)resume {
    self.fireDate = [NSDate date];
}

- (void)resumeAfterDuration:(NSTimeInterval)interval {
    self.fireDate = [NSDate dateWithTimeIntervalSinceNow:interval];
}

@end


#pragma mark - CHXBannerView

@interface CHXBannerView () <UIScrollViewDelegate>
@property (nonatomic, strong) UIScrollView *baseScrollView;
@property (nonatomic, strong) UIPageControl *pageControl;
@property (nonatomic, assign) NSUInteger currentIndex;
@property (nonatomic, strong) NSArray *imageViewList;
@property (nonatomic, strong) NSTimer *timer;

// DataSource
@property (nonatomic, assign) NSInteger numberOfPages;
@property (nonatomic, assign) NSTimeInterval timeIntervalOfTransitionsAnimation;

@end

@implementation CHXBannerView

#if DEBUG
- (void)dealloc {
    NSLog(@"%s", __FUNCTION__);
}
#endif

#pragma mark - Override methods

- (id)initWithCoder:(NSCoder *)aDecoder {
    self = [super initWithCoder:aDecoder];
    if (!self) {
        return nil;
    }
    
    [self pr_initializeControls];
    
    return self;
}

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (!self) {
        return nil;
    }
    
    [self pr_initializeControls];
    
    return self;
}

- (void)layoutSubviews {
    [super layoutSubviews];
    
    [self.timer pause];
    [self pr_updateControlsFrame];
    [self.timer resumeAfterDuration:self.timeIntervalOfTransitionsAnimation];
}

- (void)didMoveToWindow {
    [super didMoveToWindow];
    
    // When push pages, will inovke three times (tested in iOS8.4)
    // There nil, not nil, nil
    // so cant not use resume after some time duration, because
    // pause will override the resume action
    // so follow line means interrupt the animation
    // when go back the current page, go on the animation
    // if last cost half time, then anohter half time after will
    // run the animation
    if (self.window) {
        [self.timer resume];
    } else {
        [self.timer pause];
    }
}

- (void)didMoveToSuperview {
    [super didMoveToSuperview];
    
    if (!self.superview) {
        [self.timer invalidate];
        self.timer = nil;
    }
}

#pragma mark - Private

- (void)pr_initializeControls {
    self.transitionDuration = 5;
    
    self.baseScrollView = [[UIScrollView alloc] initWithFrame:self.bounds];
    self.baseScrollView.translatesAutoresizingMaskIntoConstraints = NO;
    self.baseScrollView.contentSize = CGSizeMake(CGRectGetWidth(self.bounds) * 3, CGRectGetHeight(self.bounds));
    self.baseScrollView.pagingEnabled = YES;
    self.baseScrollView.contentOffset = CGPointMake(CGRectGetWidth(self.bounds), 0);
    self.baseScrollView.showsVerticalScrollIndicator = NO;
    self.baseScrollView.showsHorizontalScrollIndicator = NO;
    self.baseScrollView.delegate = self;
    self.baseScrollView.scrollsToTop = NO;
    self.baseScrollView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    [self addSubview:self.baseScrollView];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pr_handleDidSelectItem)];
    [self.baseScrollView addGestureRecognizer:tap];
    
    self.pageControl = [UIPageControl new];
    self.pageControl.userInteractionEnabled = NO;
    [self addSubview:self.pageControl];
    
    NSMutableArray *imageViewList = [NSMutableArray new];
    for (int i = 0; i < 3; i++) {
        UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(CGRectGetWidth(self.baseScrollView.bounds) * i,
                                                                               0,
                                                                               CGRectGetWidth(self.baseScrollView.bounds),
                                                                               CGRectGetHeight(self.baseScrollView.bounds))];
        imageView.contentMode = UIViewContentModeScaleAspectFill;
        imageView.layer.masksToBounds = YES;
        imageView.tag = i;
        
        [self.baseScrollView addSubview:imageView];
        [imageViewList addObject:imageView];
    }
    self.imageViewList = imageViewList;
}

- (void)pr_updateControlsFrame {
    // set contentOffset will trigger delegate method, which will call some unimplements block yet.
    self.baseScrollView.delegate = nil;
    self.baseScrollView.frame = self.bounds;
    self.baseScrollView.contentSize = CGSizeMake(CGRectGetWidth(self.bounds) * 3, CGRectGetHeight(self.bounds));
    self.baseScrollView.contentOffset = CGPointMake(CGRectGetWidth(self.bounds), 0);
    for (int i = 0; i < 3; i++) {
        UIImageView *imageView = (UIImageView *)self.imageViewList[i];
        imageView.frame = CGRectMake(CGRectGetWidth(self.baseScrollView.bounds) * i,
                                     0,
                                     CGRectGetWidth(self.baseScrollView.bounds),
                                     CGRectGetHeight(self.baseScrollView.bounds));
    }
    self.baseScrollView.delegate = self;
    self.pageControl.bounds = CGRectMake(0, 0, CGRectGetWidth(self.bounds), 30);
    self.pageControl.center = CGPointMake(CGRectGetMidX(self.bounds), CGRectGetMaxY(self.bounds) - CGRectGetMidY(self.pageControl.bounds));
}

- (NSInteger)pr_realIndexWithIndex:(NSInteger)index {
    NSInteger numberOfPages = self.numberOfPages;
    // 更新是否允许用户交互
    self.baseScrollView.scrollEnabled = numberOfPages > 1;
    // 获取最大索引
    NSInteger maximumIndex = numberOfPages - 1;
    maximumIndex = maximumIndex > 0 ? maximumIndex : 0;
    // 判断真实索引位置
    if (index > maximumIndex) {
        index = 0;
    } else if (index < 0) {
        index = maximumIndex;
    }
    
    return index;
}

- (void)pr_updateUserInterfaceWithScrollViewContentOffset:(CGPoint)contentOffset {
    BOOL shouldUpdate = NO;
    
    if (contentOffset.x >= CGRectGetWidth(self.baseScrollView.bounds) * 2) {
        // 向右
        shouldUpdate = YES;
        self.currentIndex = [self pr_realIndexWithIndex:++self.currentIndex];
    } else if (contentOffset.x <= 0) {
        // 向左
        shouldUpdate = YES;
        self.currentIndex = [self pr_realIndexWithIndex:--self.currentIndex];
    }
    
    // 判断是否需要更新
    if (!shouldUpdate) {
        return;
    }
    
    // 更新 page control 显示
    self.pageControl.currentPage = self.currentIndex;
    
    // 更新所有imageView显示的图片
    [self pr_presentImageViewForIndex];
    
    // 恢复可见区域
    self.baseScrollView.contentOffset = CGPointMake(CGRectGetWidth(self.baseScrollView.bounds), 0);
}

- (void)pr_handleSwitchImageView:(NSTimer *)sender {
    if (self.numberOfPages <= 1) {
        [sender pause];
        return;
    }
    
    CGPoint newOffset = CGPointMake(self.baseScrollView.contentOffset.x + CGRectGetWidth(self.baseScrollView.bounds), 0);
    [self.baseScrollView setContentOffset:newOffset animated:YES];
}

#pragma mark - <UIScrollViewDelegate>

- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView {
    [self.timer pause];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate {
    [self.timer resumeAfterDuration:self.transitionDuration];
}

// scrollview滚动
- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    [self pr_updateUserInterfaceWithScrollViewContentOffset:scrollView.contentOffset];
}

// scrollView停止减速
- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    [self pr_updateUserInterfaceWithScrollViewContentOffset:scrollView.contentOffset];
}

#pragma mark - Public

- (void)setBackgroundImage:(UIImage *)backgroundImage {
    self.layer.contents = (id)backgroundImage.CGImage;
}

- (UIImage *)backgroundImage {
    return [UIImage imageWithCGImage:(__bridge CGImageRef)((id)self.layer.contents)];
}

- (void)reloadData {
    NSInteger numberOfPages = [self pr_numberOfPages];
    self.numberOfPages = numberOfPages;
    self.pageControl.numberOfPages = numberOfPages;
    self.pageControl.hidden = numberOfPages <= 1;
    self.baseScrollView.scrollEnabled = numberOfPages > 1;
    
    self.currentIndex = numberOfPages + 1;
    [self pr_updateUserInterfaceWithScrollViewContentOffset:CGPointZero];
    
    [self.timer resumeAfterDuration:self.transitionDuration];
}

#pragma mark - CHXBannerViewDataSource inner invoke

- (NSInteger)pr_numberOfPages {
    if (self.dataSource && [self.dataSource respondsToSelector:@selector(numberOfPagesInBannerView:)]) {
        return [self.dataSource numberOfPagesInBannerView:self];
    }
    
    return 0;
}

- (void)pr_presentImageViewForIndex {
    if (self.dataSource && [self.dataSource respondsToSelector:@selector(bannerView:presentImageView:forIndex:)]) {
        [self.dataSource bannerView:self presentImageView:self.imageViewList[0] forIndex:[self pr_realIndexWithIndex:self.currentIndex - 1]];
        [self.dataSource bannerView:self presentImageView:self.imageViewList[1] forIndex:[self pr_realIndexWithIndex:self.currentIndex]];
        [self.dataSource bannerView:self presentImageView:self.imageViewList[2] forIndex:[self pr_realIndexWithIndex:self.currentIndex + 1]];
    }
}

#pragma mark - CHXBannerViewDelegate inner invoke

- (void)pr_handleDidSelectItem {
    if (self.delegate && [self.delegate respondsToSelector:@selector(bannerView:didSelectItemAtIndex:)]) {
        [self.delegate bannerView:self didSelectItemAtIndex:self.currentIndex];
    }
}

#pragma mark - Accessor

- (void)setTransitionDuration:(NSTimeInterval)transitionDuration {
    if (_transitionDuration != transitionDuration) {
        _transitionDuration = transitionDuration;
        [self.timer invalidate];
        self.timer = nil;
        if (transitionDuration > 0) {
            _CHXWeakProxy *target = [_CHXWeakProxy proxyWithTarget:self];
            self.timer = [NSTimer timerWithTimeInterval:transitionDuration target:target selector:@selector(pr_handleSwitchImageView:) userInfo:nil repeats:YES];
            [NSRunLoop.currentRunLoop addTimer:self.timer forMode:NSRunLoopCommonModes];
            self.timer.fireDate = [NSDate distantFuture];
        }
    }
}

@end
