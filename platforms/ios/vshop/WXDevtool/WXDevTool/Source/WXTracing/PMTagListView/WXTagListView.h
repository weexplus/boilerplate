//
//  WXTagListView.h
//  ipaimai
//
//  Created by Jun.Shi on 1/27/15.
//  Copyright (c) 2015 taobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "WXSegmentedControl.h"

@protocol WXTagListViewDelegate <NSObject>

@optional
- (void)selectedTag:(NSString *)tagName withIndex:(NSInteger)tagIndex;

@end

@interface WXTagListView : UIView

@property (strong) WXSegmentedControl *segmentedControl;
@property (weak) id<WXTagListViewDelegate> delegate;

- (instancetype)initWithFrame:(CGRect)frame;
- (void)bindData:(NSArray *)titles;
// after bindData
- (void)setHighLightIndex:(NSInteger)index;

@end
