

Pod::Spec.new do |s|

 

  s.name         = "LMTowDatePicker"
  s.version      = "0.0.1"
  s.summary      = "Handle some data."
  s.description  = <<-DESC
                    Handle the data.
                   DESC

  s.homepage     = "http://csdn.net/veryitman"
  s.license      = "MIT"
  s.author             = { "veryitman" => "veryitman@126.com" }
  s.source       = { :git => "", :tag => "0.0.1" }
  s.source_files  = "Source", "LMTowDatePicker/**/*.{h,m}"
  s.exclude_files = "Source/Exclude"
  s.platform     = :ios, "8.0"
 
 
   s.dependency 'FSCalendar', '~> 2.7.9'
  
end
