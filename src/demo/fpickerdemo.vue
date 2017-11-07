<template>
    <div style="flex:1;">



        <flist class="list" ref="list" @load="load" @refresh="refresh"  >


            <cell class="cell" slot="cell" v-for="p in lists"  >
                <div class="panel" @click="itemClick(p)">
                    <text class="cell_title">{{p.name}}</text>
                    <text class="cell_money">{{p.salarBottom}}-{{p.salarTop}} 元/月</text>
                    <image style="width: 25;height: 32;position: absolute;left:36;bottom:20;" src="img/location.png"></image>
                    <image style="width: 20;height: 32;position: absolute;right:45;top:75;" src="img/arrow.png"></image>
                    <text class="cell_location" >{{p.workAddress}}</text>
                    <div class="bottom_line"></div>
                </div>
            </cell>

        </flist>
    </div>

</template>

<style scoped>
    .indicator {

        height: 40;
        width: 40;


    }
    .entercell
    {
        flex:1;
        justify-content: center;
        align-items: center;

    }
    .entercell:active{
        background-color: #eeeeee;
    }
    .entertext
    {
       font-size: 30;
        margin-top: 15;
        color: #7e7e7e;
    }
    .enterimg
    {
        width: 100;
        height:100;
        margin-top: -10;
    }
    .hd
    {
        height:200;
        top:0;
        left:0;
        width:200;
        background-color:blue;
        position:fixed;
    }

    .panel {
        height: 150px;
        position: relative;
    }
    .panel:active
    {
        background-color: #eeeeee;
    }
    .cell_title
    {
       left: 36;
        top:25;
        font-size: 35;
    }
    .cell_money
    {
       right:43;
        top:25;
        position: absolute;
        font-size: 30;
        color: #ffbb35;

    }
    .cell_location
    {
        position: absolute;
        left: 72;
        bottom: 15;
        font-size: 30;
        color: #e1e1e1;
    }
    .bottom_line
    {
        height: 2;
        position: absolute;
        width: 750;
        background-color: #f0f0f0;
        bottom: 0;
        left: 36;
    }

    .text {
        font-size: 50px;
        text-align: center;
        color: #41B883;
    }
</style>
<script>
    var pull =require('./pullrefresh.vue')
    var flist =require('./../component/flist.vue')

    const modal = weex.requireModule('modal')
    const LOADMORE_COUNT = 10

    export default {
        components:{pull,flist},
        data () {
            return {
                lists: [],
                pageNo:1,


            }
        },
        methods: {
            fetch (event) {

             this.pageNo++;
             this.load();

            },

            refresh(){
                this.lists.length=0;
                this.pageNo=1;
                this.isloading=false;
                this.load();
            },


            load()
            {
//                this.pageNo++;
//                const t= this.pageNo+"";
//                var self=this;
//                this.$refs.list.load('http://hrmap.renturbo.com/position/search',{"distance":"10","pageNo":t},this.lists,function(itms){
//                    self.lists=itms;
//                });
                this.$refs.list.showEmpty();
            }

        },

        created:function(){

//            this.load();
//            this.$refs.list.load('http://hrmap.renturbo.com/position/search',{"distance":"10","pageNo":this.pageNo},this.lists);
        }
    }
</script>

 