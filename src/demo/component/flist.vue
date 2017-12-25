<template>

    <div class="c">

        <waterfall class="page" :style="{'background-color':background}" :key="waterkey"

                   :column-count="getCount()" :column-gap="columnGap"
                   :show-scrollbar="showScrollbar" :scrollable="scrollable"
                   loadmoreoffset="141"
                   @loadmore="loadmore"
        >
            <pull ref="refresh" v-on:onRefresh="refresh" v-if="usePullRefresh"></pull>

            <header>
                <div>
                    <slot name="head"></slot>
                </div>
            </header>
            <slot name="cell"></slot>
            <cell>
                <div>
                    <slot name="foot"></slot>
                </div>
            </cell>

            <cell v-if="isEmpty">
                <div class="empty">
                    <image style="width: 252;height: 201;margin-top: 100" :src="img_empty_src"></image>
                    <text class="emptytxt" style="margin-top: 20">{{emptyTxt}}</text>
                </div>
            </cell>
            <cell v-if="isException">
                <div class="empty">
                    <image style="width: 252;height: 201;margin-top: 100" :src="img_exception_src"></image>
                    <text style="color:#000000;margin-top: 20">网络请求失败</text>
                    <div class="exception" @click="reload">
                        <text style="color:#000000;font-size: 28;">重新加载</text>
                    </div>
                </div>
            </cell>
            <cell v-if="!ispull&&isloading&&items.length>0">
                <div class="loading">
                    <floading style="height: 40;width: 40" color="#999999" loading-style="small"></floading>
                    <text style="margin-left: 10;color: #999999;font-size:28">正在载入</text>
                </div>
            </cell>
        </waterfall>

        <div class="progress" v-if="!ispull&&isloading&&items.length==0">
            <floading style="height: 40;width: 40;margin-top: 20" color="#ffffff" loading-style="big"></floading>
            <text style="margin-left: 0;color: #ffffff;font-size:30;margin-top: 30;font-weight: bold">加载中...</text>
        </div>
    </div>
</template>

<style>
    .progress {
        width: 220;
        height: 220;
        opacity: 0.8;
        background-color: black;
        border-radius: 30;
        justify-content: center;
        align-items: center;
        position: absolute;
        left: 265;
        top: 300;

    }

    .exception {
        margin-top: 50;
        border-radius: 10;
        justify-content: center;
        align-items: center;
        width: 300;
        height: 80;
        border-width: 2;
        border-color: #949494;
        background-color: #ffffff;
    }

    .exception:active {
        background-color: #dddddd;
    }

    .emptytxt {
        margin-top: 100;
        color: #999999;
    }

    .empty {
        height: 500;
        align-items: center;
    }

    .loading {

        height: 90;
        justify-content: center;
        align-items: center;
        flex-direction: row;
        border-radius: 5;
        border-width: 1;
        border-color: #e6e6e6;
        background-color: #fdfdfd;
        margin: 15;
        margin-left: 30;
        margin-right: 30;
    }

    .c {
        flex: 1;

    }

    .page {
        background-color: #ffffff;

        width: 750;
        flex: 1;

    }


</style>

<script>
    const pull = require('./pullrefresh.vue')
    const net = require('../util/net.js')

    export default {
        components: {pull},
        props: {
            columnCount: {
                default: 1
            },
            key: {
                default: 'key1'
            },
            columnGap: {
                default: 20
            },
            columnWidth: {
                default: 'auto'
            },
            scrollable: {
                default: true
            },
            showScrollbar: {
                default: true
            },
            showheader: {
                default: false
            },
            usePullRefresh: {
                default: true
            },
            items: {
                default: []
            },
            isEmpty: {
                default: false
            },
            background: {
                default: '#ffffff'
            },
            isException: {
                default: false
            },
            pageSize: {
                default: 15
            },
            isloading: {
                default: false
            },
            loadOnInit: {
                default: true
            },
            hasmore: {
                default: true
            },
            emptyTxt: {
                default: '您的订单为空~'
            },
            img_empty_src: {
                default: 'root:img/ico_empty.png'
            }
            ,
            img_exception_src: {
                default: 'root:img/ico_exception.png'
            },
            showweg: {
                default: false
            },
            ispull: {
                default: false
            }

        },
        data(){

            return {
                pullkey: this.key + "pull",
                waterkey: this.key + "water",
                _columnCount: this.getCount()

            }


        },

        mounted() {

            if (this.loadOnInit) {
                var self = this;
                self.$emit('load');
            }


        },

        methods: {

            loadmore: function (e) {

                if (this.hasmore)
                    this.$emit('load');
            },
            getCount()
            {
                if (this.isEmpty || this.isException) {
                    return 1;
                }
                else {
                    return this.columnCount;
                }

            },
            clear: function () {

                this.hasmore = true;
                this.items.length = 0;

            },
            showEmpty: function () {
                this._columnCount = this.getCount();
                this.isEmpty = true;
                this.isException = false;
            },
            showException: function () {
                this._columnCount = this.getCount();
                this.isEmpty = false;
                this.isException = true;
            },
            load: function (url, param, items, callback) {
                this.loadFull(url, param, items, callback, function () {
                }, function () {
                }, function () {
                }, function () {
                });
            },
            loadFull: function (url, param, items, callback, start, fail, exp, comp) {
//                var progress=weex.requireModule("progress")
                if (this.isloading)
                    return;
                this.isException = false;
                this.isEmpty = false;
                this.isloading = true;

                var self = this;
//                postFull:  function (weg,param,header,start,success,compelete)

                var modal = weex.requireModule('modal')
                net.postFull(url, param, {}, function () {
                    //start
                    start();

                }, function (res) {
                    //success
                    if (res.list.length < 15) {
                        self.hasmore = false;
                    }
                    if (res.list.length > 0) {
                        items = items.concat(res.list);
                        self.items = items;

                    }
                    if (items.length == 0) {
                        self.showEmpty();
                    }
                    callback(items)
                    self._columnCount = self.getCount();

                }, function (e) {
                    //fail
                    fail();
                    modal.toast({message: e.res.error})
                }, function () {
                    //exception
                    exp();
                    if (items.length == 0) {
                        self.showException();
                    }
                    else {
                        modal.toast({message: '网络异常'})
                    }
                }, function (e) {
                    //compelete
//                    progress.dismiss();
                    self.isloading = false;
                    self.$refs.refresh.end();
                    comp();
                });


            },

            hideRefresh: function () {
                var p = this.$refs.refresh
                p.end();
                this.ispull = false;
            },
            refresh: function () {
                this.isEmpty = false;
                this.isException = false;
                this.isloading = false;
                this.ispull = true;
                this.$emit('refresh');
            },
            reload: function () {
                this.isEmpty = false;
                this.isException = false;
                this.isloading = false;
                this.ispull = false;
                this.$emit('refresh');
            }


        }
    }
</script>
