<template>
<div  class="wraper" >
  <div class="container">
     <input class="input" placeholder="手机号"/>
     <input class="input" type="password" placeholder="密码"/>
     <a href="list.js">
        <div class="btn">
            <text style="color:#ffffff">登录</text>
        </div>
     </a>
     <div class="subcontainer">
        <text class="subtext">立即注册</text>
        <div style="flex:1"></div>
        <text class="subtext">找回密码</text>
     </div>
  </div>
</div>
</template>
<style>
.wraper
{
  flex: 1;
  background-color:#eeeeee
}
.subcontainer
{
  height: 100;
  margin-top: 50;
  flex-direction: row;
}
.container
{
   height: 500;
   margin-left: 50;
   margin-right: 50;

   margin-top: 200;
}
.input
{
  height: 90;
  border-width: 2;
  border-color: #48c9bf;
  margin-top: 50;
  border-radius: 10;
  padding-left: 20;

}
.input:focus{
  border-color: #48f547;
}
.btn
{
    height: 90;
    margin-top: 50;
    border-radius: 10;
    background-color:#48c9bf;
    align-items:center;
    justify-content: center;
}
.btn:active
{
  background-color:#489f91;
}
.subtext
{
  color: #999999;
}
.subtext:active
{
  color: black;
}
 
</style>

<script>




</script>
