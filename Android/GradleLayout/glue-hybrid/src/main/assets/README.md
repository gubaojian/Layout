### 注意不要调用动态绑定的参数的方法



### 内存回收 测试, 1000 - 10000左右new string操作

api扩充


http://www.apicloud.com/cloudmanage


http://docs.apicloud.com/Client-API/Device-Access/bluetooth


简单的动画支持.  从左到右,从上到下.  渐变.


滚动优化


file api

console.log 支持


此方法针对JSRef 做优化
 private static Method getMethod(Class<?> clazz, String methodName, Object... args) {
    

定位:


胶水语言,无缝结合


js 运营出错,直接谈alert框进行提示.


viewpager gridview scrollview 增加直接用xml中加载的方法



避免陷入大而全的最后融为H5的问题, 胶水语言,无缝结合
 
小巧精致,快速上手. 面向native开发和想学native开发的js人员


no-dom的思想,和Native无缝集合


TodoList: 

支付宝4个tab完成, 本周


pagerSlide 增加.  优化4个tab  下周

todo:

bradgeview 处理. 下周

dialog增加.  下周


DrawableUtils 类 支持圆角背景的drawable // 思考

设置titlebar的背景色


增加原生类的支持, 所有google内置的原声类都看一下,思考加上去, 增加演示的uikit.  下周


recycleview的浮动增加效果 下周


增加网商银行, 手套首页的demo 手淘搜索页面 下周

组件如何共用


做分享  下周.


如何支持组件化开发,  html ejs引擎来实现,


根据不同的平台,处理不同的js




### ref应用重用， 一定要

### 多线程的难题，攻克

### logo优化

## ios

### block支持，更优雅，更省内存的方式支持。

### 方法名字映射？


    JSC::JSObject* jsWrapper = m_cachedJSWrappers->get(object);
    if (jsWrapper)
        return [JSValue valueWithJSValueRef:toRef(jsWrapper) inContext:m_context];

    if (class_isMetaClass(object_getClass(object)))
        jsWrapper = [[self classInfoForClass:(Class)object] constructor];
    else {
        JSObjCClassInfo* classInfo = [self classInfoForClass:[object class]];
        jsWrapper = [classInfo wrapperForObject:object];
    }

    // FIXME: https://bugs.webkit.org/show_bug.cgi?id=105891
    // This general approach to wrapper caching is pretty effective, but there are a couple of problems:
    // (1) For immortal objects JSValues will effectively leak and this results in error output being logged - we should avoid adding associated objects to immortal objects.
    // (2) A long lived object may rack up many JSValues. When the contexts are released these will unprotect the associated JavaScript objects,
    //     but still, would probably nicer if we made it so that only one associated object was required, broadcasting object dealloc.
    m_cachedJSWrappers->set(object, jsWrapper);
    return [JSValue valueWithJSValueRef:toRef(jsWrapper) inContext:m_context];