 const fs = require('fs')
 const p = require('path')
 const file = require('./file')
 var replace = require("replace");
 

 var path=process.cwd();

 dir=p.basename(path)

 let android_path=path+'/platforms/android/'+dir+''
 let gradpath=android_path+'/gradle.properties'
 var result=fs.readFileSync(gradpath, 'utf8')
 let values=result.split('\n')

 let packagename=''
 for(let i=0;i<values.length;i++){
  let item=values[i]
  
  if(item.indexOf('appId')!=-1){
          packagename=item.split('=')[1]
          break
      }
 }
replaceAll()

 let from =path+'/platforms/android/wechat/.wxpScript/wxapi'
 let to=android_path+'/app/src/main/java/'+packagename.replace(/\./g,'/')+'/wxapi'
 // console.log('sss='+to)
  if(fs.existsSync(to)) {
      console.log('已存在，开始删除')
      file.del(to).then(()=>{
         process.chdir(android_path+'/app/src/main/java/'+packagename.replace(/\./g,'/')+'/')
        fs.mkdirSync('wxapi'); 
        file.copy(from,to).then(()=>{
          console.log('配置完成，enjoy!')
         })
       
      },(exp)=>{
        console.log(exp)
      })
      
  }
  else{
    console.log('不存在，直接复制')
    process.chdir(android_path+'/app/src/main/java/'+packagename.replace(/\./g,'/')+'/')
    fs.mkdirSync('wxapi');  
     file.copy(from,to).then(()=>{
      console.log('配置完成，enjoy!')
     })
     
      
  }


  function replaceAll(){
    
    let ac1=  path+'/platforms/android/wechat/.wxpScript/wxapi/WXEntryActivity.java'
    let ac2=  path+'/platforms/android/wechat/.wxpScript/wxapi/WXPayEntryActivity.java'
      replace({
        regex: "appid",
        replacement: packagename,
        paths: [ac1,ac2],
        recursive: true,
        silent: true,
      });
      console.log('改名完成')
  }
 



