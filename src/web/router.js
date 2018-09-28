import Vue from 'vue'
/*global Vue*/
import Router from 'vue-router'
import index from '../native/demo/index.vue'


Vue.use(Router)

module.exports = new Router({
  routes: [
    {
      path: '/index.js',
      name: 'index',
      component: index
    }
  ]
})
