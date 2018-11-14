<template>
    <div style="align-items: center;background-color: #ffffff">
        <!--<progress></progress>-->
        <!--<text style="margin-top: 100px">{{show}}</text>-->
        <div style="flex-direction: row;margin-top: 300px">
            <image src="root:img/logo.png" style="width: 150px;height: 150px"></image>
        </div>
        <text style="font-size: 66px;font-weight: bold;margin-top: 30px">欢迎使用 weexplus!</text>
        <!--<input style="width: 100px;height: 100px;border-color: #0088fb;border-width: 1px"/>-->

        <div class="btn" @click="goto()">
            <text class="btn-text">开始教程</text>
        </div>
        <drawerlayout style="width: 100px;height: 100px" v-show="show">

        </drawerlayout>


    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {
        xx:'xxx',
        show:false
      }
    },
    methods: {


      goto(){
//        this.show=!this.show
        this.push('nav.js')
//        this.$router.push('./demo/component/drawer.js',{})
//        let nav=weex.requireModule('navigator')
//        nav.pushFull('nav.js',()=>{
//          alert('sss')
//        })
      },

      onLoad(){
           let navbar=weex.requireModule('navbar')
           navbar.setStatusBarStyle('balck')
      }
    },
    created () {

    },
    mounted(){
//      debugger
    },
    activated(){
    }
  }
</script>
<style scoped>
    .btn {
        width: 200px;
        height: 100px;
        background-color: #0088fb;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
        margin-top: 200px;
    }

    .btn:active {
        background-color: #5ac3ff;
    }

    .btn-text {
        color: #ffffff;
        text-align: center;

    }

    .btn-text:active {
        color: #ffffff;
    }
</style>
