import Vue from 'vue'
import index from './views/index.vue'
import entry from './entry.vue'
import Router from 'vue-router'
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
    }
  ]
})
