import {Component} from 'vue'
import Vue from 'vue'
export default {

  data(){
    return {
      callback:undefined
    }
  },
  created() {
    window.router=this.$router
    window.route=this.$route
    var evt = new Event('onPageInit')
    if(window.route)
    {
      evt.param =window.route.params
    }
    document.dispatchEvent(evt)



  },
  methods:{
    isPage(){
      // let p=p.$parent.$parent
      // while(!(p.$parent.$parent instanceof Vue)){
      //   p=p.$parent
      // }
      return  (this instanceof  Vue)
    }
  },
  mounted(){
    if(this.onLoad)
    this.onLoad(window.route.params)
  },
  activated(){

    if(this.callback) {
        this.callback()
    }
  }



}
