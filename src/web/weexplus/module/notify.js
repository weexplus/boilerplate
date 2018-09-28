export default {

  /**
   * 注册
   * @param key
   * @param callback
   */
  regist(key,callback) {
     window.eventBus.$off(key,callback)
     window.eventBus.$on(key,callback)
  },
  /**
   发送
   */
  send(key,param) {
    window.eventBus.$emit(key,param)
  },


  /**
   发送给原生
   */
  sendNative(key,param) {

  }
}