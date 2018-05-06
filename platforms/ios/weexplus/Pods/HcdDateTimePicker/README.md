# HcdDateTimePicker
[![Version](https://img.shields.io/cocoapods/v/HcdDateTimePicker.svg?style=flat)](http://cocoapods.org/pods/HcdDateTimePicker)
[![License](https://img.shields.io/github/license/Jvaeyhcd/HcdDateTimePicker.svg)](http://cocoapods.org/pods/HcdDateTimePicker)
[![Platform](https://img.shields.io/cocoapods/p/HcdDateTimePicker.svg)](http://cocoapods.org/pods/HcdDateTimePicker)
[![Tag](https://img.shields.io/github/tag/Jvaeyhcd/HcdDateTimePicker.svg
)](http://cocoapods.org/pods/HcdDateTimePicker)
[![Author](https://img.shields.io/badge/author-Jvaeyhcd-f07c3d.svg)](http://www.jvaeyhcd.cc)

A beautiful DateTimePicker.

![Alt Text](https://github.com/Jvaeyhcd/HcdDateTimePicker/blob/master/screen.gif?raw=true)

## Requirements
* Xcode 6 or higher
* iOS 7.0 or higher
* ARC

## Installation

### Manual Install
All you need to do is drop `HCDDateTimePicker` files into your project, and add #include `HCDDateTimePicker.h` to the top of classes that will use it.

### Cocoapods
Change to the directory of your Xcode project:
```
$ cd /path/to/YourProject
$ touch Podfile
$ edit Podfile
```
Edit your project's `Podfile` and add the following:
```
pod 'HcdDateTimePicker'
```
Install into your Xcode project:
```
pod setup
pod install
```

## Example
```
HcdDateTimePickerView *dateTimePickerView = [[HcdDateTimePickerView alloc] initWithDatePickerMode:DatePickerDateMode defaultDateTime:[[NSDate alloc]initWithTimeIntervalSinceNow:0]];
dateTimePickerView.clickedOkBtn = ^(NSString * datetimeStr){
    NSLog(@"%@", datetimeStr);
};
[self.view addSubview:dateTimePickerView];
[dateTimePickerView showHcdDateTimePicker];
```

## TODO
* [x] DatePickerDateMode
* [x] DatePickerTimeMode
* [x] DatePickerDateTimeMode
* [x] DatePickerYearMonthMode
* [x] DatePickerMonthDayMode
* [x] DatePickerHourMinuteMode
* [x] DatePickerDateHourMinuteMode

## Contact me
If you find some bugs or you have some suggest, please contact me or [post me an issue](https://github.com/Jvaeyhcd/HcdDateTimePicker/issues/new).Thank you!
* Email: chedahuang@icloud.com
