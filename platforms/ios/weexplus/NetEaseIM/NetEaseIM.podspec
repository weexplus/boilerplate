

Pod::Spec.new do |s|

 

  s.name         = "NetEaseIM"
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
  s.exclude_files = "Source/Exclude"
  s.resources = 'jpush/resources/storyboard/**','jpush/resources/image/**','jpush/resources/xib/**'
  s.platform     = :ios, "8.0"
  s.requires_arc = true
  

  
  s.dependency 'farwolf.weex'
  s.dependency 'NIMSDK'


end
