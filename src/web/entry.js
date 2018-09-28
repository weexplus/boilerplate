import Vue from 'vue'
import App from './index.vue'
import weex from 'weex-vue-render'
import weexplus from  './weexplus'
const router = require('./router');
weex.init(Vue)
weexplus.init(weex,Vue)
new Vue(Vue.util.extend({el: '#root', router}, App));

