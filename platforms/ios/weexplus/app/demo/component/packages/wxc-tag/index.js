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
	__vue_styles__.push(__webpack_require__(290)
	)

	/* script */
	__vue_exports__ = __webpack_require__(291)

	/* template */
	var __vue_template__ = __webpack_require__(292)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/component/packages/wxc-tag/index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-3c9e89ae"
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

/***/ 290:
/***/ (function(module, exports) {

	module.exports = {
	  "tag-item": {
	    "height": 24,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "paddingBottom": 2
	  },
	  "tag-border": {
	    "borderBottomLeftRadius": 4,
	    "borderBottomRightRadius": 4,
	    "borderTopLeftRadius": 4,
	    "borderTopRightRadius": 4
	  },
	  "tag-hollow": {
	    "borderWidth": 1
	  },
	  "tag-image": {
	    "height": 24
	  },
	  "tag-special": {
	    "borderWidth": 1,
	    "flexDirection": "row"
	  },
	  "left-image": {
	    "width": 20,
	    "height": 20
	  },
	  "tag-left": {
	    "width": 24,
	    "height": 24,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "tag-text": {
	    "fontSize": 20,
	    "height": 22,
	    "lineHeight": 22,
	    "paddingLeft": 6,
	    "paddingRight": 6
	  }
	}

/***/ }),

/***/ 291:
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

	exports.default = {
	  props: {
	    type: {
	      type: String,
	      default: 'solid'
	    },
	    value: {
	      type: [String, Number],
	      default: '测试测试'
	    },
	    tagColor: {
	      type: String,
	      default: '#ff5000'
	    },
	    fontColor: {
	      type: String,
	      default: '#333333'
	    },
	    specialIcon: {
	      type: String,
	      default: ''
	    },
	    img: {
	      type: String,
	      default: ''
	    }
	  },
	  computed: {
	    showSolid: function showSolid() {
	      var type = this.type,
	          value = this.value;

	      return type === 'solid' && value !== '';
	    },
	    showHollow: function showHollow() {
	      var type = this.type,
	          value = this.value;

	      return type === 'hollow' && value !== '';
	    },
	    showSpecial: function showSpecial() {
	      var type = this.type,
	          value = this.value,
	          specialIcon = this.specialIcon;

	      return type === 'special' && value !== '' && specialIcon !== '';
	    },
	    showImage: function showImage() {
	      var type = this.type,
	          img = this.img;

	      return type === 'image' && img !== '';
	    },
	    tagTextStyle: function tagTextStyle() {
	      var tagColor = this.tagColor,
	          showSolid = this.showSolid;

	      return showSolid ? { backgroundColor: tagColor } : { borderColor: tagColor };
	    }
	  },
	  data: function data() {
	    return {
	      imgWidth: 90
	    };
	  },
	  methods: {
	    onLoad: function onLoad(e) {
	      if (e.success && e.size && e.size.naturalWidth > 0) {
	        var width = e.size.naturalWidth;
	        var height = e.size.naturalHeight;
	        this.imgWidth = width * (24 / height);
	      }
	    }
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 292:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', [(_vm.showSolid || _vm.showHollow) ? _c('div', {
	    class: ['tag-item', 'tag-border', _vm.showHollow && 'tag-hollow'],
	    style: _vm.tagTextStyle
	  }, [_c('text', {
	    staticClass: ["tag-text"],
	    style: {
	      color: _vm.fontColor
	    }
	  }, [_vm._v(_vm._s(_vm.value))])]) : _vm._e(), (_vm.showImage) ? _c('image', {
	    staticClass: ["tag-image"],
	    style: {
	      width: _vm.imgWidth + 'px'
	    },
	    attrs: {
	      "src": _vm.img,
	      "ariaHidden": true
	    },
	    on: {
	      "load": _vm.onLoad
	    }
	  }) : _vm._e(), (_vm.showSpecial) ? _c('div', {
	    staticClass: ["tag-special", "tag-border"],
	    style: {
	      borderColor: _vm.tagColor
	    },
	    attrs: {
	      "accessible": true,
	      "ariaLabel": _vm.value
	    }
	  }, [_c('div', {
	    staticClass: ["tag-left"],
	    style: {
	      backgroundColor: _vm.tagColor
	    }
	  }, [_c('image', {
	    staticClass: ["left-image"],
	    attrs: {
	      "src": _vm.specialIcon
	    }
	  })]), _c('text', {
	    staticClass: ["tag-text"],
	    style: {
	      color: _vm.fontColor
	    }
	  }, [_vm._v(_vm._s(_vm.value))])]) : _vm._e()])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });