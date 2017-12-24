//
//  WXFPicker.m
//  Pods
//
//  Created by 郑江荣 on 2017/7/14.
//
//

#import "WXFPicker.h"
#import "farwolf.h"

@implementation WXFPicker
WX_EXPORT_METHOD(@selector(load:))
WX_EXPORT_METHOD(@selector(showline:))
WX_EXPORT_METHOD(@selector(select:))
- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
   
    if(attributes[@"color"] )
    {
        _color = [WXConvert NSString:attributes[@"color"]]?:@"#000000";
         attributes[@"data"];
    }
    else
    {
          _color=@"#000000";
    }
    if(attributes[@"selectColor"] )
    {
        _selectColor = [WXConvert NSString:attributes[@"selectColor"]]?:@"#000000";
        
    }
    else
    {
        _selectColor=@"#000000";
    }
    if(attributes[@"fontSize"] )
    {
        _fontSize =   [attributes[@"fontSize"] floatValue];
        
    }
    else
    {
          _fontSize=15;
    }
    if(attributes[@"lineColor"] )
    {
        _lineColor = [WXConvert NSString:attributes[@"lineColor"]]?:nil;
    }
    
    if(attributes[@"data"])
    {
        self.data= attributes[@"data"];
        
    }

    
    self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance];
    
    
    return self;
}



-(void)updateAttributes:(NSDictionary *)attributes
{
   
    [super updateAttributes:attributes];
    if(attributes[@"data"])
    {
        self.data= attributes[@"data"];
        [self load:self.data];
    }
}

- (CGFloat)pickerView:(UIPickerView *)pickerView rowHeightForComponent:(NSInteger)component
{
    return 32;
}
-(UIView*)loadView
{
    UIPickerView *picker=[[UIPickerView alloc]init];
   
    return picker;
}

-(void)layoutDidFinish
{
    [super layoutDidFinish];
    if(_lineColor!=nil)
    {
        [self showline:_lineColor];
    }
    if(self.data)
    {
        [self load:self.data];
    }
}


-(void)load:(NSArray*)d
{
    self.data=d;
    ((UIPickerView*)self.view).dataSource=self;
    ((UIPickerView*)self.view).delegate=self;
    self.selectIndex=0;
    
   
    
}

-(void)showline:(NSString*)color
{
    UIPickerView *pickerView= ((UIPickerView*)self.view);
    [pickerView.subviews objectAtIndex:1].layer.borderWidth = 0.5f;
    [pickerView.subviews objectAtIndex:2].layer.borderWidth = 0.5f;
    [pickerView.subviews objectAtIndex:1].layer.borderColor = [color toColor].CGColor;
    [pickerView.subviews objectAtIndex:2].layer.borderColor = [color toColor].CGColor;
}



- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
    self.selectIndex=row;
    [pickerView reloadAllComponents];
    
     [self fireEvent:@"change" params:@{@"value":self.data[row],@"index":@(row)} ];
}

-(void)select:(NSInteger)index
{
     UIPickerView *picker= ((UIPickerView*)self.view);
     [picker selectRow:index inComponent:0 animated:true];
   
}


- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 1;
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    if(self.data==nil)
        return 0;
    return self.data.count;
}



-(NSString*) pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component{
    

    return  self.data[row];
}

- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view
{
 
    
    //设置文字的属性
    UILabel *genderLabel = [UILabel new];
    genderLabel.textAlignment = NSTextAlignmentCenter;
    genderLabel.text = self.data[row];//self.genderArray里边内容为@[@"男",@"女"]
    NSString* c= row==self.selectIndex?self.selectColor:self.color;
    genderLabel.textColor =  [c toColor];
    genderLabel.font=[UIFont systemFontOfSize:_fontSize];
  
    
    if(row == self.selectIndex){
    
        genderLabel.attributedText
        = [self pickerView:pickerView attributedTitleForRow:self.selectIndex forComponent:component];
        
    }else{
        
        genderLabel.text = [self pickerView:pickerView titleForRow:row forComponent:component];
        genderLabel.textColor =  [self.color toColor];
    }
    return genderLabel;
}




- (NSAttributedString *)pickerView:(UIPickerView *)pickerView attributedTitleForRow:(NSInteger)row forComponent:(NSInteger)component{
    
    NSString *titleString=  self.data[_selectIndex];
    
    
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc]initWithString:titleString];
    NSRange range = [titleString rangeOfString:titleString];
    [attributedString addAttribute:NSForegroundColorAttributeName value:[self.selectColor toColor] range:range];
    
    return attributedString;
}


@end
