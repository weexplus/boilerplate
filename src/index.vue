<template>
    <div>



        <image src="root:/img/cat.png" style="width: 300px;height: 300px;margin-top: 100px;margin-left: 20px"></image>


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
                items: [1, 1, 1, 1, 1, 1]
            }
        },
        methods: {

            add()
            {
                this.items.push(1)
            },
            remove(item)
            {
//                this.items.remove(0)

                this.items.splice(this.items.length-1, 1);
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