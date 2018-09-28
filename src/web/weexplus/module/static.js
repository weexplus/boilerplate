export default
{
  set(key,value){
    window.static[key]=value
  },
  get(key){
    return  window.static[key]
  },
  setString(key,value){
    window.static[key]=value
  },
  getString(key){
    return  window.static[key]
  }
}