<template>
    <div style="flex-direction: row;height: 100;align-items: center;background-color: red" >
        <input ref="input" @return = "onreturn" :return-key-type="return_key_type" style="flex: 1;padding-left: 20;height:100;"  @focus="onfocus"  :autofocus="autofocus" :placeholder="placeholder" :type="type"  v-model="value" @change="onchange"  @input="oninput"    :style="{'color':color,'placeholder-color':placeholder_color}"/>
        <div  style="width: 50px;height: 100px;margin-right: 10;align-items: center;justify-content: center;"  v-if="visiable" @click="onclose()">
            <image src="root:img/delete.png"   style="width: 30px;height: 30px;" ></image>
        </div>
    </div>
</template>
<script>

    export default {
        props:{
            placeholder:{
                default:''

            },
            placeholder_color:{
                default:'#ffffff'

            },
            color:{
                default:'#000000',

            },
            value:
                {
                    default:''
                },

            type:{
                type:String,
                default:'text'
            },
            font_size:
                {
                    default:20
                },
            autofocus:
                {
                    default:false
                },
            return_key_type:
                {
                    default:'defalut'
                }

        },
        data () {
            return {

                pulldistance:180,
                visiable:true,

            }
        },
        methods: {
            onchange (event) {
                this.visiable=!event.value=='';
//                this.$emit('onchange',event.value);
                this.value=event.value;
//                this.name="xxx"
            },
            onfocus()
            {
                this.$emit('focus');
            },
            focus()
            {
                this.$refs.input.focus();
            },
            blur()
            {
                this.$refs.input.blur();
                this.$emit('blur');
            },
            oninput(e)
            {

//                this.$emit('oninput');
                this.value=e.value;
                this.visiable=e.value!='';
                this.$emit('onchange', e.value);

            },
            onreturn(e)
            {
                this.$emit('return', e);
            },


            onclose()
            {
                this.value='';
                this.visiable=false;
                this.$emit('onchange','');
            }


        },

        created:function(){
            var globalEvent = weex.requireModule('globalEvent') ;
            globalEvent.addEventListener("onPageInit", function (e) {





            });

            this.visiable=!this.value=='';


        },
        ready:function()
        {



        }
    }
</script>
<style scoped>

    .text {
        font-size: 50px;
        text-align: center;
        color: #41B883;
    }
</style>