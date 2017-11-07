//
//  WXTracingTableViewCell.h
//  TBWXDevTool
//
//  Created by 齐山 on 2017/7/4.
//  Copyright © 2017年 Taobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <WeexSDK/WXTracingManager.h>

@interface WXRenderTracingTableViewCell : UITableViewCell

@property (nonatomic, strong) UILabel *timeBackgroundLabel;
@property (nonatomic, strong) UILabel *refLabel;
@property (nonatomic, strong) UILabel *nameLabel;
@property (nonatomic, strong) UILabel *fNameLabel;
@property (nonatomic, strong) UILabel *classNameLabel;
@property (nonatomic, strong) UILabel *startTimeLabel;
@property (nonatomic, strong) UILabel *durationLabel;

- (void)config:(WXTracing *)tracing begin:(NSTimeInterval)begin end:(NSTimeInterval )end;

@end
