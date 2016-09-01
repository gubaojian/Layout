---
layout: post
title:  "Glue组件库"
date:   2016-06-21 12:46:27 +0800
categories: jekyll update
---

## 组件库

#### View
基本布局容器及元素，所有元素都可使用View的xml属性。
{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<View
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
/>
{% endhighlight %}

<br/>

#### TextView

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<TextView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
text="描述文本内容|{el}"
hint="未输入时的hint"
textColor="#888888"
hintColor="#665522"
highlightColor="#889933"
fontStyle="bold|italic|normal"
textSize="10"
maxLines="10"
textAlign="center|right|left"
breakMode="head|tail|middle"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| text      | 文字内容，支持el表达式 |
| hint      | 未输入是的hint，类似于placeholder |
| textColor | 文字颜色      |
| hintColor | hint文字颜色      |
| highlightColor | 文字高亮颜色      |
| fontStyle | 文字样式     |
| textSize | 文字大小      |
| maxLines |  最大行数，默认一行      |
| textAlign |  文字位置      |
| breakMode |  文字超出之后如何显示      |

<br/>

#### EditText

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<EditText
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
text="描述文本内容|{el}"
hint="未输入时的hint"
textColor="#888888"
hintColor="#665522"
highlightColor="#889933"
fontStyle="bold|italic|normal"
textSize="10"
maxLines="10"
textAlign="center|right|left"
breakMode="head|tail|middle"
inputType="text|password|number|numberPassword"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| text      | 文字内容，支持el表达式 |
| hint      | 未输入是的hint，类似于placeholder |
| textColor | 文字颜色      |
| hintColor | hint文字颜色      |
| highlightColor | 文字高亮颜色      |
| fontStyle | 文字样式     |
| textSize | 文字大小      |
| maxLines |  最大行数，默认一行      |
| textAlign |  文字位置      |
| breakMode |  文字超出之后如何显示      |
| inputType |  输入法键盘类型 密码、数字、数字密码|

<br/>

#### ImageView
图片元素
{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<View
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
imageUrl="图片链接|{el表达式}"
placeHolder="图片未加载时的placeholder"
scaleType="center|centerCrop|centerInside|left|right|fitXY"
/>
{% endhighlight %}
部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| imageUrl      | 图片链接，支持el表达式 |
| placeHolder      | 图片placeHolder，对应本地drawable文件名字|
| scaleType | 图片拉伸策略    |

<br/>

#### Button

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<Button
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
text="描述文本内容|{el}"
hint="未输入时的hint"
textColor="#888888"
hintColor="#665522"
highlightColor="#889933"
fontStyle="bold|italic|normal"
textSize="10"
maxLines="10"
textAlign="center|right|left"
breakMode="head|tail|middle"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| text      | 文字内容，支持el表达式 |
| hint      | 未输入是的hint，类似于placeholder |
| textColor | 文字颜色      |
| hintColor | hint文字颜色      |
| highlightColor | 文字高亮颜色      |
| fontStyle | 文字样式     |
| textSize | 文字大小      |
| maxLines |  最大行数，默认一行      |
| textAlign |  文字位置      |
| breakMode |  文字超出之后如何显示      |

<br/>

#### WrapLayout别名LinearLayout

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<WrapLayout
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
orientation="vertical|horizontal"
gravity="left|right|center|centerVertical|centerHorizontal|top|start|end|"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| orientation      | 布局方向，水平或者垂直 |
| gravity      | 布局的gravity |

<br/>

#### ListView

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<ListView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
/>

{% endhighlight %}

<br/>

#### ScrollView  

垂直滚动组件

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<ScrollView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
xmlUrl="xml布局文件链接"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| xmlUrl      | xml布局文件链接 |

<br/>

#### HScrollView  

垂直滚动组件

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<HScrollView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
xmlUrl="xml布局文件链接"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| xmlUrl      | xml布局文件链接 |

<br/>

#### RefreshLayout

下拉刷新组件

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<HScrollView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
/>
{% endhighlight %}


<br/>

#### RecyclerView


{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<RecyclerView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
orientation="vertical|horizontal"
layout="grid|stagGrid|linear"
columns="2"
/>
{% endhighlight %}


部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| orientation      | 布局方向，水平或者垂直 |
| layout      | layout样式|
| columns      | grid布局时，列数 |


<br/>

#### GridView


{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<GridView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
scrollEnable="true|false"
hSpace="1px"
vSpace="1px"
columns="2"
/>
{% endhighlight %}


部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| scrollEnable      | 是否允许滚动，不允许滚动时gridView高度根据item数量自动升高 |
| hSpace      | 水平列之间的间隙|
| vSpace      | 垂直列之间的间隙|
| columns      | grid的列数 |


<br/>

#### WebView

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<WebView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
url="链接地址"
/>
{% endhighlight %}


部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| url      |  网页链接地址 |

<br/>

#### ViewPager

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<ViewPager
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
xmlUrls="xmlView链接地址"
imgUrls="图片链接地址"
/>
{% endhighlight %}


部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| xmlUrls     |  xml文件的链接地址，多个链接地址用; 分号隔开 |
| imgUrls      |  图片链接地址，多个链接地址用; 分号隔开 |



<br/>

#### PagerIndicator

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<PagerIndicator
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
circleGravity="left|right|center"
circleSize="8"
circleSelectSize="10"
circleColor="#888888"
circleSelectColor="#773322"
circleMargin="2"
viewPagerTag="viewPagerTag"
/>
{% endhighlight %}


部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| circleGravity     |  PagerIndicator的布局位置 |
| circleSize      |   圆点大小 |
| circleSelectSize      |   圆点选中大小 |
| circleColor      |   圆点颜色 |
| circleSelectColor      |   圆点选择器 |
| circleMargin      |   圆点间隙|
| viewPagerTag      |   对应ViewPager的tag名字 |

<br/>

#### IconTextView

{% highlight xml %}
<?xml version="1.0" encoding="utf-8"?>
<TextView
x="10"  
y="10"  
width="10"
height="20"
background="#FF3388"
selectBackground="#882233"
alpha="0.5"
visible="true|false"
enabled="true|false"
tag="22"
text="0xff33"
hint="未输入时的hint"
textColor="#888888"
hintColor="#665522"
highlightColor="#889933"
fontStyle="bold|italic|normal"
textSize="10"
maxLines="10"
textAlign="center|right|left"
breakMode="head|tail|middle"
/>
{% endhighlight %}

部分属性说明：

|属性名          |解释          |
| -------------------------- |--------------------------|
| text      | ionicons 16六进制标识符 |
| hint      | 未输入是的hint，类似于placeholder |
| textColor | 文字颜色      |
| hintColor | hint文字颜色      |
| highlightColor | 文字高亮颜色      |
| fontStyle | 文字样式     |
| textSize | 文字大小      |
| maxLines |  最大行数，默认一行      |
| textAlign |  文字位置      |
| breakMode |  文字超出之后如何显示      |

<br/>
