<template>
    <div style="background-color: red" :style="{'width':width}" >

        <div :style="{'width':width}" style="width: 750;height: 130;background-color: #000000">
          <div @click="back()" style="width: 100;height: 100;">
              <text>返回</text>
          </div>
        </div>
        <input :style="{'width':width}" style="height: 90"/>

        <div class="btn" @click="back()">
            <text style="color:white"> navigator.backFull({ok:this.param},true) 带参数返回</text>
        </div>

    </div>

</template>
<style>
    .text {
        font-size: 50;
    }

    .btn {

        background-color: #0085ee;
        height: 100;

        margin-top: 50;
        margin-left: 50;
        margin-right: 50;
        border-radius: 10;
        align-items: center;
        justify-content: center;

    }

    .btn:active {
        background-color: #006ce7;
    }
</style>

<script>
    var head = require('./header.vue')
    export default {
        components: {head},
        data () {
            return {
                text: 'Hello World.',
                param: '',
                width:1334
            }
        }
        ,
        methods: {

            back()
            {
                var navigator = weex.requireModule('navigator');
                navigator.dismiss();
            }
            ,
              getScreenHeight() {

             return 750/weex.config.env.deviceWidth*weex.config.env.deviceHeight


            }


        }
        ,
        created: function () {

            var self = this;
            var globalEvent = weex.requireModule('globalEvent');
            this.width=this.getScreenHeight();
            globalEvent.addEventListener("onPageInit", function (e) {


                var navigator = weex.requireModule('navigator');
                self.param = navigator.param().a;
                navigator.setPageId('nav1');


            });
        }
    }
</script>