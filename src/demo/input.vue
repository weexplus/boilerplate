

<template>
    <scroller style="flex:1;">
        <div   style="align-items: center">
            <div  style="margin-left: 0;margin-right: 0;width:650"  >
                <input ref="input" placeholder="普通的text" style="margin-top: 100" autofocus="false" class="input" @change="onchange"  @input="oninput" type="text" v-model="name"    return-key-type="next"></input>
                <input ref="input" placeholder="password" class="input" type="password" maxlength="10" return-key-type="done" ></input>
                <input ref="input" placeholder="return-key done,next,search,go" class="input" type="password" maxlength="10" return-key-type="done" ></input>
                <input ref="input" placeholder="type text，password，url，email，tel" class="input" type="tel" maxlength="10" return-key-type="done"    ></input>
                <input ref="input" placeholder="disabled" class="input" type="tel" disabled="true" maxlength="10" return-key-type="done"    ></input>
                <input ref="input" placeholder="type text，password，url，email，tel" class="input"   style="font-weight: bold;" type="tel" maxlength="10" return-key-type="done"    ></input>
                <input ref="input" placeholder="type text，password，url，email，tel" class="input"   style="font-weight: bold;" type="number" maxlength="10" return-key-type="done"    ></input>

            </div>
             <text style="position: fixed;top:5">{{display}}</text>

            <div class="btn" @click="submit()" ><text style="color:#ffffff">提交</text></div>

        </div>


    </scroller>

</template>

<style>
    .input {
        font-size: 40px;

        height: 100px;
        border-radius: 10px;
        margin-top: 50px;


        padding-left: 10px;

        color: #666666;
        border-width: 2px;
        border-style: solid;
        border-color: #41B883;
    }


</style>
<style src="./css/style.css"></style>
<script>




    export default {

        data () {
            return {
             name:"",
             display:""
            }
        },
        methods: {
            oninput (event) {

//                this.display=event.value;
//                event.value=""
            },
            onchange (event) {
//                this.display=event.value;
//                this.name="xxx"
            },
            submit()
            {

                var self=this;
                const model = weex.requireModule('modal');
                model.toast({message:this.name})

//                const modal = weex.requireModule('modal');
//                modal.toast({ message: 'native toast' });

            },


        },
        created:function(){

            var globalEvent = weex.requireModule('globalEvent') ;



            globalEvent.addEventListener("onPageInit", function (e) {
                const nav = weex.requireModule('navbar');
                nav.setTitle('input');
                nav.setBack(true);
                nav.setRightImage('img/scan.png',function(res){

                    var modal = weex.requireModule('modal') ;
                    modal.alert({message:"ok"})
                });
            });



        }
    }
</script>