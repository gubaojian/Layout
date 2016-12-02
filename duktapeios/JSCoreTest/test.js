
/**
 * android 版本去掉无用的执行代码，提高执行效率�? �? get_top这些操作
 */

function testNew(){
    importClass("UIView")
    importClass("UITableView")
    importClass("NSMutableArray");

    var view = new UIView();
    var view2 = UIView.__c("alloc").__c("init");

    var tableView = new UITableView();

    if(view
       && view2
       && tableView ){
    }

    print(view);

    print(view2);

    return true;
}

function testViewController(){
    controller.__c("alert:", "hello world, js alert");
    return true;
}

function testTypesTest(){
    var block = typesTest.__c("getBlock");
    block("ddd", "ddd");
    print(block);
    return true;
}






testTypesTest();
