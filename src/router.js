import Vue from 'vue'
/*global Vue*/
import Router from 'vue-router'
import index from './views/index.vue'
import entry from './entry.vue'
import page1 from './views/page1.vue'

Vue.use(Router)
module.exports = new Router({
  routes: [
    {
      path: '/',
     redirect:'/entry'
    },
    {
      path: '/entry',
      component: entry
    },
    {
      path: '/index',
      component: index
    },
    {
      path: '/page1',
      component: page1
    },
    {
      path: '/index2dd',
      component: index
    }
  ]
})
