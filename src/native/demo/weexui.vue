

<template>
 <div>
     <head title="weex-ui"></head>
     <wxc-button text="Open Popup"
                 @wxcButtonClicked="buttonClicked">
     </wxc-button>
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
//    var WxcButton=require('./component/packages/wxc-button')
    import { WxcButton } from './component/weexui';


    export default {
        components:{head,WxcButton},
        data () {
            return {
               back:""
            }
        },
        methods: {
            read()
            {

               var r=weex.requireModule("addressBook")
                r.read((res)=>{
                    this.back=res;
                })

            },

        },
        created:function(){


        }
    }
</script>