<template>
    <scroller class="scroller" >
        <pull ref="refresh" v-on:refresh="refresh"></pull>
        <div class="cell"   v-for="num in lists">
            <div class="panel">

                <text class="text">{{num}}</text>
            </div>
        </div>
    </scroller>
</template>
<script>
    var pull =require('./pullrefresh.vue')
    const modal = weex.requireModule('modal')
    export default {
        data () {
            return {
                refreshing: false,
                lists: [1, 2, 3, 4, 5],
                rtext:'下拉以加载'
            }
        },
        components:{pull},
        methods: {
            refresh () {


                this.refreshing = true
                this.rtext="加载中"
                setTimeout(() => {
                    this.refreshing = false
                    this.lists=[1, 2, 3, 4, 5];
                    var p= this.$refs.refresh
                   p.end();
                }, 2000)
            },

        },
        created:function(){


            var globalEvent = weex.requireModule('globalEvent') ;

            globalEvent.addEventListener("onPageInit", function (e) {
                const nav = weex.requireModule('navbar');
                nav.setTitle('refresh');
                nav.setBack(true);
                nav.setRightImage('img/scan.png',function(res){

                    var modal = weex.requireModule('modal') ;
                    modal.alert({message:"ok"})
                });
            });


        }
    }
</script>
<style scoped>
    .scroller
    {
        background-color: #ffffff;
    }
    .refresh {
        height: 128;
        width: 750;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }
    .refreshText {
        color: #888888;
        font-weight: bold;
    }
    .indicator {
        color: #888888;
        height: 40;
        width: 40;
        margin-right: 30;
    }
    .panel {
        width: 600px;
        height: 250px;
        margin-left: 75px;
        margin-top: 35px;
        margin-bottom: 35px;
        flex-direction: column;
        justify-content: center;
        border-width: 2px;
        border-style: solid;
        border-color: #DDDDDD;
        background-color: #F5F5F5;
    }
    .text {
        font-size: 50px;
        text-align: center;
        color: #41B883;
    }
</style>