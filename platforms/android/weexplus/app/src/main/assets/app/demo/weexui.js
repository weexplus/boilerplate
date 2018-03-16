// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 498);
/******/ })
/************************************************************************/
/******/ ({

/***/ 498:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(499)
)
__vue_styles__.push(__webpack_require__(500)
)

/* script */
__vue_exports__ = __webpack_require__(501)

/* template */
var __vue_template__ = __webpack_require__(508)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/weexui.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-5251f421"
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

/***/ 499:
/***/ (function(module, exports) {

module.exports = {
  "cl": {
    "alignItems": "center"
  }
}

/***/ }),

/***/ 5:
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

/***/ 500:
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
    "width": 200,
    "marginTop": 50,
    "borderRadius": 10,
    "alignItems": "center",
    "justifyContent": "center",
    "backgroundColor:active": "#006ce7"
  }
}

/***/ }),

/***/ 501:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _wxcButton = __webpack_require__(502);

var _wxcButton2 = _interopRequireDefault(_wxcButton);

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


var head = __webpack_require__(9);
//    var WxcButton=require('./component/packages/wxc-button')
exports.default = {
    components: { head: head, WxcButton: _wxcButton2.default },
    data: function data() {
        return {
            back: ""
        };
    },

    methods: {
        read: function read() {
            var _this = this;

            var r = weex.requireModule("addressBook");
            r.read(function (res) {
                _this.back = res;
            });
        }
    },
    created: function created() {}
};

/***/ }),

/***/ 502:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = __webpack_require__(503);

Object.defineProperty(exports, 'default', {
  enumerable: true,
  get: function get() {
    return _interopRequireDefault(_index).default;
  }
});

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/***/ }),

/***/ 503:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(504)
)

/* script */
__vue_exports__ = __webpack_require__(505)

/* template */
var __vue_template__ = __webpack_require__(507)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/node_modules/weex-ui/packages/wxc-button/index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-25e2c7f3"
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

/***/ 504:
/***/ (function(module, exports) {

module.exports = {
  "wxc-btn": {
    "width": "702",
    "height": "88",
    "alignItems": "center",
    "justifyContent": "center",
    "borderRadius": "12"
  },
  "btn-text": {
    "textOverflow": "ellipsis",
    "lines": 1,
    "fontSize": "36",
    "color": "#ffffff"
  }
}

/***/ }),

/***/ 505:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; }; //
//
//
//
//
//
//
//
//
//
//
//
//

var _type = __webpack_require__(506);

exports.default = {
  props: {
    text: {
      type: String,
      default: '确认'
    },
    type: {
      type: String,
      default: 'red'
    },
    disabled: {
      type: Boolean,
      default: false
    },
    btnStyle: Object,
    textStyle: Object
  },
  computed: {
    mrBtnStyle: function mrBtnStyle() {
      var type = this.type,
          disabled = this.disabled,
          btnStyle = this.btnStyle;

      var mrBtnStyle = _extends({}, _type.STYLE_MAP[type], btnStyle);
      return disabled ? _extends({}, mrBtnStyle, {
        backgroundColor: 'rgba(0, 0, 0, 0.1)',
        borderWidth: 0
      }) : mrBtnStyle;
    },
    mrTextStyle: function mrTextStyle() {
      var type = this.type,
          disabled = this.disabled,
          textStyle = this.textStyle;

      var mrTextStyle = _extends({}, _type.TEXT_STYLE_MAP[type], textStyle);
      return disabled ? _extends({}, mrTextStyle, { color: '#FFFFFF' }) : mrTextStyle;
    }
  },
  methods: {
    onClicked: function onClicked(e) {
      var type = this.type,
          disabled = this.disabled;

      this.$emit('wxcButtonClicked', { e: e, type: type, disabled: disabled });
    }
  }
};

/***/ }),

/***/ 506:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
var STYLE_MAP = exports.STYLE_MAP = {
  red: {
    backgroundColor: '#FF5000'
  },
  yellow: {
    backgroundColor: '#FFC900'
  },
  normal: {
    backgroundColor: '#FFFFFF',
    borderColor: '#A5A5A5',
    borderWidth: '1px'
  },
  highlight: {
    backgroundColor: '#FFFFFF',
    borderColor: '#EE9900',
    borderWidth: '1px'
  }
};

var TEXT_STYLE_MAP = exports.TEXT_STYLE_MAP = {
  taobao: {
    color: '#FFFFFF'
  },
  fliggy: {
    color: '#3D3D3D'
  },
  normal: {
    color: '#3D3D3D'
  },
  highlight: {
    color: '#EE9900'
  }
};

/***/ }),

/***/ 507:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["wxc-btn"],
    style: _vm.mrBtnStyle,
    attrs: {
      "accessible": true,
      "ariaLabel": _vm.text
    },
    on: {
      "click": _vm.onClicked
    }
  }, [_c('text', {
    staticClass: ["btn-text"],
    style: _vm.mrTextStyle
  }, [_vm._v(_vm._s(_vm.text))])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),

/***/ 508:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('head', {
    attrs: {
      "title": "weex-ui"
    }
  }), _c('wxc-button', {
    attrs: {
      "text": "Open Popup"
    },
    on: {
      "wxcButtonClicked": _vm.buttonClicked
    }
  })], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),

/***/ 6:
/***/ (function(module, exports) {

module.exports = {
  "layout": {
    "backgroundColor": "#333333",
    "height": 128,
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "center"
  }
}

/***/ }),

/***/ 7:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


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
    //        watch: {
    //
    //
    //            disabled:{
    //                immediate: true,
    //                handler (val) {
    //
    //                }
    //            }
    //        }
};

/***/ }),

/***/ 8:
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

/***/ 9:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(5)
)
__vue_styles__.push(__webpack_require__(6)
)

/* script */
__vue_exports__ = __webpack_require__(7)

/* template */
var __vue_template__ = __webpack_require__(8)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/header.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-25b44859"
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


/***/ })

/******/ });