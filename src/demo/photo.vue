

<template>

        <div   >
            <head title="相机"   append="tree">

            </head>
            <div  style="align-items: center"  >
                <div  @click="jk()" class="btn"><text style="color:#ffffff" >打开所有</text></div>
                <div  @click="openCamera()" class="btn"><text style="color:#ffffff" >打开相机</text></div>
                <div  @click="openPhoto()" class="btn"><text style="color:#ffffff" >打开相册</text></div>
                <image :src="src"  style="width:400;height:600;margin-top:50"></image>
            </div>




        </div>




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


    export default {
        components:{head},
        data () {
            return {
               src:""
            }
        },
        methods: {
            openPhoto()
            {
                var self=this;
                const photo = weex.requireModule('photo');
                photo.openPhoto(500,800,'#000000','#ffffff','#ffffff',function(e){

                    self.src=e.path;
//                    var net=weex.requireModule("net");
//                    net.postFile('http://10.39.1.72:8081/cdtp/file/uploadReturnName',{//param
//
//                    },{
//                        // header
//                    },{file:e.path},()=>{
//                        //start
//                    },(e)=>{
//                        //succcess
//                        var modal=weex.requireModule("modal")
//                        modal.toast({message:'上传成功！'})
//                    },()=>{
//                        //compelete
//
//                    },()=>{
//                        //exception
//                        var modal=weex.requireModule("modal")
//                        modal.toast({message:'上传异常！'})
//                    })

                });
            },
            openCamera()
            {
                var self=this;
                const photo = weex.requireModule('photo');
                photo.openCamera(500,800,'#000000',function(e){

                    self.src=e.path;

//                    var net=weex.requireModule("net");
//                    net.postFile('http://59.110.169.246/movie/imgupload.do',{//param
//
//                       },{
//                        // header
//                         },{file:e.path},()=>{
//                        //start
//                    },(e)=>{
//                        //succcess
//                        var modal=weex.requireModule("modal")
//                        modal.toast({message:'上传成功！'})
//                    },()=>{
//                        //compelete
//
//                    },()=>{
//                        //exception
//                        var modal=weex.requireModule("modal")
//                        modal.toast({message:'上传异常！'})
//                    })
                });
            },
            jk()
            {


                var self=this;
                const photo = weex.requireModule('photo');
                photo.open(500,800,'#000000','#ffffff','#ffffff',function(e){

                    self.src=e.path;

                    var net=weex.requireModule("net");
                    net.postFile('http://59.110.169.246/movie/imgupload.do',{//param

                    },{
                        // header
                    },{file:e.path},()=>{
                        //start
                    },(e)=>{
                        //succcess
                        var modal=weex.requireModule("modal")
                        modal.toast({message:'上传成功！'})
                    },()=>{
                        //compelete

                    },()=>{
                        //exception
                        var modal=weex.requireModule("modal")
                        modal.toast({message:'上传异常！'})
                    })
                });

//                const modal = weex.requireModule('modal');
//                modal.toast({ message: '右边点击' });

            },

        },
        created:function(){





            var globalEvent = weex.requireModule('globalEvent') ;

            globalEvent.addEventListener("onPageInit", function (e) {

            });

        }
    }
</script>