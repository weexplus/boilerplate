<template>
<div>
   <head></head>
    <div @click="doUpdateJs" style="width: 200;height: 100;background-color: blue;margin-top: 20;margin-left: 20px; align-items: center;justify-content: center">
     <text style="color: #ffffff">更新js</text>
    </div>
</div>
</template>


<script>
    var head = require('./component/header.vue')
    export default {

        components:{
            head
        },
        methods:{




            doUpdateJs()
            {

                var updater=weex.requireModule('updater')
                var p={}
                p.url='http://10.23.6.67:8080/jsversion.do'
                p.appid=1
                p.theme=1
                p.nativeversion=1
                p.failtoast=true
                p.showprogress=true
                updater.doCheckJs(p)
            }
        }


    }

</script>
