//
//  LPPhotoView.m
//  LPPhotoViewer
//
//  Created by litt1e-p on 16/3/27.
//  Copyright © 2016年 litt1e-p. All rights reserved.
//

#import "LPPhotoView.h"
#import "UIImageView+WebCache.h"
#import "DACircularProgressView.h"

@interface LPPhotoView ()<UIScrollViewDelegate, UIGestureRecognizerDelegate>

@property (nonatomic, strong) UIScrollView *scrollView;;
@property (nonatomic, strong) UIDynamicAnimator *animator;
@property (nonatomic, strong) UIAttachmentBehavior *imgAttatchment;
@property (nonatomic, strong) UIPanGestureRecognizer *panGr;
@property (nonatomic, strong) DACircularProgressView *progressView;

@end

@implementation LPPhotoView

- (instancetype)initWithFrame:(CGRect)frame withPhotoUrl:(NSString *)photoUrl
{
    self = [super initWithFrame:frame];
    if (self) {
        [self sharedScrollViewInit];
        self.imageView             = [[UIImageView alloc] initWithFrame:self.bounds];
        self.imageView.contentMode = UIViewContentModeScaleAspectFit;
        
        SDWebImageManager *manager = [SDWebImageManager sharedManager];
        BOOL isCached              = [manager cachedImageExistsForURL:[NSURL URLWithString:photoUrl]];
        if (!isCached) {
            [self addSubview:self.progressView];
        }
        
        [self.imageView sd_setImageWithURL:[NSURL URLWithString:photoUrl] placeholderImage:nil options:0 progress:^(NSInteger receivedSize, NSInteger expectedSize){
            [self.progressView setProgress:((float)receivedSize)/expectedSize];
        } completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, NSURL *imageURL){
            if (!isCached || error) {
                 [self.progressView removeFromSuperview];
            }
        }];
        
        [self.imageView setUserInteractionEnabled:YES];
        [_scrollView addSubview:self.imageView];
        [self sharedGestureInit];
    }
    return self;
}

- (instancetype)initWithFrame:(CGRect)frame withPhotoImage:(UIImage *)image
{
    self = [super initWithFrame:frame];
    if (self) {
        [self sharedScrollViewInit];
        self.imageView = [[UIImageView alloc] initWithFrame:self.bounds];
        self.imageView.contentMode = UIViewContentModeScaleAspectFit;
        [self.imageView setImage:image];
        [self.imageView setUserInteractionEnabled:YES];
        [_scrollView addSubview:self.imageView];
        [self sharedGestureInit];
    }
    return self;
}

- (void)sharedScrollViewInit
{
    _scrollView                                = [[UIScrollView alloc] initWithFrame:self.bounds];
    _scrollView.delegate                       = self;
    _scrollView.showsHorizontalScrollIndicator = NO;
    _scrollView.showsVerticalScrollIndicator   = NO;
    _scrollView.decelerationRate = UIScrollViewDecelerationRateFast;
    _scrollView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    [self addSubview:_scrollView];
    
}

- (void)setMaxMinZoomScalesForCurrentBounds
{
    CGSize boundsSize = self.scrollView.bounds.size;
    CGSize imageSize  = self.imageView.frame.size;
    CGFloat xScale    = boundsSize.width / imageSize.width;
    CGFloat yScale    = boundsSize.height / imageSize.height;
    CGFloat minScale  = MIN(xScale, yScale);
    CGFloat maxScale  = 4.0;
    if ([UIScreen instancesRespondToSelector:@selector(scale)]) {
        maxScale = maxScale / [[UIScreen mainScreen] scale];
        if (maxScale < minScale) {
            maxScale = minScale * 2;
        }
    }
    self.scrollView.maximumZoomScale = maxScale;
    self.scrollView.minimumZoomScale = minScale;
    self.scrollView.zoomScale = minScale;
}

- (void)sharedGestureInit
{
    UITapGestureRecognizer *singleTap    = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handleSingleTap:)];
    UITapGestureRecognizer *doubleTap    = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handleDoubleTap:)];
    UITapGestureRecognizer *twoFingerTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handleTwoFingerTap:)];
    _panGr                               = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(dragEvent:)];
    singleTap.numberOfTapsRequired       = 1;
    singleTap.numberOfTouchesRequired    = 1;
    doubleTap.numberOfTapsRequired       = 2;
    twoFingerTap.numberOfTouchesRequired = 2;
    
    [self.imageView addGestureRecognizer:singleTap];
    [self.imageView addGestureRecognizer:doubleTap];
    [self.imageView addGestureRecognizer:twoFingerTap];
    [self.imageView addGestureRecognizer:_panGr];
    
    [singleTap requireGestureRecognizerToFail:doubleTap];
    self.animator = [[UIDynamicAnimator alloc] initWithReferenceView:_scrollView];
    [self setMaxMinZoomScalesForCurrentBounds];
}

- (void)setDisableHorizontalDrag:(BOOL)disableHorizontalDrag
{
    _disableHorizontalDrag = disableHorizontalDrag;
    if (disableHorizontalDrag) {
        _panGr.delegate = self;
    }
}

#pragma mark - Gesture Recognizer Delegate
- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer
{
    CGPoint velocity = [(UIPanGestureRecognizer *)gestureRecognizer velocityInView:_scrollView];
    return fabs(velocity.y) > fabs(velocity.x);
}

#pragma mark - UIScrollViewDelegate
- (UIView *)viewForZoomingInScrollView:(UIScrollView *)scrollView
{
    return self.imageView;
}

- (void)scrollViewDidZoom:(UIScrollView *)scrollView
{
    [self.animator removeAllBehaviors];
    [self centerScrollViewContents];
}

- (void)centerScrollViewContents
{
    CGSize boundsSize    = self.scrollView.bounds.size;
    CGRect contentsFrame = self.imageView.frame;
    
    if (contentsFrame.size.width < boundsSize.width) {
        contentsFrame.origin.x = (boundsSize.width - contentsFrame.size.width) / 2.0f;
    } else {
        contentsFrame.origin.x = 0.0f;
    }
    if (contentsFrame.size.height < boundsSize.height) {
        contentsFrame.origin.y = (boundsSize.height - contentsFrame.size.height) / 2.0f;
    } else {
        contentsFrame.origin.y = 0.0f;
    }
    [UIView animateWithDuration:0.25 animations:^{
        self.imageView.frame = contentsFrame;
    }];
}

#pragma mark - tap event
- (void)handleSingleTap:(UITapGestureRecognizer *)gestureRecognizer
{
    if (gestureRecognizer.numberOfTapsRequired == 1) {
        [self.delegate tapHiddenPhotoView];
    }
}

- (void)handleDoubleTap:(UITapGestureRecognizer *)gestureRecognizer
{
    if (gestureRecognizer.numberOfTapsRequired == 2) {
        if(_scrollView.zoomScale == _scrollView.minimumZoomScale){
            float newScale  = _scrollView.maximumZoomScale;
            CGRect zoomRect = [self zoomRectForScale:newScale withCenter:[gestureRecognizer locationInView:gestureRecognizer.view]];
            [_scrollView zoomToRect:zoomRect animated:YES];
        }else{
            float newScale  = _scrollView.minimumZoomScale;
            CGRect zoomRect = [self zoomRectForScale:newScale withCenter:[gestureRecognizer locationInView:gestureRecognizer.view]];
            [_scrollView zoomToRect:zoomRect animated:YES];
        }
    }
}

- (void)handleTwoFingerTap:(UITapGestureRecognizer *)gestureRecongnizer
{
    float newScale  = [_scrollView zoomScale] / 2;
    CGRect zoomRect = [self zoomRectForScale:newScale withCenter:[gestureRecongnizer locationInView:gestureRecongnizer.view]];
    [_scrollView zoomToRect:zoomRect animated:YES];
}

#pragma mark - dragEvent
- (void)dragEvent:(UIPanGestureRecognizer *)recognizer
{
    if (recognizer.state == UIGestureRecognizerStateBegan) {
        [self.animator removeAllBehaviors];
        
        CGPoint location = [recognizer locationInView:self.scrollView];
        CGPoint imgLocation = [recognizer locationInView:self.imageView];
        
        UIOffset centerOffset = UIOffsetMake(imgLocation.x - CGRectGetMidX(self.imageView.bounds),
                                             imgLocation.y - CGRectGetMidY(self.imageView.bounds));
        self.imgAttatchment = [[UIAttachmentBehavior alloc] initWithItem:self.imageView offsetFromCenter:centerOffset attachedToAnchor:location];
        [self.animator addBehavior:self.imgAttatchment];
    } else if (recognizer.state == UIGestureRecognizerStateChanged) {
        CGPoint ancPoint = [recognizer locationInView:self.scrollView];
        [self.imgAttatchment setAnchorPoint:ancPoint];
        CGFloat halfH = self.scrollView.bounds.size.height * 0.5f;
        [self offsetDragNofify: 1.f - fabs(ancPoint.y - halfH) / halfH];
    } else if (recognizer.state == UIGestureRecognizerStateEnded) {
        CGPoint location = [recognizer locationInView:self.scrollView];
        CGRect closeTopThreshhold = CGRectMake(0, 0, self.bounds.size.width, self.bounds.size.height * .25);
        CGRect closeBottomThreshhold = CGRectMake(0, self.bounds.size.height - closeTopThreshhold.size.height, self.bounds.size.width, self.bounds.size.height * .25);
        if (CGRectContainsPoint(closeTopThreshhold, location) || CGRectContainsPoint(closeBottomThreshhold, location)) {
            [self.animator removeAllBehaviors];
            self.imageView.userInteractionEnabled = NO;
            self.scrollView.userInteractionEnabled = NO;
            
            UIGravityBehavior *exitGravity = [[UIGravityBehavior alloc] initWithItems:@[self.imageView]];
            if (CGRectContainsPoint(closeTopThreshhold, location)) {
                exitGravity.gravityDirection = CGVectorMake(0.0, -1.0);
            }
            exitGravity.magnitude = 15.0f;
            [self.animator addBehavior:exitGravity];
            
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.25f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [self dismissNotify];
                [self offsetDragNofify: 0.f];
            });
        } else {
            [self zoomReset];
            UISnapBehavior *snapBack = [[UISnapBehavior alloc] initWithItem:self.imageView snapToPoint:self.scrollView.center];
            snapBack.damping = 0.2;
            [self.animator addBehavior:snapBack];
            [self offsetDragNofify: 1.f];
        }
    }
}

- (void)zoomReset
{
    CGRect zoomRect = [self zoomRectForScale:self.scrollView.minimumZoomScale withCenter:self.scrollView.center];
    [_scrollView zoomToRect:zoomRect animated:YES];
}

#pragma mark - zoomRectForScale
- (CGRect)zoomRectForScale:(CGFloat)scale withCenter:(CGPoint)center
{
    CGRect zoomRect;
    zoomRect.size.height = _scrollView.frame.size.height / scale;
    zoomRect.size.width  = _scrollView.frame.size.width / scale;
    zoomRect.origin.x    = center.x - zoomRect.size.width / 2;
    zoomRect.origin.y    = center.y - zoomRect.size.height / 2;
    return zoomRect;
}

- (void)dismissNotify
{
    if (self.delegate && [self.delegate respondsToSelector:@selector(dragToDismiss)]) {
        [self.delegate dragToDismiss];
    }
}

- (void)offsetDragNofify:(CGFloat)offsetY
{
    if (self.delegate && [self.delegate respondsToSelector:@selector(offsetYForDrag:)]) {
        [self.delegate offsetYForDrag:offsetY];
    }
}

#pragma mark - lazy loads 
- (DACircularProgressView *)progressView
{
    if (!_progressView) {
        CGFloat screenWidth                  = self.bounds.size.width;
        CGFloat screenHeight                 = self.bounds.size.height;
        DACircularProgressView *progressView = [[DACircularProgressView alloc] initWithFrame:CGRectMake((screenWidth-35.)/2., (screenHeight-35.)/2, 35.0f, 35.0f)];
        [progressView setProgress:0.0f];
        progressView.thicknessRatio    = 0.1;
        progressView.roundedCorners    = NO;
        progressView.trackTintColor    = [UIColor colorWithWhite:0.2 alpha:1];
        progressView.progressTintColor = [UIColor colorWithWhite:1.0 alpha:1];
        _progressView                  = progressView;
    }
    return _progressView;
}

@end
