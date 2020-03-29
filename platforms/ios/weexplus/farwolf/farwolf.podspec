

Pod::Spec.new do |s|

 

  s.name         = "farwolf"
  s.version      = "0.0.1"
  s.summary      = "Handle some data."
  s.description  = <<-DESC
                    Handle the data.
                   DESC

  s.homepage     = "http://csdn.net/veryitman"
  s.license      = "MIT"
  s.author             = { "veryitman" => "veryitman@126.com" }
  s.source       = { :git => "", :tag => "0.0.1" }
  s.source_files  = "Source", "farwolf/**/*.{h,m}"
  s.exclude_files = "Source/Exclude"
  s.platform     = :ios, "8.0"

  s.dependency 'MBProgressHUD', '~> 0.9.2'
  s.dependency 'YYModel'
  s.dependency  "AFNetworking", "~>3.2.1"
  s.dependency 'Masonry', '~> 0.6.3'
  s.dependency 'SDWebImage', '~> 5.1.0'
  #s.dependency 'KoaPullToRefresh', '~> 1.0.6'    
     
  #s.dependency 'IQKeyboardManager', '~> 4.0.6'
  #s.dependency 'LPPhotoViewer', '~> 0.0.7'
  s.dependency 'TOCropViewController', '~> 2.0.8'
  s.dependency 'SDCycleScrollView','~> 1.65' 
  s.dependency 'GSKStretchyHeaderView', '~> 0.12.2'   
  s.dependency 'UIViewController_PopUp', '~> 0.0.2'
  s.dependency 'SRMonthPicker'
  s.dependency 'WeexSDK'

  
end
