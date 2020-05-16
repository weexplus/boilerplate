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
    },
    created()
    {

    }

  })
}
const install = Mixins.install
export {install}
export  default Mixins