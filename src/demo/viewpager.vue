<template>
    <div>
        <slider class="slider"   auto-play="false">
            <div class="frame"  style="background-color: #0088fb"  >
               <vpitem></vpitem>
            </div>

            <div class="frame"   style="background-color: rebeccapurple"  >

            </div>
            <div class="frame"   style="background-color: #0088fb"  >

            </div>
        </slider>
    </div>
</template>

<style scoped>
    .image {
        width: 700px;
        height: 300px;
    }
    .slider {


       flex:1;

    }
    .frame {


    }


</style>

<script>
    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
        const nav = weex.requireModule('navbar');
        nav.setTitle('viewpager');
        nav.show();
        var navigator = weex.requireModule('navigator') ;

    });
    import vpitem from './vpitem.vue'
    export default {
        components:{vpitem},
        data () {
            return {
                imageList: [
                    { src: 'https://gw.alicdn.com/tfs/TB1SA2wQXXXXXXDapXXXXXXXXXX-1400-600.png'},
                    { src: 'https://gw.alicdn.com/tfs/TB1KS_OQXXXXXcSXVXXXXXXXXXX-1400-600.png'},
                    { src: 'https://gw.alicdn.com/tfs/TB1Ez7XQXXXXXbNXXXXXXXXXXXX-1400-600.png'},
                    { src: 'https://gw.alicdn.com/tfs/TB1XLn6QXXXXXb2XpXXXXXXXXXX-1400-600.png'}
                ]

            }
        }
    }
</script>