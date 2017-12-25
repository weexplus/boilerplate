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
	__vue_styles__.push(__webpack_require__(184)
	)

	/* script */
	__vue_exports__ = __webpack_require__(185)

	/* template */
	var __vue_template__ = __webpack_require__(187)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-4562640b"
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

/***/ 145:
/***/ (function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var host = 'http://59.110.169.246/movie/';
	// var host='http://192.168.1.101:8080/'


	exports.default = {

	    postShort: function postShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.postFull(weg, param, header, start, success, function (res) {
	            //fail
	            modal.toast({ message: res.msg });
	        }, function () {
	            //exception
	            modal.toast({ message: '网络异常！' });
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
	        var token = st.getString('token');
	        if (token != undefined && token != '') {
	            header.token = token;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.post(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            if (e.res.err == 0) {

	                success(e.res);
	            } else {
	                // modal.toast({message:e.res.msg})
	                if (token != undefined && token != '') {
	                    st.remove('token');
	                    return;
	                }
	                if (e.res.err == 1000) {
	                    // var nav=weex.requireModule("navigator")
	                    // nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	                    //     self.postFull(weg,param,header,start,success,fail,exception,compelete);

	                    // },true);
	                } else fail(e.res);
	            }
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

	    postSilent: function postSilent(weg, param, success) {

	        this.postFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    }

	};
	module.exports = exports['default'];

/***/ }),

/***/ 147:
/***/ (function(module, exports) {

	module.exports = {
	  "header": {
	    "backgroundColor": "#FF0000",
	    "flex": 1,
	    "flexDirection": "row"
	  },
	  "tz": {
	    "color": "#FF0000"
	  },
	  "logo": {
	    "width": 300,
	    "height": 300,
	    "marginTop": 80
	  },
	  "k1": {
	    "alignItems": "center"
	  },
	  "titleback": {
	    "flex": 1,
	    "alignItems": "center"
	  },
	  "title": {
	    "color": "#FFFFFF",
	    "flex": 1,
	    "marginTop": 73,
	    "fontWeight": "bold"
	  },
	  "leftimage": {
	    "width": 30,
	    "height": 45,
	    "bottom": 25,
	    "left": 30,
	    "position": "absolute"
	  },
	  "rightimage": {
	    "width": 45,
	    "height": 45,
	    "bottom": 23,
	    "right": 32,
	    "position": "absolute"
	  },
	  "bottomline": {
	    "height": 1,
	    "backgroundColor": "#000000",
	    "position": "absolute",
	    "bottom": 0,
	    "left": 0,
	    "right": 0,
	    "flex": 1
	  },
	  "btn": {
	    "backgroundColor": "#0085ee",
	    "height": 100,
	    "width": 500,
	    "marginTop": 50,
	    "borderRadius": 20,
	    "alignItems": "center",
	    "justifyContent": "center",
	    "backgroundColor:active": "#006ce7"
	  }
	}

/***/ }),

/***/ 148:
/***/ (function(module, exports) {

	module.exports = {
	  "layout": {
	    "backgroundColor": "#333333",
	    "height": 128,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  }
	}

/***/ }),

/***/ 149:
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


	exports.default = {
	    props: {
	        title: {
	            default: ''

	        },
	        back: {
	            default: true
	        },
	        bgcolor: {
	            default: '#222222'

	        },
	        isloading: {
	            default: false
	        },
	        disabled: {

	            default: false
	        },

	        type: {
	            type: String,
	            default: 'text'
	        },
	        font_size: {
	            default: 20
	        },
	        height: {
	            default: 128
	        },
	        top: {
	            default: 40
	        },
	        titletop: {
	            default: 10
	        }

	    },
	    data: function data() {
	        return {};
	    },

	    methods: {
	        titleClick: function titleClick() {
	            this.$emit('titleClick');
	        },
	        rightclick: function rightclick() {
	            this.$emit('rightClick');
	        },
	        backTo: function backTo() {
	            var nav = weex.requireModule("navigator");
	            nav.back();
	        },
	        onclick: function onclick() {
	            if (!this.disabled) this.$emit('onclick');
	        },
	        adjust: function adjust() {
	            if (weex.config.env.platform == 'android') {
	                //                    if(weex.config.env.osVersion=)
	                var p = weex.config.env.osVersion;
	                p = p.replace(/\./g, '');
	                if (p.length < 3) p = p + "0";
	                if (p <= '440') {
	                    this.height = 108;
	                    this.top = 16;
	                    this.titletop = 4;
	                }
	            }
	        }
	    },

	    created: function created() {

	        this.adjust();
	    },
	    ready: function ready() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 150:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["layout"],
	    style: {
	      'background-color': _vm.bgcolor,
	      'height': _vm.height
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    },
	    style: {
	      'top': _vm.titletop
	    }
	  }, [(_vm.isloading) ? _c('div', {
	    staticStyle: {
	      height: "40",
	      width: "40",
	      marginRight: "10"
	    }
	  }) : _vm._e(), _c('text', {
	    staticStyle: {
	      flex: "1",
	      color: "#ffffff",
	      textAlign: "center",
	      fontSize: "38"
	    },
	    on: {
	      "click": _vm.titleClick
	    }
	  }, [_vm._v(_vm._s(_vm.title))]), (_vm.isloading) ? _c('floading', {
	    staticStyle: {
	      height: "40",
	      width: "40",
	      marginLeft: "10",
	      marginTop: "5"
	    },
	    attrs: {
	      "color": "#ffffff",
	      "loadingStyle": "small"
	    }
	  }) : _vm._e()], 1), (_vm.back) ? _c('div', {
	    staticStyle: {
	      width: "200",
	      top: "40",
	      position: "absolute",
	      left: "0"
	    },
	    style: {
	      'height': _vm.height,
	      'top': _vm.top
	    },
	    on: {
	      "click": _vm.backTo
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "80",
	      height: "80"
	    },
	    attrs: {
	      "src": "root:img/back.png"
	    }
	  })]) : _vm._e(), _c('div', {
	    staticStyle: {
	      width: "200",
	      position: "absolute",
	      right: "0",
	      top: "0",
	      alignItems: "center",
	      justifyContent: "center"
	    },
	    style: {
	      'height': _vm.height
	    },
	    on: {
	      "click": _vm.rightclick
	    }
	  }, [_vm._t("right")], 2), _c('div', {
	    staticStyle: {
	      height: "1",
	      backgroundColor: "#111111",
	      position: "absolute",
	      bottom: "0",
	      left: "0",
	      right: "0"
	    }
	  })])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 184:
/***/ (function(module, exports) {

	module.exports = {
	  "title": {
	    "paddingTop": 40,
	    "paddingBottom": 40,
	    "fontSize": 48
	  },
	  "item_text": {
	    "marginLeft": 20,
	    "fontSize": 35
	  },
	  "item_flex": {
	    "flex": 1
	  },
	  "item_img": {
	    "width": 25,
	    "height": 40,
	    "marginRight": 20
	  },
	  "item": {
	    "height": 100,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "borderBottomColor": "#999999",
	    "borderBottomWidth": 1,
	    "backgroundColor:active": "#eeeeee"
	  },
	  "group": {
	    "marginTop": 30,
	    "marginLeft": 20,
	    "color": "#FF0000",
	    "fontSize": 40
	  }
	}

/***/ }),

/***/ 185:
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

	var head = __webpack_require__(186);
	var net = __webpack_require__(145);
	var p = undefined;
	exports.default = {
	    components: { head: head },
	    data: {
	        logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
	        target: 'World',
	        index: 0

	    },
	    methods: {
	        update: function update(e) {
	            this.target = 'Weex';
	            console.log('target:', this.target);
	        },
	        showpicker: function showpicker() {
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: 'ok' });
	        },
	        goton: function goton() {
	            var nav = weex.requireModule("navigator");
	            nav.push('Login.js');
	        },
	        change: function change(i) {

	            this.index = i;
	        },
	        show: function show() {
	            var modal = weex.requireModule("modal");
	            var p = weex.config.env.osVersion;
	            p = p.replace(/\./g, '');
	            modal.alert({ message: p });
	        }
	    },

	    created: function created() {

	        var globalEvent = weex.requireModule('globalEvent');
	        var self = this;
	        globalEvent.addEventListener("onPageInit", function (e) {

	            var nav = weex.requireModule('navbar');
	            //                nav.hide();

	            var navigator = weex.requireModule('navigator');
	            navigator.addBackGestureSelfControl();

	            nav.setStatusBarStyle('white');
	        });
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 186:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(147)
	)
	__vue_styles__.push(__webpack_require__(148)
	)

	/* script */
	__vue_exports__ = __webpack_require__(149)

	/* template */
	var __vue_template__ = __webpack_require__(150)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/component/header.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-b4f96a94"
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

/***/ 187:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', [_c('head', {
	    attrs: {
	      "title": "weexplus demo",
	      "back": false
	    }
	  }), _c('scroller', {
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('text', {
	    staticClass: ["group"]
	  }, [_vm._v("组件")]), _c('a', {
	    staticClass: ["item"],
	    staticStyle: {
	      borderTopWidth: "1",
	      borderTopColor: "#999999",
	      marginTop: "20"
	    },
	    attrs: {
	      "href": "./host.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("tab框架")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./drawlayout.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("侧滑框架")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./looper.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("text轮播器（looper）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./floading.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("小菊花组件（floading）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('text', {
	    staticClass: ["group"]
	  }, [_vm._v("模块")]), _c('a', {
	    staticClass: ["item"],
	    staticStyle: {
	      borderTopWidth: "1",
	      borderTopColor: "#999999",
	      marginTop: "20"
	    },
	    attrs: {
	      "href": "./navigator.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("导航")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./notify.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("通知")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./photo.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("相机相册（图片上传）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./static.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("静态存储（static）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./picker.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("选择器（picker）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./progress.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("无状态进度条（progress）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./qr.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("二维码（qr）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })]), _c('a', {
	    staticClass: ["item"],
	    attrs: {
	      "href": "./net.js"
	    }
	  }, [_c('text', {
	    staticClass: ["item_text"]
	  }, [_vm._v("网络访问（net）")]), _c('div', {
	    staticClass: ["item_flex"]
	  }), _c('image', {
	    staticClass: ["item_img"],
	    attrs: {
	      "src": "root:img/arrow.png"
	    }
	  })])], 1)], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });