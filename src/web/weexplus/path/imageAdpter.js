
import path from './index'
export default {

  install(weex,vue){
    window._processImgSrc=function(src,item){

      // let url=path.getImagePath(src)
      // alert(url)
      // return url
      debugger
      if(src.startsWith('root:'))
        return src.replace('root:','')
      return src
    }
  }
}