//
//  WXMultilineTableViewCell.h
//  UICatalog
//
//  Created by Ryan Olson on 2/13/15.
//  Copyright (c) 2015 f. All rights reserved.
//

#import <UIKit/UIKit.h>

extern NSString *const kFLEXMultilineTableViewCellIdentifier;

@interface WXMultilineTableViewCell : UITableViewCell

+ (CGFloat)preferredHeightWithAttributedText:(NSAttributedString *)attributedText inTableViewWidth:(CGFloat)tableViewWidth style:(UITableViewStyle)style showsAccessory:(BOOL)showsAccessory;

@end
