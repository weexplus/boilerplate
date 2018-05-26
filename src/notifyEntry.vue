<template>
    <div>


    </div>
</template>
<style>




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


        },
        created: function () {

            var globalEvent = weex.requireModule('globalEvent');
            globalEvent.addEventListener("onPageInit", function (param) {

                var t=weex.requireModule('modal')
                var navigator=weex.requireModule('navigator')
                navigator.pushParam('root:demo/onNotifyRecive.js',param)



            });

        },
    }
</script>