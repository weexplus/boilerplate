//
//  FPicker.h
//  Pods
//
//  Created by 郑江荣 on 2017/7/18.
//
//

#import <UIKit/UIKit.h>
#import "farwolf_weex.h"
@interface FPicker : UIView<UIPickerViewDelegate, UIPickerViewDataSource>
@property (unsafe_unretained, nonatomic) IBOutlet UIPickerView *picker;
@property (unsafe_unretained, nonatomic) IBOutlet UIToolbar *toolbar;
@property(strong,nonatomic) NSArray *data1;
@property(strong,nonatomic) NSArray *data2;
@property(strong,nonatomic) NSArray *data3;
@property(nonatomic,strong)WXModuleKeepAliveCallback change1;
@property(nonatomic,strong)WXModuleKeepAliveCallback change2;
@property(nonatomic,strong)WXModuleKeepAliveCallback change3;
@property(nonatomic,strong)WXModuleKeepAliveCallback done;

@property(nonatomic) int count;
@property(nonatomic) int index1;
@property(nonatomic) int index2;
@property(nonatomic) int index3;
@property(nonatomic,strong)UIViewController *vc;
-(void)dismiss;
-(void)show;
-(void)setCount:(int)count;
-(void)setItems1:(NSArray*)l;
-(void)setItems2:(NSArray*)l;
-(void)setItems3:(NSArray*)l;
-(void)selectRow:(int)p row:(int)row;
-(void)setTheme:(NSString*)bgcolor btncolor:(NSString*)btncolor;

@property (strong,nonatomic)   typeof(void(^)()) onDismiss;
@property (strong,nonatomic)   typeof(void(^)()) onDone;

@end
