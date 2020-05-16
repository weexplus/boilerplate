<template>
    <div class="titleroot"  :style="{'background-color':bgcolor}">
        <div class="status" :style="{'background-color':statusBgcolor,'height':statusHeight+'px'}"></div>
        <div class="titlelayout">
            <div class="backarea" @click="backClick">
                <image src="root:img/back.png"
                       style="width: 70px;height: 70px;margin-left: -20px"></image>
                <text style="color: white;font-size: 27px">返回</text>
            </div>
            <text style="color: white;font-size: 36px" :style="{'color':titleColor,'font-size':titleSize+'px'}">{{title}}</text>
            <div class="rightArea">
               <slot name="right">
               </slot>
            </div>
            <div v-if="!hideBottomLine"
             style="height: 1px;background-color: #cccccc;position: absolute;bottom: 0;left: 0;right: 0;width: 750px">
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            title: {
                default: '标题'
            },
            bgcolor: {
                default: '#1EA5FC'
            },
            statusBgcolor:{
                default: '#1EA5FC'
            },
            titleColor: {
                default: '#ffffff'
            },
            titleSize:{
                default:36
            },
            hideBottomLine: {
                default: true
            },
        },
        data() {
            return {
                statusHeight:40
            }
        },
        methods: {
            onLoad(param) {

            },
            onShow() {

            },
            onHide() {

            },
            onUnload() {

            },
            backClick(){
              this.$navigator.back()
            },
            adjust() {
                if (weex.config.env.platform == 'android') {
                    // var p = weex.config.env.osVersion
                    // p = p.replace(/\./g, '')
                    // if (p.length < 3)
                    //     p = p + "0";
                    // if (p <= '440') {
                    //     this.height = 108
                    //     this.top = 16;
                    //     this.titletop = 4;
                    // }
                }else if (weex.config.env.platform == 'web') {
                    this.fontSize=20
                }
            }
        },
        created() {
            this.adjust()
        }
    }
</script>

<style scoped>
    .status {
        height: 40px;
    }

    .titlelayout {
        height: 88px;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        position: relative;
    }

    .titleroot {
        background-color: #5ac3ff;
    }

    .backarea {
        width: 170px;
        height: 88px;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        position: absolute;
        left: 0;
        top: 0;
    }
    .rightArea{
        position: absolute;
        right: 0;
        top: 0;
        height: 88px;
        justify-content: center;
    }
</style>