//
//  WXTracingTableViewCell.m
//  TBWXDevTool
//
//  Created by 齐山 on 2017/7/4.
//  Copyright © 2017年 Taobao. All rights reserved.
//

#import "WXRenderTracingTableViewCell.h"
@interface WXRenderTracingTableViewCell()
@property(nonatomic,strong)UIColor *bgColor;
@end
@implementation WXRenderTracingTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        _timeBackgroundLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 0, 80)];
        [self.contentView addSubview:_timeBackgroundLabel];
        _refLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 5, 180, 20)];
        _refLabel.font = [UIFont systemFontOfSize:14];
        [self.contentView addSubview:_refLabel];
        _nameLabel = [[UILabel alloc] initWithFrame:CGRectMake(190, 5, 200, 20)];
        [self.contentView addSubview:_nameLabel];
        _nameLabel.font = [UIFont systemFontOfSize:14];
        _classNameLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 30, 180, 20)];
        [self.contentView addSubview:_classNameLabel];
        _classNameLabel.font = [UIFont systemFontOfSize:14];
        _fNameLabel = [[UILabel alloc] initWithFrame:CGRectMake(190, 30, 200, 20)];
        [self.contentView addSubview:_fNameLabel];
        _fNameLabel.font = [UIFont systemFontOfSize:14];
        _startTimeLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 55, 180, 20)];
        [self.contentView addSubview:_startTimeLabel];
        _startTimeLabel.font = [UIFont systemFontOfSize:14];
        _durationLabel = [[UILabel alloc] initWithFrame:CGRectMake(190, 55, 200, 20)];
        [self.contentView addSubview:_durationLabel];
        _durationLabel.font = [UIFont systemFontOfSize:14];
    }
    
    return self;
}

- (void)config:(WXTracing *)tracing begin:(NSTimeInterval)begin end:(NSTimeInterval )end
{
    self.refLabel.text = [NSString stringWithFormat:@"ref:%@",tracing.ref?:@""];
    self.nameLabel.text = [NSString stringWithFormat:@"name:%@",tracing.name?:@""];
    self.fNameLabel.text = [NSString stringWithFormat:@"function:%@",tracing.fName?:@""];
    if(tracing.className.length>0){
        self.classNameLabel.text = [NSString stringWithFormat:@"class:%@",tracing.className?:@""];
    }else
    {
        self.classNameLabel.text = @"";
    }
    self.startTimeLabel.text = [NSString stringWithFormat:@"start:%@",[self getStartTime:tracing.ts]];
    self.durationLabel.text = [NSString stringWithFormat:@"duration:%0.f(ms)",tracing.duration];
    if(!self.bgColor){
        self.bgColor = [self randomColor];
        _timeBackgroundLabel.backgroundColor = self.bgColor;
    }
    
    
    if(end - begin < 0.0001){
        return;
    }
    
    CGFloat x =  [[UIScreen mainScreen] bounds].size.width * (tracing.ts -begin)/(end-begin);
    CGFloat width = 2;
    if(tracing.duration > 0.0001){
        width =  [[UIScreen mainScreen] bounds].size.width * tracing.duration/(end-begin);
        if(width < 2){
            width = 2;
        }
    }
    if(x+1>[[UIScreen mainScreen] bounds].size.width){
        x = [[UIScreen mainScreen] bounds].size.width -3;
    }
    _timeBackgroundLabel.frame = CGRectMake(x, 5, width, 70);
}

- (UIColor *)randomColor
{
    CGFloat red = arc4random() % 255 / 255.0;
    CGFloat green = arc4random() % 255 / 255.0;
    CGFloat blue = arc4random() % 255 / 255.0;
    UIColor *color = [UIColor colorWithRed:red green:green blue:blue alpha:0.8];
    return color;
}

-(NSString *)getStartTime:(NSTimeInterval )time
{
    NSDateFormatter * formatter = [[NSDateFormatter alloc ] init];
    [formatter setDateFormat:@"hh:mm:ss:SSS"];
    NSString *date =  [formatter stringFromDate:[NSDate dateWithTimeIntervalSince1970:time/1000]];
    return date;
}

@end
