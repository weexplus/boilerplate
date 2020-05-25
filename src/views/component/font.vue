<template>
    <text @click="onClick" :style="textStyle" style="font-family: nj">{{text | getFontName}}</text>
</template>

<script>
    var he = require('he');
    var font = require('../../lib/util/font');
    export default {
        props: {
            text: {
                default: ''
            },
            textStyle: {
                type: Object,
                default: function () {
                    return {'color': '#000000', 'font-size': '26px'}
                }
            }
        },
        filters: {
            getFontName: function (text) {
                return he.decode(text)
                // if(text){

                // }  
            }
        },
        data() {
            return {}
        },
        methods: {
            onClick(){
                this.$emit('onClick')
            }
        },
        created() {

        }
    }
</script>

<style scoped>

</style>