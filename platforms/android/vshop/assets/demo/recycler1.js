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
	__vue_styles__.push(__webpack_require__(281)
	)

	/* script */
	__vue_exports__ = __webpack_require__(282)

	/* template */
	var __vue_template__ = __webpack_require__(283)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/recycler1.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-581d2a8b"
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

/***/ 208:
/***/ (function(module, exports) {

	module.exports = {
	  "header": {
	    "backgroundColor": "#66CCCC",
	    "alignItems": "center",
	    "justifyContent": "center",
	    "flexDirection": "row"
	  },
	  "leftview": {
	    "width": 120,
	    "height": 135,
	    "justifyContent": "center",
	    "position": "absolute",
	    "left": 0
	  },
	  "rightview": {
	    "width": 120,
	    "height": 135,
	    "justifyContent": "center",
	    "position": "absolute",
	    "right": 0
	  },
	  "wrapper": {
	    "height": 135
	  },
	  "titleback": {
	    "flex": 1,
	    "alignItems": "center"
	  },
	  "title": {
	    "color": "#FFFFFF",
	    "marginTop": 40,
	    "fontWeight": "bold"
	  },
	  "leftimage": {
	    "width": 30,
	    "height": 45,
	    "marginLeft": 20,
	    "marginTop": 40
	  },
	  "rightimage": {
	    "width": 45,
	    "height": 45,
	    "marginTop": 30,
	    "marginLeft": 45
	  },
	  "bottomline": {
	    "height": 1,
	    "backgroundColor": "#000000",
	    "position": "absolute",
	    "bottom": 0,
	    "left": 0,
	    "right": 0,
	    "flex": 1
	  }
	}

/***/ }),

/***/ 209:
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


	exports.default = {
	  props: {
	    title: {
	      type: String
	    },
	    leftClick: {}

	  },
	  data: function data() {
	    return {
	      title: ''
	    };
	  },

	  methods: {
	    leftclick: function leftclick() {
	      var nav = weex.requireModule('navigation');
	      nav.pop();
	    },
	    rightClick: function rightClick() {

	      this.$parent.$emit('rightClick', this.id);
	    }
	  },
	  created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 210:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["wrapper", "header"]
	  }, [_c('div', {
	    staticClass: ["leftview"],
	    on: {
	      "click": _vm.leftclick
	    }
	  }, [_c('image', {
	    staticClass: ["leftimage"],
	    attrs: {
	      "resize": "contain",
	      "src": "img/backarrow.png"
	    }
	  })]), _c('text', {
	    staticClass: ["title"]
	  }, [_vm._v(_vm._s(_vm.title))]), _c('div', {
	    staticClass: ["rightview"],
	    on: {
	      "click": _vm.rightClick
	    }
	  }, [_c('image', {
	    staticClass: ["rightimage"],
	    attrs: {
	      "resize": "contain",
	      "src": "img/scan.png"
	    }
	  })])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 213:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(208)
	)

	/* script */
	__vue_exports__ = __webpack_require__(209)

	/* template */
	var __vue_template__ = __webpack_require__(210)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/head.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-034916b7"
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

/***/ 281:
/***/ (function(module, exports) {

	module.exports = {
	  "c": {
	    "flex": 1
	  },
	  "page": {
	    "backgroundColor": "#eeeeee",
	    "width": 750
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
	    "fontWeight": "bold"
	  },
	  "indicator": {
	    "color": "#888888",
	    "height": 40,
	    "width": 40,
	    "marginRight": 30
	  },
	  "absolute": {
	    "position": "absolute",
	    "top": 0,
	    "width": 750,
	    "height": 377
	  },
	  "banner": {
	    "height": 377,
	    "flexDirection": "row"
	  },
	  "bannerInfo": {
	    "width": 270,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "avatar": {
	    "width": 148,
	    "height": 108,
	    "borderRadius": 54,
	    "borderWidth": 4,
	    "borderColor": "#FFFFFF",
	    "marginBottom": 14
	  },
	  "name": {
	    "fontWeight": "bold",
	    "fontSize": 32,
	    "color": "#ffffff",
	    "lineHeight": 32,
	    "textAlign": "center",
	    "marginBottom": 16
	  },
	  "titleWrap": {
	    "width": 100,
	    "height": 24,
	    "marginBottom": 10,
	    "backgroundColor": "rgba(255,255,255,0.8)",
	    "borderRadius": 12,
	    "justifyContent": "center",
	    "alignItems": "center"
	  },
	  "title": {
	    "fontSize": 20,
	    "color": "#000000"
	  },
	  "bannerPhotoWrap": {
	    "width": 449,
	    "height": 305,
	    "backgroundColor": "#FFFFFF",
	    "borderRadius": 12,
	    "marginTop": 35,
	    "padding": 12,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "flexWrap": "wrap"
	  },
	  "bannerPhoto": {
	    "width": 137,
	    "height": 137,
	    "marginBottom": 6
	  },
	  "stickyHeader": {
	    "position": "sticky"
	  },
	  "header2": {
	    "position": "sticky",
	    "height": 94,
	    "flexDirection": "row",
	    "paddingBottom": 6
	  },
	  "stickyWrapper": {
	    "flexDirection": "row",
	    "backgroundColor": "#00cc99",
	    "justifyContent": "center",
	    "alignItems": "center",
	    "flex": 1
	  },
	  "stickyTextImageWrapper": {
	    "flex": 1,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "flexDirection": "row"
	  },
	  "stickyText": {
	    "color": "#FFFFFF",
	    "fontWeight": "bold",
	    "fontSize": 32,
	    "marginRight": 12
	  },
	  "stickyImage": {
	    "width": 64,
	    "height": 64,
	    "borderRadius": 32
	  },
	  "cell": {
	    "paddingTop": 6,
	    "paddingBottom": 6
	  },
	  "item": {
	    "backgroundColor": "#FFFFFF",
	    "alignItems": "center"
	  },
	  "itemName": {
	    "fontSize": 28,
	    "color": "#333333",
	    "lineHeight": 42,
	    "textAlign": "left",
	    "marginTop": 24
	  },
	  "itemPhoto": {
	    "marginTop": 18,
	    "width": 220,
	    "height": 220,
	    "marginBottom": 18
	  },
	  "itemDesc": {
	    "fontSize": 24,
	    "margin": 12,
	    "color": "#999999",
	    "lineHeight": 36,
	    "textAlign": "left"
	  },
	  "itemClickBehaviour": {
	    "fontSize": 36,
	    "color": "#00cc99",
	    "lineHeight": 36,
	    "textAlign": "center",
	    "marginTop": 6,
	    "marginLeft": 24,
	    "marginRight": 24,
	    "marginBottom": 30
	  },
	  "footer": {
	    "height": 94,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "backgroundColor": "#00cc99"
	  },
	  "fixedItem": {
	    "position": "fixed",
	    "width": 78,
	    "height": 78,
	    "backgroundColor": "#00cc99",
	    "right": 32,
	    "bottom": 32,
	    "borderRadius": 39,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "fixedText": {
	    "fontSize": 32,
	    "color": "#FFFFFF",
	    "lineHeight": 32
	  }
	}

/***/ }),

/***/ 282:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	var _head = __webpack_require__(213);

	var _head2 = _interopRequireDefault(_head);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	exports.default = {
	  components: { head: _head2.default },
	  data: function data() {
	    var items = [{
	      src: 'https://gw.alicdn.com/tps/TB1Jl1CPFXXXXcJXXXXXXXXXXXX-370-370.jpg',
	      name: 'Thomas Carlyle',
	      desc: 'Genius only means hard-working all one\'s life',
	      behaviourName: 'Change width',
	      behaviour: 'changeColumnWidth'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1Hv1JPFXXXXa3XXXXXXXXXXXX-370-370.jpg',
	      desc: 'The man who has made up his mind to win will never say "impossible "',
	      behaviourName: 'Change gap',
	      behaviour: 'changeColumnGap'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1eNKuPFXXXXc_XpXXXXXXXXXX-370-370.jpg',
	      desc: 'There is no such thing as a great talent without great will - power',
	      behaviourName: 'Change count',
	      behaviour: 'changeColumnCount'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1DCh8PFXXXXX7aXXXXXXXXXXX-370-370.jpg',
	      name: 'Addison',
	      desc: 'Cease to struggle and you cease to live',
	      behaviourName: 'Show scrollbar',
	      behaviour: 'showScrollbar'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1ACygPFXXXXXwXVXXXXXXXXXX-370-370.jpg',
	      desc: 'A strong man will struggle with the storms of fate',
	      behaviourName: 'Listen appear',
	      behaviour: 'listenAppear'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1IGShPFXXXXaqXVXXXXXXXXXX-370-370.jpg',
	      name: 'Ruskin',
	      desc: 'Living without an aim is like sailing without a compass',
	      behaviourName: 'Set scrollable',
	      behaviour: 'setScrollable'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1xU93PFXXXXXHaXXXXXXXXXXX-240-240.jpg',
	      behaviourName: 'waterfall padding',
	      behaviour: 'setPadding'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB19hu0PFXXXXaXaXXXXXXXXXXX-240-240.jpg',
	      name: 'Balzac',
	      desc: 'There is no such thing as a great talent without great will - power',
	      behaviourName: 'listen scroll',
	      behaviour: 'listenScroll'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1ux2vPFXXXXbkXXXXXXXXXXXX-240-240.jpg',
	      behaviourName: 'Remove cell',
	      behaviour: 'removeCell'
	    }, {
	      src: 'https://gw.alicdn.com/tps/TB1tCCWPFXXXXa7aXXXXXXXXXXX-240-240.jpg',
	      behaviourName: 'Move cell',
	      behaviour: 'moveCell'

	    }];

	    var repeatItems = [];
	    for (var i = 0; i < 9; i++) {
	      repeatItems.push.apply(repeatItems, items);
	    }

	    return {
	      padding: 0,
	      refreshing: false,
	      refreshText: '↓   pull to refresh...',
	      columnCount: 3,
	      columnGap: 12,
	      columnWidth: 'auto',
	      contentOffset: '0',
	      showHeader: true,
	      showScrollbar: false,
	      scrollable: true,
	      showStickyHeader: false,
	      appearImage: null,
	      disappearImage: null,
	      stickyHeaderType: 'none',
	      // fixedRect:'',
	      banner: {
	        photos: [{ src: 'https://gw.alicdn.com/tps/TB1JyaCPFXXXXc9XXXXXXXXXXXX-140-140.jpg' }, { src: 'https://gw.alicdn.com/tps/TB1MwSFPFXXXXbdXXXXXXXXXXXX-140-140.jpg' }, { src: 'https://gw.alicdn.com/tps/TB1U8avPFXXXXaDXpXXXXXXXXXX-140-140.jpg' }, { src: 'https://gw.alicdn.com/tps/TB17Xh8PFXXXXbkaXXXXXXXXXXX-140-140.jpg' }, { src: 'https://gw.alicdn.com/tps/TB1cTmLPFXXXXXRXXXXXXXXXXXX-140-140.jpg' }, { src: 'https://gw.alicdn.com/tps/TB1oCefPFXXXXbVXVXXXXXXXXXX-140-140.jpg' }]
	      },
	      items: repeatItems
	    };
	  },

	  created: function created() {
	    // let self = this
	    // setTimeout(()=>{
	    //   weex.requireModule('dom').getComponentRect(this.$refs.fixed, result=>{
	    //     const x = result.size.left
	    //     const y = result.size.top
	    //     const width = result.size.width
	    //     const height = result.size.height
	    //     self.fixedRect = `${x}|${y}|${width}|${height}`
	    //   })
	    // }, 3000)
	  },


	  methods: {
	    recylerScroll: function recylerScroll(e) {
	      this.contentOffset = e.contentOffset.y;
	    },
	    loadmore: function loadmore(e) {
	      console.log('receive loadmore event');
	      // this.$refs.waterfall.resetLoadmore()
	    },
	    showOrRemoveHeader: function showOrRemoveHeader() {
	      this.showHeader = !this.showHeader;
	    },
	    onItemclick: function onItemclick(behaviour, index) {
	      console.log('click...' + behaviour + ' at index ' + index);
	      switch (behaviour) {
	        case 'changeColumnCount':
	          this.changeColumnCount();
	          break;
	        case 'changeColumnGap':
	          this.changeColumnGap();
	          break;
	        case 'changeColumnWidth':
	          this.changeColumnWidth();
	          break;
	        case 'showScrollbar':
	          this.showOrHideScrollbar();
	          break;
	        case 'listenAppear':
	          this.listenAppearAndDisappear();
	          break;
	        case 'setScrollable':
	          this.setScrollable();
	          break;
	        case 'setPadding':
	          this.setRecyclerPadding();
	          break;
	        case 'listenScroll':
	          this.listenScrollEvent();
	          break;
	        case 'removeCell':
	          this.removeCell(index);
	          break;
	        case 'moveCell':
	          this.moveCell(index);
	          break;
	      }
	    },

	    itemAppear: function itemAppear(src) {
	      this.appearImage = src;
	    },

	    itemDisappear: function itemDisappear(src) {
	      this.disappearImage = src;
	    },

	    changeColumnCount: function changeColumnCount() {
	      if (this.columnCount === 2) {
	        this.columnCount = 3;
	      } else {
	        this.columnCount = 2;
	      }
	    },

	    changeColumnGap: function changeColumnGap() {
	      if (this.columnGap === 12) {
	        this.columnGap = 'normal';
	      } else {
	        this.columnGap = 12;
	      }
	    },

	    changeColumnWidth: function changeColumnWidth() {
	      if (this.columnWidth === 'auto') {
	        this.columnWidth = 600;
	      } else {
	        this.columnWidth = 'auto';
	      }
	    },

	    showOrHideScrollbar: function showOrHideScrollbar() {
	      this.showScrollbar = !this.showScrollbar;
	    },

	    setScrollable: function setScrollable() {
	      this.scrollable = !this.scrollable;
	    },

	    listenAppearAndDisappear: function listenAppearAndDisappear() {
	      this.stickyHeaderType = this.stickyHeaderType === 'appear' ? 'none' : 'appear';
	    },

	    listenScrollEvent: function listenScrollEvent() {
	      this.stickyHeaderType = this.stickyHeaderType === 'scroll' ? 'none' : 'scroll';
	    },

	    scrollToNext: function scrollToNext() {
	      weex.requireModule('dom').scrollToElement(this.$refs.footer, {});
	    },

	    setRecyclerPadding: function setRecyclerPadding() {
	      this.padding = this.padding == 0 ? 12 : 0;
	    },

	    removeCell: function removeCell(index) {
	      this.items.splice(index, 1);
	    },

	    moveCell: function moveCell(index) {
	      if (index == 0) {
	        this.items.splice(this.items.length - 1, 0, this.items.splice(index, 1)[0]);
	      } else {
	        this.items.splice(0, 0, this.items.splice(index, 1)[0]);
	      }
	    },

	    onrefresh: function onrefresh(event) {
	      var _this = this;

	      this.refreshing = true;
	      this.refreshText = "loading...";
	      setTimeout(function () {
	        _this.refreshing = false;
	        _this.refreshText = '↓   pull to refresh...';
	      }, 2000);
	    },
	    onpullingdown: function onpullingdown(event) {
	      // console.log(`${event.pullingDistance}`)
	      if (event.pullingDistance < -64) {
	        this.refreshText = '↑   release to refresh...';
	      } else {
	        this.refreshText = '↓   pull to refresh...';
	      }
	    }
	  }
	}; //
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//

	module.exports = exports['default'];

/***/ }),

/***/ 283:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["c"]
	  }, [_c('waterfall', {
	    ref: "waterfall",
	    staticClass: ["page"],
	    style: {
	      padding: _vm.padding
	    },
	    attrs: {
	      "testId": "waterfall",
	      "columnWidth": _vm.columnWidth,
	      "columnCount": _vm.columnCount,
	      "columnGap": _vm.columnGap,
	      "showScrollbar": _vm.showScrollbar,
	      "scrollable": _vm.scrollable,
	      "loadmoreoffset": "3000"
	    },
	    on: {
	      "scroll": _vm.recylerScroll,
	      "loadmore": _vm.loadmore
	    }
	  }, [_c('refresh', {
	    staticClass: ["refresh"],
	    attrs: {
	      "display": _vm.refreshing ? 'show' : 'hide'
	    },
	    on: {
	      "refresh": _vm.onrefresh,
	      "pullingdown": _vm.onpullingdown
	    }
	  }, [_c('loading-indicator', {
	    staticClass: ["indicator"]
	  }), _c('text', {
	    staticClass: ["refreshText"]
	  }, [_vm._v(_vm._s(_vm.refreshText))])], 1), _vm._l((_vm.items), function(item) {
	    return _c('cell', {
	      key: item.src,
	      staticClass: ["cell"],
	      appendAsTree: true,
	      attrs: {
	        "append": "tree"
	      }
	    }, [_c('div', {
	      staticClass: ["item"],
	      on: {
	        "appear": function($event) {
	          _vm.itemAppear(item.src)
	        },
	        "disappear": function($event) {
	          _vm.itemDisappear(item.src)
	        }
	      }
	    }, [_c('image', {
	      staticClass: ["itemPhoto"],
	      attrs: {
	        "src": item.src
	      }
	    }), (item.behaviourName) ? _c('text', {
	      staticClass: ["itemClickBehaviour"]
	    }, [_vm._v(" " + _vm._s(item.behaviourName))]) : _vm._e()])])
	  })], 2)], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });