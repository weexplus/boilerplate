<template>
    <div>
    <div style="flex-direction: row;align-items: center">
         <div :style="{'border-radius':radius}" style="flex: 1; background-color: #5c616f;height: 65;border-radius: 5;justify-content: center;align-items: center;flex-direction: row">
           <image src="root:img/search.png" style="width: 40;height: 40;margin-left: 20"></image>
             <finput ref="input" @return="onreturn" @onchange="change" color="#8d909a" :value="value"  @focus="onfocus" @blur="onblur" return_key_type="search"  :placeholder_color="placeholder_color" :autofocus="autofocus" style="flex:1;height: 65;padding-left: 1;margin-top: 5" :placeholder="placeholder"        ></finput>
         </div>
        <text @click="cancel" class="cancel" v-if="needCancel">取消</text>

    </div>

    </div>
</template>
<style scoped>


    .cancel{
        margin-left: 20;
        color: #444444;
        font-size: 30;
        font-weight: bold;
    }
    .cancel:active{
        color: #000000;
    }





</style>
<script>
    var finput =require('./input.vue')
    export default {
        components:{finput},
        props:{
            text:{
                type:String,

            },
            radius:
                {
                    default:5
                },
            color:{
                type:String,

            },
            value:
                {
                  default:''
                },
            placeholder:{
                default:'',

            },
            disabled:
                {

                    default:false
                },
            autofocus:{
                default:true
            },
            needCancel:{
                default:true
            },
            type:{
                type:String,
                default:'text'
            },
            font_size:
            {
               default:20
            },
            placeholder_color:
                {
                    default:'#8d909a'
                }

        },
        data () {
            return {

                visiable:true,


            }
        },
        methods: {
            cancel()
            {
              this.$refs.input.blur();
                const nav = weex.requireModule('navigator');
                nav.backFull({},false);
            },
            change(res)
            {
                this.$emit('change',res);
            },
            onreturn(e)
            {
                this.$emit('return',e);
            },
            onfocus()
            {
                this.$emit('focus');
            },
            focus()
            {
                this.$refs.input.focus();
            },
            onblur()
            {
                this.$emit('blur');
            },
            blur()
            {
                this.$refs.input.blur();
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
