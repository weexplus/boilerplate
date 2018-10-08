import navigation from  './navigation'
import drawerlayout from  './DrawerLayout.vue'
import host from  './host.vue'
import progress from  '../plugin/progress'
export default {

  install(weex,vue)
  {
     weex.registerComponent('navigation',navigation)
     weex.registerComponent('drawerlayout',drawerlayout)
     weex.registerComponent('progress',progress)
     weex.registerComponent('host',host)
  }
}


