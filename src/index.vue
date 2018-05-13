<template>
    <scroller>
      <div style="padding: 5;flex-direction: row;margin-top: 50">
          <div style="border-radius: 3; margin-left: 20;width: 30;height: 30;background-color:red;margin-top: 40;transform:rotate(-45deg) ">

          </div>
          <div style="background-color: red;padding: 15;border-radius: 5;padding-top: 10;padding-bottom: 10;margin-left:-15">
              <text style="max-width: 300">1111111111111111111111ssssssssssssssssssssssssssssssdssssssssssssssssssssssssssssssssssssssss</text>
          </div>
          <div style="flex: 1">
          </div>
      </div>
    </scroller>
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
                t.toast({message:'111'})

            });

        },
    }
</script>