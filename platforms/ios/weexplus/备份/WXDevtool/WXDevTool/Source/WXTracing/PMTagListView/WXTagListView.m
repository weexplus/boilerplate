//
//  WXTagListView.m
//  ipaimai
//
//  Created by Jun.Shi on 1/27/15.
//  Copyright (c) 2015 taobao. All rights reserved.
//

#import "WXTagListView.h"

static int kSegmentedPadding = 40;
static int kAlphaPadding = 5;
// static int kHeight = 400;
#define kHeight ([UIScreen mainScreen].bounds.size.height - 64)

@interface WXTagListView ()

@property (assign) BOOL isExpand;
@property (strong) UIImageView *arrowImageView;
@property (strong) UIView *tagListContainerView;
@property (strong) UILabel *titleLabel;
@property (strong) UIView *tagContainerView;

@end

@implementation WXTagListView

- (instancetype)init
{
    self = [super init];
    if (self) {
        [self __initSegmentedControl];
    }
    return self;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self __initSegmentedControl];
    }
    return self;
}

- (void)__initSegmentedControl
{
    _segmentedControl = ({
        WXSegmentedControl *segmented = [[WXSegmentedControl alloc]
            initWithFrame:CGRectMake(0, 0, self.bounds.size.width - kSegmentedPadding + kAlphaPadding,
                                     self.bounds.size.height)];
        segmented.font = [UIFont boldSystemFontOfSize:16];
        segmented.textColor = [UIColor blackColor];
        segmented.selectedTextColor = [UIColor redColor];
        segmented.autoresizingMask =
            UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleWidth;
        segmented.selectionStyle = WXSegmentedControlSelectionStyleTextWidthStripe;
        segmented.selectionIndicatorLocation = WXSegmentedControlSelectionIndicatorLocationDown;
        segmented.selectionIndicatorHeight = 2.0;
        segmented.selectionIndicatorColor = [UIColor redColor];
        segmented.type = WXSegmentedControlTypeText;
        segmented.segmentWidthStyle = WXSegmentedControlSegmentWidthStyleDynamic;
        [segmented addTarget:self
                      action:@selector(segmentedControlChangedValue:)
            forControlEvents:UIControlEventValueChanged];

        CALayer *bottomBorder = [CALayer layer];
        bottomBorder.frame = CGRectMake(0, self.bounds.size.height, self.bounds.size.width, 0.5);
        bottomBorder.backgroundColor = [UIColor colorWithWhite:0.8f alpha:1.0f].CGColor;
        [segmented.layer addSublayer:bottomBorder];

        [self addSubview:segmented];
        segmented;
    });
}

- (void)__initTagList
{

    _tagListContainerView = ({
        UIView *containerView =
            [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.bounds.size.height, self.bounds.size.height)];
        containerView.backgroundColor = [UIColor clearColor];
        containerView.alpha = 0;

        UIToolbar *toolbar = [[UIToolbar alloc] initWithFrame:self.bounds];
        toolbar.autoresizingMask =
            UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
        [toolbar setBarStyle:UIBarStyleDefault];
        [containerView addSubview:toolbar];

        [self addSubview:containerView];
        containerView;
    });

    _titleLabel = ({
        UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(9, 12, 70, 30)];
        label.font = [UIFont systemFontOfSize:16];
        label.textColor = [UIColor blackColor];
        label.textAlignment = NSTextAlignmentCenter;
        label.text = @"选一个吧";
        [_tagListContainerView addSubview:label];
        label;
    });
}

-(void)__initArrowView
{
    [self initArrowView];
}

- (void)initArrowView
{
    
    _tagContainerView = [[UIView alloc] init];

    CGFloat tagContainerX =
        _segmentedControl.frame.origin.x + _segmentedControl.frame.size.width - kAlphaPadding;
    CGFloat tagContainerY = _segmentedControl.frame.origin.y;
    CGFloat tagContainerW =
        self.frame.size.width - _segmentedControl.frame.size.width + kAlphaPadding;
    CGFloat tagContainerH = self.frame.size.height;

    _tagContainerView.frame =
        CGRectMake(tagContainerX, tagContainerY, tagContainerW, tagContainerH);
    _tagContainerView.backgroundColor = [UIColor whiteColor];
    [self addSubview:_tagContainerView];

    UITapGestureRecognizer *tagGesture =
        [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onTapDown:)];
    [_tagContainerView addGestureRecognizer:tagGesture];

    _arrowImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"down_arrow"]];
    if (floor(NSFoundationVersionNumber) > NSFoundationVersionNumber_iOS_6_1) {
        _arrowImageView.tintAdjustmentMode = UIViewTintAdjustmentModeDimmed;
        _arrowImageView.tintColor = [UIColor blackColor];
    }

    _arrowImageView.alpha = 1;
    _arrowImageView.frame = CGRectMake(10, 10, 20, 20);
    _arrowImageView.contentMode = UIViewContentModeScaleAspectFit;

    [_tagContainerView addSubview:_arrowImageView];
}

- (void)bindData:(NSArray *)titles
{
    NSMutableArray *localTitles = [NSMutableArray new];
    [titles enumerateObjectsUsingBlock:^(id obj, NSUInteger idx, BOOL *stop) {
        if ([obj isKindOfClass:[NSString class]]) {
            NSString *title = (NSString *)obj;
            if (title.length > 4) {
                title = [title substringToIndex:4];
            }
            [localTitles addObject:title];
        }
    }];

    self.segmentedControl.sectionTitles = titles;
    [self.segmentedControl setNeedsDisplay];
}

- (void)setHighLightIndex:(NSInteger)index
{
}

#pragma mark - WXSegmentedControl
- (void)segmentedControlChangedValue:(id)sender
{
    WXSegmentedControl *segmentControl = (WXSegmentedControl *)sender;
    if (self.delegate && [self.delegate respondsToSelector:@selector(selectedTag:withIndex:)]) {
        NSInteger index = segmentControl.selectedSegmentIndex;
        NSString *title = segmentControl.sectionImages[index];
        [self.delegate selectedTag:title withIndex:index];
    }
}

#pragma mark - DWTagListDelegate

- (void)selectedTag:(NSString *)tagName tagIndex:(NSInteger)tagIndex
{
    if (self.delegate && [self.delegate respondsToSelector:@selector(selectedTag:withIndex:)]) {
        [self.delegate selectedTag:tagName withIndex:tagIndex];
        [self onTapDown:nil];
        [self.segmentedControl setSelectedSegmentIndex:tagIndex];
    }
}

#pragma mark -
- (void)onTapDown:(id)sender
{
    double radian = M_PI;
    if (!self.isExpand) {
        [self expand];
    } else {
        [self collapse];
        radian = M_PI * 2;
    }

    [UIView animateWithDuration:0.3
                     animations:^{
                         _arrowImageView.transform = CGAffineTransformMakeRotation(radian);
                     }];
}

- (void)expand
{
    self.frame =
        CGRectMake(self.frame.origin.x, self.frame.origin.y, self.bounds.size.width, kHeight);
    self.tagListContainerView.alpha = 1;
    self.tagContainerView.backgroundColor = [UIColor clearColor];
    [UIView animateWithDuration:0.2
        delay:0.1
        options:UIViewAnimationOptionCurveLinear
        animations:^{
           
        }
        completion:^(BOOL finished){

        }];
    self.isExpand = YES;
}

- (void)collapse
{

    self.frame = CGRectMake(self.frame.origin.x, self.frame.origin.y, self.bounds.size.width, 40);
    [UIView animateWithDuration:0.2
        delay:0.1
        options:UIViewAnimationOptionCurveLinear
        animations:^{
        }
        completion:^(BOOL finished) {
            if (finished) {
                self.tagContainerView.backgroundColor = [UIColor whiteColor];
                self.tagListContainerView.alpha = 0;
            }
        }];
    self.isExpand = NO;
}

@end
