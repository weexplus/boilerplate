

<template>

        <div   >
            <head title="picker"   append="tree">

            </head>
            <div  style="align-items: center"  >
                <div  @click="jk()" class="btn"><text style="color:#ffffff;font-size: 30" >picker单列</text></div>

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


            jk()
            {


                var picker=weex.requireModule("fpicker");
                picker.setCount(1);
                picker.setItems1(['猫','狗','鹦鹉'])
//                picker.setItems2(this.toArray(l[0].children))
                picker.setTheme('#5261f7','#ffffff')
                picker.show();
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