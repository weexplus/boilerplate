<template>
    <div class="layout">
        <image class="img" src="root:img/logo.png" ></image>
        <text class="text">欢迎使用weexplus!</text>
    </div>
</template>
<script>
    export default{
        data(){
            return {
                txt:{}
            }
        },
        props: {},
        methods: {
            onLoad(px){

            },

            onShow(){

            }
        },
        created(){

        }
    }
</script>
<style scoped>
  .layout{
      align-items: center;
      background-color: #ffffff;
      justify-content: center;
  }
    .img{
        width: 200px;
        height: 200px;
        margin-top: -100px;
    }
    .text{
        color: black;
        margin-top: 30px;
        font-size: 70px;
        font-weight: bold;
    }
</style>