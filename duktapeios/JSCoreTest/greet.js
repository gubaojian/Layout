importClass("UIView")
importClass("UITableView")
importClass("NSMutableArray");

var view = new UIView();
//var view2 = UIView.__c("alloc").__c("init");



var tableView = new UITableView();
tableView.__c("addSubview:", view);

print(tableView);

//var d = tableView.__c("separatorStyle");
//print(d);


//d = tableView.__c("rowHeight");
//print(d);

//var frame =  {x:10, y:9, width:4, height:200};

//print("frame " + frame.x);

//d = tableView.__c("setFrame:", frame);

//frame = tableView.__c("frame");

//var x = frame.x;
//print("frame x " + frame.x);

//var a = "hello world";

//print(a);

//print(tableView);

//var backgroundView =  tableView.__c("backgroundView");

//print(backgroundView);

/**
Object.prototype.__c = function(){
    if(this.__oc){
        if(arguments.length == 0){
             return __native__c(this);
        }else if(arguments.length == 1){
            return __native__c(this, arguments[0]);
        }else if(arguments.length == 2){
            return __native__c(this, arguments[0], arguments[1]);
        }else if(arguments.length == 3){
            return __native__c(this, arguments[0], arguments[1], arguments[2]);
        }else if(arguments.length == 4){
            return __native__c(this, arguments[0], arguments[1], arguments[2], arguments[3]);
        }else if(arguments.length == 5){
            return __native__c(this,  arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);
        }else if(arguments.length == 6){
            return __native__c(this,  arguments[0], arguments[1], arguments[2], arguments[3], arguments[5],  arguments[6]);
        }else{
            throw new Error(this + 'too many arguments, max arguments is 6');
        }
    }else{
       return this.apply(this, arguments);
    }
};



importClass("UITableView")
importClass("NSMutableArray");


//UITableView.init();

var a = UITableView.__c("init");

log(a);

var array = NSMutableArray.__c("init");

log(array);

var d = a.__c("separatorStyle");
log(d);

// 自动类型换号

// 所有object类型自动封装，不允许自动类型转换

var a = "hello world";

//a.__c("startwith", "ddd");


//


UIView.alloc().init();

//UIView.__c("alloc").__c("init");






function greet(name) {
  return "Hello, " + name + "!";
}*/
