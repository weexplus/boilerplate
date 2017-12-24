/*
 Copyright (C) 2012-2015 Simon Rice
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */

#import "SRMonthPicker.h"

#ifdef NSCalendarUnitMonth
#define NSCalendarUnitMonth NSMonthCalendarUnit
#endif

#ifdef NSCalendarUnitYear
#define NSCalendarUnitYear NSYearCalendarUnit
#endif

@interface SRMonthPicker()

@property (nonatomic) NSInteger monthComponent;
@property (nonatomic) NSInteger yearComponent;
@property (nonatomic, strong, readonly) NSArray* monthStrings;
@property (nonatomic, strong, readonly) NSDateFormatter* monthFormatter;
@property (nonatomic, strong, readonly) NSDateFormatter* yearFormatter;

-(void)p_prepare;
-(NSInteger)p_yearFromRow:(NSUInteger)row;
-(NSUInteger)p_rowFromYear:(NSInteger)year;

@end

@implementation SRMonthPicker

static const NSInteger SRMonthRowMultiplier = 340;
static const NSInteger SRDefaultMinimumYear = 1;
static const NSInteger SRDefaultMaximumYear = 99999;
static const NSCalendarUnit SRDateComponentFlags = NSCalendarUnitMonth | NSCalendarUnitYear;

- (id)initWithDate:(NSDate *)date calendar:(NSCalendar *)calendar
{
    self = [super init];
    
    if (self)
    {
        _calendar = calendar;
        [self p_prepare];
        [self setDate:date];
        self.showsSelectionIndicator = YES;
    }
    
    return self;
}

-(id)initWithDate:(NSDate *)date
{
    self = [self initWithDate:date calendar:[NSCalendar currentCalendar]];
    return self;
}

-(id)init
{
    self = [self initWithDate:[NSDate date] calendar:[NSCalendar currentCalendar]];
    return self;
}

-(id)initWithCoder:(NSCoder *)aDecoder
{
    self = [super initWithCoder:aDecoder];
    
    if (self)
    {
        _minimumYear = SRDefaultMinimumYear;
        _maximumYear = SRDefaultMaximumYear;
        [self p_prepare];
        if (!_calendar)
            _calendar = [NSCalendar currentCalendar];
        if (!_date)
            [self setDate:[NSDate date]];
    }
    
    return self;
}

-(id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    
    if (self)
    {
        _minimumYear = SRDefaultMinimumYear;
        _maximumYear = SRDefaultMaximumYear;
        
        [self p_prepare];
        if (!_calendar)
            _calendar = [NSCalendar currentCalendar];
        if (!_date)
            [self setDate:[NSDate date]];
    }
    
    return self;
}

-(id<UIPickerViewDelegate>)delegate
{
    return self;
}

-(void)setDelegate:(id<UIPickerViewDelegate>)delegate
{
    if ([delegate isEqual:self])
        [super setDelegate:delegate];
}

-(id<UIPickerViewDataSource>)dataSource
{
    return self;
}

-(void)setDataSource:(id<UIPickerViewDataSource>)dataSource
{
    if ([dataSource isEqual:self])
        [super setDataSource:dataSource];
}

-(NSInteger)monthComponent
{
    return self.yearComponent ^ 1;
}

-(NSInteger)yearComponent
{
    return !self.yearFirst;
}

-(NSDateFormatter *)monthFormatter {
    static NSDateFormatter *formatter;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        formatter = [[NSDateFormatter alloc] init];
        formatter.calendar = self.calendar;
        formatter.dateFormat = @"MMMM";
    });
    return formatter;
}

-(NSDateFormatter *)yearFormatter {
    static NSDateFormatter *formatter;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        formatter = [[NSDateFormatter alloc] init];
        formatter.calendar = self.calendar;
        formatter.dateFormat = @"y";
    });
    return formatter;
}

-(NSArray *)monthStrings
{
    return self.monthFormatter.monthSymbols;
}

-(void)setYearFirst:(BOOL)yearFirst
{
    _yearFirst = yearFirst;
    NSDate* date = self.date;
    [self reloadAllComponents];
    [self setNeedsLayout];
    [self setDate:date];
}

-(void)setMinimumYear:(NSInteger)minimumYear
{
    NSDate* currentDate = self.date;
    NSDateComponents* components = [self.calendar components:SRDateComponentFlags fromDate:currentDate];
    components.timeZone = [NSTimeZone defaultTimeZone];
    
    if (components.year < minimumYear)
        components.year = minimumYear;
    
    _minimumYear = minimumYear;
    [self reloadAllComponents];
    [self setDate:[self.calendar dateFromComponents:components]];
}

-(void)setMaximumYear:(NSInteger)maximumYear
{
    NSDate* currentDate = self.date;
    NSDateComponents* components = [self.calendar components:SRDateComponentFlags fromDate:currentDate];
    components.timeZone = [NSTimeZone defaultTimeZone];
    
    if (components.year > maximumYear)
        components.year = maximumYear;
    
    _maximumYear = maximumYear;
    [self reloadAllComponents];
    [self setDate:[self.calendar dateFromComponents:components]];
}

-(void)setWrapMonths:(BOOL)wrapMonths
{
    _wrapMonths = wrapMonths;
    [self reloadAllComponents];
}

-(void)setDate:(NSDate *)date
{
    NSDateComponents* components = [self.calendar components:SRDateComponentFlags fromDate:date];
    components.timeZone = [NSTimeZone defaultTimeZone];
    
    if (self.minimumYear && components.year < self.minimumYear)
        components.year = self.minimumYear;
    else if (self.maximumYear && components.year > self.maximumYear)
        components.year = self.maximumYear;
    
    if(self.wrapMonths) {
        NSInteger monthMidpoint = self.monthStrings.count * (SRMonthRowMultiplier / 2);
        
        [self selectRow:(components.month - 1 + monthMidpoint) inComponent:self.monthComponent animated:NO];
    } else
        [self selectRow:(components.month - 1) inComponent:self.monthComponent animated:NO];
        
    [self selectRow:[self p_rowFromYear:components.year] inComponent:self.yearComponent animated:NO];
    
    _date = [self.calendar dateFromComponents:components];
}

-(void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
    NSDateComponents* components = [[NSDateComponents alloc] init];
    components.month = 1 + ([self selectedRowInComponent:self.monthComponent] % self.monthStrings.count);
    components.year = [self p_yearFromRow:[self selectedRowInComponent:self.yearComponent]];
    
    [self willChangeValueForKey:@"date"];
    if ([self.monthPickerDelegate respondsToSelector:@selector(monthPickerWillChangeDate:)])
        [self.monthPickerDelegate monthPickerWillChangeDate:self];
    
    _date = [self.calendar dateFromComponents:components];
    
    if ([self.monthPickerDelegate respondsToSelector:@selector(monthPickerDidChangeDate:)])
        [self.monthPickerDelegate monthPickerDidChangeDate:self];
    [self didChangeValueForKey:@"date"];
}

-(NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 2;
}

-(NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    if (component == self.monthComponent && !self.wrapMonths)
        return self.monthStrings.count;
    else if(component == self.monthComponent)
        return SRMonthRowMultiplier * self.monthStrings.count;
    
    NSInteger maxYear = SRDefaultMaximumYear;
    if (self.maximumYear)
        maxYear = self.maximumYear;
    
    return [self p_rowFromYear:maxYear] + 1;
}

-(CGFloat)pickerView:(UIPickerView *)pickerView widthForComponent:(NSInteger)component
{
    if (component == self.monthComponent)
        return 150.0f;
    else
        return 76.0f;
}

-(UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view
{
    CGFloat width = [self pickerView:self widthForComponent:component];
    CGRect frame = CGRectMake(0.0f, 0.0f, width, 45.0f);
    
    if (component == self.monthComponent)
    {
        const CGFloat padding = 9.0f;
        if (component) {
            frame.origin.x += padding;
            frame.size.width -= padding;
        }
        
        frame.size.width -= padding;
    }
    
    UILabel* label = [[UILabel alloc] initWithFrame:frame];
    
    NSDateFormatter* formatter = nil;
    
    if (component == self.monthComponent) {
        label.text = [self.monthStrings objectAtIndex:(row % self.monthStrings.count)];
        label.textAlignment = component ? NSTextAlignmentLeft : NSTextAlignmentRight;
        formatter = self.monthFormatter;
    } else {
        formatter = self.yearFormatter;
        
        label.text = [NSString stringWithFormat:@"%ld", (long)[self p_yearFromRow:row]];
        label.textAlignment = NSTextAlignmentCenter;
    }
    
    label.font = self.font;
    label.textColor = self.fontColour;
    
    if (self.enableColourRow && [[formatter stringFromDate:[NSDate date]] isEqualToString:label.text])
        label.textColor = [UIColor colorWithRed:0.0f green:0.35f blue:0.91f alpha:1.0f];
    
    label.backgroundColor = [UIColor clearColor];
    label.shadowOffset = CGSizeMake(0.0f, 0.1f);
    label.shadowColor = [UIColor whiteColor];
    
    return label;
}

#pragma mark Private Methods

-(NSInteger)p_yearFromRow:(NSUInteger)row
{
    NSInteger minYear = SRDefaultMinimumYear;
    
    if (self.minimumYear)
        minYear = self.minimumYear;
    
    return row + minYear;
}

-(NSUInteger)p_rowFromYear:(NSInteger)year
{
    NSInteger minYear = SRDefaultMinimumYear;
    
    if (self.minimumYear)
        minYear = self.minimumYear;
    
    return year - minYear;
}

-(void)p_prepare
{
    self.dataSource = self;
    self.delegate = self;
    
    self.enableColourRow = YES;
    self.wrapMonths = YES;
    
    self.font = [UIFont boldSystemFontOfSize:24.0f];
    self.fontColor = [UIColor blackColor];
}

@end
