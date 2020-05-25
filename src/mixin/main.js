const Mixins = {}
let wraper = require('../views/component/wraper.vue')
let head = require('../views/component/head.vue')
let button = require('../views/component/button.vue')
let page = require('../views/component/page.vue')
let api = require('weexplus-api')
let tabHost = require('../views/component/tabHost.vue')
let tabitem = require('../views/component/tabitem.vue')

Mixins.install = (Vue, options) => {
  api.install(Vue)
  Vue.mixin({
    components: {wraper, head, button, page,tabHost,tabitem},
    data(){
      return {
      txt:'1'
      }
    },
    methods: {
      log(msg){
        let type = typeof msg
        let log = weex.requireModule('log')
        if (type == 'object') {
          msg = JSON.stringify(msg)
        }
        log.log({msg:this.timestamp() + '    ' + msg + '',level:'info'})
      },
      timestamp() {
        var date = new Date();//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() < 10 ? '0' + date.getDate() + ' ' : date.getDate() + ' ';
        var h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
        var m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
        var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
        return Y + M + D + h + m + s;
      }
    },
    created()
    {

    }

  })
}
const install = Mixins.install
export {install}
export  default Mixins