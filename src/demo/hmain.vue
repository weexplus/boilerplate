<template>
    <div style="flex:1;">

        <list class="list" layout="2" @loadmore="fetch" >
            <pull ref="refresh" v-on:refresh="refresh"></pull>
            <cell>
                <image style="width: 750;height:230" src="img/banner.jpg"></image>
                <div style="width:750;height: 220;flex-direction: row">
                    <div class="entercell" @click="near" >
                         <image src="img/home_ico_01.png"  class="enterimg"></image>
                          <text class="entertext">附近职位</text>
                    </div>
                    <div class="entercell"  >
                        <image src="img/home_ico_02.png" @click="goRecord"  class="enterimg"></image>
                        <text class="entertext">申请记录</text>
                    </div>
                    <div class="entercell"  >
                        <image src="img/home_ico_03.png"  @click="goCollection"   class="enterimg"></image>
                        <text class="entertext">我的收藏</text>
                    </div>
                    <div class="entercell" >
                        <image src="img/home_ico_04.png"   @click="goResume"  class="enterimg"></image>
                        <text class="entertext">简历中心</text>
                    </div>

                </div>
                <div style="background-color: #f0f0f0;height:2;width:750"></div>
            </cell>
            <cell class="cell" v-for="p in lists"  >
                <div class="panel" @click="itemClick(p)">
                   <text class="cell_title">{{p.name}}</text>
                    <text class="cell_money">{{p.salarBottom}}-{{p.salarTop}} 元/月</text>
                    <image style="width: 25;height: 32;position: absolute;left:36;bottom:20;" src="img/location.png"></image>
                    <image style="width: 20;height: 32;position: absolute;right:45;top:75;" src="img/arrow.png"></image>
                    <text class="cell_location" >{{p.workAddress}}</text>
                    <div class="bottom_line"></div>
                </div>
            </cell>
            <loading  >
               <div style="width: 750;height:100;justify-content: center;align-items: center;flex-direction: row">
                   <floading   class="indicator" color="#000000" loading-style="small" display="show"> </floading>

                   <text style="margin-left: 10">加载中...</text>
               </div>
            </loading>
        </list>
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
    import head from './head.vue'
    const modal = weex.requireModule('modal')
    const LOADMORE_COUNT = 10

    export default {
        components:{pull},
        data () {
            return {
                lists: [],
                pageNo:1,
                isloading:false

            }
        },
        methods: {
            fetch (event) {

             this.pageNo++;
             this.load();

            },
            itemClick(p)
            {
                var mp = weex.requireModule('mainpage') ;
                mp.gotoDetail(p);
            },
            near()
            {

                var mp = weex.requireModule('mainpage') ;
                mp.goNearBy();
            },
            goRecord()
            {

                var mp = weex.requireModule('mainpage') ;
                mp.goRecord();
            },
            goCollection()
            {

                var mp = weex.requireModule('mainpage') ;
                mp.goCollection();
            },
            goResume()
            {

                var mp = weex.requireModule('mainpage') ;
                mp.goResume();
            },
            refresh(){
                this.lists.length=0;
                this.pageNo=1;
                this.isloading=false;
                this.load();
            }
             ,
            load(){
                if(this.isloading)
                    return;
                this.isloading=true;
//                modal.alert({message:'ssss'})
                var self=this;
                const net = weex.requireModule('net');
                net.post('http://hrmap.renturbo.com/position/search',{"distance":"10","pageNo":this.pageNo},{},function(){
                    //start
                },function(e){
                    //success

                    self.lists= self.lists.concat(e.res.list);


                },function(e){
                    //exception

                },function(){
                    //compelete
                    var p= self.$refs.refresh
                    p.end();
                    self.isloading=false;
                });


            },
        },

        created:function(){

            var globalEvent = weex.requireModule('globalEvent') ;
//
            var self=this;
//
            globalEvent.addEventListener("onPageInit", function (e) {
//                modal.alert({message:'xxxx'})

                self.load();

            });
        }
    }
</script>

 