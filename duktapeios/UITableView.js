importClass("UITableView")

UITableView.prototype.scrollToRowAtIndexPathAtScrollPositionAnimated = function(indexPath, position, animated){
    __native__c(this, "scrollToRowAtIndexPath:atScrollPosition:animated:", indexPath, position, animated);
}
