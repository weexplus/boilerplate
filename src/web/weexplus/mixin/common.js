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
  mounted(){
    if(this.onLoad)
    this.onLoad(window.route.params)
  },
  activated(){
    alert(this.callback)
      if(this.callback) {
        debugger
        this.callback()
      }
  }


}
