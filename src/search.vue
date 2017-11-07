<template>
	<div class="search_wrapper">

        <!--导航栏开始-->

		<div class="head_wrapper">
			
			<div style="height: 128px;margin-top: 60px;margin-left: 20px;flex-direction: row;align-items: center;">

				<image src="../images/my/backWhite.png" style="width:40;height: 40;margin-right: 10px;" @click="img_click"></image>

				<div style="flex-direction: row;position: relative;">

					<input type="text" placeholder="搜索" class="input" :autofocus=true value="" v-model="inputs" @change="cunchu"/>

					<image style="width: 33px;height: 33px;position: absolute;left: 30px;top: 15px;" src="../images/my/iconSearch.png"></image>

				</div>

				<!--<search :w="w" @doneInp="doneInp" ref="search"></search>&lt;!&ndash;ref拿到标签，在操作标签，.的方法可以操作里面的方法（只有组件的时候才可以操作里面的方法）&ndash;&gt;-->

				<text style="font-size: 30px;color: #FFFFFF;margin-left: 20px;font-weight: bold;" @click="quxiao">取消</text>

			</div>

		</div>

		<!--导航栏结束-->

		<!--热门搜索-->

		<!--<div class="hotSearch">-->

			<!--<div class="hotHead">-->

				<!--&lt;!&ndash;<image class="hotImg" src="../images/my/hotlmg.png" style="width: 31px;height: 32px;"></image>&ndash;&gt;-->
				<!--<text class="hotText" @click="wzx">热门搜索</text>-->

			<!--</div>-->

			<!--<div class="searchWord">-->

				<!--<div class="word" v-for="item in words">-->

					<!--<text style="font-size: 24px;color: #7A7A7A;line-height: 45px;">{{item}}</text>-->

				<!--</div>-->

			<!--</div>-->

		<!--</div>-->

		<!--搜索历史-->

		<scroller>
		<div class="hotSearch" v-if="isShow">

			<div class="hotHead">
				<!--<image class="hotImg" src="../images/my/hos.png" style="width: 20px;height: 20px;"></image>-->
				<text class="hotText" >搜索历史</text>
				<text class="clearH" @click="qingchulishi">清除历史</text>

			</div>

			<div class="searchHT" v-for="(item,index) in ss" @click="sousuo1(item,index)">

				<text style="color: #000000;font-size: 26px;font-weight: bold;">{{item}}</text>

				<image style="width: 35px;height: 35px;position: absolute;right: 0;top: 27px;" src="../images/deleteBtn.png" @click="removeTodo(index)"></image>

			</div>

		</div>

			<div v-for='item in lists' style="margin-left: 28px;margin-top: 20px">

				<taskcontent :list="item" ></taskcontent>

			</div>

		</scroller>

	</div>

</template>

<script>

    var nav = weex.requireModule('navigator');
//    const net = require('../vue/busi/util/net.js')
    const storage = weex.requireModule('storage')
    var modal = weex.requireModule('modal');
//    import taskcontent from '../components/TaskContent.vue';

    export default{

        data(){

            return{
                isShow:true,
                inputs:'',
                lists:[],
                title: '我的',
                w: '570px',
//                words: ['工艺设计','视觉传达设计','纯艺术','工艺艺术','环境艺术设计','什么是环境艺术设计'],
                ss: []

            }
        },

        components:{

//            taskcontent

        },

        mounted(){

            this._gethuoqu();


        },
        methods:{

            sousuo1(item,index){

              this.inputs = item;
              this.sousuo();

			},

            cunchu(){

                var pref=weex.requireModule("pref")
                var obj={};
                this.ss.push(this.inputs)
                obj.xxx=this.ss;
//                obj.xxx.length = 10;
                pref.setObj('objkey',obj);
                var modal=weex.requireModule("modal")
                modal.toast({message:'存储成功'});
                this.sousuo();
                this.isShow = !this.isShow;

            },

            sousuo(){

                if(this.inputs == ''){
                    var modal=weex.requireModule("modal")
                    modal.toast({message:'您输入的搜索内容不能为空！'});

                }else {

                }

                var this_se = this;
                var modal = weex.requireModule('modal')
                net.post('taskHall/findMobileTasks',
                    {
                        pageNum:1,
                        pageSize:10,
                        conditions:this_se.inputs
                    },
                    function (e) {
                        this_se.lists=e.data.list;

//                            modal.alert({message:e.data.list});



                    })

			},

            img_click(){

                nav.push('../task/Task.js')
            },

            //点击取消清空输入框里面的内容
            quxiao(){

                this.inputs = ''

//                this.$refs.search.clear();//点击取消的时候清空里面的内容
            },

            //删除每一行
            removeTodo(index){

                this.ss.splice(index,1)


            },

            //删除全部
            qingchulishi(){

//                this.ss.splice([])

                var pref=weex.requireModule("pref")
                pref.remove('objkey');
                var modal=weex.requireModule("modal")
                modal.toast({message:123});
                this._gethuoqu();

            },

            //自定义获取字符串
            _gethuoqu(){

                var pref=weex.requireModule("pref")
                var p=  pref.getObj('objkey');
                if(p.xxx==undefined)
				{
                    this.ss=[];
                    return;
				}
                this.ss = p.xxx;
            }
        }
    }

</script>

<style scoped>
	.search_wrapper{
		width: 750px;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: #EEEEEE;
	}
	.head_wrapper{
		width: 750px;
		height: 170px;
		background-color: #17ACF6;
		flex-direction: row;
		align-items: center;

	}
	.hotSearch{
		width: 750px;
		padding-left: 20px;
		padding-right: 20px;
		margin-bottom: 15px;
		flex-direction: column;
		background-color: #FFFFFF;
	}
	.hotHead{
		height: 90px;
		flex-direction: row;
		align-items: center;
		border-bottom-style: solid;
		border-bottom-color: #DEDEDE;
		border-bottom-width: 1px;
	}
	.hotImg{
		width: 53px;
		height: 53px;
		margin-left: 20px;
	}
	.hotText{
		font-size: 30px;
		font-weight: bold;
	}
	.searchWord{
		padding-bottom: 25px;
		padding-top: 25px;
		flex-direction: row;
		flex-wrap: wrap;
	}
	.word{
		height: 45px;
		margin-right: 15px;
		margin-bottom: 15px;
		border-style: solid;
		border-radius: 5px;
		border-width: 1px;
		border-color: #7A7A7A;
		padding-left: 15px;
		padding-right: 15px;
	}
	.clearH{
		font-size: 25px;
		font-weight: bold;
		color: #17ACF6;
		position: absolute;
		right: 0;
		line-height: 90px;
	}
	.searchHT{
		height: 90px;
		border-top-color: #DFDFDF;
		border-top-style: solid;
		border-top-width: 1px;
		flex-direction: row;
		align-items: center;
	}

	.input{

		width: 580px;
		height: 60px;
		background-color: #ffffff;
		border-width:1px;
		border-color: #ffffff;
		border-radius: 10px;
		padding-left: 70px;

	}
</style>