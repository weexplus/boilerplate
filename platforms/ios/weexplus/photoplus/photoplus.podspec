

Pod::Spec.new do |s|

 
  s.platform  = :ios, "8.0"
  s.name         = "photoplus"
  s.version      = "0.0.1"
  s.summary      = "Handle some data."
  s.description  = <<-DESC
                    Handle the data.
                   DESC

  s.homepage     = "http://csdn.net/veryitman"
  s.license      = "MIT"
  s.author             = { "veryitman" => "362675035@qq.com" }
  s.source =  { :path => '.' }
  s.source_files  = "Source", "**/**/*.{h,m,mm,c}"
  s.resources = "resources/*",'TZImagePickerController/*.{png,bundle}'
  s.ios.vendored_libraries = '*.a'
  s.ios.vendored_frameworks = '*.framework'

  s.exclude_files = "Source/Exclude"
  s.dependency 'farwolf.weex' 
  s.frameworks   = "Photos", "CoreServices"
  s.dependency 'GPUImage'
  s.dependency 'ZLPhotoBrowser'
 
 


 
  
  #s.frameworks =  'UIKit'
  #s.libraries = "z", "c++"
  #s.requires_arc  = true
    

end
