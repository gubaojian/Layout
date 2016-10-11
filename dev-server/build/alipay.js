(function(modules) {
  var installedModules = {};
  function __webpack_require__(moduleId) {
    if (installedModules[moduleId]) 
    return installedModules[moduleId].exports;
    var module = installedModules[moduleId] = {exports: {}, id: moduleId, loaded: false};
    modules[moduleId].__c("call", module.__g("exports"), module, module.__g("exports"), __webpack_require__);
    module.__s("loaded", true);
    return module.exports;
  }
  __webpack_require__.__s("m", modules);
  __webpack_require__.__s("c", installedModules);
  __webpack_require__.__s("p", "");
  return __webpack_require__(0);
})([function(module, exports, __webpack_require__) {
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
  var listView;
  var popupWindow;
  var tabName = 'home';
  var page = {createTopGridView: function createTopGridView() {
  var gridView = ui.__c("fromXml", listViewGridItemXml, null);
  gridView.__c("setAdapter", new BaseAdapter({getCount: function getCount() {
  return 8;
}, getView: function getView(position, convertView, parent) {
  if (convertView == null) 
  {
    convertView = ui.__c("fromXml", gridItemXml, null);
  }
  return convertView;
}, getViewTypeCount: function getViewTypeCount() {
  return 1;
}}));
  return gridView;
}, createMiddleBannerView: function createMiddleBannerView() {
  var listViewViewPagerItem = ui.__c("fromXml", listViewViewPagerItemXml, null);
  return listViewViewPagerItem;
}, createBottomGridView: function createBottomGridView() {
  var gridView = ui.__c("fromXml", listViewGridItemXml, null);
  gridView.__c("setAdapter", new BaseAdapter({getCount: function getCount() {
  return 24;
}, getView: function getView(position, convertView, parent) {
  if (convertView == null) 
  {
    convertView = ui.__c("fromXml", gridItemXml, null);
  }
  return convertView;
}}));
  return gridView;
}, bindTabEvent: function bindTabEvent(tabName) {
  if (tabName != 'home') 
  {
    var homeTabIcon = ui.__c("find", "homeTabTextIcon");
    homeTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var homeTabText = ui.__c("find", "homeTabText");
    homeTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
  if (tabName != 'koubei') 
  {
    var koubeiTabIcon = ui.__c("find", "koubeiTabTextIcon");
    koubeiTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var koubeiTabText = ui.__c("find", "koubeiTabText");
    koubeiTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
  if (tabName != 'friends') 
  {
    var friendsTabIcon = ui.__c("find", "friendsTabTextIcon");
    friendsTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var friendsTabText = ui.__c("find", "friendsTabText");
    friendsTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
  if (tabName != 'wealth') 
  {
    var wealthTabIcon = ui.__c("find", "wealthTabTextIcon");
    wealthTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var wealthTabText = ui.__c("find", "wealthTabText");
    wealthTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
}, onLoadHomeXml: function onLoadHomeXml() {
  var _self = this;
  var jsAdapter = {getCount: function getCount() {
  return 3;
}, getView: function getView(position, convertView, parent) {
  var type = jsAdapter.__c("getItemViewType", position);
  if (convertView == null) 
  {
    if (type == 'MiddleBanner') 
    {
      convertView = _self.__c("createMiddleBannerView");
    } else if (type == 'BottomBanner') 
    {
      convertView = _self.__c("createBottomGridView");
    } else {
      convertView = _self.__c("createTopGridView");
    }
  }
  print("getView js " + type);
  return convertView;
}, getItemViewType: function getItemViewType(position) {
  if (position == 0) 
  {
    return 'TopGrid';
  }
  if (position == 1) 
  {
    return 'MiddleBanner';
  }
  if (position == 2) 
  {
    return 'BottomBanner';
  }
}, getViewTypeCount: function getViewTypeCount() {
  return 3;
}};
  listView = hybridView.__c("findViewWithTag", "homeListView");
  listView.__c("setAdapter", new MultiTypeAdapter(jsAdapter));
  var addFriendView = ui.__c("find", "top_add_friend_logo");
  addFriendView.__c("setOnClickListener", new OnClickListener(function() {
  if (popupWindow) 
  {
    popupWindow.__c("dismiss");
    popupWindow = null;
  }
  popupWindow = new XmlPopupWindow(activity, addPopupMenuXml);
  popupWindow.__c("showAsDropDown", addFriendView);
}));
  ui.__c("onClick", "top_search_logo", function(view) {
  var dialog = new XmlDialog(activity);
  dialog.__c("setXml", addPopupMenuXml);
  dialog.__c("show");
});
  _self.__c("bindTabEvent", tabName);
}};
  hybridView.__s("onLoad", function(result) {
  page.__c("onLoadHomeXml");
});
  hybridView.__c("loadUrl", homeUrl);
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View background=\"#FFFFFF\">\n  <View\n    width=\"180\"\n    height=\"180\">\n    <ImageView\n      y=\"40\"\n      x=\"60\"\n      width=\"60\"\n      height=\"60\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n    <TextView\n      y=\"100\"\n      width=\"180\"\n      textSize=\"32\"\n      textAlign=\"center\"\n      text=\"\u7f51\u5546\u94f6\u884c\"/>\n  </View>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<GridView\n  width=\"720\"\n  screenUnit=\"720\"\n  columns=\"4\"\n  hSpace=\"1px\"\n  vSpace=\"1px\"/>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  >\n  <View\n        width=\"720\"\n        height=\"1px\"\n        background=\"#ECEDF0\"/>\n     <ViewPager\n       y=\"30\"\n       width=\"720\"\n       height=\"160\"\n       tag=\"topViewPager\"\n       imgUrls=\"https://aecpm.alicdn.com/simba/img/TB1Yob5KpXXXXX9aXXXSutbFXXX.jpg;https://aecpm.alicdn.com/simba/img/TB1FJf_KpXXXXbdXXXXSutbFXXX.jpg;https://img.alicdn.com/imgextra/i3/5/TB2DsBdqpXXXXXyXXXXXXXXXXXX_!!5-0-yamato.jpg_300x300.jpg;https://aecpm.alicdn.com/simba/img/TB1xDdrKpXXXXcrXXXXSutbFXXX.jpg;https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n\n  <PagerIndicator\n               width=\"720\"\n               height=\"40\"\n               y=\"155\"\n               circleColor=\"#E4E5E6\"\n               circleSelectColor=\"#1DAEFC\"\n               circleSize=\"16\"\n               viewPagerTag=\"topViewPager\"/>\n    <View\n       y=\"30\"\n       width=\"720\"\n       height=\"1px\"\n       background=\"#ECEDF0\"/>\n    <View\n          y=\"190\"\n          width=\"720\"\n          height=\"1px\"\n          background=\"#ECEDF0\"/>\n   <View\n          y=\"220\"\n          width=\"720\"\n          height=\"1px\"\n          background=\"#ECEDF0\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\">\n\n  <View\n    width=\"386\"\n    height=\"430\">\n <View\n   width=\"356\"\n   y=\"30\"\n   height=\"400\"\n   background=\"#434445\">\n  <IconTextView\n     x=\"30\"\n     height=\"80\"\n     width=\"80\"\n     text=\"0f39f\"\n     textSize=\"48\"\n     textColor=\"#FFFFFF\"/>\n  <TextView\n     x=\"90\"\n     text=\"\u6dfb\u52a0\u670b\u53cb\"\n     height=\"80\"\n     textSize=\"32\"\n     textColor=\"#FFFFFF\"/>\n <View\n             width=\"360\"\n             height=\"1\"\n             y=\"80\"\n             background=\"#4B4C4D\"/>\n\n  <IconTextView\n        x=\"30\"\n        y=\"80\"\n        height=\"80\"\n        width=\"80\"\n        text=\"0f2d9\"\n        textSize=\"48\"\n        textColor=\"#FFFFFF\"/>\n     <TextView\n        x=\"90\"\n        y=\"80\"\n        text=\"\u53d1\u8d77\u7fa4\u804a\"\n        height=\"80\"\n        textSize=\"32\"\n        textColor=\"#FFFFFF\"/>\n        <View\n                    width=\"360\"\n                    height=\"1\"\n                    y=\"160\"\n                    background=\"#4B4C4D\"/>\n         <IconTextView\n               x=\"30\"\n               y=\"160\"\n               height=\"80\"\n               width=\"80\"\n               text=\"0f346\"\n               textSize=\"48\"\n               textColor=\"#FFFFFF\"/>\n            <TextView\n               x=\"90\"\n               y=\"160\"\n               text=\"\u626b\u4e00\u626b\"\n               height=\"80\"\n               textSize=\"32\"\n               textColor=\"#FFFFFF\"/>\n               <View\n                           width=\"360\"\n                           height=\"1\"\n                           y=\"240\"\n                           background=\"#4B4C4D\"/>\n                <IconTextView\n                      x=\"30\"\n                      y=\"240\"\n                      height=\"80\"\n                      width=\"80\"\n                      text=\"0f316\"\n                      textSize=\"48\"\n                      textColor=\"#FFFFFF\"/>\n                   <TextView\n                      x=\"90\"\n                      y=\"240\"\n                      text=\"\u6211\u7684\u4e8c\u7ef4\u7801/\u6536\u6b3e\"\n                      height=\"80\"\n                      textSize=\"32\"\n                      textColor=\"#FFFFFF\"/>\n                      <View\n                                  width=\"360\"\n                                  height=\"1\"\n                                  y=\"320\"\n                                  background=\"#4B4C4D\"/>\n\n                       <IconTextView\n                             x=\"30\"\n                             y=\"320\"\n                             height=\"80\"\n                             width=\"80\"\n                             text=\"0f445\"\n                             textSize=\"48\"\n                             textColor=\"#FFFFFF\"/>\n                          <TextView\n                             x=\"90\"\n                             y=\"320\"\n                             text=\"\u4f7f\u7528\u5e2e\u52a9\"\n                             height=\"80\"\n                             textSize=\"32\"\n                             textColor=\"#FFFFFF\"/>\n\n  </View>\n</View>\n</View>\n");
}]);
