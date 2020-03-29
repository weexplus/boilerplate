<template>
    <div  >
        <div class="layout" :style="{'background-color':bgcolor,'height':height+'px'}">
            <div :style="{'top':titletop+'px'}" style="flex-direction: row;margin-top: 13px;">
                <text style="flex: 1;text-align: center;font-size: 36;" :style="{'color':titleColor}"
                      @click="titleClick">{{title}}
                </text>
            </div>
            <div :style="{'height':height}" class="backbg" v-if="back" @click="backTo">
                <image src="root:img/back.png"
                       style="width: 70px;height: 70px;margin-top: 40px;margin-left: 22px"></image>
            </div>
            <div :style="{'height':height}" @click="rightclick"
                 style="width: 130;position: absolute;right: 0;top: 0;align-items: center;justify-content: center;">
                <slot name="right"></slot>
            </div>
            <!--<div v-if="!hideBottomLine"-->
            <!--style="height: 1;background-color: #cccccc;position: absolute;bottom: 0;left: 0;right: 0">-->
            <!--</div>-->

            <!--<image src="root:img/shadow.png" style="height: 50;width:750; position: absolute;left: 0;bottom: 0;"></image>-->
        </div>
    </div>
</template>
<script>

    export default {
        props: {
            title: {
                default: ''

            },
            back: {
                default: true
            },
            titleColor: {
                default: '#ffffff'
            },
            hideBottomLine: {
                default: false
            },

            bgcolor: {
                default: '#1EA5FC'

            },
            autoback: {
                default: true
            },
            isloading: {
                default: false
            },
            disabled: {

                default: false
            },
            type: {
                type: String,
                default: 'text'
            },
            font_size: {
                default: 20
            },
            height: {
                default: 135
            },
            top: {
                default: 40
            },
            titletop: {
                default: 27
            }


        },
        data() {
            return {}
        },
        methods: {
            titleClick() {
                this.$emit('titleClick');
            },
            rightclick() {
                this.$emit('rightClick');
            },
            backTo() {
                if (this.autoback) {
                    var nav = weex.requireModule("navigator");
                    nav.back();
                    return;
                }
                this.$emit('backClick');


            },

            onclick() {
                if (!this.disabled)
                    this.$emit('onclick');
            },


            adjust() {
                if (weex.config.env.platform == 'android') {
//                    if(weex.config.env.osVersion=)
                    var p = weex.config.env.osVersion
                    p = p.replace(/\./g, '')
                    if (p.length < 3)
                        p = p + "0";
                    if (p <= '440') {
                        this.height = 108
                        this.top = 16;
                        this.titletop = 4;
                    }
                }
            }


        },

        created: function () {


            this.adjust();

        },
        ready: function () {


        },

    }
</script>
<style scoped>

    .backbg {
        width: 180;
        position: absolute;
        left: 0;
        justify-content: center;
    }

    .backbg:active {

    }

    .layout {
        /*position: fixed;*/
        /*top: 0;*/
        /*left: 0;*/
        background-color: #02993c;
        height: 128;
        width: 750;
        flex-direction: row;
        align-items: center;
        justify-content: center;

    }


</style>