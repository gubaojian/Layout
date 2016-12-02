# iOS JavaScriptCore demo

This is a demo iOS app that shows how to run JavaScript function from Objective-C. If you are reading this you might also be interested in [JS evaluator for Android](https://github.com/evgenyneu/js-evaluator-for-android) library.


### iOS上javascript core比较强大。原则：主逻辑采用JS控制，尽可能多的迁移到js保持灵活。

### 允许通过js来处理多个参数的selector，允许对现有参数进行封装， 示例


importClass("UIView")
UIView.prototype.setHeader =  function(){

}

UITableView.extends.scrollToRowAtIndexPathAtScrollPositionAnimated = function(indexPath, position, animated){
    __native__c(this, "scrollToRowAtIndexPath: atScrollPosition: animated:", indexPath, position, animated);
}

scrollToRowAtIndexPath: atScrollPosition: animated:
