//
//  WXTracingMethodTableViewCell.m
//  Pods
//
//  Created by 齐山 on 2017/7/24.
//
//

#import "WXTracingLogTableViewCell.h"
#import "WXTracingUtility.h"
@interface WXTracingLogTableViewCell()
@property(nonatomic,strong)UIColor *bgColor;
@end
@implementation WXTracingLogTableViewCell

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

- (void)config:(NSString *)str searchStr:(NSString *)searchStr
{
    
    CGRect frame = [self frame];
    //文本赋值
    self.nameLabel.text = str?:@"";
    //设置label的最大行数
    self.nameLabel.numberOfLines = 0;
    
    //创建NSMutableAttributedString
    NSString *labelText = str?:@"";
    NSMutableAttributedString *attrStr = [[NSMutableAttributedString alloc]initWithString:labelText];
    
    //设置字体和设置字体的范围
    [attrStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:16.0f] range:NSMakeRange(0, labelText.length)];
    //添加文字颜色
    //    [attrStr addAttribute:NSForegroundColorAttributeName value:[UIColor redColor] range:NSMakeRange(17, 7)];
    if([labelText rangeOfString:@"<Weex>[exception]"].location != NSNotFound){
        [attrStr addAttribute:NSForegroundColorAttributeName value:COLOR_TRACING_EXCEPTION range:NSMakeRange(0, labelText.length)];
    }else if([str rangeOfString:@"<Weex>[error]"].location != NSNotFound){
        [attrStr addAttribute:NSForegroundColorAttributeName value:COLOR_TRACING_ERROR range:NSMakeRange(0, labelText.length)];
    } else if([str rangeOfString:@"<Weex>[warn]"].location != NSNotFound){
        [attrStr addAttribute:NSForegroundColorAttributeName value:COLOR_TRACING_WARN range:NSMakeRange(0, labelText.length)];
    } else{
        [attrStr addAttribute:NSForegroundColorAttributeName value:[UIColor blackColor] range:NSMakeRange(0, labelText.length)];
    }
    //添加文字背景颜色
    //    [attrStr addAttribute:NSBackgroundColorAttributeName value:[UIColor orangeColor] range:NSMakeRange(17, 7)];
    //添加下划线
    if(searchStr.length > 0){
        NSString *regex = searchStr;
        NSString *str = labelText;
        NSError *error;
        NSRegularExpression *regular = [NSRegularExpression regularExpressionWithPattern:regex
                                                                                 options:NSRegularExpressionCaseInsensitive
                                                                                   error:&error];
        // 对str字符串进行匹配
        NSArray *matches = [regular matchesInString:str
                                            options:0
                                              range:NSMakeRange(0, str.length)];
        // 遍历匹配后的每一条记录
        for (NSTextCheckingResult *match in matches) {
            NSRange range = [match range];
//            NSString *mStr = [str substringWithRange:range];
//            NSLog(@"%@", mStr);
            [attrStr addAttribute:NSBackgroundColorAttributeName value:COLOR_TRACING_SEARCBG range:range];

        }

    }
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
