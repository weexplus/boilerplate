<template>
    <div @click="onBgClick" style="position: fixed;top: 0;left: 0;right: 0;bottom: 0;background-color: rgba(0,0,0,0.8);justify-content: center;">
        <slot></slot>
    </div>
</template>

<script>
    export default {
        props: {},
        data() {
            return {}
        },
        methods: {
            onBgClick(){
              this.$emit('bgClick')
            },
            onLoad(param) {

            },
            onShow() {

            },
            onHide() {

            },
            onUnload() {

            }
        },
        created() {

        }
    }
</script>

<style scoped>

</style>
