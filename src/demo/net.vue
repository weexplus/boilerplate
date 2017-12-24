

<template>
 <scroller>
     <head title="网络请求sss"  append="tree">

     </head>
        <div  style="margin-bottom:50" >
            <div  class="cl" >
                <div  @click="post()" class="btn"><text style="color:#ffffff" >post</text></div>
                <div  @click="get()" class="btn" style="width: 500"><text style="color:#ffffff" >get</text></div>

                    <text style="color:#ffffff">{{header}}</text>
                    <text style="color:#ffffff">{{back}}</text>


            </div>




        </div>

 </scroller>


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
        const nav = weex.requireModule('navbar');
        nav.setTitle('网络请求');
        var navigator = weex.requireModule('navigator') ;

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