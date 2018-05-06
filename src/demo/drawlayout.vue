

<template>

    <drawerlayout
     src="font.js"
     slidSrc="center.js"
    >

    </drawerlayout>


</template>

<style>
.cl
{

    align-items: center;

}


</style>
<style src="./style.css"></style>
<script>

    var head =require('./header.vue')
    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {


    });

    export default {
        components:{head},
        data () {
            return {
               back:"",
                header:{}
            }
        },
        methods: {
            post()
            {
                 var self=this;
                self.back="";
                const net = weex.requireModule('net');
                net.post('http://121.40.81.1:9080/edu/getBanners.do',{a:"1",b:"2"},{},function(){
                    //start
                },function(e){
                    //success
                    self.back=e.res;
                    self.header=r.headers;
                },function(e){
                  //exception

                },function(){
                    //compelete
                });


            },
            get()
            {


                var self=this;
                const net = weex.requireModule('net');
                self.back="";
                net.get('http://121.40.81.1:9080/edu/getBanners.do',{},{},function(){
                    //start
                },function(e){
                    //success
                    self.back=e.res;
                },function(e){
                    //exception

                },function(){
                    //compelete
                });


            }

        },
        created:function(){


        }
    }
</script>