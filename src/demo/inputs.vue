

<template>
  <scroller>


 <div style="align-items: center;">
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
   <input class="it" placeholder="姓名"/>
 </div>
  </scroller>
</template>

<style>
.it
{
    width: 700;
    height: 100;
    margin-top: 10;
    border-color: #006ce7;
    border-width: 1;
    border-radius: 10;
}




</style>
<style src="./style.css"></style>
<script>

    var head =require('./header.vue')
    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
        const nav = weex.requireModule('navbar');
        nav.setTitle('网络请求');
        var navigator = weex.requireModule('navigator') ;

    });

    export default {
        components:{head},
        data () {
            return {
               back:""
            }
        },
        methods: {
            read()
            {

               var r=weex.requireModule("addressBook")
                r.read((res)=>{
                    this.back=res;
                })

            },

        },
        created:function(){


        }
    }
</script>