<template>
    <div style="align-items: center;justify-content: center">
        <text style="font-size: 66px;font-weight: bold">welcom to weexplus!</text>
        <div class="btn">
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
      load(){
        this.toast('sss')
      }
    },
    created () {
      this.toast('dd')
    }
  }
</script>
<style scoped>
    .btn{
        width: 200px;
        height: 100px;
        background-color: #0088fb;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
        margin-top: 50px;
    }
    .btn:active{
        background-color: #5ac3ff;
    }
    .btn-text{
        color: #ffffff;
        text-align: center;
        justify-content: center;
        position: absolute;
        left: 0;
        top:0;
        right: 0;
        bottom: 0;
        justify-content: center;
        padding-top: 28px;
    }
    .btn-text:active{
        color: #ffffff;
    }
</style>
