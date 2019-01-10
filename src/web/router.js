import Vue from 'vue'
/*global Vue*/
import Router from 'vue-router'
import index from '../native/index.vue'


Vue.use(Router)

module.exports = new Router({
  routes: [
    {
      path: '/',
     redirect:'/index.js'
    },
    {
      path: '/index.js',
      name: 'index',
      component: index
    }
  ]
})
