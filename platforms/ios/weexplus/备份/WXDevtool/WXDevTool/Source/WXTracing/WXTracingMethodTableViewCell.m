//
//  WXTracingMethodTableViewCell.m
//  Pods
//
//  Created by 齐山 on 2017/7/24.
//
//

#import "WXTracingMethodTableViewCell.h"
#import "WXTracingUtility.h"
@interface WXTracingMethodTableViewCell()
@property(nonatomic,strong)UIColor *bgColor;
@end
@implementation WXTracingMethodTableViewCell

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
        _nameLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 12, 320, 20)];
        _nameLabel.font = [UIFont systemFontOfSize:20];
        _nameLabel.lineBreakMode = NSLineBreakByWordWrapping;
        _nameLabel.numberOfLines = 0;
        [self.contentView addSubview:_nameLabel];
    }
    
    return self;
}

- (void)config:(NSString *)str
{
    
    CGRect frame = [self frame];
    //文本赋值
    self.nameLabel.text = str?:@"";
    //设置label的最大行数
    self.nameLabel.numberOfLines = 0;
    
    //创建NSMutableAttributedString
    NSString *labelText = str?:@"";
    NSMutableAttributedString *attrStr = [[NSMutableAttributedString alloc]initWithString:str];
    
    //设置字体和设置字体的范围
    [attrStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:20.0f] range:NSMakeRange(0, labelText.length)];
    [attrStr addAttribute:NSForegroundColorAttributeName value:[UIColor blackColor] range:NSMakeRange(0, labelText.length)];
    //添加下划线
//    [attrStr addAttribute:NSUnderlineStyleAttributeName value:[NSNumber numberWithInteger:NSUnderlineStyleSingle] range:NSMakeRange(8, 7)];
    self.nameLabel.attributedText = attrStr;
    CGRect expectedLabelSize = [attrStr boundingRectWithSize:(CGSize){self.nameLabel.frame.size.width, CGFLOAT_MAX}
                                                              options:NSStringDrawingUsesLineFragmentOrigin
                                                              context:nil];
    self.nameLabel.frame = CGRectMake(self.nameLabel.frame.origin.x, self.nameLabel.frame.origin.y, self.nameLabel.frame.size.width, ceil(expectedLabelSize.size.height));
    
    //计算出自适应的高度
    frame.size.height = ceil(expectedLabelSize.size.height)+20;
    
    self.frame = frame;
}
@end
