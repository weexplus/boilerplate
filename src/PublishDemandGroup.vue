<template>
<scroller>
  <div class="home-container">

    <div class="content">

      <div class="item_content">
        <div class="item_list">
          <text class="item_list_txt">联系人：</text>
          <div class="item_list_select" @click="nameShowpicker">
            <text class="slect_text">{{pickerValue.name}}</text>
            <image class="item_list_select_arrow" src="../images/xiala.png"></image>
          </div>
        </div>
        <div class="item_list">
          <text class="item_list_txt">联系电话：</text>
          <input class="item_list_input" type="tel" placeholder="请输入联系电话..."></input>
        </div>
        <div class="item_list">
          <text class="item_list_txt">邮箱：</text>
          <input class="item_list_input" type="text"></input>
        </div>
        <div class="item_list">
          <text class="item_list_txt">传真：</text>
          <input class="item_list_input" type="text"></input>
        </div>
        <div class="item_list">
          <text class="item_list_txt" style="width: 223px;">项目建设单位：</text>
          <input class="item_list_input" type="text" style="width: 466px;"></input>
        </div>
        <div class="item_list">
          <text class="item_list_txt" style="width: 223px;">建设单位地址：</text>
          <input class="item_list_input" type="text" style="width: 466px;"></input>
        </div>
        <div class="item_list">
          <text class="item_list_txt">项目名称：</text>
          <input class="item_list_input" type="text" placeholder="请输入项目名称..."></input>
        </div>
        <div class="item_list">
          <text class="item_list_txt">项目需求：</text>
          <div class="item_list_select" @click="xuqiuShowpicker">
            <text class="slect_text">{{pickerValue.xuqiu}}</text>
            <image class="item_list_select_arrow" src="../images/xiala.png"></image>
          </div>
        </div>
        <div class="item_list">
          <text class="item_list_txt">建设地点：</text>
          <div class="item_list_select" @click="areaShowpicker">
            <text class="slect_text">{{pickerValue.didian}}</text>
            <image class="item_list_select_arrow" src="../images/xiala.png"></image>
          </div>
        </div>
        <div class="item_list">
          <text class="item_list_txt">建设投资：</text>
          <input class="item_list_input" type="text" style="width: 430px; margin-right: 26px;"></input>
          <text style="font-size:32px; color:#333; line-height:66px;">万元</text>
        </div>
        <div class="item_list">
          <text class="item_list_txt">行业：</text>
          <div class="item_list_select" @click="industryShowpicker">
            <text class="slect_text">{{pickerValue.industry}}</text>
            <image class="item_list_select_arrow" src="../images/xiala.png"></image>
          </div>
        </div>
        <div class="item_list">
          <text class="item_list_txt">项目类型：</text>
          <div class="item_list_select" @click="protypeShowpicker">
            <text class="slect_text">{{pickerValue.proType}}</text>
            <image class="item_list_select_arrow" src="../images/xiala.png"></image>
          </div>
        </div>
        <div class="item_list" style="flex-direction:column;">
          <div class="contract_box">
            <text class="item_list_txt">合同范围：</text>
            <div style="flex-direction:row; align-items:flex-start; margin-top:18px;">
              <div class="contract_item" style="color:red" @click="contractClick($event)"  data="false" v-for="contract in contracts">
                <text class="contract_item_txt1">{{contract.title}}</text>
              </div>
            </div>
          </div>
          <div class="contract_more_box">
            <text style="color:#999; font-size:32px; border-bottom-width:1px; border-style:solid; border-color: #999; padding-bottom:10px;">查看更多</text>
          </div>
        </div>
        <div class="item_list" style="flex-direction:column;">
          <text class="item_list_txt">项目规模：</text>
          <textarea class="item_list_area"></textarea>
        </div>
        <div class="item_list" style="flex-direction:column;">
          <text class="item_list_txt" style="width:500px;">项目建设计划：</text>
          <textarea class="item_list_area"></textarea>
        </div>
        <div class="item_list" style="flex-direction:column;">
          <text class="item_list_txt" style="width:500px;">设计进度要求：</text>
          <textarea class="item_list_area"></textarea>
        </div>
        <div class="item_list" style="flex-direction:column;">
          <text class="item_list_txt">其他要求：</text>
          <textarea class="item_list_area"></textarea>
        </div>
        <div class="sure_box">
          <text class="text_blue">添加附件</text>
          <text class="text_grey">(附件大小不得超过10M)</text>
        </div>
        <div class="btn_box">
          <div class="btn" @click="submit()">
            <text style="font-size:36px; color:#fff; line-height:80px;">保存</text>
          </div>
          <div class="btn_concel" @click="submit()">
            <text style="font-size:36px; color:#18ACF4; line-height:78px;">发布</text>
          </div>
        </div>
      </div>
      
    </div>
  </div>
</scroller>
</template>
<script>

    var modal = weex.requireModule('modal')


    var selectName,selectXuqiu,selectDidian,selectIndustry,selectProType;
    export default{
        components:{

        },
        data: function () {
            return {
                contract_item:'contract_item2',
                contracts:[
                  {title:'土木工程',state:false},
                  {title:'园林设计',state:false},
                  {title:'建筑学',state:false},
                  {title:'建筑学',state:false},
                  {title:'建筑学',state:false},
                ],
                default1:{
                  name:['周xx','李xx','王xx'],
                  xuqiu:['需求1','需求2','需求3'],
                  proType:['项目类型1','项目类型2','项目类型3','项目类型4'],
                  industry:['行业1','行业2','行业3','行业4'],
                },
                pickerValue:{
                  name:'',
                  xuqiu:'',
                  didian:'',
                  industry:'',
                  proType:''
                },
                areaMap:[{"id":1,"name":"河北省","children":[{"id":1,"name":"保定市"},{"id":2,"name":"石家庄"},{"id":3,"name":"廊坊"}]},
               {"id":2,"name":"辽宁省","children":[{"id":1,"name":"沈阳市"},{"id":2,"name":"大连市"},{"id":3,"name":"阜新市"}]},
               {"id":3,"name":"山西省","children":[{"id":1,"name":"大同市"},{"id":2,"name":"太原市"},{"id":3,"name":"吕梁市"}]}],
              province:['河北','北京','上海'],
              city:['保定','廊坊','石家庄']
                
            };
        },
        methods:{
            contractClick:function(item){
              modal.alert({
                   message: item,
                   duration: 5
               })
            },
            change1:function(x){
              this.ss = x.value;
            },
            done:function(x){
              this.default1[this.index] = this.ss;
            },
            nameSelect:function(){
              this.$refs.layout.show();
              this.index = 0;
            },
            toArray(list){
              var p=[];
              for(let i=0;i<list.length;i++)
              {
                  p.push(list[i].name);
              }
              return p;
            },
            nameShowpicker()
            {    //联系人选择
                 var index1=0;
                 var _this = this;
                 var m=weex.requireModule('modal')
                 if(selectName == undefined)
                { 
                  selectName=weex.requireModule('fpicker')
                } 
                selectName.setCount(1)
                selectName.setItems1(_this.default1.name)
                selectName.setDone(function(e){
                  var p1= _this.default1.name[e.index1];
                  _this.pickerValue.name = p1;
                 
                })
                selectName.show()
            
            },
            xuqiuShowpicker()
            {    //需求选择
                 var index1=0;
                 var _this = this;
                 var m=weex.requireModule('modal')
                 if(selectXuqiu == undefined){ 
                  selectXuqiu=weex.requireModule('fpicker');
                  
                } 
                selectXuqiu.setCount(1)
                selectXuqiu.setItems1(_this.default1.xuqiu)
                selectXuqiu.setDone(function(e){
                  var p1= _this.default1.xuqiu[e.index1];
                  _this.pickerValue.xuqiu = p1;
                 
                })
                selectXuqiu.show()
            
            },
            areaShowpicker()
            {    //地区选择
                 var index1=0;
                 var index2=0;
                 var _this = this;
                 var m=weex.requireModule('modal')
                 if(selectDidian == undefined)
                { 
                  selectDidian=weex.requireModule('fpicker')
                  // modal.alert({message:'wwww'})
                  // selectDidian2.setCount(2)
                } 
                selectDidian.setCount(2)
                selectDidian.setItems1(_this.toArray(_this.areaMap)) 
                selectDidian.setItems2(_this.toArray(_this.areaMap[index2].children)) 
                selectDidian.setChange(function(e){
                index1=e.index;
                selectDidian.setItems2(_this.toArray(_this.areaMap[index1].children))
                  /*picker.select(2,0);*/ 
                 },function(e){
                  index2=e.index;
                 })
                selectDidian2.setDone(function(e){
                    var p1= _this.areaMap[e.index1].name;
                    var p2= _this.areaMap[e.index1].children[e.index2].name;
                    _this.pickerValue.didian = p1 + '  ' + p2
                    modal.alert({message:_this.pickerValue.didian+p1+p2})
                   
                  })
                selectDidian.show()
            
            },
            industryShowpicker(){
              //行业选择
              var index1=0;
                 var _this = this;
                 var m=weex.requireModule('modal')
                 if(selectIndustry == undefined){ 
                  selectIndustry=weex.requireModule('fpicker');
                } 
                selectIndustry.setCount(1)
                selectIndustry.setItems1(_this.default1.industry)
                selectIndustry.setDone(function(e){
                  var p1= _this.default1.industry[e.index1];
                  _this.pickerValue.industry = p1;
                 
                })
                selectIndustry.show()
            },
            protypeShowpicker(){
              //项目类型选择
              var index1=0;
                 var _this = this;
                 var m=weex.requireModule('modal')
                 if(selectProType == undefined){ 
                  selectProType=weex.requireModule('fpicker');
                } 
                selectProType.setCount(1)
                selectProType.setItems1(_this.default1.proType)
                selectProType.setDone(function(e){
                  var p1= _this.default1.proType[e.index1];
                  _this.pickerValue.proType = p1;
                 
                })
                selectProType.show()
            },
            submit: function(){
//                if (false) {
//                    modal.alert({
//                        message: "请输入正确的用户名或密码",
//                        duration: 0.3
//                    })
//                }else{
//                    modal.alert({
//                        message: "登陆成功",
//                        duration: 0.3
//                    })
//                    this.$router.push("home")
//                }
                //存储全局变量
                // var st=weex.requireModule("static")
                // st.set('user',{});
                //导航控制器
                // var nav=weex.requireModule("navigator")
                // nav.dismiss();
                // modal.toast({message:'登录成功!'})

            }

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
    background-color: #eee;
  }
  .content{
  }
  .item_content{
  }
  .item_list{
    position: relative;
    padding-left: 20px;
    padding-bottom:12px;
    padding-top: 13px;
    flex-direction: row;
    align-items:flex-start; 
    background-color: #fff;
    margin-bottom: 16px;

  }
  .item_list_txt{
    color:#333;
    font-size: 32px;
    line-height: 66px;
    width: 183px;
  }
  .slect_text{
    color:#333;
    font-size: 32px;
    line-height: 64px;
  }
  .item_list_input{
    color:#333;
    font-size: 32px;
    line-height: 64px;
    padding-left: 26px;
    border-width: 1px;
    border-style: solid;
    border-color: #cdcdcd;
    border-radius: 5px;
    width: 506px;
  }
  .item_list_select{
    color:#333;
    font-size: 32px;
    line-height: 64px;
    height: 64px;
    padding-left: 26px;
    border-width: 1px;
    border-style: solid;
    border-color: #cdcdcd;
    border-radius: 5px;
    width: 506px;
    position: relative;
  }
  .item_list_select_arrow{
    width: 20px;
    height: 10px;
    position: absolute;
    right: 30px;
    top: 26px;
  }
  .item_list_area{
    color:#333;
    font-size: 32px;
    line-height: 66px;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 10px;
    padding-bottom: 10px;
    margin-left: 10px;
    border-width: 1px;
    border-style: solid;
    border-color: #cdcdcd;
    border-radius: 5px;
    width: 690px;
    height: 146px;
    margin-bottom: 22px;
  }
  .contract_item{
    height:29px;
    border-width:1px;
    border-style: solid;
    border-color:#17acf6;
    border-radius: 29px;
    margin-right: 14px;
    padding-right: 14px;
    padding-left: 14px;
  }
  .contract_item_txt1{
    line-height: 29px;
    align-items:center; 
    color: #17acf6;
    font-size: 17px;
  }
  .btn_box{
    margin-top: 58px;
    margin-bottom: 42px;
    flex-direction: row;
    justify-content: center;
    align-items:center; 
  }
  .btn{
    justify-content: center;
    align-items:center;     
    width:290px; 
    height: 80px;
    background-color: #18ACF4;
    border-radius: 80px;
  }
  .btn_concel{   
    justify-content: center;
    align-items:center;      
    width:288px; 
    height: 78px;
    border-radius: 80px;
    margin-left:30px;
    border-width:1px;
    border-style: solid;
    border-color:#18ACF4;
  }
  .contract_box{
    flex-direction: row;
    justify-content: flex-start; 
  }
  .contract_more_box{
    flex-direction: row;
    justify-content: center; 
    margin-bottom: 20px;
  }
  .sure_box{
    flex-direction: row;
    justify-content: flex-start; 
    margin-left: 30px;
  }
  .text_grey{
    font-size: 20px;
    color: #666;
  }
  .text_blue{
    font-size: 24px;
    color: #18ACF4;
  }
  .b1{bottom:-10000}
  .b0{bottom:0;}

</style>