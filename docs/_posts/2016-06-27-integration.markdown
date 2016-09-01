---
layout: post
title:  "Glue与现有工程整合"
date:   2016-06-21 12:46:27 +0800
categories: jekyll update
---

## Glue与现有工程整合

#### View 基本布局容器

{% highlight xml %}

   <?xml version="1.0" encoding="utf-8"?>

   <View
    x="10"
    y="10"
    width="10"
    height="20"


    >

{% endhighlight %}



#### Native开发面临的问题及H5平台的兴起
   目前Native平台积累了大量的丰富的组件库，以体验好、操作便捷而著称。受限于语言的编译、调试的时间长，native开发效率不高，并且无法动态发布。H5以其跨平台，快速开发，适应变更快速流行。React结合H5和Native的特点，采用H5的方式开发Native，用react组件方式，通过virtual-dom进行解耦，大大提升Native的开发效率。在iOS平台上React是非常不错的开发框架。但React目前也存在不少问题，JS代码多，占用内存大，在Android平台不完善的缺点。


#### Glue的定位及特性

    无缝集成，可调用任何Native的API，充分发挥Native的特性

    简单快速入门，快速开发: 对Native和前端人员友好

    实时预览、动态发布， 采用JavaScript语言，语义化书写，快速预览，动态发布

    无DO， virtual-dom也是dom，以用H5的方式布局，传给Native组件渲染，是一种比较耗内存。

    高性能，低内存

    工具化强，充分利用现有H5工具提升开发效率，如webpack gulp 插件

    扩展性强 快速利用现有Native组件，通过语言工具转换，支持上层语言变化，如可采用TypeScript进行开发

#### Glue的整体架构

  ![Glue 架构](/img/Glue架构.png)


#### 快速入门, Hello World

{% highlight javascript %}

importApi('ui')

ui.toast('Hello World');

// 或者直接调用原生Native的API

importClass("android.widget.Toast");

Toast.makeText(activity, "Hello World", Toast.LENGTH_SHORT).show();

{% endhighlight %}


更多详情[查看][jekyll-docs]

[jekyll-docs]: http://jekyllrb.com/docs/home
[jekyll-gh]:   https://github.com/jekyll/jekyll
[jekyll-talk]: https://talk.jekyllrb.com/
