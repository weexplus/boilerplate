const Mixins={}
Mixins.install = (Vue, options) => {
    Vue.mixin({
        data(){
            return {
                name:'gfgfgf'
            }
        }
    })
}

Vue.use(Mixins)
