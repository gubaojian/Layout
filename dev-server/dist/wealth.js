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
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	importApi("ui");
	importApi("nav");
	importClass("com.efurture.glue.ui.XmlViewLoadListener");
	importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
	importClass("android.view.View.OnClickListener");

	var homeUrl = "./wealth.xml";
	var userHeaderItemXml = __webpack_require__(19);
	var middleLineXml = __webpack_require__(20);
	var assetAmountItemXml = __webpack_require__(21);
	var topGridItemXml = __webpack_require__(22);
	var bottomGridItemXml = __webpack_require__(23);

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
	        return 13;
	      },
	      onCreateView: function onCreateView(parent, viewType) {
	        var view = null;
	        if (viewType == 'UserHeaderItem') {
	          view = ui.fromXml(userHeaderItemXml, parent, false);
	        } else if (viewType == 'MiddleLine') {
	          view = ui.fromXml(middleLineXml, parent, false);
	        } else if (viewType == 'AssetAmountItem') {
	          view = ui.fromXml(assetAmountItemXml, parent, false);
	        } else if (viewType == 'TopGridItem') {
	          view = ui.fromXml(topGridItemXml, parent, false);
	        } else if (viewType == 'BottomGridItem') {
	          view = ui.fromXml(bottomGridItemXml, parent, false);
	        } else {
	          view = ui.fromXml(userHeaderItemXml, parent, false);
	        }

	        print("getView js " + viewType);
	        return view;
	      },
	      onBindView: function onBindView(view, position) {},
	      getItemViewType: function getItemViewType(position) {
	        if (position == 0) {
	          return 'UserHeaderItem';
	        }

	        if (position == 1 || position == 5 || position == 8 || position == 12) {
	          return 'MiddleLine';
	        }
	        if (position == 2) {
	          return 'AssetAmountItem';
	        }

	        if (position == 3 || position == 6 || position == 9) {
	          return 'TopGridItem';
	        }

	        if (position == 4 || position == 7 || position == 10 || position == 11) {
	          return 'BottomGridItem';
	        }
	        return 'UserHeaderItem';
	      }
	    };
	    recycleView = hybridView.findViewWithTag("recycleView");
	    recycleView.setAdapter(new RecycleAdapter(jsAdapter));
	    _self.bindTabEvent('wealth');
	  }
	};
	hybridView.onLoad = function (result) {
	  page.onLoadXml();
	};
	hybridView.loadUrl(homeUrl);

/***/ },

/***/ 19:
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n\n  <ImageView\n    x=\"30\"\n    y=\"30\"\n    width=\"120\"\n    height=\"120\"\n    imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n <TextView\n      x=\"170\"\n      y=\"30\"\n      width=\"500\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"剑白\"/>\n  <TextView\n     x=\"170\"\n     y=\"70\"\n     height=\"40\"\n     textSize=\"26\"\n     textColor=\"#888888\"\n     text=\"jianbai.gbj@alibaba-inc.com\"/>\n\n  <LinearLayout\n    x=\"170\"\n    y=\"110\"\n    height=\"40\"\n    gravity=\"left|centerVertical\">\n\n    <IconTextView\n      width=\"40\"\n      height=\"40\"\n      text=\"0f481\"\n      textSize=\"36\"\n       textColor=\"#2d78f4\"\n       textAlign=\"center\"/>\n     <TextView\n        x=\"5\"\n        height=\"40\"\n        textSize=\"26\"\n        textColor=\"#888888\"\n        text=\"相册\"/>\n      <View\n           x=\"20\"\n           width=\"2\"\n           height=\"35\"\n           background=\"#ECEDF0\"/>\n\n     <IconTextView\n        x=\"20\"\n        width=\"40\"\n        height=\"40\"\n        text=\"0f442\"\n        textSize=\"36\"\n        textColor=\"#2d78f4\"\n        textAlign=\"center\"/>\n      <TextView\n         x=\"5\"\n         height=\"40\"\n         textSize=\"26\"\n         textColor=\"#888888\"\n         text=\"收藏\"/>\n       <View\n            x=\"20\"\n            width=\"2\"\n            height=\"35\"\n            background=\"#ECEDF0\"/>\n\n      <IconTextView\n         x=\"20\"\n         width=\"40\"\n         height=\"40\"\n         text=\"0f3c6\"\n         textSize=\"36\"\n         textColor=\"#2d78f4\"\n         textAlign=\"center\"/>\n       <TextView\n          x=\"5\"\n          height=\"40\"\n          textSize=\"26\"\n          textColor=\"#888888\"\n          text=\"会员中心\"/>\n\n </LinearLayout>\n\n\n<View\n     y=\"180\"\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n"

/***/ },

/***/ 20:
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#F4F5F9\">\n  <View\n     height=\"30\"/>\n</View>\n"

/***/ },

/***/ 21:
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n\n <WrapLayout\n   height=\"118\"\n   gravity=\"left|centerVertical\">\n   <TextView\n        x=\"30\"\n        height=\"118\"\n        textSize=\"50\"\n        text=\"1,500.00\"/>\n    <IconTextView\n       x=\"10\"\n       width=\"60\"\n       height=\"60\"\n       text=\"0f424\"\n       textSize=\"50\"\n       textColor=\"#888888\"\n       textAlign=\"center\"/>\n </WrapLayout>\n <TextView\n    x=\"360\"\n    width=\"280\"\n    height=\"118\"\n    text=\"开启账户安全险享100完保障保障保障\"\n    textSize=\"28\"\n    textColor=\"#888888\"\n    lineBreakMode=\"tail\"\n    textAlign=\"center\"/>\n\n  <IconTextView\n     x=\"640\"\n     width=\"60\"\n     height=\"118\"\n     text=\"0f125\"\n     textSize=\"34\"\n     textColor=\"#888888\"\n     textAlign=\"center\"/>\n\n\n</View>\n"

/***/ },

/***/ 22:
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\"\n  screenUnit=\"720\"\n  width=\"720\">\n  <View\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n  <View\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"余额\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"0.30\"/>\n  </View>\n\n  <View\n    x=\"360\"\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"蚂蚁花呗\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"普惠金融\"/>\n  </View>\n  <View x=\"360\"\n        height=\"126\"\n        width=\"1px\"\n        background=\"#ECEDF0\"/>\n  <View y=\"126\"\n        height=\"1px\"\n        width=\"720\"\n        background=\"#ECEDF0\"/>\n</View>\n"

/***/ },

/***/ 23:
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\"\n  screenUnit=\"720\"\n  width=\"720\">\n  <View\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"余额宝\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"+0.80\"/>\n  </View>\n\n  <View\n    x=\"360\"\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"招财宝\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"定期理财\"/>\n  </View>\n  <View x=\"360\"\n        height=\"126\"\n        width=\"1px\"\n        background=\"#ECEDF0\"/>\n  <View y=\"126\"\n        height=\"1px\"\n        width=\"720\"\n        background=\"#ECEDF0\"/>\n</View>\n"

/***/ }

/******/ });