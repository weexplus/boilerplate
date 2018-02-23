<template>
    <div style="flex: 1;">
     <head title="viewpager"></head>
     <viewpager :items="items">

     </viewpager>

    </div>
</template>

<style>


</style>

<script>
    var viewpager =require('./component/viewpager.vue')
    var head =require('./component/header.vue')
    export default {
        components:{viewpager,head },
        data: {

            items:[ {text:'已关联',url:'root:demo/home.js',param:{a:'ss'}},{text:'22',url:'root:demo/net.js',param:{b:'dsds'}}]
        },
        methods: {
            update: function (e) {
                this.target = 'Weex'
                console.log('target:', this.target)
            },
            change(i)
            {
                this.index=i;
            },
            show()
            {
                var modal=weex.requireModule("modal")
                var p=weex.config.env.osVersion
//              p=p.replace('.', "")
//          p=p.replace(/./g,"");
                p= p.replace(/\./g,'')
                modal.alert({message:p})
            }
        }
        ,


        created:function(){


            var globalEvent = weex.requireModule('globalEvent') ;
            var self=this;
            globalEvent.addEventListener("onPageInit", function (e) {




            });

        },
    }
</script>