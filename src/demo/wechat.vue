

<template>

    <scroller class="layout" show-scrollbar="false">
     <div class="line" style="margin-top: 30;"></div>
        <div class="cell" >
          <image class="left_img" src="img/friend.png"></image>
            <text   class="t">朋友圈</text>
            <image class="right_img" src="img/arrow.png"></image>
        </div>
        <div class="line"></div>


        <div class="block">
            <div class="line"  ></div>
            <div class="cell" >
                <image class="left_img"  style="width: 42;height:40"  src="img/we_scan.png"></image>
                <text   class="t">扫一扫</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>
            <div class="leftline"></div>
            <div class="cell" >
                <image class="left_img"  style="width: 44;height:44" src="img/shake.png"></image>
                <text   class="t">摇一摇</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>
            <div class="leftline"></div>
            <div class="cell" >
                <image class="left_img" style="width: 40;height:40;margin-left: 40"  src="img/search.png"></image>
                <text   class="t">搜一搜</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>
            <div class="leftline"></div>
            <div class="cell" >
                <image class="left_img" style="width: 44;height: 30" src="img/look.png"></image>
                <text   class="t">看一看</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>
            <div class="line"></div>
        </div>

        <div class="block">
            <div class="line"  ></div>
            <div class="cell" >
                <image class="left_img"  style="width: 42;height:40"  src="img/nearby.png"></image>
                <text   class="t">附近的人</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>
            <div class="leftline"></div>
            <div class="cell" >
                <image class="left_img"  style="width: 44;height:44" src="img/bot.png"></image>
                <text   class="t">漂流瓶</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>

            <div class="line"></div>
        </div>


        <div class="block">
            <div class="line"  ></div>
            <div class="cell" >
                <image class="left_img"  style="width: 36;height:40;margin-left: 38"  src="img/buy.png"></image>
                <text   class="t">购物</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>
            <div class="leftline"></div>
            <div class="cell" >
                <image class="left_img"  style="width: 44;height:44;margin-left: 31" src="img/game.png"></image>
                <text   class="t">游戏</text>
                <image class="right_img" src="img/arrow.png"></image>
            </div>

            <div class="line"></div>
        </div>

        <div class="line" style="margin-top: 50"></div>
        <div class="cell"  >
            <image class="left_img" src="img/small_app.png"></image>
            <text   class="t">朋友圈</text>
            <image class="right_img" src="img/arrow.png"></image>
        </div>
        <div class="line" style="margin-bottom: 100"></div>

    </scroller>





</template>

<style>
.layout
{
   background-color: #efeff4;


}
    .line
    {
        background-color:#dddddd;

        height:1.5px;
        width:750px;
    }
    .block
    {
        background-color: #ffffff;
        margin-top: 40;
    }
.leftline
{
    background-color:#dddddd;

    height:1px;
    margin-left: 35;


}
    .t
    {

        color: #000000;
        margin-left: 30;
        font-size: 35;
        flex: 1;
    }
    .cell
    {
        width:750px;
        height:90px;
        flex-direction: row;
        align-items: center;
        background-color: #ffffff;
    }
    .cell:active
    {
        background-color: #dddddd;
    }
    .left_img{
        width:47;
        height:47;
        margin-left: 30;
    }
.right_img{
    width:20;
    height:30;
    margin-right: 30;
}

</style>
<style src="./css/style.css"></style>
<script>


    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
        const nav = weex.requireModule('navbar');
        nav.setTitle('发现');
        nav.setBack(true);
        nav.setBackgroundColor('#353439');
        nav.setRightImage('img/scan.png',function(res){

            var modal = weex.requireModule('modal') ;
            modal.alert({message:"ok"})
        });
    });

    export default {

        data () {
            return {

            }
        },
        methods: {
            jk()
            {

                const modal = weex.requireModule('modal');
                modal.toast({ message: 'native toast' });

            },

        },
        created:function(){


        }
    }
</script>