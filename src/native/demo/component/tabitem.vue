<template>

    <div  @click="layoutclick" style="align-items: center;justify-content: center" >
       <image  :src="currentIndex==index?selectImg:img"   style="width: 50;height: 50;margin-top: 10">

       </image>
        <text style="margin-top: 5" :style="{'font-size':fontSize,'color':currentIndex==index?selectColor:color }">{{text}}</text>
    </div>

</template>
<script>

    export default {
        props:{

            text:{
                 default:''

            },
            currentIndex:
                {
                    default:0
                },
            color:
                {
                    default:'#000000'
                },
            selectColor:
                {
                    default:'#000000'
                },
            img:{
                default:''
            },
            selectImg:{
                default:''
            },
            select:{
              default:false
            },

            fontSize:
            {
               default:28
            },
            index:
                {
                    default:0
                }

        },
        data () {
            return {

                visiable:true,


            }
        },
        methods: {

            layoutclick()
            {
                this.select=!this.select;
                this.$emit('change',this.index)
            },
            oninput(e)
            {

//                this.$emit('oninput');
                this.$emit('oninput', e);
                 this.visiable=e.value!='';


            },
            onclick()
            {
                if(!this.disabled)
                    this.$emit('onclick');
            },
            panstart()
            {
                if(!this.disabled)
                this.bgcolor='#ff1b08'
            },
            panend()
            {
                if(!this.disabled)
                this.bgcolor='#ff4800'
            },
            setenable()
            {

            },


            onclose()
            {
                this.value='';
            }


        },

        created:function(){

            this.visiable=!this.value=='';


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