

<template>
    <scroller style="flex:1;">
        <div   >
            <div  style="align-items: center"  >
                <div  @click="jk()" class="btn"><text style="color:#ffffff" >打开所有</text></div>
                <div  @click="openCamera()" class="btn"><text style="color:#ffffff" >打开相机</text></div>
                <div  @click="openPhoto()" class="btn"><text style="color:#ffffff" >打开相册</text></div>
                <image :src="src"  style="width:400;height:600;margin-top:50"></image>
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
<style src="./css/style.css"></style>
<script>




    export default {

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

                    self.src=e.base64;
                });
            },
            openCamera()
            {
                var self=this;
                const photo = weex.requireModule('photo');
                photo.openCamera(500,800,'#000000',function(e){

                    self.src=e.base64;
                });
            },
            jk()
            {


                var self=this;
                const photo = weex.requireModule('photo');
                photo.open(500,800,'#000000','#ffffff','#ffffff',function(e){

                 self.src=e.base64;
                });

//                const modal = weex.requireModule('modal');
//                modal.toast({ message: '右边点击' });

            },

        },
        created:function(){





            var globalEvent = weex.requireModule('globalEvent') ;

            globalEvent.addEventListener("onPageInit", function (e) {
                const nav = weex.requireModule('navbar');
                nav.setTitle('照相');
                nav.setBack(true);
                nav.setRightImage('img/scan.png',function(res){

                    var modal = weex.requireModule('modal') ;
                    modal.alert({message:"ok"})
                });
            });

        }
    }
</script>