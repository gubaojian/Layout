importApi("ui");

importClass("android.widget.BaseAdapter");

var listXml = require('raw!./list.xml');
var listItemXml = require('raw!./list/listItem.xml');


var page = {
    onLoad : function () {

      var listView = ui.find("listView");


      listView.setAdapter(new BaseAdapter({
        getCount : function() {
          return 80;
        },
        getView : function(position,  convertView, parent){
            if(convertView == null){
                convertView = ui.fromXml(listItemXml, null);
            }
            return convertView;
        },
        getViewTypeCount : function () {
            return 1;
        }
      }));



    }
};
hybridView.onLoad = function(result){
     page.onLoad();
};

hybridView.loadXml(listXml);
