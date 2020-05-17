<template>
    <tabHost :items="items">
    </tabHost>
</template>
<script>
    export default {
        props: {},
        data() {
            return {
                items: [
                    {text: '首页', normalImg: '&#xe970;', selectImg: '&#xe971;',component:require('./tab/tab1.vue')},
                    {text: '新闻', normalImg: '&#xe97e;', selectImg: '&#xe980;',component:require('./tab/tab2.vue')},
                    {text: '我的', normalImg: '&#xe973;', selectImg: '&#xe987;',component:require('./tab/tab3.vue')},
                ],
                index: 0
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
            registFont(){
                const font = weex.requireModule('font')
                font.addFont('nj','root:font/iconfont.ttf')
            }
        },
        mounted(){
            // this.$refs.host.components={tab1,tab2,tab3}

            // debugger

            // this.log(JSON.stringify(this.components))
        },
        created() {
            this.registFont()
        }
    }
</script>

<style scoped>
    .full {
        position: absolute;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        width: 750px;
    }
</style>