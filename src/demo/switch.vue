<template>
     <div>
       <switch  class="sw" checked="true" @change="change"></switch>
         <text class="info">{{checked}}</text>

     </div>
</template>
<script>
    const modal = weex.requireModule('modal')
    const LOADMORE_COUNT = 4
    export default {
        data () {
            return {
                checked: false
            }
        },
        methods: {
            fetch (event) {

                const length = this.lists.length
                for (let i = length; i < length + LOADMORE_COUNT; ++i) {
                    this.lists.push(i + 1)
                }
            },
            change(event)
            {
                this.checked = event.value
            }

        }
    }
</script>
<style scoped>
   .sw
   {
       width:200;
       margin-left:20;
       margin-top:30;
       background-color: red;
   }
</style>
