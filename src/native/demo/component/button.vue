<template>
       <div   :class="[disabled? 'button-disabled':'button']"      @click="onclick">
           <text    :class="[disabled? 'text-disabled':'text']"  >{{text}}</text>
       </div>

</template>
<script>

    export default {
        props:{
            text:{
                type:String,

            },
            color:{
                type:String,

            },
            disabled:
                {

                    default:false
                },

            type:{
                type:String,
                default:'text'
            },
            font_size:
            {
               default:20
            }

        },
        data () {
            return {

                visiable:true,


            }
        },
        methods: {

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