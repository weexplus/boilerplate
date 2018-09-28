<template>

        <refresh class="refresh" id="rex" :key="key" @refresh="onrefresh" @pullingdown="onpullingdown" :display="refreshing ? 'show' : 'hide'">
            <div style="flex-direction: row"  >

                <floading class="indicator"    v-if="refreshing"  color="#555555" > </floading>

                <image ref="arrow" src="root:img/pull_arrow.png" class="limg"   v-if="!refreshing" ></image>
                <div style="align-items: center;">
                    <text class="refreshText">{{rtext}}</text>
                    <text    style="font-size: 25; color: #888888;">上次更新:{{updatetime}}</text>
                </div>
            </div>
        </refresh>

</template>
<script>

    export default {

        data () {
            return {
                rtext:'下拉以加载',
                updatetime:'没有更新',
                offset:0,
                deg:20,
                refreshing:false,
                pulldistance:135,
                hasrotate:false,
                key:"ky"+Math.random()
            }
        },
        methods: {

            animateArrow(deg)
            {
                const animation = weex.requireModule('animation')
                var arrow = this.$refs.arrow;
//                var deg=this.hasrotate?180:0

                    animation.transition(arrow, {
                        styles: {
                            transform: "rotate("+deg+"deg"+")",
                        },

                        duration:150, //ms
                        timingFunction: 'ease',
                        delay: 0 //ms
                    }, function () {

                    })



            },
            onrefresh (event) {
                if(this.offset>=this.pulldistance)
                {

                    this.refreshing = true
                    this.rtext="加载中"
                    this.$emit('onRefresh');
//                    setTimeout(() => {
//                        this.refreshing = false
//                    }, 2000)
                }
            },
            end()
            {
                this.refreshing = false;
//                this.deg=0;
                this.updatetime=this.getNowFormatDate();
//                this.rtext='下拉以加载'

            },
            onpullingdown (event) {

                var dis=event.pullingDistance;
                if(dis<0)
                    dis*=-1;
                this.offset=dis;

                if(this.refreshing==false)
                {


//                     var t=dis>this.pulldistance
//                    if(t!=this.hasrotate)
//                    {
//                        this.hasrotate=t;
//                        this.animateArrow();
//                    }
                    if(dis>this.pulldistance)
                    {
                        this.rtext="松开刷新"
                        this.deg=180;
                        this.hasrotate=false;
                        this.animateArrow(180);
                    }
                    else {
                        var p=dis/this.pulldistance;
                        if(p>1)
                            p==1;
                        this.deg=p*180;
                        this.animateArrow(0);
                        this.rtext='下拉以加载'
                    }
                }

            },

            getNowFormatDate() {
                var date = new Date();
                var seperator1 = "-";
                var seperator2 = ":";
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                var min=date.getMinutes()
                var secon=date.getSeconds()
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                if (min >= 0 && min <= 9) {
                    min = "0" + min;
                }
                if (secon >= 0 && secon <= 9) {
                    secon = "0" + secon;
                }

                var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + date.getHours() + seperator2 + min
                    + seperator2 + secon;
                return currentdate;
            }
        },

        created:function(){





        }
    }
</script>
<style scoped>
    .limg{
        width:32;height:46;;
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
        font-size: 30;

    }
    .indicator {
        color: #888888;
        height: 40;
        width: 40;
        margin-right: 10;

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