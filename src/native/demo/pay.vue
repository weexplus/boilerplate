<template>
    <div>


         <text>{{text}}</text>
        <image src="root:/img/cat.png" @click="pay" style="width: 300px;height: 300px;margin-top: 100px;margin-left: 20px"></image>


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
                 text:{}
            }
        },
        methods: {

            pay()
            {

                var progress=weex.requireModule('progress')
                var alipay=weex.requireModule('alipay')
                progress.show();
                alipay.open('alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018051060110243&biz_content=%7B%22body%22%3A%22%E6%94%AF%E4%BB%98%E5%8F%91%E5%B8%83%E8%AF%A2%E4%BB%B7%22%2C%22out_trade_no%22%3A%2220180531152017697388%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%AF%A2%E4%BB%B7%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%225.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&sign=JjY1Bq0UyUl77n5KH7H2nqwolaaqQ10Mt4lWCpRsSgqzPILlYlWeyzOdvsvBlgnkQY1VAhsaewJqkBBNEqs1MShePBcpdJlr1et06IXDjlghSsAZvNxr5vX5NPjgH1fUrk5xF11VTea%2F8ZDwo1XRxJZMY5JSGvGHTH8Rn%2B5YdRYekPHuYRvW7sFHDPMOkrJNxmRqoXxB5173t7k50yjwuMGSoDvmr9qsjhwrPdjmf0PrJRbYqaDwMJnA6P4ZT3T3ifWzTcDcM%2F54eudFzbELpdAAqLLHknpCTVZNZYjl5k6HwRVqJdf7VLMOXN3HA0xVc5mbeibED51fakCoSa53lw%3D%3D&sign_type=RSA2&timestamp=2018-05-31+15%3A21%3A49&version=1.0',(res)=>{
                    this.text=res;
                    progress.dismiss();
                })
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