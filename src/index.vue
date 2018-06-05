<template>
    <div>


 <div   @click="add" style="width: 100;height: 100;background-color: red"></div>
 <div   :style="{'visibility':show?'visible':'hidden'}"  @click="add" style="width: 100;height: 100;background-color: blue;margin-top: 10;visibility:visible"></div>
 <div   :style="{'visibility':!show?'visible':'hidden'}"  @click="add" style="width: 100;height: 100;background-color: green;margin-top: 10"></div>

    </div>
</template>
<style>

    .add {
        width: 250px;
        height: 250px;
        background-color: #0088fb;
        align-items: center;
        justify-content: center;
    }

    .pic {
        width: 250px;
        height: 250px;
        background-color: red;
        border-width: 1;
        border-color: #ffffff;
    }


</style>

<script>
    export default {

        data()
        {
            return {
                items: [1, 1, 1, 1, 1, 1],
                show:true
            }
        },
        methods: {

            add()
            {
//             this.show=!this.show;
                var t=weex.requireModule('navbar')
                t.setStatusBarStyle('black')
            },
            remove(item)
            {
//                this.items.remove(0)


            }
        },
        created: function () {

            var globalEvent = weex.requireModule('globalEvent');
            globalEvent.addEventListener("onPageInit", function (e) {

                var t=weex.requireModule('modal')
//                t.toast({message:'111'})

            });

        },
    }
</script>