const Mixins={}
Mixins.install = (Vue, options) => {
    Vue.mixin({
        data(){
            return {
                modal:{},
                navigator:{},
            }
        },
        methods:{
            toast(msg)
            {

                if(this.modal&&this.modal.toast)
                this.modal.toast({message:msg})
            },
            alert(msg)
            {
                this.modal.alert({message:msg})
            },
            push(url)
            {
                this.navigator.push(url)
            }
          

        },
        created()
        {
            var globalEvent = weex.requireModule('globalEvent') ;
            globalEvent.addEventListener("onPageInit",  (param)=> {

                this.navigator=weex.requireModule('navigator')
                this.modal=weex.requireModule('modal')
                 if(this.onload!=undefined)
                     this.onload(param)

            });

        }

    })
}

Vue.use(Mixins)
