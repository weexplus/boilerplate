import Vue from 'vue'
import module from  './module'
import component from  './component'
import imageAdpter from  './path/imageAdpter'
import path from  './path'
import mixin from  './mixin'
import plugin from  './plugin'
import main from  '../../native/mixin/main'



export default {

  init(weex,vue){
    window.vue=vue
    window.static={}
    window.eventBus=new Vue()
    vue.prototype.path = path
    module.install(weex,vue)
    component.install(weex,vue)
    mixin.install(weex,vue)
    imageAdpter.install(weex,vue)
    plugin.install(weex,vue)
    vue.use(main)

  }
}