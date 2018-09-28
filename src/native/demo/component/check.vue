<template>
       <div   style="align-items: center;justify-content: center"    @click="onclick">
           <image :src="ischeck?'root:img/select.png':'root:img/unselect.png'" style="width: 40;height: 40"></image>
       </div>

</template>
<script>

    export default {
        props:{

            ischeck:
                {

                    default:false
                },
            item:{
                default:{}
            }



        },
        data () {
            return {


            }
        },
        methods: {

            onclick()
            {
                this.ischeck=!this.ischeck;
                var p={};
                p.item=this.item;
                p.check= this.ischeck
                this.$emit('change',p)
            },



        },

        created:function(){




        },
        ready:function()
        {



        },
//        watch: {
//
//
//            disabled:{
//                immediate: true,
//                handler (val) {
//
//                }
//            }
//        }
    }
</script>
<style src="../css/style.css"></style>
<style scoped>


    .text {
        color:#ffffff;
        font-size: 30;
    }

    .text-disabled {
        color:#b4b4b4;
        font-size: 30;
    }
    .button
    {
        height: 100;
        background-color: #ff4800;
        align-items: center;
        justify-content: center;
        color: #ffffff;
        border-radius: 8;
    }
    .button:active
    {
        background-color: #ff1b08;
    }
    .button-disabled
    {
        height: 100;
        background-color: #eeeeee;
        align-items: center;
        justify-content: center;
        color: #ffffff;
        border-radius: 8;
    }
    .button-disabled:active
    {
        background-color: #eeeeee;
    }




</style>