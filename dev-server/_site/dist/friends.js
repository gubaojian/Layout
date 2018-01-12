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
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	importApi("ui");
	importApi("nav");
	importClass("com.efurture.glue.ui.XmlViewLoadListener");
	importClass("android.widget.BaseAdapter");
	importClass("com.efurture.gule.hybrid.adapter.StickyRecycleAdapter");
	importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
	importClass("android.widget.PopupWindow");
	importClass("android.view.View.OnClickListener");
	importClass("com.efurture.gule.hybrid.ui.HPopupWindow");

	var homeUrl = "./friends.xml";
	var lifeCommunityItemXml = __webpack_require__(5);
	var normalCellTopXml = __webpack_require__(6);
	var normalCellMiddleXml = __webpack_require__(7);
	var normalCellBottomXml = __webpack_require__(8);

	var recycleView; //

	var page = {
	  bindTabEvent: function bindTabEvent(tabName) {

	    if (tabName != 'home') {
	      var homeTabIcon = ui.find("homeTabTextIcon");
	      homeTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var homeTabText = ui.find("homeTabText");
	      homeTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }

	    if (tabName != 'koubei') {
	      var koubeiTabIcon = ui.find("koubeiTabTextIcon");
	      koubeiTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var koubeiTabText = ui.find("koubeiTabText");
	      koubeiTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }

	    if (tabName != 'friends') {
	      var friendsTabIcon = ui.find("friendsTabTextIcon");
	      friendsTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var friendsTabText = ui.find("friendsTabText");
	      friendsTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }

	    if (tabName != 'wealth') {
	      var wealthTabIcon = ui.find("wealthTabTextIcon");
	      wealthTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var wealthTabText = ui.find("wealthTabText");
	      wealthTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }
	  },
	  onLoadXml: function onLoadXml() {
	    var _self = this;

	    //初始化listview
	    var jsAdapter = {
	      getItemCount: function getItemCount() {
	        return 30;
	      },
	      onCreateView: function onCreateView(parent, viewType) {
	        var view = null;
	        if (viewType == 'LifeCommunityItem') {
	          view = ui.fromXml(lifeCommunityItemXml, parent, false);
	        } else if (viewType == 'NormalCellTop') {
	          view = ui.fromXml(normalCellTopXml, parent, false);
	        } else if (viewType == 'NormalCellMiddle') {
	          view = ui.fromXml(normalCellMiddleXml, parent, false);
	        } else if (viewType == 'NormalCellBottom') {
	          view = ui.fromXml(normalCellBottomXml, parent, false);
	        } else {
	          view = ui.fromXml(lifeCommunityItemXml, parent, false);
	        }
	        print("getView js " + viewType);
	        return view;
	      },
	      onBindView: function onBindView(view, position) {},
	      getItemViewType: function getItemViewType(position) {
	        if (position == 0) {
	          return 'LifeCommunityItem';
	        }

	        if (position == 1) {
	          return 'NormalCellTop';
	        }

	        if (position == 29) {
	          return 'NormalCellBottom';
	        }

	        return 'NormalCellMiddle';
	      }
	    };
	    recycleView = hybridView.findViewWithTag("recycleView");
	    recycleView.setAdapter(new RecycleAdapter(jsAdapter));

	    _self.bindTabEvent('friends');
	  }
	};
	hybridView.onLoad = function (result) {
	  page.onLoadXml();
	};
	hybridView.loadUrl(homeUrl);

/***/ },
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n\n  <ImageView\n    x=\"20\"\n    y=\"20\"\n    width=\"88\"\n    height=\"88\"\n    imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n <TextView\n      x=\"128\"\n      width=\"530\"\n      height=\"128\"\n      textSize=\"32\"\n      text=\"生活圈\"/>\n  <IconTextView\n     x=\"640\"\n     width=\"60\"\n     height=\"128\"\n     text=\"0f125\"\n     textSize=\"40\"\n     textColor=\"#888888\"\n     textAlign=\"center\"/>\n\n<View\n     y=\"128\"\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n"

/***/ },
/* 6 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\">\n  <View\n       width=\"720\"\n       height=\"1\"\n       background=\"#ECEDF0\"/>\n  <View\n    y=\"30\"\n    background=\"#FFFFFF\">\n    <ImageView\n      x=\"20\"\n      y=\"20\"\n      width=\"88\"\n      height=\"88\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n   <TextView\n        x=\"128\"\n        y=\"20\"\n        width=\"300\"\n        height=\"50\"\n        textSize=\"32\"\n        text=\"服务窗\"/>\n  <TextView\n        x=\"460\"\n        y=\"20\"\n        width=\"230\"\n        height=\"50\"\n        textSize=\"26\"\n        textColor=\"#888888\"\n        textAlign=\"right\"\n        text=\"上午 11:48\"/>\n  <TextView\n          x=\"128\"\n          y=\"68\"\n          width=\"560\"\n          height=\"40\"\n          textSize=\"28\"\n          textColor=\"#888888\"\n          lineBreakMode=\"tail\"\n          text=\"天虹基金：余额宝三周年！分50亿虚拟资金，领红包\"/>\n\n  <View\n       y=\"128\"\n       x=\"20\"\n       width=\"700\"\n       height=\"1px\"\n       background=\"#ECEDF0\"/>\n   </View>\n</View>\n"

/***/ },
/* 7 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n  <ImageView\n    x=\"20\"\n    y=\"20\"\n    width=\"88\"\n    height=\"88\"\n    imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n <TextView\n      x=\"128\"\n      y=\"20\"\n      width=\"300\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"微博头条\"/>\n<TextView\n      x=\"460\"\n      y=\"20\"\n      width=\"230\"\n      height=\"50\"\n      textSize=\"26\"\n      textColor=\"#888888\"\n      textAlign=\"right\"\n      text=\"10:48\"/>\n<TextView\n        x=\"128\"\n        y=\"68\"\n        width=\"560\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        lineBreakMode=\"tail\"\n        text=\"深陷约炮门的吴亦凡还能洗白吗？\"/>\n\n<View\n     y=\"128\"\n     x=\"20\"\n     width=\"700\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n"

/***/ },
/* 8 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n  <ImageView\n    x=\"20\"\n    y=\"20\"\n    width=\"88\"\n    height=\"88\"\n    imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n  <TextView\n      x=\"128\"\n      y=\"20\"\n      width=\"300\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"鸿荣\"/>\n  <TextView\n      x=\"460\"\n      y=\"20\"\n      width=\"230\"\n      height=\"50\"\n      textSize=\"26\"\n      textColor=\"#888888\"\n      textAlign=\"right\"\n      text=\"16/5/19\"/>\n  <TextView\n        x=\"128\"\n        y=\"68\"\n        width=\"560\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        lineBreakMode=\"tail\"\n        text=\"[转账]想你转账9.00元\"/>\n\n  <View\n     y=\"128\"\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n"

/***/ }
/******/ ]);