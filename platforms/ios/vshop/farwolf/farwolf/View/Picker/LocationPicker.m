//
//  LocationPicker.m
//  farwolf
//
//  Created by 郑江荣 on 2017/4/27.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "LocationPicker.h"
#import "Region.h"
#import "farwolf.h"
@implementation LocationPicker

-(void)load:(NSArray<Region*>*)l
{
    self.regions=l;
    self.picker.delegate=self;
    self.picker.dataSource=self;
    self.l1=[self getProvince];
    self.l2=[self getChild:self.l1[0].code];
    self.l3=[self getChild:self.l2[0].code];
    self.first_row=0;
    self.second_row=0;
    
}
-( NSArray<Region*>*)getProvince
{
    NSMutableArray<Region*>* l=[NSMutableArray<Region*> new];
   for(Region *r in self.regions)
   {
       if(r.regionType==2)
       {
           [l addObject:r];
       }
   }
    return l;
}

-(NSArray<Region*>*)getChild:(NSString*)parentcode
{
    NSMutableArray<Region*>* l=[NSMutableArray<Region*> new];
    for(Region *r in self.regions)
    {
        if([r.parentCode isEqualToString:parentcode])
        {
            [l addObject:r];
        }
    }
    return l;
}

-(void)show
{
    [self setHidden:false];
}
-(void)hide
{
    [self setHidden:true];
}
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 3;
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    if(component==0)
    {
        return self.l1.count;
        
    }
    else if(component==1)
    {
        return self.l2.count;
        
    }
    else
    {
        return self.l3.count;
        
    }

    
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:
(NSInteger)row inComponent:(NSInteger)component
{
    if(component==0)
    {
        if(self.first_row!=row)
        {
            self.first_row=row;
            self.l2=[self getChild:self.l1[row].code];
            self.l3=[self getChild:self.l2[0].code];
            self.second_row=0;
            self.third_row=0;
            [self.picker selectRow:0 inComponent:1 animated:false];
            [self.picker selectRow:0 inComponent:2 animated:false];
            [self.picker reloadComponent:1];
            [self.picker reloadComponent:2];
        }
    }
    else if(component==1)
    {
        if(self.second_row!=row)
        {
            self.second_row=row;
            self.l3=[self getChild:self.l2[row].code];
             [self.picker selectRow:0 inComponent:2 animated:false];
            self.third_row=0;
            [self.picker reloadComponent:2];
        }
    }
    else if(component==2)
    {
        if(self.third_row!=row)
        {
            self.third_row=row;
            
        }
    }
  
    
}

-(NSString*) pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component{
  

    
    if(component==0)
    {
        return self.l1[row].regionName;
        
    }
    else if(component==1)
    {
        if(self.l2.count>row)
        return self.l2[row].regionName;
        return @"";
    }
    else
    {
        if(self.l3.count>row)
        return self.l3[row].regionName;
        return @"";
        
    }
}


- (IBAction)cancelClick:(id)sender {
    [self hide];
}
- (IBAction)doneClick:(id)sender {
    
    [self hide];
     self.onSelect(self.l1[self.first_row],self.l2[self.second_row],self.l3[self.third_row]);
}

@end
