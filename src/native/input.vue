<template>
    <div style="align-items: center;">
        <input v-model="name" :type="type" style="margin-top: 100; width: 750;height: 100;border-color: #006ce7;border-width: 1"/>
        <div @click="ok" style="width: 200;height: 100;background-color: red">
            <text>切换</text>
        </div>
    </div>
</template>
<script>
    export default{
        data()
        {
            return {
                type:'text',
                name:''
            }
        },
        methods:{
            ok(){
               this.type=this.type=='text'?'password':'text'
            }
        }

    }
</script>