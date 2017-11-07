<template>


        <refresh class="refresh" id="rex" @refresh="onrefresh" @pullingdown="onpullingdown" :display="refreshing ? 'show' : 'hide'">
            <div style="flex-direction: row">
                <div style="width: 50;height: 50;position: absolute;" :style="{visibility:refreshing ? 'show':'hidden'}"><floading class="indicator"  > </floading></div>
                <image ref="ll" src="img/pull_arrow.png" class="limg" :style="{transform:'rotate('+deg+'deg)',visibility:refreshing ? 'hidden':'show'}" ></image>
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
                refreshing: false,
                rtext:'下拉以加载',
                updatetime:'没有更新',
                offset:0,
                deg:20,
                pulldistance:180,

            }
        },
        methods: {
            onrefresh (event) {
                if(this.offset>=this.pulldistance)
                {
                    this.refreshing = true
                    this.rtext="加载中"
                    this.$emit('refresh');
                }
            },
            end()
            {
                this.refreshing = false;
                this.deg=0;
                this.updatetime=this.getNowFormatDate();
//                this.rtext='下拉以加载'

            },
            onpullingdown (event) {

                var dis=event.pullingDistance*-1;
                this.offset=dis;

                if(this.refreshing==false)
                {



                    if(dis>this.pulldistance)
                    {
                        this.rtext="松开刷新"
                        this.deg=180;
                    }
                    else {
                        this.deg=dis/this.pulldistance*180;
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
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + date.getHours() + seperator2 + date.getMinutes()
                    + seperator2 + date.getSeconds();
                return currentdate;
            }
        },

        created:function(){





        }
    }
</script>
<style scoped>
    .limg{
        width:32;height:46;
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