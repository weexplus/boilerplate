var fs=require('fs');
var path = require("path"); 
var stat=fs.stat;


const copy = ( src, dst) => {
  return new Promise((resolve, reject) => {
       stat(src,function(err,st){
                if(err){
                    throw err;
                }
              
                if(st.isFile()){
                  // console.log('copy file')
                   copyFile(src,dst).then(()=>{
                     resolve()
                   })
                }else if(st.isDirectory()){

                     copyDir(src,dst).then(()=>{
                       resolve()
                     })
                }
            });
             
  });
};

const copyFile = ( src, dst) => {
  return new Promise((resolve, reject) => {
       
                if(!fs.existsSync(dst) )
                    {
                          // console.log('创造'+dst)
                          fs.writeFileSync(dst,'cc',{flag:'w',encoding:'utf-8',mode:'07777'});
                    }
           var  readable=fs.createReadStream(src);//创建读取流
           var  writable=fs.createWriteStream(dst);//创建写入流
            readable.pipe(writable);
           resolve()
  });
};

const copyDir = ( src, dst) => {
  return new Promise((resolve, reject) => {

     fs.readdir(src,function(err,paths){
       
        // if(err){
        //     throw err;
        // }

        
        paths.forEach(function(path){
            var _src=src+'/'+path;
            var _dst=dst+'/'+path;
            // console.log('ddd='+_dst)
            var readable;
            var writable;
            stat(_src,function(err,st){
                if(err){

                    throw err;
                }
                
                if(st.isFile()){
                    if(!fs.existsSync(_dst) )
                    {
                          // console.log('创造'+_dst)
                          fs.writeFileSync(_dst,'cc',{flag:'w',encoding:'utf-8',mode:'07777'});
                    }
                    readable=fs.createReadStream(_src);//创建读取流
                    writable=fs.createWriteStream(_dst);//创建写入流
                    readable.pipe(writable);
                }else if(st.isDirectory()){
                    exist(_src,_dst,copy);
                }
            });
        });
       resolve()
    });
             
  });
};

const del = (path) => {
  return new Promise((resolve, reject) => {
    // path='dist/img'
 // console.log(path)
     var del = require('delete');
     del.sync(path, {force: true});
   })
             

};



const mkdir = (dirname) => {
  return new Promise((resolve, reject) => {
     
       if (fs.existsSync(dirname)) {  
            return true;  
        } else {  
            if (mkdir(path.dirname(dirname))) {  
                fs.mkdirSync(dirname);  
                return true;  
            }  
        }  


    });  
             

};





const exist = ( src, dst, callback) => {
  return new Promise((resolve, reject) => {

       
      fs.exists(dst,function(exists){
              if(exists){//不存在
                  callback(src,dst);
              }else{//存在
                  fs.mkdir(dst,function(){//创建目录
                      callback(src,dst)
                  })
              }
          })


    });  
             

};

 
 
module.exports = {
  copy,del,mkdir,copyFile,copyDir
  
};

