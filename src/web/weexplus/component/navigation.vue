<template>
    <div id="app">

        <!--<image style="width: 100px;height: 100px;background-color: black;position: fixed;top: 0;z-index: 999" :src="url"></image>-->
        <transition name="custom-classes-transition" :enter-active-class="enterAnimate"
                    :leave-active-class="leaveAnimate">
            <!--<keep-alive  >-->
                <!--<router-view    class="sub-page" ></router-view>-->
            <!--</keep-alive>-->
            <keep-alive>
                <router-view class="sub-page" v-if="$route.meta.keepAlive"></router-view>
            </keep-alive>
        </transition>
        <transition name="custom-classes-transition" :enter-active-class="enterAnimate"
                    :leave-active-class="leaveAnimate">
            <router-view class="sub-page" v-if="!$route.meta.keepAlive"></router-view>
        </transition>
     <div style="color: #0088fb;z-index: 999999">{{$route.name}}{{$route.meta.keepAlive}}</div>
    </div>
</template>

<script>
//import imx from '../assets/img/loading.gif'
  export default {
    name: 'app',
    components: {},
    data() {
      return {
        routerAnimation: true,
        enterAnimate: "", //页面进入动效
        leaveAnimate: "", //页面离开动效
        direction:'forward',
        url:require('../assets/img/loading.gif'),
        routerList:[],
        cache:true
      }
    },
    mounted()
    {
      window.eventBus.$on('UPDATE_DIRECTION', (res) => {
        if (!this.routerAnimation) {
          return
        }
        this.direction = res
        this.enterAnimate = this.direction == 'forward' ? "animated fadeInRight" : "animated fadeInLeft"
        this.leaveAnimate = this.direction == 'forward' ? "animated fadeOutLeft" : "animated fadeOutRight"
      })
      window.eventBus.$on('ENABLE_ROUTER_ANIMATION', (res) => {
        this.routerAnimation = res
        if (!this.routerAnimation) {
          this.enterAnimate = ""
          this.leaveAnimate = ""
        }
      })
    },
//    beforeRouteLeave(to, from, next){
//      if (this.routerList.length && this.routerList.indexOf(to.name) === this.routerList.length - 1) {
//        // 后退
//        this.routerList.splice(this.routerList.length - 1, 1)
////        to.meta.keepAlive = true
//      } else {
//        // 前进
//        this.routerList.push(from.name || '/')
////        to.meta.keepAlive = false
//      }
//      next()
//    },
    created(){
      let _this=this
      this.$router.beforeEach((to, from, next) => {
//        to.meta.keepAlive = true
//        if(!to.meta)
//        {
//          to.meta={}
//        }
//        debugger
//        if (_this.routerList.length && _this.routerList.indexOf(to.name) === _this.routerList.length - 1) {
//          // 后退
//          _this.routerList.splice(_this.routerList.length - 1, 1)
//          to.meta.keepAlive = true
//        } else {
//          // 前进
//          _this.routerList.push(from.name || '/')
//          to.meta.keepAlive = false
//        }
        next()
      })

    }
  }
</script>
<style>
    /*将公用的样式统一在此导入*/
    @import "../assets/css/animate.css";

    #app {
        width: 100%;
        height: 100%;
    }

    body, html {
        width: 100%;
        height: 100%;
    }

    * {
        margin: 0;
    }
    .sub-page {
        position: absolute;
        top: 0px;
        bottom: 0px;
        background: #eee;
        height: 100%;
        width: 100%;
        /* overflow: hidden; */
        right: 0;
        left: 0;
        z-index: 5;
    }
</style>