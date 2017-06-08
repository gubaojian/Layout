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
  importClass("android.view.View.OnClickListener");
  importClass("android.widget.Toast");
  var helloXml = __webpack_require__(9);
  var page = {onLoad: function onLoad() {
  var imageView = hybridView.__c("findViewWithTag", "imageView");
  imageView.__c("setOnClickListener", new OnClickListener(function() {
  Toast.__c("makeText", activity, "\u539f\u751fAPI\u8c03\u7528", Toast.__g("LENGTH_SHORT")).__c("show");
}));
}};
  ui.__c("alert", activity);
  hybridView.__s("onLoad", function() {
  page.__c("onLoad");
});
  hybridView.__c("loadXml", helloXml);
}, 9: function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n    width=\"750\"\n    height=\"100%\"\n    screenUnit=\"750\">\n   <ImageView\n     width=\"750\"\n     height=\"300\"\n     imageUrl=\"https://zos.alipayobjects.com/rmsportal/VIZVslEXXguQJbi.jpg\"\n     scaleType=\"fitXY\"\n     tag=\"imageView\"/>\n  <TextView\n     y=\"300\"\n     width=\"750\"\n     textAlign=\"center\"\n     height=\"100\"\n     text=\"Hello World \u7f51\u5546\u94f6\u884c\"\n     tag=\"helloTextField\"/>\n</View>\n");
}});
