export default
{
  set(key,value){
    window.localStorage.setItem(key,value)
  },
  get(key){
    return  window.localStorage.getItem(key)
  },
  setString(key,value){
    window.localStorage.setItem(key,value)
  },
  getString(key){
    return  window.localStorage.getItem(key)
  },
  remove(key){
    window.localStorage.removeItem(key)
  }
}