<template>
    <div style="">
        此页面不是真正的首页
        此页面加载时用户看到的是启动图，不会看到本页面的内容
        可以在此页面写一些跳转逻辑
        例如根据登录状态跳转到登录页还是首页
    </div>
</template>
<script>
    export default{
        props: {},
        data () {
            return {}
        },
        methods: {
            onLoad(){
                //首页的地址
                let url = '/index'
                this.goToMain(url)
            },
            goToMain(url){
                let navigator = weex.requireModule('navigator')
                // this.log(weex.config.env.platform)
                if (weex.config.env.platform != 'iOS') {
                    //注意android不要用presentFull
                    navigator.pushFull({url: url, animated: false})
                } else {
                    navigator.presentFull({url: url, animated: false})
                }
            }
        },
        created () {

        }
    }
</script>
<style scoped>
</style>
