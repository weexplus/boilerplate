const Mixins={}
let wraper=require('../component/wraper.vue')
Mixins.install = (Vue, options) => {
  Vue.mixin({
    components:{wraper},
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
      },
      getScreenHeight() {
        return 750/weex.config.env.deviceWidth*weex.config.env.deviceHeight
      }


    },
    created()
    {
      this.navigator=weex.requireModule('navigator')
      this.modal=weex.requireModule('modal')
      var globalEvent = weex.requireModule('globalEvent') ;
      globalEvent.addEventListener("onPageInit",  (param)=> {
        let p=param
        if(p.param&&p.bubbles)
          p=p.param
        if(this.onLoad!=undefined)
          this.onLoad(p)


      });

    }

  })
}
export  default Mixins