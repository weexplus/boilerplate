import module from './module'
import component from './component'



let web={
    mainInstall(vue,weex){
        module.install(vue,weex)
        component.install(vue,weex)
    }
}
export  default web
