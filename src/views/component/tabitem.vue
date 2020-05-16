<template>
    <div  @click="layoutclick" style="align-items: center;justify-content: center" >
        <text style="width: 50px;height: 50px;margin-top: 15px;font-size: 42px;margin-left: 0px;text-align: center;"  :style="{'color':currentIndex==index?selectColor:color,'font-family':fontFamily}">{{ currentIndex==index?selectImg:img | getFontName}}</text>
        <text style="margin-top: 5px;margin-bottom: 5px;margin-left: 0px;text-align: center;" :style="{'font-size':fontSize,'color':currentIndex==index?selectColor:btmTextColor,'margin-left':left }">{{text}}</text>
        <div class="round" v-if="unread>0">
            <text style="color:white;font-size: 32px">{{unread}}</text>
        </div>
    </div>
</template>
<script>
    var he = require('he');
    export default {
        props:{

          fontFamily:{
            default:'nj'
          },
            text:{
                 default:''
            },
            unread:{
                default:0
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
            btmTextColor:
                {
                    default:'#999999'
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
            left:
                {
                    default:6.7
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
        filters:{
            getFontName: function(text) {
                return he.decode(text)
            }
        },

        methods: {

            getFontName: function(text) {
                return he.decode(text)
            },
            layoutclick()
            {
                this.select=!this.select;
                this.$emit('onChange',this.index)

            },

            onclick()
            {
                if(!this.disabled)
                    this.$emit('onclick');
            },



        },

        created:function(){

            this.visiable=!this.value=='';
            // this.registFont()

        },

    }
</script>
<style scoped>

    .round{
        width: 40px;
        height: 40px;
        border-radius: 20px;
        background-color: #ff2025;
        align-items: center;
        justify-content: center;
        position: absolute;
        right: 10px;
        top:10px;
    }
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