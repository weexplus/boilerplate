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
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(4)
	)

	/* script */
	__vue_exports__ = __webpack_require__(5)

	/* template */
	var __vue_template__ = __webpack_require__(6)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/PublishDemandGroup.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-092d972c"
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
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */
/***/ (function(module, exports) {

	module.exports = {
	  "top-bar": {
	    "position": "relative",
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "paddingLeft": 70,
	    "paddingRight": 70,
	    "backgroundColor": "#16ADF6",
	    "borderBottomWidth": 1,
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#eeeeee"
	  },
	  "back": {
	    "position": "absolute",
	    "left": 0,
	    "top": 0,
	    "width": 108,
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "color": "#ffffff"
	  },
	  "back-text": {
	    "color": "#999999",
	    "fontSize": 24
	  },
	  "title-name": {
	    "color": "#ffffff",
	    "fontSize": 36
	  },
	  "home-container": {
	    "width": 750,
	    "fontSize": 30,
	    "textAlign": "center",
	    "backgroundColor": "#eeeeee"
	  },
	  "item_list": {
	    "position": "relative",
	    "paddingLeft": 20,
	    "paddingBottom": 12,
	    "paddingTop": 13,
	    "flexDirection": "row",
	    "alignItems": "flex-start",
	    "backgroundColor": "#ffffff",
	    "marginBottom": 16
	  },
	  "item_list_txt": {
	    "color": "#333333",
	    "fontSize": 32,
	    "lineHeight": 66,
	    "width": 183
	  },
	  "slect_text": {
	    "color": "#333333",
	    "fontSize": 32,
	    "lineHeight": 64
	  },
	  "item_list_input": {
	    "color": "#333333",
	    "fontSize": 32,
	    "lineHeight": 64,
	    "paddingLeft": 26,
	    "borderWidth": 1,
	    "borderStyle": "solid",
	    "borderColor": "#cdcdcd",
	    "borderRadius": 5,
	    "width": 506
	  },
	  "item_list_select": {
	    "color": "#333333",
	    "fontSize": 32,
	    "lineHeight": 64,
	    "height": 64,
	    "paddingLeft": 26,
	    "borderWidth": 1,
	    "borderStyle": "solid",
	    "borderColor": "#cdcdcd",
	    "borderRadius": 5,
	    "width": 506,
	    "position": "relative"
	  },
	  "item_list_select_arrow": {
	    "width": 20,
	    "height": 10,
	    "position": "absolute",
	    "right": 30,
	    "top": 26
	  },
	  "item_list_area": {
	    "color": "#333333",
	    "fontSize": 32,
	    "lineHeight": 66,
	    "paddingLeft": 20,
	    "paddingRight": 20,
	    "paddingTop": 10,
	    "paddingBottom": 10,
	    "marginLeft": 10,
	    "borderWidth": 1,
	    "borderStyle": "solid",
	    "borderColor": "#cdcdcd",
	    "borderRadius": 5,
	    "width": 690,
	    "height": 146,
	    "marginBottom": 22
	  },
	  "contract_item": {
	    "height": 29,
	    "borderWidth": 1,
	    "borderStyle": "solid",
	    "borderColor": "#17acf6",
	    "borderRadius": 29,
	    "marginRight": 14,
	    "paddingRight": 14,
	    "paddingLeft": 14
	  },
	  "contract_item_txt1": {
	    "lineHeight": 29,
	    "alignItems": "center",
	    "color": "#17acf6",
	    "fontSize": 17
	  },
	  "btn_box": {
	    "marginTop": 58,
	    "marginBottom": 42,
	    "flexDirection": "row",
	    "justifyContent": "center",
	    "alignItems": "center"
	  },
	  "btn": {
	    "justifyContent": "center",
	    "alignItems": "center",
	    "width": 290,
	    "height": 80,
	    "backgroundColor": "#18ACF4",
	    "borderRadius": 80
	  },
	  "btn_concel": {
	    "justifyContent": "center",
	    "alignItems": "center",
	    "width": 288,
	    "height": 78,
	    "borderRadius": 80,
	    "marginLeft": 30,
	    "borderWidth": 1,
	    "borderStyle": "solid",
	    "borderColor": "#18ACF4"
	  },
	  "contract_box": {
	    "flexDirection": "row",
	    "justifyContent": "flex-start"
	  },
	  "contract_more_box": {
	    "flexDirection": "row",
	    "justifyContent": "center",
	    "marginBottom": 20
	  },
	  "sure_box": {
	    "flexDirection": "row",
	    "justifyContent": "flex-start",
	    "marginLeft": 30
	  },
	  "text_grey": {
	    "fontSize": 20,
	    "color": "#666666"
	  },
	  "text_blue": {
	    "fontSize": 24,
	    "color": "#18ACF4"
	  },
	  "b1": {
	    "bottom": -10000
	  },
	  "b0": {
	    "bottom": 0
	  }
	}

/***/ }),
/* 5 */
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


	var modal = weex.requireModule('modal');

	var selectName, selectXuqiu, selectDidian, selectIndustry, selectProType;
	exports.default = {
	  components: {},
	  data: function data() {
	    return {
	      contract_item: 'contract_item2',
	      contracts: [{ title: '土木工程', state: false }, { title: '园林设计', state: false }, { title: '建筑学', state: false }, { title: '建筑学', state: false }, { title: '建筑学', state: false }],
	      default1: {
	        name: ['周xx', '李xx', '王xx'],
	        xuqiu: ['需求1', '需求2', '需求3'],
	        proType: ['项目类型1', '项目类型2', '项目类型3', '项目类型4'],
	        industry: ['行业1', '行业2', '行业3', '行业4']
	      },
	      pickerValue: {
	        name: '',
	        xuqiu: '',
	        didian: '',
	        industry: '',
	        proType: ''
	      },
	      areaMap: [{ "id": 1, "name": "河北省", "children": [{ "id": 1, "name": "保定市" }, { "id": 2, "name": "石家庄" }, { "id": 3, "name": "廊坊" }] }, { "id": 2, "name": "辽宁省", "children": [{ "id": 1, "name": "沈阳市" }, { "id": 2, "name": "大连市" }, { "id": 3, "name": "阜新市" }] }, { "id": 3, "name": "山西省", "children": [{ "id": 1, "name": "大同市" }, { "id": 2, "name": "太原市" }, { "id": 3, "name": "吕梁市" }] }],
	      province: ['河北', '北京', '上海'],
	      city: ['保定', '廊坊', '石家庄']

	    };
	  },
	  methods: {
	    contractClick: function contractClick(item) {
	      modal.alert({
	        message: item,
	        duration: 5
	      });
	    },
	    change1: function change1(x) {
	      this.ss = x.value;
	    },
	    done: function done(x) {
	      this.default1[this.index] = this.ss;
	    },
	    nameSelect: function nameSelect() {
	      this.$refs.layout.show();
	      this.index = 0;
	    },
	    toArray: function toArray(list) {
	      var p = [];
	      for (var i = 0; i < list.length; i++) {
	        p.push(list[i].name);
	      }
	      return p;
	    },
	    nameShowpicker: function nameShowpicker() {
	      //联系人选择
	      var index1 = 0;
	      var _this = this;
	      var m = weex.requireModule('modal');
	      if (selectName == undefined) {
	        selectName = weex.requireModule('fpicker');
	      }
	      selectName.setCount(1);
	      selectName.setItems1(_this.default1.name);
	      selectName.setDone(function (e) {
	        var p1 = _this.default1.name[e.index1];
	        _this.pickerValue.name = p1;
	      });
	      selectName.show();
	    },
	    xuqiuShowpicker: function xuqiuShowpicker() {
	      //需求选择
	      var index1 = 0;
	      var _this = this;
	      var m = weex.requireModule('modal');
	      if (selectXuqiu == undefined) {
	        selectXuqiu = weex.requireModule('fpicker');
	      }
	      selectXuqiu.setCount(1);
	      selectXuqiu.setItems1(_this.default1.xuqiu);
	      selectXuqiu.setDone(function (e) {
	        var p1 = _this.default1.xuqiu[e.index1];
	        _this.pickerValue.xuqiu = p1;
	      });
	      selectXuqiu.show();
	    },
	    areaShowpicker: function areaShowpicker() {
	      //地区选择
	      var index1 = 0;
	      var index2 = 0;
	      var _this = this;
	      var m = weex.requireModule('modal');
	      if (selectDidian == undefined) {
	        selectDidian = weex.requireModule('fpicker');
	        // modal.alert({message:'wwww'})
	        // selectDidian2.setCount(2)
	      }
	      selectDidian.setCount(2);
	      selectDidian.setItems1(_this.toArray(_this.areaMap));
	      selectDidian.setItems2(_this.toArray(_this.areaMap[index2].children));
	      selectDidian.setChange(function (e) {
	        index1 = e.index;
	        selectDidian.setItems2(_this.toArray(_this.areaMap[index1].children));
	        /*picker.select(2,0);*/
	      }, function (e) {
	        index2 = e.index;
	      });
	      selectDidian2.setDone(function (e) {
	        var p1 = _this.areaMap[e.index1].name;
	        var p2 = _this.areaMap[e.index1].children[e.index2].name;
	        _this.pickerValue.didian = p1 + '  ' + p2;
	        modal.alert({ message: _this.pickerValue.didian + p1 + p2 });
	      });
	      selectDidian.show();
	    },
	    industryShowpicker: function industryShowpicker() {
	      //行业选择
	      var index1 = 0;
	      var _this = this;
	      var m = weex.requireModule('modal');
	      if (selectIndustry == undefined) {
	        selectIndustry = weex.requireModule('fpicker');
	      }
	      selectIndustry.setCount(1);
	      selectIndustry.setItems1(_this.default1.industry);
	      selectIndustry.setDone(function (e) {
	        var p1 = _this.default1.industry[e.index1];
	        _this.pickerValue.industry = p1;
	      });
	      selectIndustry.show();
	    },
	    protypeShowpicker: function protypeShowpicker() {
	      //项目类型选择
	      var index1 = 0;
	      var _this = this;
	      var m = weex.requireModule('modal');
	      if (selectProType == undefined) {
	        selectProType = weex.requireModule('fpicker');
	      }
	      selectProType.setCount(1);
	      selectProType.setItems1(_this.default1.proType);
	      selectProType.setDone(function (e) {
	        var p1 = _this.default1.proType[e.index1];
	        _this.pickerValue.proType = p1;
	      });
	      selectProType.show();
	    },

	    submit: function submit() {
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
	};
	module.exports = exports['default'];

/***/ }),
/* 6 */
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', [_c('div', {
	    staticClass: ["home-container"]
	  }, [_c('div', {
	    staticClass: ["content"]
	  }, [_c('div', {
	    staticClass: ["item_content"]
	  }, [_c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("联系人：")]), _c('div', {
	    staticClass: ["item_list_select"],
	    on: {
	      "click": _vm.nameShowpicker
	    }
	  }, [_c('text', {
	    staticClass: ["slect_text"]
	  }, [_vm._v(_vm._s(_vm.pickerValue.name))]), _c('image', {
	    staticClass: ["item_list_select_arrow"],
	    attrs: {
	      "src": "../images/xiala.png"
	    }
	  })])]), _vm._m(0), _vm._m(1), _vm._m(2), _vm._m(3), _vm._m(4), _vm._m(5), _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("项目需求：")]), _c('div', {
	    staticClass: ["item_list_select"],
	    on: {
	      "click": _vm.xuqiuShowpicker
	    }
	  }, [_c('text', {
	    staticClass: ["slect_text"]
	  }, [_vm._v(_vm._s(_vm.pickerValue.xuqiu))]), _c('image', {
	    staticClass: ["item_list_select_arrow"],
	    attrs: {
	      "src": "../images/xiala.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("建设地点：")]), _c('div', {
	    staticClass: ["item_list_select"],
	    on: {
	      "click": _vm.areaShowpicker
	    }
	  }, [_c('text', {
	    staticClass: ["slect_text"]
	  }, [_vm._v(_vm._s(_vm.pickerValue.didian))]), _c('image', {
	    staticClass: ["item_list_select_arrow"],
	    attrs: {
	      "src": "../images/xiala.png"
	    }
	  })])]), _vm._m(6), _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("行业：")]), _c('div', {
	    staticClass: ["item_list_select"],
	    on: {
	      "click": _vm.industryShowpicker
	    }
	  }, [_c('text', {
	    staticClass: ["slect_text"]
	  }, [_vm._v(_vm._s(_vm.pickerValue.industry))]), _c('image', {
	    staticClass: ["item_list_select_arrow"],
	    attrs: {
	      "src": "../images/xiala.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("项目类型：")]), _c('div', {
	    staticClass: ["item_list_select"],
	    on: {
	      "click": _vm.protypeShowpicker
	    }
	  }, [_c('text', {
	    staticClass: ["slect_text"]
	  }, [_vm._v(_vm._s(_vm.pickerValue.proType))]), _c('image', {
	    staticClass: ["item_list_select_arrow"],
	    attrs: {
	      "src": "../images/xiala.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('div', {
	    staticClass: ["contract_box"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("合同范围：")]), _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      alignItems: "flex-start",
	      marginTop: "18px"
	    }
	  }, _vm._l((_vm.contracts), function(contract) {
	    return _c('div', {
	      staticClass: ["contract_item"],
	      staticStyle: {
	        color: "red"
	      },
	      attrs: {
	        "data": "false"
	      },
	      on: {
	        "click": function($event) {
	          _vm.contractClick($event)
	        }
	      }
	    }, [_c('text', {
	      staticClass: ["contract_item_txt1"]
	    }, [_vm._v(_vm._s(contract.title))])])
	  }))]), _vm._m(7)]), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("项目规模：")]), _c('textarea', {
	    staticClass: ["item_list_area"]
	  })], 1), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('text', {
	    staticClass: ["item_list_txt"],
	    staticStyle: {
	      width: "500px"
	    }
	  }, [_vm._v("项目建设计划：")]), _c('textarea', {
	    staticClass: ["item_list_area"]
	  })], 1), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('text', {
	    staticClass: ["item_list_txt"],
	    staticStyle: {
	      width: "500px"
	    }
	  }, [_vm._v("设计进度要求：")]), _c('textarea', {
	    staticClass: ["item_list_area"]
	  })], 1), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("其他要求：")]), _c('textarea', {
	    staticClass: ["item_list_area"]
	  })], 1), _vm._m(8), _c('div', {
	    staticClass: ["btn_box"]
	  }, [_c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": function($event) {
	        _vm.submit()
	      }
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "36px",
	      color: "#fff",
	      lineHeight: "80px"
	    }
	  }, [_vm._v("保存")])]), _c('div', {
	    staticClass: ["btn_concel"],
	    on: {
	      "click": function($event) {
	        _vm.submit()
	      }
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "36px",
	      color: "#18ACF4",
	      lineHeight: "78px"
	    }
	  }, [_vm._v("发布")])])])])])])])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("联系电话：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    attrs: {
	      "type": "tel",
	      "placeholder": "请输入联系电话..."
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("邮箱：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    attrs: {
	      "type": "text"
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("传真：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    attrs: {
	      "type": "text"
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"],
	    staticStyle: {
	      width: "223px"
	    }
	  }, [_vm._v("项目建设单位：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    staticStyle: {
	      width: "466px"
	    },
	    attrs: {
	      "type": "text"
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"],
	    staticStyle: {
	      width: "223px"
	    }
	  }, [_vm._v("建设单位地址：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    staticStyle: {
	      width: "466px"
	    },
	    attrs: {
	      "type": "text"
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("项目名称：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    attrs: {
	      "type": "text",
	      "placeholder": "请输入项目名称..."
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["item_list"]
	  }, [_c('text', {
	    staticClass: ["item_list_txt"]
	  }, [_vm._v("建设投资：")]), _c('input', {
	    staticClass: ["item_list_input"],
	    staticStyle: {
	      width: "430px",
	      marginRight: "26px"
	    },
	    attrs: {
	      "type": "text"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333",
	      lineHeight: "66px"
	    }
	  }, [_vm._v("万元")])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["contract_more_box"]
	  }, [_c('text', {
	    staticStyle: {
	      color: "#999",
	      fontSize: "32px",
	      borderBottomWidth: "1px",
	      borderStyle: "solid",
	      borderColor: "#999",
	      paddingBottom: "10px"
	    }
	  }, [_vm._v("查看更多")])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["sure_box"]
	  }, [_c('text', {
	    staticClass: ["text_blue"]
	  }, [_vm._v("添加附件")]), _c('text', {
	    staticClass: ["text_grey"]
	  }, [_vm._v("(附件大小不得超过10M)")])])
	}]}
	module.exports.render._withStripped = true

/***/ })
/******/ ]);