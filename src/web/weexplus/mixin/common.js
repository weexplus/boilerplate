export default {


  created(){
    window.router=this.$router
    window.route=this.$route
    var evt = new Event('onPageInit')
    evt.param =window.route.params
    document.dispatchEvent(evt)

  },
  mounted(){

  }


}
