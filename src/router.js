import Vue from 'vue'
/*global Vue*/
import Router from 'vue-router'
import index from './views/index.vue'
import button from './views/button.vue'
import page1 from './views/page1.vue'
Vue.use(Router)
module.exports = new Router({
  routes: [
    {
      path: '/',
     redirect:'/index'
    },
    {
      path: '/index',
      name: 'index',
      component: index
    },
    {
      path: '/button',
      name: 'button',
      component: button
    },
    {
      path: '/page1',
      name: 'page1',
      component: page1
    }
  ]
})
