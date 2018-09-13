<template>
    <div style="">
      <list style="flex: 1">
          <cell>
              <div style="height:100;background-color: red;margin-top: 20" v-for="item in 15">

              </div>
          </cell>
         <cell>
             <div style="height: 100;background-color: #fbb723" v-if="show">

             </div>
         </cell>
      </list>
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
                show:false,
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