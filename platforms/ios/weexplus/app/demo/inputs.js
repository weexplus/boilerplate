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
/******/ 	return __webpack_require__(__webpack_require__.s = 336);
/******/ })
/************************************************************************/
/******/ ({

/***/ 1:
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

/***/ 100:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(49)
)

/* script */
__vue_exports__ = __webpack_require__(50)

/* template */
var __vue_template__ = __webpack_require__(51)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/component/input.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-3960f0c8"
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

/***/ 2:
/***/ (function(module, exports) {

module.exports = {
  "layout": {
    "backgroundColor": "#333333",
    "height": 128,
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "center",
    "width": "750"
  }
}

/***/ }),

/***/ 3:
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

/***/ 336:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(337)
)
__vue_styles__.push(__webpack_require__(338)
)

/* script */
__vue_exports__ = __webpack_require__(339)

/* template */
var __vue_template__ = __webpack_require__(340)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/inputs.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-0387f9b5"
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

/***/ 337:
/***/ (function(module, exports) {

module.exports = {
  "it": {
    "width": 700,
    "height": 100,
    "marginTop": 10,
    "borderColor": "#006ce7",
    "borderWidth": 1,
    "borderRadius": 10
  }
}

/***/ }),

/***/ 338:
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

/***/ 339:
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


var head = __webpack_require__(5);
var cinput = __webpack_require__(100);
var globalEvent = weex.requireModule('globalEvent');
globalEvent.addEventListener("onPageInit", function (e) {});

exports.default = {
    components: { head: head, cinput: cinput },
    data: function data() {
        return {
            name: "111",
            age: ""
        };
    },

    methods: {
        set: function set() {
            //                this.name=this.name+'ss'
            this.$refs.inp.val('xx');
            this.$refs.inp.val('xx1');
            this.$refs.inp.val('x2');
            this.name = "xx";
        }
    },
    created: function created() {}
};

/***/ }),

/***/ 340:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      alignItems: "center",
      justifyContent: "center"
    }
  }, [_c('text', {
    staticStyle: {
      color: "#000000",
      fontSize: "32"
    }
  }, [_vm._v(_vm._s(_vm.name))]), _c('input', {
    ref: "inp",
    staticClass: ["it"],
    attrs: {
      "type": "input",
      "placeholder": "姓名",
      "value": (_vm.name)
    },
    on: {
      "input": function($event) {
        _vm.name = $event.target.attr.value
      }
    }
  }), _c('div', {
    staticStyle: {
      width: "100",
      height: "100",
      backgroundColor: "red"
    },
    on: {
      "click": _vm.set
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  }), _c('input', {
    staticClass: ["it"],
    attrs: {
      "placeholder": "姓名"
    }
  })])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),

/***/ 4:
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

/***/ 49:
/***/ (function(module, exports) {

module.exports = {
  "text": {
    "fontSize": "50",
    "textAlign": "center",
    "color": "#41B883"
  }
}

/***/ }),

/***/ 5:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(1)
)
__vue_styles__.push(__webpack_require__(2)
)

/* script */
__vue_exports__ = __webpack_require__(3)

/* template */
var __vue_template__ = __webpack_require__(4)
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


/***/ }),

/***/ 50:
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


exports.default = {
    props: {
        placeholder: {
            default: ''

        },
        placeholder_color: {
            default: '#ffffff'

        },
        color: {
            default: '#000000'

        },
        value: {
            default: ''
        },

        type: {
            type: String,
            default: 'text'
        },
        font_size: {
            default: 20
        },
        autofocus: {
            default: false
        },
        return_key_type: {
            default: 'defalut'
        }

    },
    data: function data() {
        return {

            pulldistance: 180,
            visiable: true

        };
    },

    methods: {
        onchange: function onchange(event) {
            this.visiable = !event.value == '';
            //                this.$emit('onchange',event.value);
            this.value = event.value;
            //                this.name="xxx"
        },
        onfocus: function onfocus() {
            this.$emit('focus');
        },
        focus: function focus() {
            this.$refs.input.focus();
        },
        blur: function blur() {
            this.$refs.input.blur();
            this.$emit('blur');
        },
        oninput: function oninput(e) {

            //                this.$emit('oninput');
            this.value = e.value;
            this.visiable = e.value != '';
            this.$emit('onchange', e.value);
        },
        onreturn: function onreturn(e) {
            this.$emit('return', e);
        },
        onclose: function onclose() {
            this.value = '';
            this.visiable = false;
            this.$emit('onchange', '');
        }
    },

    created: function created() {
        var globalEvent = weex.requireModule('globalEvent');
        globalEvent.addEventListener("onPageInit", function (e) {});

        this.visiable = !this.value == '';
    },
    ready: function ready() {}
};

/***/ }),

/***/ 51:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      flexDirection: "row",
      height: "100",
      alignItems: "center",
      backgroundColor: "red"
    }
  }, [_c('input', {
    ref: "input",
    staticStyle: {
      flex: "1",
      paddingLeft: "20",
      height: "100"
    },
    style: {
      'color': _vm.color,
      'placeholder-color': _vm.placeholder_color
    },
    attrs: {
      "returnKeyType": _vm.return_key_type,
      "autofocus": _vm.autofocus,
      "placeholder": _vm.placeholder,
      "type": _vm.type,
      "value": (_vm.value)
    },
    on: {
      "return": _vm.onreturn,
      "focus": _vm.onfocus,
      "change": _vm.onchange,
      "input": [function($event) {
        _vm.value = $event.target.attr.value
      }, _vm.oninput]
    }
  }), (_vm.visiable) ? _c('div', {
    staticStyle: {
      width: "50px",
      height: "100px",
      marginRight: "10",
      alignItems: "center",
      justifyContent: "center"
    },
    on: {
      "click": function($event) {
        _vm.onclose()
      }
    }
  }, [_c('image', {
    staticStyle: {
      width: "30px",
      height: "30px"
    },
    attrs: {
      "src": "root:img/delete.png"
    }
  })]) : _vm._e()])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });