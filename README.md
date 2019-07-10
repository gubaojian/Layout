Android/iOS Native View Render Engine With JavaScript Support
======
1、Write IOS APP With demo.xml 
    
    <?xml version="1.0" encoding="utf-8"?>
    <View
     y ="20"
     width="320"
     height="240"
     background="#f0f0f0">

    <View
        height="0.5"
        width="288"
        x = "12"
        y = "16"
        background="#02F6AB"
        />
    <TextView width="96"
        height="18"
        fontSize="16"
        text="demo"
        y="7"
        x = "112"
        background="#f0f0f0"
        textAlignment="center"
        valueData="${name}"/>

    <ImageView width="144"
        height="72"
        x="12"
        y="32"
        imageUrl="http://gtms03.alicdn.com/tps/i3/T1d4z2FrxXXXa8j.Iq-288-144.png"
        cornerRadius="4"
        background="#3322A1"/>  
    </View>

2、Infalter View From Xml
   
      UIView* xmlView = [[ViewInfalter shareInflater] viewFromFile:@"demo.xml"];
      [self.view addSubview:xmlView];

3、bind data to view
     
     BinderCallback* binderCallback = [[BinderCallback alloc] init];
     [ViewDataBinder doBindData:[NSDictionary dictionaryWithObjectsAndKeys:@"Demo动态数据",@"name", nil] toView:xmlView withCallback:binderCallback];


4、More Docs

https://github.com/gubaojian/DuktapeJava/

5、Alipay Demo






