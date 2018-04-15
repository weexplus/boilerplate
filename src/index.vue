<template>

    <div style="background-color: yellow">

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


        created: function () {

            var globalEvent = weex.requireModule('globalEvent');
            globalEvent.addEventListener("onPageInit", function (e) {

                var nav=weex.requireModule('navigator')
//                nav.push('index1.js');


            });

        },
    }
</script>