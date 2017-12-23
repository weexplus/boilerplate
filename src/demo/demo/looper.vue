<template>

    <div>
        <head title="相机" append="tree">

        </head>
        <looper ref="looper" font-size="25" @change="onchange" @click="ok" :data="items" color="#eeeeee"
                style="width: 300;height: 100;background-color: #0088fb">
        </looper>
        <text>{{index}}</text>
        <text>{{index}}</text>
        <div style="width: 200;height: 100;background-color: #006ce7" @click="getindex"></div>
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


    export default {
        components: {head},
        data () {
            return {
                src: "",
                index: 0,
                items: ['1111', '2222', '33333', '44444']
            }
        },
        methods: {
            ok()
            {
//                this.index++;
                this.$refs.looper.getIndex((res) => {

                    this.index = res.index;
                });

            },
            getindex()
            {

                this.$refs.looper.getIndex((res) => {

                    this.index = res.index;
                });
            },
            onchange(res)
            {
                this.index = res.index;
            },

            openCamera()
            {
                var self = this;
                const photo = weex.requireModule('photo');
                photo.openCamera(500, 800, '#000000', function (e) {

                    self.src = e.path;

                    var net = weex.requireModule("net");
                    net.postFile('http://59.110.169.246/movie/imgupload.do', {//param

                    }, {
                        // header
                    }, {file: e.path}, () => {
                        //start
                    }, (e) => {
                        //succcess
                        var modal = weex.requireModule("modal")
                        modal.toast({message: '上传成功！'})
                    }, () => {
                        //compelete

                    }, () => {
                        //exception
                        var modal = weex.requireModule("modal")
                        modal.toast({message: '上传异常！'})
                    })
                });
            },
            jk()
            {


                var self = this;
                const photo = weex.requireModule('photo');
                photo.open(500, 800, '#000000', '#ffffff', '#ffffff', function (e) {

                    self.src = e.path;

                    var net = weex.requireModule("net");
                    net.postFile('http://59.110.169.246/movie/imgupload.do', {//param

                    }, {
                        // header
                    }, {file: e.path}, () => {
                        //start
                    }, (e) => {
                        //succcess
                        var modal = weex.requireModule("modal")
                        modal.toast({message: '上传成功！'})
                    }, () => {
                        //compelete

                    }, () => {
                        //exception
                        var modal = weex.requireModule("modal")
                        modal.toast({message: '上传异常！'})
                    })
                });

//                const modal = weex.requireModule('modal');
//                modal.toast({ message: '右边点击' });

            },

        },
        created: function () {


            var globalEvent = weex.requireModule('globalEvent');

            globalEvent.addEventListener("onPageInit", function (e) {

            });

        }
    }
</script>