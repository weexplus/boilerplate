<template>
    <div style="">

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
