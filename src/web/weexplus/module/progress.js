export default {

  show(){
    document.getElementById('progress').style.display='block'
    $wxp.show()
  },
  showFull(txt,cancel){
    document.getElementById('progress').style.display='block'
    $wxp.show()
  },
  dismiss(){
    document.getElementById('progress').style.display='none'
    $wxp.hide()
  }

}