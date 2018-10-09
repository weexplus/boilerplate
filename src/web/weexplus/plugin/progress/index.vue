<template>
    <div class="root" @click="bgclick" :style="{'top':showLayout?0:99999+'px'}">
        <div class="bg">
            <image style="width: 80px;height: 80px" :src="url"></image>
            <text class="txt" :style="{'color':color}">{{txt}}</text>
        </div>
    </div>
</template>
<script>
  export default{
    props: {
      color:{
        default:'#ffffff'
      }
    },
    data () {
      return {
        url: require('../../assets/img/loading.gif'),
        showLayout: false,
        txt: '加载中...',
        cancel: false
      }
    },
    methods: {
      bgclick(){
        if (this.cancel)
          this.hide()
      },
      show(txt, cancel){
        this.showLayout = true
        if (txt) {
          this.txt = txt
        }
        if (cancel) {
          this.cancel = cancel
        }
      },
      hide(){
        this.showLayout = false
        this.txt = '加载中...'
      }
    },
    created () {
    }
  }
</script>
<style scoped>
    .txt {
        color: #ffffff;
        margin-top: 0.15rem;
        font-size: 0.3rem;
    }

    .root {
        position: fixed;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        align-items: center;
        justify-content: center;
        z-index: 999;
    }

    .bg {
        width: 170px;
        height: 170px;
        border-radius: 10px;
        background-color: rgba(0, 0, 0, 0.8);
        align-items: center;
        justify-content: center;
        margin-top: -200px;
        z-index: 1000;
    }
</style>
