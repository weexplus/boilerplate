export default {

  show(){
    // document.getElementById('progress').style.display='block'
    // debugger
    window.$wxp.loading.show()
  },
  showFull(txt,cancel){
    // document.getElementById('progress').style.display='block'
    window.$wxp.loading.show(txt,cancel)
  },
  dismiss(){
    // document.getElementById('progress').style.display='none'
    window.$wxp.loading.hide()
  }

}