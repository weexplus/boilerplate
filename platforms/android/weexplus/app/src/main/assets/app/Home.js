// { "framework": "Vue" }
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(246)
	)

	/* script */
	__vue_exports__ = __webpack_require__(247)

	/* template */
	var __vue_template__ = __webpack_require__(256)
	__vue_options__ = __vue_exports__ = __vue_exports__ || {}
	if (
	  typeof __vue_exports__.default === "object" ||
	  typeof __vue_exports__.default === "function"
	) {
	if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
	__vue_options__ = __vue_exports__ = __vue_exports__.default
	}
	if (typeof __vue_options__ === "function") {
	  __vue_options__ = __vue_options__.options
	}
	__vue_options__.__file = "/Users/zhengjiangrong/work/corenerstone/netplatform/src/home/Home.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-00e93187"
	__vue_options__.style = __vue_options__.style || {}
	__vue_styles__.forEach(function (module) {
	  for (var name in module) {
	    __vue_options__.style[name] = module[name]
	  }
	})
	if (typeof __register_static_styles__ === "function") {
	  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
	}

	module.exports = __vue_exports__
	module.exports.el = 'true'
	new Vue(module.exports)


/***/ }),

/***/ 99:
/***/ (function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	//var host='http://10.39.1.237:8081/cdtp/';
	//var host='http://10.39.1.237:8081/cdtp/';
	var host = 'http://10.39.1.72:8081/cdtp/';
	//var token= "8c0ecee9ba760a1820f8006e0cfb7413";
	//var refreshToken="68a24a0722b6fc0aed1006b2a28f4091";
	//var Header = {
	//	token: token,
	//	refreshToken: refreshToken
	//}

	exports.default = {
	    //	userId: '85935039157a4740aac07aa0e015a7e8',
	    postShort: function postShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.postFull(weg, param, header, start, success, function (res) {
	            //fail
	            //          modal.toast({message:res.msg})
	        }, function () {
	            //exception
	            //          modal.toast({message:'网络异常！'})
	        }, function () {
	            //compelete
	            compelete();
	        });
	    },

	    postFull: function postFull(weg, param, header, start, success, fail, exception, compelete) {
	        var net = weex.requireModule("net");
	        var modal = weex.requireModule("modal");
	        var self = this;
	        var url = host + weg;
	        var st = weex.requireModule('static');
	        var objkey = st.get('user');
	        if (objkey != undefined && objkey != '') {
	            header.token = objkey.userInfo.token;
	            header.refreshToken = objkey.userInfo.refreshToken;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.post(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            //          if(e.res.err==0)
	            //          {
	            //
	            success(e.res);
	            /*token=e.data.token
	            refreshToken=e.data.refreshToken*/
	            //          }
	            //          else
	            //          {
	            //              // modal.toast({message:e.res.msg})
	            //              if(e.res.err==1000)
	            //              {
	            //                  var nav=weex.requireModule("navigator")
	            //                  nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	            //                      self.postFull(weg,param,header,start,success,fail,exception,compelete);
	            //                  },true);
	            //              }
	            //              else
	            //              fail(e.res);
	            //          }
	        }, function (e) {
	            //compelete


	            compelete();
	        }, function (e) {
	            // exception
	            exception();
	        });
	    },
	    post: function post(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.postShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },
	    postNoHeader: function postNoHeader(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.postShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },

	    postSlient: function postSlient(weg, param, success) {

	        this.postFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    },

	    //     GET
	    getShort: function getShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.getFull(weg, param, header, start, success, function (res) {
	            //fail
	            //          modal.toast({message:res.msg})
	        }, function () {
	            //exception
	            //          modal.toast({message:'网络异常！'})
	        }, function () {
	            //compelete

	            compelete();
	        });
	    },

	    getFull: function getFull(weg, param, header, start, success, fail, exception, compelete) {
	        var net = weex.requireModule("net");
	        var modal = weex.requireModule("modal");
	        var self = this;
	        var url = host + weg;
	        var st = weex.requireModule('static');
	        var objkey = st.get('objkey');
	        if (objkey != undefined && objkey != '') {
	            header.token = objkey.userInfo.token;
	            header.refreshToken = objkey.userInfo.refreshToken;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.get(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            //          if(e.res.err==0)
	            //          {
	            //
	            success(e.res);
	            /*token=e.data.token
	            refreshToken=e.data.refreshToken*/
	            //          }
	            //          else
	            //          {
	            //              // modal.toast({message:e.res.msg})
	            //              if(e.res.err==1000)
	            //              {
	            //                  var nav=weex.requireModule("navigator")
	            //                  nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	            //                      self.postFull(weg,param,header,start,success,fail,exception,compelete);
	            //                  },true);
	            //              }
	            //              else
	            //              fail(e.res);
	            //          }
	        }, function (e) {
	            //compelete


	            compelete();
	        }, function (e) {
	            // exception
	            exception();
	        });
	    },
	    get: function get(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.getShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },
	    getNoHeader: function getNoHeader(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.getShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },

	    getSlient: function getSlient(weg, param, success) {

	        this.getFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    }
	};
	module.exports = exports["default"];

/***/ }),

/***/ 246:
/***/ (function(module, exports) {

	module.exports = {
	  "home-container": {
	    "width": 750,
	    "fontSize": 30,
	    "textAlign": "center"
	  },
	  "scroller": {
	    "width": 750,
	    "flex": 1,
	    "paddingBottom": 100
	  },
	  "btn": {
	    "width": 400,
	    "height": 70,
	    "lineHeight": 70,
	    "color": "#ffffff",
	    "backgroundColor": "#16ADF6",
	    "borderRadius": 100,
	    "marginLeft": 200,
	    "marginTop": 200,
	    "textAlign": "center"
	  },
	  "image": {
	    "width": 750,
	    "height": 410
	  },
	  "slider": {
	    "width": 750,
	    "height": 410
	  },
	  "frame": {
	    "width": 700,
	    "height": 700,
	    "position": "relative"
	  },
	  "item": {
	    "width": 750,
	    "paddingLeft": 36,
	    "paddingRight": 36,
	    "flexDirection": "row",
	    "flexWrap": "wrap",
	    "paddingTop": 20,
	    "backgroundColor": "#ffffff"
	  },
	  "itemWrap": {
	    "width": 168,
	    "height": 168,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "float": "left"
	  },
	  "message": {
	    "height": 90,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "panel": {
	    "width": 702,
	    "marginLeft": 25,
	    "marginTop": 20,
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "backgroundColor": "#ffffff"
	  },
	  "botm": {
	    "width": 652,
	    "height": 88,
	    "marginTop": 28,
	    "borderTopWidth": 1,
	    "borderTopStyle": "solid",
	    "borderTopColor": "#eeeeee"
	  },
	  "down_tab": {
	    "flexDirection": "row",
	    "position": "fixed",
	    "left": 0,
	    "bottom": 0,
	    "height": 98,
	    "width": 750,
	    "backgroundColor": "#ffffff",
	    "borderTopColor": "#cccccc",
	    "borderTopStyle": "solid",
	    "borderTopWidth": 1,
	    "justifyContent": "space-around"
	  },
	  "tab_list": {
	    "width": 150,
	    "textAlign": "center",
	    "justifyContent": "center"
	  },
	  "tab_text": {
	    "fontSize": 20,
	    "color": "#9f9f9f",
	    "textAlign": "center"
	  },
	  "tab_img": {
	    "marginTop": 16,
	    "marginBottom": 10,
	    "marginLeft": 55
	  },
	  "loading": {
	    "width": 750,
	    "justifyContent": "center"
	  },
	  "indicator": {
	    "color": "#888888",
	    "fontSize": 30,
	    "paddingTop": 20,
	    "paddingBottom": 20,
	    "textAlign": "center"
	  },
	  "cell": {
	    "paddingBottom": 20
	  }
	}

/***/ }),

/***/ 247:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	var _flist = __webpack_require__(248);

	var _flist2 = _interopRequireDefault(_flist);

	var _net = __webpack_require__(99);

	var _net2 = _interopRequireDefault(_net);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//

	exports.default = {
	  data: function data() {
	    return {
	      imageList: [],
	      itemList: [{ title: "商机合作", src: "../images/home/item1.png" }, { title: "总包服务", src: "../images/home/item2.png" }, { title: "技术转化", src: "../images/home/item3.png" }, { title: "设备代理", src: "../images/home/item4.png" }, { title: "联合设计", src: "../images/home/item5.png" }, { title: "联合施工", src: "../images/home/item6.png" }, { title: "加盟合作", src: "../images/home/item7.png" }, { title: "其它", src: "../images/home/item8.png" }],
	      IndustryCategoryList: [{ id: 1, industryDefinition: "燃气设计", src: "../images/design1.png" }, { id: 2, industryDefinition: "化工设计", src: "../images/design2.png" }, { id: 3, industryDefinition: "热力设计", src: "../images/design3.png"
	        //        {title:"查看全部",src:"../images/design4.png"}
	      }],
	      lists: [{ "taskStateString": "报名中", "publishTimeString": "2017-09-20", "startTimeString": "2017-09-12", "endTimeString": "2017-09-30", "viewCount": 0, "signUpCount": 0, "adaptable": false, "id": "e79b5b3a20314236a1202f3299430318", "taskCode": "XDA201700672-09-S44-000002", "projectId": "DM2017f1f93", "taskName": "AB面问题-施工图", "managePrice": 29.502, "techRequirement": "312312312", "publishModel": 1, "publishUserId": "109", "role": "设计经理", "startTime": "Sep 12, 2017 12:00:00 AM", "endTime": "Sep 30, 2017 12:00:00 AM", "taskDuration": 18, "taskContent": "231312", "paymentMode": 1, "taskFlag": "1", "taskState": "报名中", "publishTime": "2017-09-20", "designStage": "施工图", "industryId": "8", "demandProTypeId1": "304f677684894faf8e7a8733500a9959", "demandProTypeId2": "6f09c192cc5c42a59969f249aaf19c3d", "demandProTypeId3": "9623acf9a0bc4b98b9c623f6d058d2f4", "demandProTypeId4": "8e9b05db65ab4ac7b4b0c4cf60a2a564", "demandProvince": "天津市", "demandCity": "天津市", "xmsysj": 5, "taskStateId": 2, "demandProTypeName": "建筑行业测试数据1级-建筑行业测试数据2级-建筑行业测试数据3级-建筑行业测试数据4级", "demandProIndustry": "建筑行业" }, { "taskStateString": "报名中", "publishTimeString": "2017-09-20", "startTimeString": "2017-09-12", "endTimeString": "2017-09-30", "viewCount": 0, "signUpCount": 0, "adaptable": false, "id": "e79b5b3a20314236a1202f3299430318", "taskCode": "XDA201700672-09-S44-000002", "projectId": "DM2017f1f93", "taskName": "AB面问题-施工图", "managePrice": 29.502, "techRequirement": "312312312", "publishModel": 1, "publishUserId": "109", "role": "设计经理", "startTime": "Sep 12, 2017 12:00:00 AM", "endTime": "Sep 30, 2017 12:00:00 AM", "taskDuration": 18, "taskContent": "231312", "paymentMode": 1, "taskFlag": "1", "taskState": "报名中", "publishTime": "2017-09-20", "designStage": "施工图", "industryId": "8", "demandProTypeId1": "304f677684894faf8e7a8733500a9959", "demandProTypeId2": "6f09c192cc5c42a59969f249aaf19c3d", "demandProTypeId3": "9623acf9a0bc4b98b9c623f6d058d2f4", "demandProTypeId4": "8e9b05db65ab4ac7b4b0c4cf60a2a564", "demandProvince": "天津市", "demandCity": "天津市", "xmsysj": 5, "taskStateId": 2, "demandProTypeName": "建筑行业测试数据1级-建筑行业测试数据2级-建筑行业测试数据3级-建筑行业测试数据4级", "demandProIndustry": "建筑行业" }, { "taskStateString": "报名中", "publishTimeString": "2017-09-20", "startTimeString": "2017-09-12", "endTimeString": "2017-09-30", "viewCount": 0, "signUpCount": 0, "adaptable": false, "id": "e79b5b3a20314236a1202f3299430318", "taskCode": "XDA201700672-09-S44-000002", "projectId": "DM2017f1f93", "taskName": "AB面问题-施工图", "managePrice": 29.502, "techRequirement": "312312312", "publishModel": 1, "publishUserId": "109", "role": "设计经理", "startTime": "Sep 12, 2017 12:00:00 AM", "endTime": "Sep 30, 2017 12:00:00 AM", "taskDuration": 18, "taskContent": "231312", "paymentMode": 1, "taskFlag": "1", "taskState": "报名中", "publishTime": "2017-09-20", "designStage": "施工图", "industryId": "8", "demandProTypeId1": "304f677684894faf8e7a8733500a9959", "demandProTypeId2": "6f09c192cc5c42a59969f249aaf19c3d", "demandProTypeId3": "9623acf9a0bc4b98b9c623f6d058d2f4", "demandProTypeId4": "8e9b05db65ab4ac7b4b0c4cf60a2a564", "demandProvince": "天津市", "demandCity": "天津市", "xmsysj": 5, "taskStateId": 2, "demandProTypeName": "建筑行业测试数据1级-建筑行业测试数据2级-建筑行业测试数据3级-建筑行业测试数据4级", "demandProIndustry": "建筑行业" }, { "taskStateString": "报名中", "publishTimeString": "2017-09-20", "startTimeString": "2017-09-12", "endTimeString": "2017-09-30", "viewCount": 0, "signUpCount": 0, "adaptable": false, "id": "e79b5b3a20314236a1202f3299430318", "taskCode": "XDA201700672-09-S44-000002", "projectId": "DM2017f1f93", "taskName": "AB面问题-施工图", "managePrice": 29.502, "techRequirement": "312312312", "publishModel": 1, "publishUserId": "109", "role": "设计经理", "startTime": "Sep 12, 2017 12:00:00 AM", "endTime": "Sep 30, 2017 12:00:00 AM", "taskDuration": 18, "taskContent": "231312", "paymentMode": 1, "taskFlag": "1", "taskState": "报名中", "publishTime": "2017-09-20", "designStage": "施工图", "industryId": "8", "demandProTypeId1": "304f677684894faf8e7a8733500a9959", "demandProTypeId2": "6f09c192cc5c42a59969f249aaf19c3d", "demandProTypeId3": "9623acf9a0bc4b98b9c623f6d058d2f4", "demandProTypeId4": "8e9b05db65ab4ac7b4b0c4cf60a2a564", "demandProvince": "天津市", "demandCity": "天津市", "xmsysj": 5, "taskStateId": 2, "demandProTypeName": "建筑行业测试数据1级-建筑行业测试数据2级-建筑行业测试数据3级-建筑行业测试数据4级", "demandProIndustry": "建筑行业" }],
	      showLoading: 'hide',
	      pageNum: 1,
	      pageSize: 10
	    };
	  },

	  components: {
	    flist: _flist2.default
	  },
	  methods: {
	    submit: function submit() {
	      this.$router.push("login");
	    },
	    push: function push(item, index) {
	      //点击行业分类模块进入任务界面
	      var self = this;
	      var nav = weex.requireModule('navigator');
	      var modal = weex.requireModule('modal');
	      //    	 modal.alert({message:self.IndustryCategoryList[index]});
	      var hangye = self.IndustryCategoryList[index];
	      nav.pushParam('Tasklist.js', { name: hangye }); //向任务列表页面传数据   self.IndustryCategoryList[index].title 
	      //		nav.pushParam('Tasklist.js',{id:hangye.id})
	    },
	    push1: function push1() {
	      //    	net.post('/user/getStatus',{},function(e){
	      //    		if(e.status == 0){
	      //				var modal = weex.requireModule('modal')
	      //				modal.toast({message: '您未登录！',duration: 0.3});
	      //				setTimeout(function(){
	      //					var nav = weex.requireModule('navigator');
	      //      			nav.push('../login/Login.js')
	      //				},1000)
	      //			}else{
	      //				var nav = weex.requireModule('navigator');
	      //      		nav.push('Classification.js')
	      //			}
	      //    	})
	      var nav = weex.requireModule('navigator');
	      nav.push('Classification.js');
	    },
	    push2: function push2() {
	      //发任务跳转
	      var nav = weex.requireModule('navigator');
	      nav.push('Choose.js');
	    },
	    push3: function push3() {
	      //社区跳转
	      var nav = weex.requireModule('navigator');
	      nav.push('Home.js');
	    },
	    search_click: function search_click() {
	      //                this.$router.push("register")
	      var nav = weex.requireModule("navigator");
	      nav.push('../my/search.js');
	    },
	    jump: function jump(item, index) {
	      if (index == 0) {
	        var nav = weex.requireModule("navigator");
	        nav.push('cooperation.js');
	      };
	      if (index == 1) {
	        var nav = weex.requireModule("navigator");
	        nav.push('turnkeyService.js');
	      };
	      if (index == 2) {
	        var nav = weex.requireModule("navigator");
	        nav.push('cooperation.js');
	      };
	      if (index == 3) {
	        var nav = weex.requireModule("navigator");
	        nav.push('deviceAgent.js');
	      };
	      if (index == 4) {
	        var nav = weex.requireModule("navigator");
	        nav.push('jointDesign.js');
	      };
	      if (index == 5) {
	        var nav = weex.requireModule("navigator");
	        nav.push('combinedConstruction.js');
	      };
	      if (index == 6) {
	        var nav = weex.requireModule("navigator");
	        nav.push('franchiseCooperation.js');
	      };
	      if (index == 7) {
	        var nav = weex.requireModule("navigator");
	        nav.push('other.js');
	      };
	    },
	    jumpto: function jumpto() {
	      var nav = weex.requireModule("navigator");
	      nav.push('../task/Task.js');
	    },
	    jumptask: function jumptask(item) {
	      //点击任务跳转到任务详情页面
	      if (!item.acceptable) {
	        this._judgeState(item.taskCode, item.taskState, '2');
	        return;
	      }
	      this._judgeState(item.taskCode, item.taskState, item.acceptable);
	    },
	    _judgeState: function _judgeState(code, state, acceptable) {
	      var nav = weex.requireModule('navigator');
	      var that = this;
	      _net2.default.post('taskHall/taskSensitiveDetailCode', {
	        taskCode: code
	      }, function (res) {
	        if (res.status == '1') {
	          var details = res.data;
	          if (details.taskDetails.length == 1) {
	            nav.pushFull('../task/Task_Project.js', {
	              resData: details,
	              taskCode: code,
	              taskState: state, //状态
	              acceptable: acceptable //是否可接
	              //				    				diff: 'fb'
	            }, 'hidden', function (e) {
	              if (!e) {
	                return;
	              }
	              self.callbackdata = e.ok;
	            }, true);
	          }
	          if (details.taskDetails.length > 1) {
	            nav.pushFull('../task/Task_MultistageBid.js', {
	              resData: details,
	              taskCode: code,
	              taskState: state, //状态
	              acceptable: acceptable //是否可接
	              //				    				diff: 'fb'
	            }, 'hidden', function (e) {
	              if (!e) {
	                return;
	              }
	              self.callbackdata = e.ok;
	            }, true);
	          }
	        }
	      });
	    },

	    // __requestData(num1,num2){
	    // 	net.post('taskHall/recommendTask',             //推荐任务数据定义函数
	    // 	{pageNum:num1,pageSize:num2},
	    //     		function (e) {
	    //     			self.lists=e.data.list;

	    // });
	    // },
	    __requestData: function __requestData() {
	      var _this = this;
	      var parms = {
	        pageNum: _this.pageNum,
	        pageSize: _this.pageSize
	      };
	      _this.$refs.loadDown.load('taskHall/recommendTask', parms, _this.lists, function () {
	        _this.pageNum++;
	      });
	    }
	  },
	  mounted: function mounted() {
	    var self = this;
	    var aa;
	    var modal = weex.requireModule('modal');
	    //    	net.post('system/banner/getBanner',    //轮播图
	    //    			{pictureKind:'001'},
	    //          		function (e) {
	    ////					console.log(e.data)
	    //          			self.imageList=e.data;
	    ////        			modal.alert({message:self.imageList});
	    //
	    //    	});
	    ////		self.__requestData(1,8),
	    //    	net.post('taskHall/recommendTask',             //推荐任务数据
	    //    			{pageNum:self.num1,pageSize:self.num2},
	    //          		function (e) {
	    ////					console.log(e.data)
	    //          			self.lists=e.data.list;
	    ////        			modal.alert({message:self.lists});
	    //
	    //    	});
	    var globalEvent = weex.requireModule('globalEvent');
	    globalEvent.addEventListener("onPageInit", function (e) {

	      var nav = weex.requireModule('navigator');
	      nav.setPageId('index');
	      nav.setRoot('index');
	    });
	  },


	  created: function created() {

	    var globalEvent = weex.requireModule('globalEvent');
	    globalEvent.addEventListener("onPageInit", function (e) {

	      var nav = weex.requireModule('navigator');
	      nav.setPageId('index');
	      nav.setRoot('index');
	    });
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 248:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(249)
	)

	/* script */
	__vue_exports__ = __webpack_require__(250)

	/* template */
	var __vue_template__ = __webpack_require__(255)
	__vue_options__ = __vue_exports__ = __vue_exports__ || {}
	if (
	  typeof __vue_exports__.default === "object" ||
	  typeof __vue_exports__.default === "function"
	) {
	if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
	__vue_options__ = __vue_exports__ = __vue_exports__.default
	}
	if (typeof __vue_options__ === "function") {
	  __vue_options__ = __vue_options__.options
	}
	__vue_options__.__file = "/Users/zhengjiangrong/work/corenerstone/netplatform/src/vue/component/flist.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-dd41977e"
	__vue_options__.style = __vue_options__.style || {}
	__vue_styles__.forEach(function (module) {
	  for (var name in module) {
	    __vue_options__.style[name] = module[name]
	  }
	})
	if (typeof __register_static_styles__ === "function") {
	  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
	}

	module.exports = __vue_exports__


/***/ }),

/***/ 249:
/***/ (function(module, exports) {

	module.exports = {
	  "progress": {
	    "width": 220,
	    "height": 220,
	    "opacity": 0.8,
	    "backgroundColor": "#000000",
	    "borderRadius": 30,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "position": "absolute",
	    "left": 265,
	    "top": 300
	  },
	  "exception": {
	    "marginTop": 50,
	    "borderRadius": 10,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "width": 300,
	    "height": 80,
	    "borderWidth": 2,
	    "borderColor": "#949494",
	    "backgroundColor": "#ffffff",
	    "backgroundColor:active": "#dddddd"
	  },
	  "emptytxt": {
	    "marginTop": 100,
	    "color": "#999999"
	  },
	  "empty": {
	    "height": 500,
	    "alignItems": "center"
	  },
	  "loading": {
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "flexDirection": "row",
	    "borderRadius": 5,
	    "borderWidth": 1,
	    "borderColor": "#e6e6e6",
	    "backgroundColor": "#fdfdfd",
	    "margin": 15,
	    "marginLeft": 30,
	    "marginRight": 30
	  },
	  "c": {
	    "flex": 1
	  },
	  "page": {
	    "backgroundColor": "#ffffff",
	    "width": 750,
	    "flex": 1
	  }
	}

/***/ }),

/***/ 250:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//

	var pull = __webpack_require__(251);
	var net = __webpack_require__(99);

	exports.default = {
	    components: { pull: pull },
	    props: {
	        columnCount: {
	            default: 1
	        },
	        key: {
	            default: 'key1'
	        },
	        columnGap: {
	            default: 20
	        },
	        columnWidth: {
	            default: 'auto'
	        },
	        scrollable: {
	            default: true
	        },
	        showScrollbar: {
	            default: true
	        },
	        showheader: {
	            default: false
	        },
	        usePullRefresh: {
	            default: true
	        },
	        items: {
	            default: []
	        },
	        isEmpty: {
	            default: false
	        },
	        background: {
	            default: '#ffffff'
	        },
	        isException: {
	            default: false
	        },
	        pageSize: {
	            default: 10
	        },
	        isloading: {
	            default: false
	        },
	        loadOnInit: {
	            default: true
	        },
	        hasmore: {
	            default: true
	        },
	        emptyTxt: {
	            default: '您的订单为空~'
	        },
	        img_empty_src: {
	            default: 'root:img/ico_empty.png'
	        },

	        img_exception_src: {
	            default: 'root:img/ico_exception.png'
	        },
	        showweg: {
	            default: false
	        },
	        ispull: {
	            default: false
	        }

	    },
	    data: function data() {

	        return {
	            pullkey: this.key + "pull",
	            waterkey: this.key + "water"

	        };
	    },
	    mounted: function mounted() {

	        if (this.loadOnInit) {
	            var self = this;
	            self.$emit('load');
	        }
	    },


	    methods: {

	        loadmore: function loadmore(e) {

	            if (this.hasmore) this.$emit('load');
	        },
	        getCount: function getCount() {
	            if (this.isEmpty || this.isException) return 1;else return this.columnCount;
	        },

	        clear: function clear() {

	            this.hasmore = true;
	            this.items.length = 0;
	        },
	        showEmpty: function showEmpty() {
	            this.columnCount = 1;
	            this.isEmpty = true;
	            this.isException = false;
	        },
	        showException: function showException() {
	            this.columnCount = 1;
	            this.isEmpty = false;
	            this.isException = true;
	        },
	        load: function load(url, param, items, callback) {
	            this.loadFull(url, param, items, callback, function () {}, function () {}, function () {}, function () {});
	        },
	        loadFull: function loadFull(url, param, items, callback, start, fail, exp, comp) {
	            //                var progress=weex.requireModule("progress")
	            if (this.isloading) return;
	            this.isException = false;
	            this.isEmpty = false;
	            this.isloading = true;

	            var self = this;
	            //                postFull:  function (weg,param,header,start,success,compelete)

	            var modal = weex.requireModule('modal');
	            net.postFull(url, param, {}, function () {
	                //start
	                start();
	            }, function (res) {
	                //success
	                if (res.data.list.length < self.pageSize) {
	                    self.hasmore = false;
	                }
	                if (res.data.list.length > 0) {
	                    items = items.concat(res.data.list);
	                    self.items = items;
	                }
	                if (items.length == 0) {
	                    self.showEmpty();
	                }
	                callback(items);
	            }, function (e) {
	                //fail
	                fail();
	                modal.toast({ message: e.res.error });
	            }, function () {
	                //exception
	                exp();
	                if (items.length == 0) {
	                    self.showException();
	                } else {
	                    modal.toast({ message: '网络异常' });
	                }
	            }, function (e) {
	                //compelete
	                //                    progress.dismiss();
	                self.isloading = false;
	                self.$refs.refresh.end();
	                comp();
	            });
	        },

	        hideRefresh: function hideRefresh() {
	            var p = this.$refs.refresh;
	            p.end();
	            this.ispull = false;
	        },
	        refresh: function refresh() {
	            this.isEmpty = false;
	            this.isException = false;
	            this.isloading = false;
	            this.ispull = true;
	            this.$emit('refresh');
	        },
	        reload: function reload() {
	            this.isEmpty = false;
	            this.isException = false;
	            this.isloading = false;
	            this.ispull = false;
	            this.$emit('refresh');
	        }

	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 251:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(252)
	)

	/* script */
	__vue_exports__ = __webpack_require__(253)

	/* template */
	var __vue_template__ = __webpack_require__(254)
	__vue_options__ = __vue_exports__ = __vue_exports__ || {}
	if (
	  typeof __vue_exports__.default === "object" ||
	  typeof __vue_exports__.default === "function"
	) {
	if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
	__vue_options__ = __vue_exports__ = __vue_exports__.default
	}
	if (typeof __vue_options__ === "function") {
	  __vue_options__ = __vue_options__.options
	}
	__vue_options__.__file = "/Users/zhengjiangrong/work/corenerstone/netplatform/src/vue/component/pullrefresh.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-ac6beb1a"
	__vue_options__.style = __vue_options__.style || {}
	__vue_styles__.forEach(function (module) {
	  for (var name in module) {
	    __vue_options__.style[name] = module[name]
	  }
	})
	if (typeof __register_static_styles__ === "function") {
	  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
	}

	module.exports = __vue_exports__


/***/ }),

/***/ 252:
/***/ (function(module, exports) {

	module.exports = {
	  "limg": {
	    "width": 32,
	    "height": 46
	  },
	  "refresh": {
	    "height": 128,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "refreshText": {
	    "color": "#888888",
	    "fontSize": 30
	  },
	  "indicator": {
	    "color": "#888888",
	    "height": 40,
	    "width": 40,
	    "marginRight": 10
	  },
	  "panel": {
	    "width": 600,
	    "height": 250,
	    "marginLeft": 75,
	    "marginTop": 35,
	    "marginBottom": 35,
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "borderWidth": 2,
	    "borderStyle": "solid",
	    "borderColor": "#DDDDDD",
	    "backgroundColor": "#F5F5F5"
	  },
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 253:
/***/ (function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//


	exports.default = {
	    data: function data() {
	        return {
	            rtext: '下拉以加载',
	            updatetime: '没有更新',
	            offset: 0,
	            deg: 20,
	            refreshing: false,
	            pulldistance: 135,
	            hasrotate: false,
	            key: "ky" + Math.random()
	        };
	    },

	    methods: {
	        animateArrow: function animateArrow(deg) {
	            var animation = weex.requireModule('animation');
	            var arrow = this.$refs.arrow;
	            //                var deg=this.hasrotate?180:0

	            animation.transition(arrow, {
	                styles: {
	                    transform: "rotate(" + deg + "deg" + ")"
	                },

	                duration: 150, //ms
	                timingFunction: 'ease',
	                delay: 0 //ms
	            }, function () {});
	        },
	        onrefresh: function onrefresh(event) {
	            if (this.offset >= this.pulldistance) {

	                this.refreshing = true;
	                this.rtext = "加载中";
	                this.$emit('onRefresh');
	                //                    setTimeout(() => {
	                //                        this.refreshing = false
	                //                    }, 2000)
	            }
	        },
	        end: function end() {
	            this.refreshing = false;
	            //                this.deg=0;
	            this.updatetime = this.getNowFormatDate();
	            //                this.rtext='下拉以加载'
	        },
	        onpullingdown: function onpullingdown(event) {

	            var dis = event.pullingDistance;
	            if (dis < 0) dis *= -1;
	            this.offset = dis;

	            if (this.refreshing == false) {

	                //                     var t=dis>this.pulldistance
	                //                    if(t!=this.hasrotate)
	                //                    {
	                //                        this.hasrotate=t;
	                //                        this.animateArrow();
	                //                    }
	                if (dis > this.pulldistance) {
	                    this.rtext = "松开刷新";
	                    this.deg = 180;
	                    this.hasrotate = false;
	                    this.animateArrow(180);
	                } else {
	                    var p = dis / this.pulldistance;
	                    if (p > 1) p == 1;
	                    this.deg = p * 180;
	                    this.animateArrow(0);
	                    this.rtext = '下拉以加载';
	                }
	            }
	        },
	        getNowFormatDate: function getNowFormatDate() {
	            var date = new Date();
	            var seperator1 = "-";
	            var seperator2 = ":";
	            var month = date.getMonth() + 1;
	            var strDate = date.getDate();
	            var min = date.getMinutes();
	            var secon = date.getSeconds();
	            if (month >= 1 && month <= 9) {
	                month = "0" + month;
	            }
	            if (strDate >= 0 && strDate <= 9) {
	                strDate = "0" + strDate;
	            }
	            if (min >= 0 && min <= 9) {
	                min = "0" + min;
	            }
	            if (secon >= 0 && secon <= 9) {
	                secon = "0" + secon;
	            }

	            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + date.getHours() + seperator2 + min + seperator2 + secon;
	            return currentdate;
	        }
	    },

	    created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 254:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('refresh', {
	    key: _vm.key,
	    staticClass: ["refresh"],
	    attrs: {
	      "id": "rex",
	      "display": _vm.refreshing ? 'show' : 'hide'
	    },
	    on: {
	      "refresh": _vm.onrefresh,
	      "pullingdown": _vm.onpullingdown
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    }
	  }, [(_vm.refreshing) ? _c('floading', {
	    staticClass: ["indicator"],
	    attrs: {
	      "color": "#555555"
	    }
	  }) : _vm._e(), (!_vm.refreshing) ? _c('image', {
	    ref: "arrow",
	    staticClass: ["limg"],
	    attrs: {
	      "src": "root:img/pull_arrow.png"
	    }
	  }) : _vm._e(), _c('div', {
	    staticStyle: {
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticClass: ["refreshText"]
	  }, [_vm._v(_vm._s(_vm.rtext))]), _c('text', {
	    staticStyle: {
	      fontSize: "25",
	      color: "#888888"
	    }
	  }, [_vm._v("上次更新:" + _vm._s(_vm.updatetime))])])], 1)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 255:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["c"]
	  }, [_c('waterfall', {
	    key: _vm.waterkey,
	    staticClass: ["page"],
	    style: {
	      'background-color': _vm.background
	    },
	    attrs: {
	      "columnCount": _vm.getCount(),
	      "columnGap": _vm.columnGap,
	      "showScrollbar": _vm.showScrollbar,
	      "scrollable": _vm.scrollable,
	      "loadmoreoffset": "141"
	    },
	    on: {
	      "loadmore": _vm.loadmore
	    }
	  }, [(_vm.usePullRefresh) ? _c('pull', {
	    ref: "refresh",
	    on: {
	      "onRefresh": _vm.refresh
	    }
	  }) : _vm._e(), _c('header', [_c('div', [_vm._t("head")], 2)]), _vm._t("cell"), _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', [_vm._t("foot")], 2)]), (_vm.isEmpty) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticClass: ["empty"]
	  }, [_c('image', {
	    staticStyle: {
	      width: "252",
	      height: "201",
	      marginTop: "100"
	    },
	    attrs: {
	      "src": _vm.img_empty_src
	    }
	  }), _c('text', {
	    staticClass: ["emptytxt"],
	    staticStyle: {
	      marginTop: "20"
	    }
	  }, [_vm._v(_vm._s(_vm.emptyTxt))])])]) : _vm._e(), (_vm.isException) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticClass: ["empty"]
	  }, [_c('image', {
	    staticStyle: {
	      width: "252",
	      height: "201",
	      marginTop: "100"
	    },
	    attrs: {
	      "src": _vm.img_exception_src
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#000000",
	      marginTop: "20"
	    }
	  }, [_vm._v("网络请求失败")]), _c('div', {
	    staticClass: ["exception"],
	    on: {
	      "click": _vm.reload
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#000000",
	      fontSize: "28"
	    }
	  }, [_vm._v("重新加载")])])])]) : _vm._e(), (!_vm.ispull && _vm.isloading && _vm.items.length > 0) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticClass: ["loading"]
	  }, [_c('floading', {
	    staticStyle: {
	      height: "40",
	      width: "40"
	    },
	    attrs: {
	      "color": "#999999",
	      "loadingStyle": "small"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginLeft: "10",
	      color: "#999999",
	      fontSize: "28"
	    }
	  }, [_vm._v("正在载入")])], 1)]) : _vm._e()], 2), (!_vm.ispull && _vm.isloading && _vm.items.length == 0) ? _c('div', {
	    staticClass: ["progress"]
	  }, [_c('floading', {
	    staticStyle: {
	      height: "40",
	      width: "40",
	      marginTop: "20"
	    },
	    attrs: {
	      "color": "#ffffff",
	      "loadingStyle": "big"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginLeft: "0",
	      color: "#ffffff",
	      fontSize: "30",
	      marginTop: "30",
	      fontWeight: "bold"
	    }
	  }, [_vm._v("加载中...")])], 1) : _vm._e()], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 256:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["home-container"]
	  }, [_c('flist', {
	    ref: "loadDown",
	    attrs: {
	      "columnCount": 1,
	      "pageSize": _vm.pageSize,
	      "background": "#eeeeee"
	    },
	    on: {
	      "load": _vm.__requestData
	    }
	  }, [_c('div', {
	    slot: "head"
	  }, [_c('div', [_c('slider', {
	    staticClass: ["slider"],
	    attrs: {
	      "interval": "3000",
	      "autoPlay": "true"
	    }
	  }, _vm._l((_vm.imageList), function(img) {
	    return _c('div', {
	      staticClass: ["frame"]
	    }, [_c('image', {
	      staticClass: ["image"],
	      attrs: {
	        "resize": "cover",
	        "src": 'http://10.39.1.237:8081/cdtp/file/show/' + img.pictureUrl
	      }
	    })])
	  }))]), _c('div', {
	    staticClass: ["search_box"],
	    staticStyle: {
	      width: "750px",
	      height: "60px",
	      justifyContent: "center",
	      position: "absolute",
	      top: "50px",
	      left: "0"
	    }
	  }, [_c('div', {
	    staticClass: ["search"],
	    staticStyle: {
	      flexDirection: "row",
	      height: "60px",
	      backgroundColor: "#fff",
	      width: "690px",
	      borderRadius: "8px",
	      marginLeft: "30px"
	    },
	    on: {
	      "click": _vm.search_click
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "30px",
	      height: "30px",
	      marginLeft: "20px",
	      marginTop: "16px"
	    },
	    attrs: {
	      "src": "../images/6.3.1/iconSearch.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      lineHeight: "60px",
	      color: "#999999",
	      fontSize: "24px",
	      marginLeft: "12px"
	    }
	  }, [_vm._v("搜索感兴趣的任务")])])]), _c('div', {
	    staticClass: ["item"],
	    staticStyle: {
	      marginLeft: "0"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      color: "#333",
	      width: "750px",
	      marginBottom: "10px",
	      marginLeft: "25px"
	    }
	  }, [_vm._v("服务模式")]), _vm._l((_vm.itemList), function(item, index) {
	    return _c('div', {
	      staticClass: ["itemWrap"],
	      on: {
	        "click": function($event) {
	          _vm.jump(item, index)
	        }
	      }
	    }, [_c('image', {
	      staticClass: ["img"],
	      staticStyle: {
	        width: "109px",
	        height: "106px"
	      },
	      attrs: {
	        "src": item.src
	      }
	    }), _c('text', {
	      staticStyle: {
	        fontSize: "22px",
	        marginTop: "16px"
	      }
	    }, [_vm._v(_vm._s(item.title))])])
	  }), _c('div', {
	    staticStyle: {
	      width: "750",
	      height: "1",
	      backgroundColor: "#eee",
	      marginLeft: "-40",
	      marginTop: "25px"
	    }
	  }), _c('div', {
	    staticClass: ["message"]
	  }, [_c('image', {
	    staticClass: ["img_message"],
	    staticStyle: {
	      width: "155px",
	      height: "43px"
	    },
	    attrs: {
	      "src": "../images/newMessage.png"
	    }
	  }), _c('looper', {
	    staticStyle: {
	      width: "500px",
	      height: "50px",
	      lineHeight: "100px",
	      marginLeft: "10px",
	      overflow: "hidden",
	      textOverflow: "ellipsis",
	      whiteSpace: "nowrap"
	    },
	    attrs: {
	      "interval": "5",
	      "color": "#000000",
	      "fontSize": "30",
	      "textAlign": "left",
	      "data": ['王玉锁：希望中美共建天然气', '王玉锁：希望中美共建天然气']
	    }
	  })], 1)], 2), _c('div', {
	    staticClass: ["item"],
	    staticStyle: {
	      marginTop: "20px"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      color: "#333",
	      width: "750px",
	      marginBottom: "10px",
	      marginLeft: "25px"
	    }
	  }, [_vm._v("行业分类")]), _vm._l((_vm.IndustryCategoryList), function(item, index) {
	    return _c('div', {
	      staticClass: ["itemWrap"],
	      on: {
	        "click": function($event) {
	          _vm.push(item, index)
	        }
	      }
	    }, [_c('image', {
	      staticClass: ["img"],
	      staticStyle: {
	        width: "60px",
	        height: "60px"
	      },
	      attrs: {
	        "src": item.src
	      }
	    }), _c('text', {
	      staticStyle: {
	        fontSize: "22px",
	        marginTop: "16px"
	      }
	    }, [_vm._v(_vm._s(item.industryDefinition))])])
	  }), _c('div', {
	    staticClass: ["itemWrap"],
	    on: {
	      "click": function($event) {
	        _vm.push1()
	      }
	    }
	  }, [_c('image', {
	    staticClass: ["img"],
	    staticStyle: {
	      width: "60px",
	      height: "60px"
	    },
	    attrs: {
	      "src": "../images/design4.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "22px",
	      marginTop: "16px"
	    }
	  }, [_vm._v("查看全部")])])], 2), _c('div', {
	    staticClass: ["task_send"],
	    staticStyle: {
	      flexDirection: "row",
	      justifyContent: "space-between",
	      marginBottom: "16px"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      width: "339px",
	      height: "160px",
	      backgroundColor: "#fff",
	      borderRadius: "5px",
	      marginLeft: "25px",
	      marginTop: "24px"
	    },
	    on: {
	      "click": function($event) {
	        _vm.push2()
	      }
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "106px",
	      height: "106px",
	      marginLeft: "32px",
	      marginTop: "32px"
	    },
	    attrs: {
	      "src": "../images/farenwu.png"
	    }
	  }), _c('div', {
	    staticStyle: {
	      marginLeft: "34px",
	      marginTop: "34px"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333",
	      marginBottom: "8px"
	    }
	  }, [_vm._v("发布任务")]), _c('text', {
	    staticStyle: {
	      fontSize: "26px",
	      color: "#999",
	      width: "140px",
	      flexDirection: "row",
	      flexWrap: "nowrap"
	    }
	  }, [_vm._v("填单发布需求和任务")])])]), _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      width: "339px",
	      height: "160px",
	      backgroundColor: "#fff",
	      borderRadius: "5px",
	      marginRight: "25px",
	      marginTop: "24px"
	    },
	    on: {
	      "click": function($event) {
	        _vm.push3()
	      }
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "106px",
	      height: "106px",
	      marginLeft: "32px",
	      marginTop: "32px"
	    },
	    attrs: {
	      "src": "../images/shequ.png"
	    }
	  }), _c('div', {
	    staticStyle: {
	      marginLeft: "34px",
	      marginTop: "34px"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333",
	      marginBottom: "8px"
	    }
	  }, [_vm._v("论坛")]), _c('text', {
	    staticStyle: {
	      fontSize: "26px",
	      color: "#999",
	      width: "140px",
	      flexDirection: "row",
	      flexWrap: "nowrap"
	    }
	  }, [_vm._v("行业信息经验技术交流")])])])]), _c('div', {
	    staticClass: ["title"],
	    staticStyle: {
	      width: "700px",
	      height: "70px",
	      marginLeft: "25px",
	      flexDirection: "row",
	      alignItems: "center",
	      justifyContent: "space-between"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "30px"
	    }
	  }, [_vm._v("推荐任务")]), _c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#9A9A9A",
	      fontSize: "28"
	    },
	    on: {
	      "click": _vm.jumpto
	    }
	  }, [_vm._v("任务大厅 ")]), _c('image', {
	    staticStyle: {
	      width: "14px",
	      height: "22px",
	      marginTop: "4px",
	      marginLeft: "10px"
	    },
	    attrs: {
	      "src": "../images/home/right.png"
	    }
	  })])])]), _vm._l((_vm.lists), function(item) {
	    return _c('cell', {
	      staticClass: ["cell"],
	      appendAsTree: true,
	      attrs: {
	        "append": "tree"
	      },
	      on: {
	        "click": function($event) {
	          _vm.jumptask(item)
	        }
	      },
	      slot: "cell"
	    }, [_c('div', {
	      staticClass: ["panel"]
	    }, [_c('div', {
	      staticClass: ["top"],
	      staticStyle: {
	        height: "107px",
	        paddingLeft: "25px",
	        paddingTop: "20px"
	      }
	    }, [_c('text', {
	      staticClass: ["text1"],
	      staticStyle: {
	        width: "652px",
	        fontSize: "30px",
	        color: "#333333",
	        marginBottom: "24px"
	      }
	    }, [_vm._v(_vm._s(item.taskName))]), _c('div', {
	      staticStyle: {
	        flexDirection: "row",
	        justifyContent: "flex-start",
	        fontSize: "26px"
	      }
	    }, [_c('text', {
	      staticStyle: {
	        fontSize: "26px",
	        color: "#888"
	      }
	    }, [_vm._v("项目类型：")]), _c('text', {
	      staticClass: ["text"],
	      staticStyle: {
	        fontSize: "26px",
	        color: "#888"
	      }
	    }, [_vm._v(_vm._s(item.designStage))]), _c('text', {
	      staticStyle: {
	        fontSize: "26px",
	        color: "#888"
	      }
	    }, [_vm._v("｜")]), _c('text', {
	      staticStyle: {
	        fontSize: "26px",
	        color: "#888"
	      }
	    }, [_vm._v("项目地点：")]), _c('text', {
	      staticClass: ["text"],
	      staticStyle: {
	        fontSize: "26px",
	        color: "#888"
	      }
	    }, [_vm._v(_vm._s(item.demandCity))])])]), _c('div', {
	      staticClass: ["botm"],
	      staticStyle: {
	        flexDirection: "row",
	        justifyContent: "flex-start",
	        alignItems: "center"
	      }
	    }, [_c('div', {
	      staticClass: ["com"],
	      staticStyle: {
	        flexDirection: "row"
	      }
	    }, [_c('image', {
	      staticStyle: {
	        width: "33px",
	        height: "33px",
	        fontSize: "26px",
	        marginLeft: "30px"
	      },
	      attrs: {
	        "src": "../images/amount.png"
	      }
	    }), _vm._v(" "), _c('text', {
	      staticStyle: {
	        color: "#FF9602",
	        fontSize: "26px"
	      }
	    }, [_vm._v(_vm._s(item.managePrice) + "万元  ")])]), _c('div', {
	      staticClass: ["com"],
	      staticStyle: {
	        flexDirection: "row",
	        marginLeft: "30px"
	      }
	    }, [_c('image', {
	      staticStyle: {
	        width: "33px",
	        height: "33px"
	      },
	      attrs: {
	        "src": "../images/process.png"
	      }
	    }), _vm._v(" "), _c('text', {
	      staticStyle: {
	        color: "#878787",
	        fontSize: "26px"
	      }
	    }, [_vm._v(_vm._s(item.xmsysj) + "天    ")])]), _c('div', {
	      staticClass: ["com"],
	      staticStyle: {
	        flexDirection: "row",
	        marginTop: "-5px"
	      }
	    }, [_c('text', {
	      staticStyle: {
	        color: "#878787",
	        fontSize: "26px",
	        marginLeft: "50px"
	      }
	    }, [_vm._v(_vm._s(item.bmjz < 0 ? 0 : item.bmjz) + "天后结束  ")])]), _c('text', {
	      staticStyle: {
	        color: "#45C904",
	        fontSize: "26px",
	        marginTop: "-5px",
	        marginLeft: "20px"
	      }
	    }, [_vm._v(_vm._s(item.taskState))])])])])
	  })], 2)], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });