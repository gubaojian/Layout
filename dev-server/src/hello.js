importApi("ui");
importClass("android.view.View.OnClickListener");
importClass("android.widget.Toast");

var helloXml = require('raw!./hello.xml');
var page = {
    onLoad : function () {

        var imageView = hybridView.findViewWithTag("imageView");
        imageView.setOnClickListener(new OnClickListener(function(){
            Toast.makeText(activity, "原生API调用", Toast.LENGTH_SHORT).show();
        }));

        /**
        ui.click("imageView", function(view){
             ui.toast(view);
             view.setOnClickListener(new OnClickListener(function(){
                  Toast.makeText(activity, "原生API调用", Toast.LENGTH_SHORT).show();
             }));
        });*/
    }
};
ui.alert(activity);
hybridView.onLoad = function () {
    page.onLoad();
}
hybridView.loadXml(helloXml);
