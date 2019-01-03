import path from '../path'
let nav ={


  push(url){


    nav.pushFull({url:url,param:{}})
  },
  pushParam(url,param){
     nav.pushFull({url:url,param:param})
  },
  pushFull(p,callback){
    let url=p.url
    let param=p.param
    let animate=p.animate
    if(!animate)
      animate=true
    // if(callback)
    // window.router.app._data.callback =callback
    window.eventBus.$emit('UPDATE_DIRECTION','forward')
    window.eventBus.$emit('ENABLE_ROUTER_ANIMATION',animate)
    url=path.getPath(url)
   let res=  window.router.push(url,param)
  },
  back(){
    nav.backFull({param:{},animate:true})
  },
  backFull(param,animate){
    if(!animate)
      animate=true
    window.eventBus.$emit('UPDATE_DIRECTION','back')
    window.eventBus.$emit('ENABLE_ROUTER_ANIMATION',animate)
    window.router.back(-1)
  },
  present(url){
    nav.push(url,{})
  },
  presentParam(url,param ){
    nav.pushParam(url,param)
  },
  presentFull(p,callback){
    nav.pushFull(p,callback)
  },
  dismiss(){
    nav.back()
  },
  dismissFull(param,animate){
    nav.back()
  },
  enableBackGesture(){

  },
  param(){
    return window.route.params
  },
  backTo(id){
   nav.back()
  },
  setPageId(id){

  },
  setRoot(id){

  }


}
export default nav