<template>
    <div style="align-items: center;justify-content: center">
        <text style="font-size: 90px" @click="back">page</text>
    </div>
</template>

<script>
    export default {
        props: {},
        data() {
            return {}
        },
        methods: {
            back(){
              this.$navigator.back()
            },
            onLoad(param) {
               // this.log('lod')
            },
            onShow() {

            },
            onHide() {

            },
            onUnload() {
                this.log('onUnload')
            }
        },
        created() {

        }
    }
</script>

<style scoped>

</style>