//
//  Picker.m
//  oa
//
//  Created by 郑江荣 on 16/5/12.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "Picker.h"
#import "farwolf.h"
@implementation Picker
-(void)awakeFromNib
{
     [super awakeFromNib];
//    self.data=[[NSMutableArray alloc]init];
         self.picker.dataSource=self;
    self.picker.delegate=self;
    [self.picker reloadComponent:0];
    
}

-(void)reloadData
{
      [self.picker reloadComponent:0];
}
-(void)select:(PickerType*)s
{
    
    [self.picker selectRow:[self.data indexOfObject:s] inComponent:0 animated:false];
    
}
-(void)show
{
    [self setHidden:false];
}
-(void)dismiss
{
    [self setHidden:true];
}
- (IBAction)doneClick:(id)sender {
    
    [self setHidden:true];
    PickerType *s= self.data[[self.picker selectedRowInComponent:0]];
    self.onSelect(s);
  
}
- (IBAction)cancelClick:(id)sender {
     [self setHidden:true];
}

- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 1;
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    return self.data.count;
}



-(NSString*) pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component{
    
    
    return   self.data[row].name;
}


@end
