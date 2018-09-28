

<template>



 <scroller style="align-items: center;justify-content: center;">
   <text style="color: #000000;font-size: 32">{{name}}</text>
   <input ref="inp" type="input" class="it" placeholder="姓名" v-model="name"/>
     <div style="width: 100;height: 100;background-color: red" @click="set">

     </div>
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
    var cinput =require('./component/input.vue')


    export default {
        components:{head,cinput},
        data () {
            return {
                name:"111",
                age:""
            }
        },
        methods: {

            set()
            {
//                this.name=this.name+'ss'
                this.$refs.inp.val('xx');
                this.$refs.inp.val('xx1');
                this.$refs.inp.val('x2');
                 this.name="xx";
            }

        },
        created:function(){

            var globalEvent = weex.requireModule('globalEvent') ;
            globalEvent.addEventListener("onPageInit", function (e) {

                var modal = weex.requireModule('modal') ;
                modal.toast({message:'input init'})


            });
        }
    }
</script>