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
})({0: function(module, exports, __webpack_require__) {
  "use strict";
  importApi("ui");
  importClass("android.widget.BaseAdapter");
  var listXml = __webpack_require__(17);
  var listItemXml = __webpack_require__(23);
  var page = {onLoad: function onLoad() {
  var listView = ui.__c("find", "listView");
  listView.__c("setAdapter", new BaseAdapter({getCount: function getCount() {
  return 8;
}, getView: function getView(position, convertView, parent) {
  if (convertView == null) 
  {
    convertView = ui.__c("fromXml", listItemXml, null);
  }
  return convertView;
}, getViewTypeCount: function getViewTypeCount() {
  return 1;
}}));
}};
  hybridView.__s("onLoad", function(result) {
  page.__c("onLoad");
});
  hybridView.__c("loadXml", listXml);
}, 17: function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n    width=\"750\"\n    height=\"100%\"\n    screenUnit=\"750\"\n    background=\"#889922\">\n   <ImageView\n     width=\"750\"\n     height=\"200\"\n     imageUrl=\"https://zos.alipayobjects.com/rmsportal/VIZVslEXXguQJbi.jpg\"/>\n  <ListView\n    width=\"750\"\n    y=\"200\"\n    height=\"calc(100%-200)\"\n    tag=\"listView\"/>\n</View>\n");
}, 23: function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\">\n  <View\n    width=\"180\"\n    height=\"180\">\n    <ImageView\n      y=\"40\"\n      x=\"60\"\n      width=\"60\"\n      height=\"60\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n  </View>\n  <TextView\n    x=\"160\"\n    y=\"40\"\n    width=\"300\"\n    textSize=\"32\"\n    text=\"\u7f51\u5546\u94f6\u884c\"/>\n  <TextView\n      x=\"160\"\n      y=\"80\"\n      width=\"300\"\n      textSize=\"32\"\n      textColor=\"#888888\"\n      text=\"\u7f51\u5546\u94f6\u884c\uff0c\u65e0\u5fae\u4e0d\u81f3\"/>\n</View>\n");
}});
