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

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	exports.Draggable = undefined;

	var _classCallCheck2 = __webpack_require__(403);

	var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	var supportTouch = WXEnvironment.platform === 'Web' && 'ontouchstart' in window;

	var Draggable = exports.Draggable = function Draggable(element, option) {
	  (0, _classCallCheck3.default)(this, Draggable);

	  if (WXEnvironment.platform === 'Web') {
	    this.isDragging = false;

	    var _getOffset = function _getOffset(e) {
	      if (supportTouch) {
	        return {
	          x: e.changedTouches[0].pageX,
	          y: e.changedTouches[0].pageY
	        };
	      } else {
	        return {
	          x: e.pageX,
	          y: e.pageY
	        };
	      }
	    };

	    var onDragStart = function onDragStart(e) {
	      this.isDragging = true;
	      document.onselectstart = function () {
	        return false;
	      };
	      document.ondragstart = function () {
	        return false;
	      };
	      if (!supportTouch) {
	        element.addEventListener('mousemove', _onDragMove, false);
	        element.addEventListener('mouseup', _onDragEnd, false);
	        element.addEventListener('mouseout', _onDragEnd, false);
	      }

	      if (option.start) {
	        option.start(_getOffset(e));
	      }
	    };

	    var _onDragMove = function _onDragMove(e) {
	      if (option.move) {
	        option.move(_getOffset(e));
	      }
	    };

	    var _onDragEnd = function _onDragEnd(e) {
	      this.isDragging = false;
	      document.onselectstart = null;
	      document.ondragstart = null;

	      if (!supportTouch) {
	        element.removeEventListener('mousemove', _onDragMove, false);
	        element.removeEventListener('mouseup', _onDragEnd, false);
	        element.removeEventListener('mouseout', _onDragEnd, false);
	      }

	      if (option.end) {
	        option.end(_getOffset(e));
	      }
	    };

	    element.addEventListener(supportTouch ? 'touchstart' : 'mousedown', onDragStart, false);

	    if (supportTouch) {
	      element.addEventListener('touchmove', _onDragMove, false);
	      element.addEventListener('touchend', _onDragEnd, false);
	      element.addEventListener('touchcancel', _onDragEnd, false);
	    }

	    this.removeListener = function () {
	      element.removeEventListener(supportTouch ? 'touchstart' : 'mousedown', onDragStart, false);
	      if (supportTouch) {
	        element.removeEventListener('touchmove', _onDragMove, false);
	        element.removeEventListener('touchend', _onDragEnd, false);
	        element.removeEventListener('touchcancel', _onDragEnd, false);
	      }
	    };
	  }
	};

/***/ }),

/***/ 403:
/***/ (function(module, exports) {

	"use strict";

	exports.__esModule = true;

	exports.default = function (instance, Constructor) {
	  if (!(instance instanceof Constructor)) {
	    throw new TypeError("Cannot call a class as a function");
	  }
	};

/***/ })

/******/ });