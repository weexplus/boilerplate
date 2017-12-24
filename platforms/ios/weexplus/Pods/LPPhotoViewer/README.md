# LPPhotoViewer

a simple photo browser 

# Installation

- use cocoapods
```swift
pod 'LPPhotoViewer', '~> 0.0.6'
```
- manual import
```swift
just download lib folder and add into your project
```

# Usage

- initialize imgae url array or UIImage objs
```swift
    self.urlArrays  = [[NSMutableArray alloc] init];
    self.imgArrays  = [[NSMutableArray alloc] init];
    [self.urlArrays addObject:@"https://drscdn.500px.org/photo/42393810/q=80_m=2000/fed5ccbf106c289f62e5762df92f1438"];
    [self.urlArrays addObject:@"https://drscdn.500px.org/photo/146441995/q=80_m=2000/0a6e687c0750ea05abf709bbd8c3d7f8"];
    [self.urlArrays addObject:@"https://drscdn.500px.org/photo/146512755/q=80_m=2000_k=1/62c584ed280fb11bbdb7d1c5451b6676"];
    [self.urlArrays addObject:@"https://drscdn.500px.org/photo/146409463/q=80_m=2000/9658bd373b7f84799dda05253d404a5d"];
    
    [self.imgArrays addObject:[UIImage imageNamed:@"carousel01"]];
    [self.imgArrays addObject:[UIImage imageNamed:@"carousel02"]];
    [self.imgArrays addObject:[UIImage imageNamed:@"carousel03"]];
```
- set properties for LPPhotoViewer
```swift
LPPhotoViewer *pvc = [[LPPhotoViewer alloc] init];
// 1. for image urls
pvc.imgArr = self.urlArrays;

// 2. for UIImage objs
pvc.imgArr = self.imgArrays;

/** for custom properties */
// 1. show from specify index
pvc.currentIndex = 2;

// 2. indicatorType: IndicatorTypeNumLabel or IndicatorTypePageControl or none
pvc.indicatorType = IndicatorTypeNumLabel;

```

- present LPPhotoViewer
```swift
[self presentViewController:pvc animated:YES completion:nil];
```

# Screenshot

<img src="screenshot1.gif" width="320"> <img src="screenshot2.gif" width="320">

# Notice

`LPPhotoViewer will upgrade to 1.x & Swift3 which will be much better performance soon`

# Release notes

#### ver. 0.0.6 
`add fade in transition effect & fade out drag effect`

#### ver. 0.0.5
`new progress view & drag bugfix`

#### ver. 0.0.4
`add vertical drag gesture for dismiss`

#### ver. 0.0.3
`add zoom reset fix`

#### ver. 0.0.2
`add pageControl indicate type`

#### ver. 0.0.1
`first commit`
