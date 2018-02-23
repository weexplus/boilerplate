<template>
    <div class="bg" style="flex: 1; ">
        <div style="background-color: #ffffff;">
            <indicate :items="items" ref="indicator" :textColor="headerTextColor"  @change="changeheader" :lineWidth="lineWidth" :lineLeft="lineLeft"  ></indicate>
        </div>
        <cycleslider @change="change"  :index="selectIndex"  style="width: 750;flex: 1" :style="{'margin-top':gape}"  :infinite="false">
            <div key="k1" class="content"    v-for="item in  items">
                <embed
                        :src="item.url"
                        type="weex"
                        :param="item.param"
                        class="content"
                ></embed>
            </div>
        </cycleslider>

    </div>
</template>
<script>

    var indicate = require('./indicator.vue')
    export default {
        components: {indicate},
        props: {
            lineWidth:{
                default:200
            },
            lineLeft:{
                default:85
            },
            gape:{
                default:0
            },
            items:[
                {selectSrc:'root:img/linkSelect.png',src:'root:img/link.png',text:'已关联',url:'root:busi/contract/thirdcontract/link.js'},
                {selectSrc:'root:img/linkSelect.png',src:'root:img/link.png',text:'已关联',url:'root:busi/contract/thirdcontract/link.js'}
            ],
            headerTextColor:
                {
                default:'#8d8d8d'
                }

        },
        data () {
            return {
//                list:[1,1,1,1,1,1,1,1,1,11,1,],
//                headers:[{selectSrc:'root:img/linkSelect.png',src:'root:img/link.png',text:'已关联'},{selectSrc:'root:img/cancellinkselect.png',src:'root:img/cancellink.png',text:'未关联'}],

                 selectIndex: 0
            }
        },
        methods: {

            changeheader(index)
            {
                this.selectIndex =index;
            },
            change(e)
            {

                this.selectIndex = e.index;
                this.$refs.indicator.move(e.index)

            },
        },

        created: function () {

//            var domModule=weex.requireModule("font");
//            domModule.addRule('fontFace',{
//                'fontFamily':'iconfont',
//                'src':"url(\'./../../font/icomoon.ttf\')"
//            })

            const nav = weex.requireModule('navbar');
            nav.setStatusBarStyle('white');


        },



    }
</script>
<style src="../css/style.css"></style>
<style scoped>

    .content {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin-top: 0;
        margin-bottom: 0;
    }



</style>