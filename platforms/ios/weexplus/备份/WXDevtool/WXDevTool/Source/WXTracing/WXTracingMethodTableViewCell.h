//
//  WXTracingMethodTableViewCell.h
//  Pods
//
//  Created by 齐山 on 2017/7/24.
//
//

#import <UIKit/UIKit.h>

@interface WXTracingMethodTableViewCell : UITableViewCell

@property (nonatomic, strong) UILabel *nameLabel;

- (void)config:(NSString *)str;
@end
