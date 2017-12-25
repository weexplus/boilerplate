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
	__vue_styles__.push(__webpack_require__(223)
	)

	/* script */
	__vue_exports__ = __webpack_require__(224)

	/* template */
	var __vue_template__ = __webpack_require__(225)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/pref.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-0e19dd0c"
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

/***/ 117:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(118)
	)
	__vue_styles__.push(__webpack_require__(119)
	)

	/* script */
	__vue_exports__ = __webpack_require__(120)

	/* template */
	var __vue_template__ = __webpack_require__(121)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/header.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-9a3f0db8"
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

/***/ 118:
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

/***/ 119:
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

/***/ 120:
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
	            this.$emit('backClick');
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

/***/ 121:
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

/***/ 223:
/***/ (function(module, exports) {

	module.exports = {
	  "text": {
	    "fontSize": 50
	  },
	  "btn": {
	    "backgroundColor": "#0085ee",
	    "height": 100,
	    "marginTop": 50,
	    "marginLeft": 50,
	    "marginRight": 50,
	    "borderRadius": 10,
	    "alignItems": "center",
	    "justifyContent": "center",
	    "backgroundColor:active": "#006ce7"
	  }
	}

/***/ }),

/***/ 224:
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

	var head = __webpack_require__(117);
	exports.default = {
	    components: { head: head },
	    data: function data() {
	        return {
	            text: '',
	            param: '',
	            data: {}
	        };
	    },

	    methods: {
	        save: function save() {
	            var pref = weex.requireModule("pref");
	            pref.set('key', this.text);
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: '存储成功' });
	        },
	        get: function get() {
	            var pref = weex.requireModule("pref");
	            var s = pref.get('key');
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: '存储成功的值' + s });
	        },
	        remove: function remove() {
	            var pref = weex.requireModule("pref");
	            pref.remove('key');
	            pref.remove('objkey');
	            var s = pref.get('key');
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: '删除成功：' + s });
	        },
	        saveObj: function saveObj() {
	            var pref = weex.requireModule("pref");
	            var obj = {};
	            obj.a = 1;
	            obj.b = 2;
	            pref.setObj('objkey', obj);
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: '存储成功' });
	        },
	        getObj: function getObj() {
	            var pref = weex.requireModule("pref");
	            var p = pref.getObj('objkey');
	            this.data = p;
	            //              var modal=weex.requireModule("modal")
	            //              modal.toast({message:p});
	        }
	    },

	    created: function created() {

	        var self = this;
	        var globalEvent = weex.requireModule('globalEvent');
	        globalEvent.addEventListener("onPageInit", function (e) {});
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 225:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', [_c('head', {
	    appendAsTree: true,
	    attrs: {
	      "title": "固定存储类似于cookie",
	      "append": "tree"
	    }
	  }), _c('input', {
	    staticStyle: {
	      width: "750",
	      paddingLeft: "10",
	      height: "100",
	      color: "#000000"
	    },
	    attrs: {
	      "type": "text",
	      "placeholder": "请输入要存储的值",
	      "value": (_vm.text)
	    },
	    on: {
	      "input": function($event) {
	        _vm.text = $event.target.attr.value
	      }
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#000000"
	    }
	  }, [_vm._v(_vm._s(_vm.data))]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": _vm.save
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("存储字符串")])]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": _vm.get
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("获取字符串")])]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": _vm.remove
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("删除")])]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": _vm.saveObj
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("存储对象{a:1}")])]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": _vm.getObj
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("获取对象")])])], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });