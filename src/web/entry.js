import Vue from 'vue'
import App from './index.vue'
import weex from 'weex-vue-render'
import weexplus from  './weexplus'
import Navigation from 'vue-navigation'
const router = require('./router');
weex.init(Vue)
weexplus.init(weex,Vue)
Vue.use(Navigation, {router})
new Vue(Vue.util.extend({el: '#root', router}, App));







