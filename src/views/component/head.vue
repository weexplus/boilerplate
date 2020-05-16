<template>
    <div class="titleroot"  :style="{'background-color':bgcolor}">
        <div class="status" :style="{'background-color':statusBgcolor,'height':statusHeight+'px'}"></div>
        <div class="titlelayout">
            <div class="backarea" @click="backClick">
                <text class="back" style="font-family: sys;font-size: 30px;margin-left: 15px" :style="{'color':titleColor}">&#xe96e;</text>
                <text style="color: white;font-size: 32px;margin-left: 2px;margin-bottom: 1px" :style="{'color':titleColor}" v-if="showCloseWord">{{closeWord}}</text>
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
                default:38
            },
            hideBottomLine: {
                default: true
            },
            closeWord:{
                default:'返回'
            },
            showCloseWord:{
                default:true
            }
        },
        data() {
            return {
                statusHeight:44
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
            registFont(){
                const font = weex.requireModule('font')
                font.addFont('sys','root:font/iconfont.ttf')
            },
            adjust() {
                if (weex.config.env.platform == 'android') { d
                }else if (weex.config.env.platform == 'web') {
                    this.fontSize=20
                }else if (weex.config.env.platform == 'iOS') {
                    let isfullscreen= weex.requireModule('env').isFringeScreen()
                    if(isfullscreen){
                        this.statusHeight=90
                    }
                }
            }
        },
        created() {
            this.adjust()
            this.registFont()
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