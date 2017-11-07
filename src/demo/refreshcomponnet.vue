<template>
        <refresh class="refresh" @refresh="onrefresh" @pullingdown="onpullingdown" :display="refreshing ? 'show' : 'hide'">

            <floading   class="indicator" color="#000000" loading-style="small" display="show"> </floading>
            <text class="refreshText">loading....</text>
        </refresh>
</template>
<script>
    const modal = weex.requireModule('modal')
    export default {
        props:{


            refreshing:{
                type:boolean,
            }

        },
        data () {
            return {
                refreshing: false

            }
        },
        methods: {
            onrefresh (event) {
                console.log('is refreshing')
                modal.toast({ message: 'refresh', duration: 1 })
                this.refreshing = true

            },
            onpullingdown (event) {
                console.log('is onpulling down')
//                modal.toast({ message: 'pulling down', duration: 1 })
            }
        },
        created:function(){



        }
    }
</script>
<style scoped>
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