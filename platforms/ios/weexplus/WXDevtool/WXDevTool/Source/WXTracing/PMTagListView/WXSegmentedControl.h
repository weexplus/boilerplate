//
//  WXSegmentedControl.h
//  WXSegmentedControl
//
//  Created by Hesham Abd-Elmegid on 23/12/12.
//  Copyright (c) 2012 Hesham Abd-Elmegid. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void (^IndexChangeBlock)(NSInteger index);

typedef enum {
    WXSegmentedControlSelectionStyleTextWidthStripe, // Indicator width will only be as big as the text width
    WXSegmentedControlSelectionStyleFullWidthStripe, // Indicator width will fill the whole segment
    WXSegmentedControlSelectionStyleBox, // A rectangle that covers the whole segment
    WXSegmentedControlSelectionStyleArrow // An arrow in the middle of the segment pointing up or down depending on `WXSegmentedControlSelectionIndicatorLocation`
} WXSegmentedControlSelectionStyle;

typedef enum {
    WXSegmentedControlSelectionIndicatorLocationUp,
    WXSegmentedControlSelectionIndicatorLocationDown,
	WXSegmentedControlSelectionIndicatorLocationNone // No selection indicator
} WXSegmentedControlSelectionIndicatorLocation;

typedef enum {
    WXSegmentedControlSegmentWidthStyleFixed, // Segment width is fixed
    WXSegmentedControlSegmentWidthStyleDynamic, // Segment width will only be as big as the text width (including inset)
} WXSegmentedControlSegmentWidthStyle;

enum {
    WXSegmentedControlNoSegment = -1   // Segment index for no selected segment
};

typedef enum {
    WXSegmentedControlTypeText,
    WXSegmentedControlTypeImages,
	WXSegmentedControlTypeTextImages
} WXSegmentedControlType;

@interface WXSegmentedControl : UIControl

@property (nonatomic, strong) NSArray *sectionTitles;
@property (nonatomic, strong) NSArray *sectionImages;
@property (nonatomic, strong) NSArray *sectionSelectedImages;

/*
 Provide a block to be executed when selected index is changed.
 
 Alternativly, you could use `addTarget:action:forControlEvents:`
 */
@property (nonatomic, copy) IndexChangeBlock indexChangeBlock;

/*
 Font for segments names when segmented control type is `WXSegmentedControlTypeText`
 
 Default is [UIFont fontWithName:@"STHeitiSC-Light" size:18.0f]
 */
@property (nonatomic, strong) UIFont *font;

/*
 Text color for segments names when segmented control type is `WXSegmentedControlTypeText`
 
 Default is [UIColor blackColor]
 */
@property (nonatomic, strong) UIColor *textColor;

/* 
 Text color for selected segment name when segmented control type is `WXSegmentedControlTypeText`
 
 Default is [UIColor blackColor]
 */
@property (nonatomic, strong) UIColor *selectedTextColor;

/*
 Segmented control background color.
 
 Default is [UIColor whiteColor]
 */
@property (nonatomic, strong) UIColor *backgroundColor;

/*
 Color for the selection indicator stripe/box
 
 Default is R:52, G:181, B:229
 */
@property (nonatomic, strong) UIColor *selectionIndicatorColor;

/*
 Opacity for the seletion inficator box.
 
 Default is 0.2
 */
@property (nonatomic) CGFloat selectionIndicatorBoxOpacity;

/*
 Specifies the style of the control
 
 Default is `WXSegmentedControlTypeText`
 */
@property (nonatomic, assign) WXSegmentedControlType type;

/*
 Specifies the style of the selection indicator.
 
 Default is `WXSegmentedControlSelectionStyleTextWidthStripe`
 */
@property (nonatomic, assign) WXSegmentedControlSelectionStyle selectionStyle;

/*
 Specifies the style of the segment's width.
 
 Default is `WXSegmentedControlSegmentWidthStyleFixed`
 */
@property (nonatomic, assign) WXSegmentedControlSegmentWidthStyle segmentWidthStyle;

/*
 Specifies the location of the selection indicator.
 
 Default is `WXSegmentedControlSelectionIndicatorLocationUp`
 */
@property (nonatomic, assign) WXSegmentedControlSelectionIndicatorLocation selectionIndicatorLocation;

/*
 Default is NO. Set to YES to allow for adding more tabs than the screen width could fit.
 
 When set to YES, segment width will be automatically set to the width of the biggest segment's text or image,
 otherwise it will be equal to the width of the control's frame divided by the number of segments.
 
 As of v 1.4 this is no longer needed. The control will manage scrolling automatically based on tabs sizes.
 */
@property(nonatomic, getter = isScrollEnabled) BOOL scrollEnabled DEPRECATED_ATTRIBUTE;

/*
 Default is YES. Set to NO to deny scrolling by dragging the scrollView by the user.
 */
@property(nonatomic, getter = isUserDraggable) BOOL userDraggable;

/*
 Default is YES. Set to NO to deny any touch events by the user.
 */
@property(nonatomic, getter = isTouchEnabled) BOOL touchEnabled;


/*
 Index of the currently selected segment.
 */
@property (nonatomic, assign) NSInteger selectedSegmentIndex;

/*
 Height of the selection indicator. Only effective when `WXSegmentedControlSelectionStyle` is either `WXSegmentedControlSelectionStyleTextWidthStripe` or `WXSegmentedControlSelectionStyleFullWidthStripe`.
 
 Default is 5.0
 */
@property (nonatomic, readwrite) CGFloat selectionIndicatorHeight;

/*
 Edge insets for the selection indicator.
 NOTE: This does not affect the bounding box of WXSegmentedControlSelectionStyleBox
 
 When WXSegmentedControlSelectionIndicatorLocationUp is selected, bottom edge insets are not used
 
 When WXSegmentedControlSelectionIndicatorLocationDown is selected, top edge insets are not used
 
 Defaults are top: 0.0f
             left: 0.0f
           bottom: 0.0f
            right: 0.0f
 */
@property (nonatomic, readwrite) UIEdgeInsets selectionIndicatorEdgeInsets;

/*
 Inset left and right edges of segments. Only effective when `scrollEnabled` is set to YES.
 
 Default is UIEdgeInsetsMake(0, 5, 0, 5)
 */
@property (nonatomic, readwrite) UIEdgeInsets segmentEdgeInset;

/*
 Default is YES. Set to NO to disable animation during user selection.
 */
@property (nonatomic) BOOL shouldAnimateUserSelection;

- (id)initWithSectionTitles:(NSArray *)sectiontitles;
- (id)initWithSectionImages:(NSArray *)sectionImages sectionSelectedImages:(NSArray *)sectionSelectedImages;
- (instancetype)initWithSectionImages:(NSArray *)sectionImages sectionSelectedImages:(NSArray *)sectionSelectedImages titlesForSections:(NSArray *)sectiontitles;
- (void)setSelectedSegmentIndex:(NSUInteger)index animated:(BOOL)animated;
- (void)setIndexChangeBlock:(IndexChangeBlock)indexChangeBlock;

@end
