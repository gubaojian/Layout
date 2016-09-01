
importApi("ui");
importApi("http");
importApi("timer")

var xmlView = require('raw!./test/demo.xml');


http.get("http://www.baidu.com", function(result) {
    ui.alert(result, "确定");
});

hybridView.loadXml(xmlView);

timer.setInterval(function(){
   ui.toast("timer toast");
},  5000);
