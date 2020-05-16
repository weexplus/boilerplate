<template>
   <div style="flex: 1;width: 750px">
       <head v-if="hasHead" :title="title"  ></head>
       <div style="flex: 1;display: flex;width: 750px">
           <slot  ></slot>
       </div>
   </div>
</template>

<script>

    export default {
        component:{},
        props: {
            title:{
                default:'标题'
            },
            hasHead:{
                default: true
            }
        },
        data() {
            return {}
        },
        methods: {

        },
        created() {

        }
    }
</script>

<style scoped>

</style>