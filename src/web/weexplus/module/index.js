import navigator from  './navigator'
import net from  './net'
import st from  './static'
import pref from  './pref'
import notify from  './notify'
import font from  './font'
import progress from  './progress'
export default {

  install(weex,vue)
  {
    weex.registerModule('navigator',navigator)
    weex.registerModule('net',net)
    weex.registerModule('static',st)
    weex.registerModule('pref',pref)
    weex.registerModule('notify',notify)
    weex.registerModule('font',font)
    weex.registerModule('progress',progress)
  }
}


