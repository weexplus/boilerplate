import Vue from 'vue'
/*global Vue*/
import Router from 'vue-router'
import index from '../native/index.vue'
import index1 from '../native/index1.vue'
import clone from '../native/clone.vue'

Vue.use(Router)

module.exports = new Router({
  routes: [
    {
      path: '/index.js',
      name: 'index',
      component: index
    },
    {
      path: '/clone.js',
      name: 'clone',
      component: clone
    },
    {
      path: '/index1.js',
      name: 'index1',
      component: index1
    },
  ]
})
