<template>
    <page title="首页"  :hasBackBtn="false">
        <div class="layout" >
            <image  class="img" v-show="showImg" @click="test"  src="root:img/logo.png" ></image>
            <text class="text" @click="showImg=!showImg">欢迎使用weexplus!</text>
        </div>
    </page>

</template>
<script>
    export default{
        data(){
            return {
                showImg:true
            }
        },
        props: {},
        methods: {
            test(){
                this.log('sssss')
            },
            onLoad(px){
                //页面加载完成
            },
            onShow(){
                //页面展示
            },
            onUnload(){
                //页面卸载

            },
            onHide(){
                //页面隐藏
            },

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
        flex: 1;
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
