# SRMonthPicker

[![Build Status](https://img.shields.io/travis/simonrice/SRMonthPicker.svg)](https://travis-ci.org/simonrice/SRMonthPicker) 
[![CocoaPods Platform](https://img.shields.io/cocoapods/p/SRMonthPicker.svg)](http://cocoadocs.org/docsets/SRMonthPicker) 
[![CocoaPods Version](https://img.shields.io/cocoapods/v/SRMonthPicker.svg)](http://cocoadocs.org/docsets/SRMonthPicker) 
[![Carthage compatible](https://img.shields.io/badge/Carthage-compatible-4BC51D.svg)](https://github.com/Carthage/Carthage)	

SRMonthPicker is like UIDatePicker, but without the days.  As the name says, it's a fully localised month picker that allows the user to choose a month and a year.  Perfect for entering credit card expiry dates or just when you don't need a day component.  It's meant to mimic the look, feel & behaviour of 2 native UI elements on iOS:

* A month picker that appears for an `<input type="month" />` element on iOS Safari, sadly not exposed via any native control or a setting on UIDatePicker.
* A UIDatePicker set to Date-only mode, but without the day element.

![Screenshot](Doc/screenshot.png)

SRMonthPicker has been tested on iOS 7 and above.  It should also work on iOS 5-6, but this hasn't been tested for a while.  It is licensed under the terms of the [MIT License](http://opensource.org/licenses/mit-license.php).

## Including in your project

The easiest way by far of including this project is to use [CocoaPods](http://cocoapods.org).  Once you've got that up & running for your project, simply add the dependency to your `Podfile`:

```ruby
platform :ios, "5.0"

pod "SRMonthPicker"

```

Then run `pod install` to install the dependencies.

Alternatively, if you're targeting iOS8 or above, SRMonthPicker is also compatible with [Carthage](https://github.com/Carthage/Carthage) - simply add this to your `Cartfile`:

```
github "SimonRice/SRMonthPicker"
```

If you're not using a dependency manager, simply clone this project as a submodule or download the classes in the `SRMonthPicker` directory, and include them in your project.  Bear in mind this library uses ARC, so you should create a Static Library if your project doesn't.

If you're using QuickDialog, you may be interested in [QMonthElement](https://github.com/simonrice/QMonthElement), which integrates one of these month pickers into your QuickDialog forms.  Integrations for other form frameworks, such as XLForm and FXForms, are in the works as well.

## Usage

For Interface Builder (including Storyboard), simply drag out a `UIPickerView` in Interface Builder.  Under the Assistant Editor, set the class of the picker to `SRMonthPicker`.  If you're building your interfaces by code, it's simply a case of initialising an SRMonthPicker instance & adding a subview.  In XCode 6 and above, you will also have the option of modifying some of the month picker's properties straight from the attributes inspector on Interface Builder.

You have properties to show the year first, along with the setting (& getting) the selected date, plus the maximum & minimum years.

As of version 0.2.5, there is a `SRMonthPickerDelegate`.  This provides 2 optional methods:

* `- (void)monthPickerWillChangeDate:(SRMonthPicker *)monthPicker`
* `- (void)monthPickerDidChangeDate:(SRMonthPicker *)monthPicker`

Because the `delegate` property is used internally, you'll need to assign your delegate to the month picker's `monthPickerDelegate` property.

A simple example project showing off many of the features can be seen in the `Example` folder.  In addition, the header files are fully documented in AppleDoc format - thanks to CocoaDocs, you can see the full documentation [in your browser](http://cocoadocs.org/docsets/SRMonthPicker).

## Contributions

As everyone says, GitHub is about social coding - I didn't just choose to use it because of my love of git as a version control system.  Please do chip in & help make this an even better project, or even file in an issue if you see anything not working right.

## License

As stated further up on this page, usage is provided under the [MIT License](http://opensource.org/licenses/mit-license.php).  See the `LICENSE` file or any class header for the full details.
