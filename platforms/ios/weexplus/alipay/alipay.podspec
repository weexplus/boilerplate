

Pod::Spec.new do |s|

 

  s.name         = "alipay"
  s.version      = "0.0.1"
  s.summary      = "Handle some data."
  s.description  = <<-DESC
                    Handle the data.
                   DESC

  s.homepage     = "http://csdn.net/veryitman"
  s.license      = "MIT"
  s.author             = { "veryitman" => "veryitman@126.com" }
  s.source =  { :path => '.' }
  s.source_files  = "Source", "**/**/*.{h,m,mm,c}"
  s.resources = '*.bundle'
  s.ios.vendored_libraries = '*.a'
  s.ios.vendored_frameworks = '*.framework'

  s.exclude_files = "Source/Exclude"
  #s.dependency 'farwolf'
  #s.dependency 'farwolf.business'
  #s.dependency 'WeexSDK'  
  s.dependency 'farwolf.weex' 
  s.platform  = :ios, "8.0"
  
  s.frameworks = 'SystemConfiguration', 'CoreTelephony', 'QuartzCore', 'CoreText', 'CoreGraphics', 'UIKit', 'Foundation', 'CFNetwork', 'CoreMotion','AlipaySDK'
  s.libraries = "z", "c++"
  s.requires_arc  = true


end
