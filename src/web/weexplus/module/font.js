import path from '../path/index'
export default {
  addFont(name,url){

    let px=  path.getPath(url)
    const domModule = weex.requireModule('dom')
    domModule.addRule('fontFace', {
      'fontFamily': name,
      'src': "url('"+px+"')"
    });


  }
}