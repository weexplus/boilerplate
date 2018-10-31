const Mixins = {}
let wraper = require('../component/wraper.vue')
let head = require('../component/head.vue')
let button = require('../component/button.vue')
let page = require('../component/page.vue')
Mixins.install = (Vue, options) => {

  // Vue.directive('show', {
  //
  //   inserted: function (el) {
  //     el.position=el.style.position
  //     el.top=el.style.top
  //     let modal=weex.requireModule('modal')
  //     // modal.alert({message:el.style})
  //     // modal.confirm({message:JSON.stringify(el)+''})
  //   },
  //   // 当被绑定的元素插入到 DOM 中时……
  //   bind: function (el, binding, vnode){
  //
  //     let modal=weex.requireModule('modal')
  //     // modal.alert({message:el.style})
  //
  //     binding.style="{'top':show?'0':'-9999'}"
  //     modal.confirm({message:JSON.stringify(binding)+''})
  //     // if(!binding.value){
  //     //
  //     //   el.style.position='absolute'
  //     //   el.style.top='-99999px'
  //     // }else{
  //     //   el.style.position= ''
  //     //   el.style.top= 0
  //     //   // el.style=el.defaultStyle
  //     // }
  //
  //   },
  //   update: function (el,binding) {
  //     // 聚焦元素
  //     let modal=weex.requireModule('modal')
  //     // // modal.alert({message:el.style})
  //     modal.confirm({message:JSON.stringify(el.style)+''})
  //     el.style.width='100px'
  //     el.style.height='100px'
  //     el.style['background-color']='red'
  //     if(!binding.value){
  //
  //       el.style.position='absolute'
  //       el.style.top='-99999px'
  //     }else{
  //       el.style.position= ''
  //       el.style.top= '0'
  //       // el.style=el.defaultStyle
  //     }
  //     el.restyle(el.style)
  //
  //   }
  // })
  Vue.mixin({
    components: {wraper, head, button, page},
    data(){
      return {
        modal: {},
        navigator: {},
      }
    },
    methods: {
      toast(msg)
      {
        if (this.modal && this.modal.toast)
          this.modal.toast({message: msg})
      },
      alert(msg)
      {
        this.modal.alert({message: msg})
      },
      push(url, param)
      {
        if (!param)
          this.navigator.push(url)
        else
          this.navigator.pushParam(url, param)
      },
      getScreenHeight() {
        return 750 / weex.config.env.deviceWidth * weex.config.env.deviceHeight
      }


    },
    created()
    {
      this.navigator = weex.requireModule('navigator')
      this.modal = weex.requireModule('modal')
      var globalEvent = weex.requireModule('globalEvent');
      globalEvent.addEventListener("onPageInit", (param) => {
        let p = param
        if (param && p.param && p.bubbles)
          p = p.param
        if (this.onLoad != undefined)
          this.onLoad(p)


      });

    }

  })
}
export  default Mixins