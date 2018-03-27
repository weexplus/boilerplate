

Pod::Spec.new do |s|

 

  s.name         = "farwolf.business"
  s.version      = "0.0.1"
  s.summary      = "Handle some data."
  s.description  = <<-DESC
                    Handle the data.
                   DESC

  s.homepage     = "http://csdn.net/veryitman"
  s.license      = "MIT"
  s.author             = { "veryitman" => "veryitman@126.com" }
  s.source =  { :path => '.' }
  s.source_files  = "Source", "farwolf.business/**/*.{h,m,mm,c}"
  s.resources = 'farwolf.business/resources/storyboard/**','farwolf.business/resources/image/**','farwolf.business/resources/xib/**'
 


  s.exclude_files = "Source/Exclude"
  s.dependency 'TOCropViewController', '~> 2.0.8'
  s.dependency 'farwolf'
  s.platform     = :ios, "8.0"
end
