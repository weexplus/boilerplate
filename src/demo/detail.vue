<template>
  <div class="wrapper">
  <scroller class="scroller">
    <slider class="slider" interval="3000">
      <div class="frame" v-for="img in imageList">
        <image class="image" resize="cover" :src="img.src"></image>
      </div>
    </slider>
    <div class="panel">
       <text class="text">欧思曼双肩包女真皮2017新款韩版百搭女包牛皮包包女士双肩背包潮</text>
    </div>
	<div class="price">
		<div class="price-fx">
		   <text class="text1">分销价：</text><text class="text2">￥</text><text class="text3">108</text>
		</div>
		<div class="countdown">
		   <image class="ico-time" src="./img/ico_time.png"></image><text class="text1">还剩：12时30分</text>
		</div>
	</div>
	<div class="price">
		<div class="price-fx">
		   <text class="text4">快递：</text><text class="text5">包邮</text>
		</div>
		<div>
		   <text class="text4">已售150件</text>
		</div>
		<div>
		   <text class="text4">国内现货</text>
		</div>
	</div>
	<div class="sort-color">
		<div>
		   <text class="text6">颜色分类：</text>
		</div>
		<div>
		   <image class="ico-arrow-r" src="./img/arrow_right_02.png"></image>
		</div>		
	</div>
	<div class="num-sel">
		<div>
		   <text class="text6">选择数量：</text>
		</div>
		<div class="num-inp">
		   <text class="minus">-</text><input class="num-input" type="text" value="1"></input><text class="plus">+</text>
		</div>		
	</div>
	<div class="drag">
		<div>
		   <text class="text1">继续拖动，查看商品详情</text>
		</div>	
	</div>
  </scroller>
	<div class="detail-bt">
		<div class="kf">
			<image class="ico-kf" src="./img/ico_kefu.png"></image>
			<text class="kf-txt">客服</text>
		</div>
		<div class="fav">
			<image class="ico-fav" src="./img/ico_fav.png"></image>
			<text class="fav-txt">关注</text>
		</div>
		<div class="cart-join">
			<text class="cj-txt">加入购物车</text>
		</div>
		<div class="order-now">
			<text class="on-txt">立即下单</text>
		</div>
	</div>
  </div>
</template>

<style>
  .scroller{background-color:#efefef;margin-bottom:100px;}
  .image{width:750px;height:560px;}
  .slider{width:750px;height:560px;}
  .frame{width:750px;height:560px;position:relative;}
  .panel{background-color:#fff;width:750px;padding-top:15px;padding-bottom:40px;padding-left:20px;padding-right:20px;}
  .text{color:#333;font-size:36px;}
  .price,.sort-color,.num-sel{background-color:#fff;width:750px;padding-bottom:15px;padding-left:20px;padding-right:20px;flex-direction:row;justify-content:space-between;align-items:center;}
  .price-fx,.countdown,.num-inp{flex-direction:row;align-items:center;}
  .ico-time{height:30px;width:30px;margin-right:5px;}
  .text1,.text6{color:#333;font-size:28px;}
  .text2{color:#f60;}
  .text3{color:#f60;font-size:50px;}
  .text4{color:#999;font-size:28px;}
  .text5{border-radius:5;background-color:#eb6100;color:#fff;font-size:28px;}
  .sort-color{border-top-style:solid;border-top-width:1px;border-top-color:#d1d1d1;width:750px;padding-top:25px;padding-bottom:25px;margin-top:20px;margin-bottom:20px;}
  .ico-arrow-r{height:30px;width:18px;}
  .text6{font-size:32px;}
  .num-sel{width:750px;padding-top:25px;padding-bottom:25px;}
  .minus,.plus{background-color:#fbfbfb;color:#aaa;font-size:40px;width:50px;height:50px;text-align:center;}
  .minus{margin-right:5px;}
  .plus{margin-left:5px;}
  .num-input{background-color:#f5f5f5;color:#666;height:50px;width:50px;text-align:center;}
  .drag{justify-content:center;align-items:center;padding-top:25px;padding-bottom:25px;}
  .detail-bt{position:fixed;width:750px;padding-top:15px;padding-bttom:15px;flex-direction:row;align-items:center;justify-content:center;bottom:0px;right:0;left:0;}
  .kf{background-color:#f8f7f5;border-top-style:solid;border-top-width:1px;border-top-color:#d1d1d1;border-right-style:solid;border-right-width:1px;border-right-color:#d1d1d1;flex:1;height:100px;align-items:center;justify-content:center;}
  .ico-kf{height:40px;width:40px;margin-bottom:10px;}
  .kf-txt{background-color:#f8f7f5;color:#666;font-size:28px;text-align:center;}
  .fav{background-color:#f8f7f5;border-top-style:solid;border-top-width:1px;border-top-color:#d1d1d1;flex:1;height:100px;align-items:center;justify-content:center;}
  .ico-fav{height:40px;width:40px;margin-bottom:10px;}
  .fav-txt{background-color:#f8f7f5;color:#666;font-size:28px;text-align:center;}
  .cart-join{background-color:#e4d085;flex:2;height:100px;align-items:center;justify-content:center;}
  .cj-txt{background-color:#e4d085;color:#fff;font-size:32px;text-align:center;}
  .order-now{background-color:#c90;flex:2;height:100px;align-items:center;justify-content:center;}
  .on-txt{background-color:#c90;color:#fff;font-size:32px;text-align:center;}
</style>

<script>
  export default {
    data () {
      return {
        imageList: [
          { src: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg'},
          { src: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg'},
          { src: 'https://gd3.alicdn.com/bao/uploaded/i3/TB1x6hYLXXXXXazXVXXXXXXXXXX_!!0-item_pic.jpg'}
        ]
      }
    }
  }
</script>