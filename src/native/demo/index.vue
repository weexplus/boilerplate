<template>
    <div style="align-items: center;background-color: #ffffff">
        <!--<progress></progress>-->
        <div style="flex-direction: row;margin-top: 300px">
            <image src="root:img/logo.png" style="width: 150px;height: 150px"></image>
        </div>
        <text style="font-size: 66px;font-weight: bold;margin-top: 30px">欢迎使用 weexplus!</text>
        <div class="btn" @click="goto()">
            <text class="btn-text">开始教程</text>
        </div>

    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {

      goto(){
//        this.push('nav.js')
        this.$router.push('./demo/component/drawer.js',{})
      },
      showProgress(){
//       this.push('nav.js')
        let progress=weex.requireModule('progress')
//         progress.showFull('上传中....')
        progress.show()
        setTimeout(()=>{
          progress.dismiss()
        },1000)
      },
      onLoad(){

      }
    },
    created () {

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
        margin-top: 500px;
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
