import  main from './main'
import  api from 'weexplus-api'
Vue.directive('show', api.vshow)
Vue.use(main)
