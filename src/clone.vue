<template>
    <div style="background-color: yellow">
        <text>{{obj}}</text>

        <div class="btn" @click="clk"></div>
        <text>{{sd}}</text>
    </div>
</template>
<style>
    .btn {
        width: 500;
        height: 100;
        background-color: red;
    }
</style>

<script>


    export default {
        components: {},
        data: {
            obj:{
                a:[1,1,1,1]
            },
            sd:{}

        },
        methods: {
            copy(obj1, obj2) {
                let temp = obj2 || {}; //最初的时候给它一个初始值=它自己或者是一个json
                for (var name in obj1) {
                    if (typeof obj1[name] === "object") { //先判断一下obj[name]是不是一个对象
                        temp[name] = (obj1[name].constructor === Array) ? [] : {}; //我们让要复制的对象的name项=数组或者是json

                        this.copy(obj1[name], temp[name]); //然后来无限调用函数自己 递归思想
                    } else {
                        temp[name] = obj1[name];  //如果不是对象，直接等于即可，不会发生引用。
                    }
                }
                return temp; //然后在把复制好的对象给return出去
            },

            clk()
            {

                this.sd=this.copy(this.obj,{})
            }

        }
    }
</script>