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
	importClass("com.efurture.gule.hybrid.adapter.MultiTypeAdapter");
	importClass("android.widget.PopupWindow");
	importClass("android.view.View.OnClickListener");
	importClass("com.efurture.glue.ui.XmlPopupWindow");
	importClass("com.efurture.glue.ui.XmlDialog");

	var homeUrl = "./alipay.xml";
	var gridItemXml = __webpack_require__(1);
	var listViewGridItemXml = __webpack_require__(2);
	var listViewViewPagerItemXml = __webpack_require__(3);
	var addPopupMenuXml = __webpack_require__(4);

	var listView; //listview

	var popupWindow; //下拉菜单
	var tabName = 'home';

	var page = {
	  createTopGridView: function createTopGridView() {
	    var gridView = ui.fromXml(listViewGridItemXml, null);
	    gridView.setAdapter(new BaseAdapter({
	      getCount: function getCount() {
	        return 8;
	      },
	      getView: function getView(position, convertView, parent) {
	        if (convertView == null) {
	          convertView = ui.fromXml(gridItemXml, null);
	        }
	        return convertView;
	      },
	      getViewTypeCount: function getViewTypeCount() {
	        return 1;
	      }
	    }));
	    return gridView;
	  },
	  createMiddleBannerView: function createMiddleBannerView() {
	    var listViewViewPagerItem = ui.fromXml(listViewViewPagerItemXml, null);
	    return listViewViewPagerItem;
	  },
	  createBottomGridView: function createBottomGridView() {
	    var gridView = ui.fromXml(listViewGridItemXml, null);
	    gridView.setAdapter(new BaseAdapter({
	      getCount: function getCount() {
	        return 24;
	      },
	      getView: function getView(position, convertView, parent) {
	        if (convertView == null) {
	          convertView = ui.fromXml(gridItemXml, null);
	        }
	        return convertView;
	      }
	    }));
	    return gridView;
	  },
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
	  onLoadHomeXml: function onLoadHomeXml() {
	    var _self = this;

	    //初始化listview
	    var jsAdapter = {
	      getCount: function getCount() {
	        return 3;
	      },
	      getView: function getView(position, convertView, parent) {
	        var type = jsAdapter.getItemViewType(position);
	        if (convertView == null) {
	          if (type == 'MiddleBanner') {
	            convertView = _self.createMiddleBannerView();
	          } else if (type == 'BottomBanner') {
	            convertView = _self.createBottomGridView();
	          } else {
	            convertView = _self.createTopGridView();
	          }
	        }
	        print("getView js " + type);
	        return convertView;
	      },
	      getItemViewType: function getItemViewType(position) {
	        if (position == 0) {
	          return 'TopGrid';
	        }
	        if (position == 1) {
	          return 'MiddleBanner';
	        }
	        if (position == 2) {
	          return 'BottomBanner';
	        }
	      },
	      getViewTypeCount: function getViewTypeCount() {
	        return 3;
	      }
	    };
	    listView = hybridView.findViewWithTag("homeListView");
	    listView.setAdapter(new MultiTypeAdapter(jsAdapter));

	    //绑定按钮事件
	    var addFriendView = ui.find("top_add_friend_logo");
	    addFriendView.setOnClickListener(new OnClickListener(function () {
	      if (popupWindow) {
	        popupWindow.dismiss();
	        popupWindow = null;
	      }
	      popupWindow = new XmlPopupWindow(activity, addPopupMenuXml);
	      popupWindow.showAsDropDown(addFriendView);
	    }));

	    ui.onClick("top_search_logo", function (view) {
	      var dialog = new XmlDialog(activity);
	      dialog.setXml(addPopupMenuXml);
	      dialog.show();
	    });

	    //tab 事件
	    _self.bindTabEvent(tabName);
	  }
	};
	hybridView.onLoad = function (result) {
	  page.onLoadHomeXml();
	};
	hybridView.loadUrl(homeUrl);

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View background=\"#FFFFFF\">\n  <View\n    width=\"180\"\n    height=\"180\">\n    <ImageView\n      y=\"40\"\n      x=\"60\"\n      width=\"60\"\n      height=\"60\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n    <TextView\n      y=\"100\"\n      width=\"180\"\n      textSize=\"32\"\n      textAlign=\"center\"\n      text=\"网商银行\"/>\n  </View>\n</View>\n"

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<GridView\n  width=\"720\"\n  screenUnit=\"720\"\n  columns=\"4\"\n  hSpace=\"1px\"\n  vSpace=\"1px\"/>\n"

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  >\n  <View\n        width=\"720\"\n        height=\"1px\"\n        background=\"#ECEDF0\"/>\n     <ViewPager\n       y=\"30\"\n       width=\"720\"\n       height=\"160\"\n       tag=\"topViewPager\"\n       imgUrls=\"https://aecpm.alicdn.com/simba/img/TB1Yob5KpXXXXX9aXXXSutbFXXX.jpg;https://aecpm.alicdn.com/simba/img/TB1FJf_KpXXXXbdXXXXSutbFXXX.jpg;https://img.alicdn.com/imgextra/i3/5/TB2DsBdqpXXXXXyXXXXXXXXXXXX_!!5-0-yamato.jpg_300x300.jpg;https://aecpm.alicdn.com/simba/img/TB1xDdrKpXXXXcrXXXXSutbFXXX.jpg;https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n\n  <PagerIndicator\n               width=\"720\"\n               height=\"40\"\n               y=\"155\"\n               circleColor=\"#E4E5E6\"\n               circleSelectColor=\"#1DAEFC\"\n               circleSize=\"16\"\n               viewPagerTag=\"topViewPager\"/>\n    <View\n       y=\"30\"\n       width=\"720\"\n       height=\"1px\"\n       background=\"#ECEDF0\"/>\n    <View\n          y=\"190\"\n          width=\"720\"\n          height=\"1px\"\n          background=\"#ECEDF0\"/>\n   <View\n          y=\"220\"\n          width=\"720\"\n          height=\"1px\"\n          background=\"#ECEDF0\"/>\n</View>\n"

/***/ },
/* 4 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\">\n\n  <View\n    width=\"386\"\n    height=\"430\">\n <View\n   width=\"356\"\n   y=\"30\"\n   height=\"400\"\n   background=\"#434445\">\n  <IconTextView\n     x=\"30\"\n     height=\"80\"\n     width=\"80\"\n     text=\"0f39f\"\n     textSize=\"48\"\n     textColor=\"#FFFFFF\"/>\n  <TextView\n     x=\"90\"\n     text=\"添加朋友\"\n     height=\"80\"\n     textSize=\"32\"\n     textColor=\"#FFFFFF\"/>\n <View\n             width=\"360\"\n             height=\"1\"\n             y=\"80\"\n             background=\"#4B4C4D\"/>\n\n  <IconTextView\n        x=\"30\"\n        y=\"80\"\n        height=\"80\"\n        width=\"80\"\n        text=\"0f2d9\"\n        textSize=\"48\"\n        textColor=\"#FFFFFF\"/>\n     <TextView\n        x=\"90\"\n        y=\"80\"\n        text=\"发起群聊\"\n        height=\"80\"\n        textSize=\"32\"\n        textColor=\"#FFFFFF\"/>\n        <View\n                    width=\"360\"\n                    height=\"1\"\n                    y=\"160\"\n                    background=\"#4B4C4D\"/>\n         <IconTextView\n               x=\"30\"\n               y=\"160\"\n               height=\"80\"\n               width=\"80\"\n               text=\"0f346\"\n               textSize=\"48\"\n               textColor=\"#FFFFFF\"/>\n            <TextView\n               x=\"90\"\n               y=\"160\"\n               text=\"扫一扫\"\n               height=\"80\"\n               textSize=\"32\"\n               textColor=\"#FFFFFF\"/>\n               <View\n                           width=\"360\"\n                           height=\"1\"\n                           y=\"240\"\n                           background=\"#4B4C4D\"/>\n                <IconTextView\n                      x=\"30\"\n                      y=\"240\"\n                      height=\"80\"\n                      width=\"80\"\n                      text=\"0f316\"\n                      textSize=\"48\"\n                      textColor=\"#FFFFFF\"/>\n                   <TextView\n                      x=\"90\"\n                      y=\"240\"\n                      text=\"我的二维码/收款\"\n                      height=\"80\"\n                      textSize=\"32\"\n                      textColor=\"#FFFFFF\"/>\n                      <View\n                                  width=\"360\"\n                                  height=\"1\"\n                                  y=\"320\"\n                                  background=\"#4B4C4D\"/>\n\n                       <IconTextView\n                             x=\"30\"\n                             y=\"320\"\n                             height=\"80\"\n                             width=\"80\"\n                             text=\"0f445\"\n                             textSize=\"48\"\n                             textColor=\"#FFFFFF\"/>\n                          <TextView\n                             x=\"90\"\n                             y=\"320\"\n                             text=\"使用帮助\"\n                             height=\"80\"\n                             textSize=\"32\"\n                             textColor=\"#FFFFFF\"/>\n\n  </View>\n</View>\n</View>\n"

/***/ }
/******/ ]);