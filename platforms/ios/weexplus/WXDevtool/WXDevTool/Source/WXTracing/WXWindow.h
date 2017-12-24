//
//  WXWindow.h
//  
//
//  Created by Ryan Olson on 4/13/14.
//  Copyright (c) 2014 Flipboard. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol WXWindowEventDelegate;

@interface WXWindow : UIWindow

@property (nonatomic, weak) id <WXWindowEventDelegate> eventDelegate;

@end

@protocol WXWindowEventDelegate <NSObject>

- (BOOL)shouldHandleTouchAtPoint:(CGPoint)pointInWindow;
- (BOOL)canBecomeKeyWindow;

@end
