<template>
    <div style="justify-content: center;align-items: center">
        <div @click="setOpen" style="width: 100;height: 100;background-color: red">
            <text>打开</text>
        </div>
        <div @click="setClose" style="width: 100;height: 100;background-color: red">
            <text>关闭</text>
        </div>

        <drawerlayout
                style="width: 750;height: 800;background-color: chartreuse"
                :isOpen="open"
        >

            <font></font>
            <netx>
            </netx>
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
              this.open=true;
            },
            setClose()
            {
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