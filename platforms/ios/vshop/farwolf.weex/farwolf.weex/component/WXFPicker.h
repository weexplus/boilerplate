//
//  WXFPicker.h
//  Pods
//
//  Created by 郑江荣 on 2017/7/14.
//
//

#import <WeexSDK/WeexSDK.h>

@interface WXFPicker : WXComponent<UIPickerViewDelegate, UIPickerViewDataSource>
@property(nonatomic,strong)NSArray *data;
@property(nonatomic,strong)NSString *color;
@property(nonatomic,strong)NSString *selectColor;
@property(nonatomic,strong)NSString *lineColor;
@property(nonatomic)CGFloat fontSize;
@property(nonatomic)NSInteger  selectIndex;
@end
