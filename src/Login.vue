<template>
  <div class="home-container">
    <!--<top-bar title="登录"  ></top-bar>-->
    <div class="content">
      <div class="imageContent">
        <image src="images/u19.png" class="img" style="width:325px; height:77px;margin-left:40px"></image>
      </div>
      <div class=inputGroup>
        <div class="user">
          <image src="images/user.png" style="height: 40px;width: 36px"></image>
          <text class="username">&nbsp;用户名&nbsp;</text>
          <input class="inputcon" type="text" :value="username" name="username" placeholder="请输入用户名" @change="onchange" @input="oninput" v-model="username" maxlength="15" />
        </div>
        <div class="user">
          <image src="images/password.png" style="height: 40px;width: 36px"></image>
          <text class="username">&nbsp;密&nbsp;&nbsp;码&nbsp;</text>
          <input class="inputcon" type="password" :value="password" name="password"  @change="onchange" placeholder="请输入密码" @input="oninput" v-model="password" maxlength="16" />
        </div>
      </div>
      <div class="btn" @click="login()"> <text style="font-size:40px;color:#fff;line-height: 70px">登&nbsp;&nbsp;陆</text></div>
      <div class="group">
        <text style="color:#17acf6; font-size:24px;" @click="forgetpass">忘记密码?&nbsp;</text>
        <text style="color:#AFAFAF;text-align: center; font-size:24px;"   @click="telLogin">手机动态密码登陆&nbsp;</text>
        <text style="color: #17acf6; font-size:24px;" @click="register">注册&gt;</text>
      </div>


    </div>

    <div class="parent">
      <div class="wechat" @click="wexinLogin">
        <image src="../images/wechat.png" style="height:75px;width:75px"></image>
      </div>
      <text style="color:#fff;font-weight: 700;margin-top: 20px; font-size:24px;">微信登陆</text>
    </div>
  </div>
</template>
<script>
    var modal = weex.requireModule('modal')
//    const net = require('../vue/busi/util/net.js');
//    import TopBar from '../components/headerlogin.vue'
    export default{
        components:{
//            TopBar
        },
        data: function () {
            return {
                username:"",
                password:""
            };
        },
        methods:{
            ready: function () {
                var self = this;
                setTimeout(function () {
                    self.autofocus = true;
                }, 50000);
            },
            onchange: function (event) {
                this.txtChange = event.value;
            },

            oninput: function (event) {
                this.txtInput = event.value;
            },
            login: function(){
//               if (this.check()) {
//                  net.post('user/userLoginWithoutVerifyCode',{
//                    userName: this.username,
//                    password: this.password,
//                    clientCode:'app',
//                    platformCode:'jypt'
////                  platformCode:'gzt'
//                  },function(res){
//                    if(res.status == 1){
//						var modal=weex.requireModule("modal");
//                        modal.toast({
//                           message: "登录成功",
//                           duration:1
//                       })
//                        var pref=weex.requireModule("static");
//                        var obj={};
//                        obj.userInfo = res.data;
//                        pref.set('objkey',obj);
//                        modal.toast({message:'存储成功'});
//
//              			pref.setString('user','');
//
//                        var nav=weex.requireModule("navigator")
//                        nav.dismiss();
//                        var notify=weex.requireModule("notify")
//                        notify.sendNative('tabselect',{name:'我的'})
//                         //nav.push('../my/my.js')
//                     }else{
//                        var modal=weex.requireModule('modal');
//                        modal.toast({
//                       message: '您输入的用户名或密码不正确，请重新输入！',
//                     })
//                     }
//                  });

                    var nav=weex.requireModule("navigator")
                    nav.dismiss();
                    var notify=weex.requireModule("notify")
                    notify.sendNative('tabselect',{name:'我的'})
//               }
                
                //导航控制器
                // var nav=weex.requireModule("navigator")
                // nav.dismiss();
                // modal.toast({message:'登录成功!'})

            },
            check:function(){
              if(this.username == ''){
                modal.toast({
                    message: "请输入用户名！",
                duration: 0.3
              })
                return false;
              }
              if(this.password == ''){
                modal.toast({
                      message: "请填写密码！",
                      duration: 0.3
                    })
                    return false;
              }
              return true;
            },
            register:function(){
//                this.$router.push("register")
                var nav=weex.requireModule("navigator")
                nav.push('./Register.js')
            },
            telLogin:function(){
//                this.$router.push("mobileLogin")
                var nav=weex.requireModule("navigator")
                nav.push('./MobileLogin.js')
            },
            wexinLogin:function(){
//                this.$router.push("weixinLogin")
                var nav=weex.requireModule("navigator")
                nav.push('./WeixinLogin.js')
//                nav.pushParam('WeixinLogin.js',{});//带参数传递
            },
            forgetpass:function(){
//                this.$router.push("weixinLogin")
                var nav=weex.requireModule("navigator")
                nav.push('./forgetPassword.js')
//                nav.pushParam('WeixinLogin.js',{});//带参数传递
            },

        }
    }
</script>
<style scoped>
  /*头部topbar显示文字开始*/
  .top-bar{
    position:relative;
    height:90px;
    justify-content: center;
    align-items: center;
    padding-left:70px;
    padding-right:70px;
    background-color:#16ADF6;
    border-bottom-width: 1px;
    border-bottom-style:solid;
    border-bottom-color: #eee;
  }
  .back{
    position:absolute;
    left:0;
    top:0;
    width:108px;
    height: 90px;
    justify-content: center;
    align-items:center;
    color: #fff;
  }
  .back-text{
    color:#999;
    font-size:24px;
  }
  .title-name{
    color:#fff;
    font-size:36px;
  }
  /*结束*/
  .home-container{
    width:750px;
    /* height:1050px; */
    font-size: 30px;
    text-align: center;
    background-color: #16ADF6;
  }
  .content{
    margin-top: 60px;
    height: 800px;
    margin-left: 70px;
    margin-right: 70px;
    background-color: #ffffff;
    border-radius: 30px;
  }
  .imageContent{
    margin-left: 90px;
    margin-right: 90px;
    margin-top: 30px;
  }
  .img{
    width: 750px;
    height: 110px;
  }
  .inputcon{
    flex: 1; /*注意要想ios能输入必须加这行*/
    padding-left: 30px;
    padding-right:30px;
    height:48px;
  }
  .inputGroup{
    margin-top: 60px;
    margin-left: 70px;
    margin-right: 70px;
  }
  .user{
    height: 120px;
    justify-content: flex-start;
    flex-direction: row;
    align-items: center;
    border-bottom-style: solid;
    border-bottom-color: #EDEDED;
    border-bottom-width: 1px;
  }
  .username{
    color: #17acf6;
    font-size: 34px;
  }
  input{
    width: 300px;
  }
  .btn{
    margin-top: 78px;
    margin-left: 116px;
    margin-right: 116px;
    height: 70px;
    background-color: #18ACF4;
    border-radius: 100px;
    justify-content: flex-start;
    align-items: center;
  }
  .group{
    margin-top: 90px;
    justify-content: space-between;
    flex-direction: row;
    align-items: center;
    margin-left: 40px;
    margin-right: 40px;
  }
  .parent{
    width: 750px;
    justify-content: flex-start;
    align-items: center;
  }
  .wechat{
    margin-top: 50px;
    width: 73px;
    height: 73px;
    background-color: #ffffff;
    border-radius: 100%;
  }
</style>
