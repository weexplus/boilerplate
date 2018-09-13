<template>
    <div style="justify-content: center;align-items: center">
        <div @click="setOpen" style="width: 100;height: 100;background-color: red">
            <text>打开</text>
        </div>
        <div @click="setClose" style="width: 100;height: 100;background-color: red">
            <text>关闭</text>
        </div>

        <drawerlayout
                style="width: 700;height: 800;background-color: chartreuse"
                :isOpen="open"
                :leftWidth="500"
        >

            <div style="position: absolute;left: 0;top: 0;right: 0;bottom: 0;background-color: red;width: 500">
              <div style="flex: 1;background-color: #0085ee;justify-content: center;align-items: center">
                     <text>侧边栏</text>
                  <text>目前必须用2层div包裹才能保证填充满</text>
                  <text>第一层的样式必须是position: absolute;left: 0;top: 0;right: 0;bottom: 0;</text>
                  <text>第二层的样式必须是flex:1</text>
                  <text>第一层div的宽度必须和leftWidth一致</text>
              </div>
            </div>
            <div style="position: absolute;left: 0;top: 0;right: 0;bottom: 0;background-color: red">
                <div @click="setClose" style="flex: 1;background-color: #0085ee;justify-content: center;align-items: center">
                    <text>主界面</text>
                    <text>目前必须用2层div包裹才能保证填充满</text>
                    <text>第一层的样式必须是position: absolute;left: 0;top: 0;right: 0;bottom: 0;</text>
                    <text>第二层的样式必须是flex:1</text>
                </div>
            </div>
        </drawerlayout>

    </div>
</template>

<style>
    .cl {

        align-items: center;

    }


</style>
<style src="./style.css"></style>
<script>

    var head = require('./header.vue')
    var netx = require('./net.vue')
    var font = require('./font.vue')
    var globalEvent = weex.requireModule('globalEvent');
    globalEvent.addEventListener("onPageInit", function (e) {


    });

    export default {
        components: {head,netx,font},
        data () {
            return {
                back: "",
                header: {},
                open: false
            }
        },
        methods: {
            setOpen()
            {
              this.toast('ssdsds')
              this.open=true;
            },
            setClose()
            {
              this.toast('xxsxss')
                this.open=false;
            },
            post()
            {
                var self = this;
                self.back = "";
                const net = weex.requireModule('net');
                net.post('http://121.40.81.1:9080/edu/getBanners.do', {a: "1", b: "2"}, {}, function () {
                    //start
                }, function (e) {
                    //success
                    self.back = e.res;
                    self.header = r.headers;
                }, function (e) {
                    //exception

                }, function () {
                    //compelete
                });


            },
            get()
            {


                var self = this;
                const net = weex.requireModule('net');
                self.back = "";
                net.get('http://121.40.81.1:9080/edu/getBanners.do', {}, {}, function () {
                    //start
                }, function (e) {
                    //success
                    self.back = e.res;
                }, function (e) {
                    //exception

                }, function () {
                    //compelete
                });


            }

        },
        created: function () {


        }
    }
</script>