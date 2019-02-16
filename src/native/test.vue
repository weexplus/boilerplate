<template>
    <div style="align-items: center;justify-content: center">
        <button @click="back" text="返回"></button>
    </div>
</template>
<script>
    export default{
        data(){
            return {}
        },
        props: {},
        methods: {
            back(){
              let nav=weex.requireModule('navigator')
                nav.back()
            },
            onLoad(p){
             debugger
            }
        },
        created(){

        }
    }
</script>
<style scoped>

</style>