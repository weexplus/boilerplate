import module from 'module'
import component from 'component'

let web={
    install(vue,weex){
        module.install(vue,weex)
        component.install(vue,weex)
    }
}
module.exports=web
