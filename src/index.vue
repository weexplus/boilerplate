<template>
    <div style="">

        <drawerlayout  style="flex: 1;">
            <child role="slid" style="background-color: red">
                <div style="background-color: #afddff;">

                </div>
            </child>
            <child role="main" style="background-color: #ff7231;width: 750">

            </child>
        </drawerlayout>
    </div>
</template>
<style>
    .in
    {
        width: 700px;
        height: 100px;
        border-color: #006ce7;
        border-width: 1px;
        margin-top: 200px;
    }
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

//    const common=require('./common.vue')
    export default {

        data()
        {
            return {
                items: [1, 1, 1, 1, 1, 1],
                show:true,
                text:''
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