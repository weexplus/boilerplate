<template>
      <div class="layout"  :style="{'background-color':bgcolor,'height':height}">


          <div :style="{'top':titletop}" style="flex-direction: row;" >
              <div    v-if="isloading" style=" height: 40;width: 40;margin-right: 10;"     ></div>
              <text style="flex: 1;color: #ffffff;text-align: center;font-size: 38;" @click="titleClick">{{title}}</text>
              <floading    v-if="isloading" style=" height: 40;width: 40;margin-left: 10;margin-top: 5" color="#ffffff" loading-style="small" ></floading>

          </div>

        <!--<div style="height: 128;width: 100;">-->
        <!--</div>-->
          <div  :style="{'height':height,'top':top}"  style="width: 200;top: 40;position: absolute;left: 0;" v-if="back" @click="backTo" >
              <image src="root:img/back.png" style="width: 80;height: 80"></image>

          </div>

          <div :style="{'height':height}" @click="rightclick" style="width: 200;position: absolute;right: 0;top: 0;align-items: center;justify-content: center">
              <slot name="right"></slot>
          </div>
          <div style="height: 1;background-color: #111111;position: absolute;bottom: 0;left: 0;right: 0">

          </div>
      </div>
</template>
<script>

    export default {
        props:{
            title:{
               default:''

            },
            back:{
              default:true
            },
            bgcolor:{
                default:'#222222'

            },
            isloading:{
                default:false
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
            },
            height:
            {
                default:128
            },
            top:
            {
                default:40
            },
            titletop:
            {
                default:10
            }



        },
        data () {
            return {




            }
        },
        methods: {
            titleClick()
            {
                this.$emit('titleClick');
            },
            rightclick()
            {
                this.$emit('rightClick');
            },
            backTo()
            {
                var nav=weex.requireModule("navigator");
                nav.back();
                this.$emit('backClick');
            },

            onclick()
            {
                if(!this.disabled)
                    this.$emit('onclick');
            },



            adjust()
            {
                if(weex.config.env.platform=='android')
                {
//                    if(weex.config.env.osVersion=)
                    var p=weex.config.env.osVersion
                    p= p.replace(/\./g,'')
                    if(p.length<3)
                        p=p+"0";
                    if(p<='440')
                    {
                        this.height=108
                        this.top=16;
                        this.titletop=4;
                    }
                }
            }


        },

        created:function(){


             this.adjust();

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
<style src="./css/style.css"></style>
<style scoped>


    .layout {
        background-color: #333333;height: 128;flex-direction: row;align-items: center;justify-content: center;width: 750px;
    }


</style>