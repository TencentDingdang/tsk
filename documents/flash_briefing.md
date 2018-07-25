
腾讯叮当 简讯播报文档 API说明

> 腾讯叮当的简讯内容播报技能将会读取开发者定义的内容展示或者播报给用户。内容以有一定要求固定格式的文本为载体。本API文档要求开发者提供遵循RSS2.0格式或者Json格式要求。

[TOC]

## 前提要求

+ 提供的HTTPS链接应当没有密码保护，方便快速获取到链接内容，建议提供HTTPS链接
+ 提供的HTTPS链接应该稳定且在保证在一定有效期内有效，响应时间需要小于1秒，响应时间太长将会调低推送优先级，建议维持在100ms左右
+ 格式要求，RSS2.0、Json
+ 内容编码要求为 UTF-8
+ 其它字段要求见下表



RSS 2.0 格式
----

### channel节点

字段|是否必须|含义
---|---|---
ttl|必须|缓存刷新时间间隔（秒）
item|必须|子项目节点，不得超过5个，如果item超过5个，腾讯叮当将会读取前5个item
copyright|非必须|版权声明
lastBuildDate|非必须|内容更新时间
language|非必须|语言


### item项目节点

字段|是否必须|含义
---|---|---
guid|必须|资源标识码，需要开发者生成唯一guid，用以标识item的唯一性
title|必须|标题,尽量简短，不要超过100字
description|非必须|纯文本的详细描述。无屏设备将会朗读这个字段，不要提供超过500字简报
enclosure|非必须|用于播放多媒体的地址，时间长度不要超过10分钟
link|非必须|原文地址
pubDate|非必须|内容发布时间
resource|非必须|用来存放图片地址等其他信息


__注__:

<font color=#ff0000>description,resource和 enclosure,必须有其一，否则判定无内容。</font>description和 enclosure两者同时有内容则取<font color=#ff0000> enclosure</font>作为播放源

category 目前 可取值为:

新闻类:

`科技`、`财经`、`娱乐`、`体育`、`社会`、`军事`、`时事`、`国际`、`国内`、`综合`

有声类:

`广播`、`笑话`、`相声`、`小品`、`小说`、`评书`、`公开课`、`脱口秀`、`诗词`、`历史`、`综合`

***

Json 格式
-----

字段|是否必须|含义
---|---|---
guid|必须|资源标识码，需要开发者生成唯一guid，用以标识item的唯一性
category|必须|分类
title|必须|标题
description|非必须|纯文本的详细描述。无屏设备将会朗读这个字段，不要提供超过5000字简报
streamUrl|非必须|用于播放多媒体的地址，时间长度不要超过10分钟
link|非必须|原文地址
pubDate|非必须|内容发布时间
imgUrl|非必须|图片地址

__注__:

<font color=#ff0000>description,imgUrl和 enclosure,必须有其一，否则判定无内容。</font>description和 enclosure两者同时有内容则取<font color=#ff0000> enclosure</font>作为播放源

category 目前 可取值为:

新闻类:

`科技`、`财经`、`娱乐`、`体育`、`社会`、`军事`、`时事`、`国际`、`国内`、`综合`

有声类:

`广播`、`笑话`、`相声`、`小品`、`小说`、`评书`、`公开课`、`脱口秀`、`诗词`、`历史`、`综合`

举例
-----

### 自定义有声内容播报

+ RSS格式

```sh
<?xml version="1.0" encoding="UTF-8" ?>
<rss version="2.0">
  <channel>
    <ttl>86400</ttl>
    <item>
      <guid>da36194c07c640a9f1d59354e691b6620bbc80ab</guid>
      <title>刘德华将拍《扫毒2》《拆弹2》</title>
      <category>娱乐</category>
      <description>
        刘德华将接拍两部续集电影，6月先拍《扫毒2》，明年开拍《拆弹专家2》。自去年坠马受伤后，华仔休息了半年，伤愈复工后，最先公布年尾将在香港举办圣诞、跨年演唱。“寰宇娱乐”昨天（3月16日）宣布，将开拍《扫毒2》和《拆弹2》。 刘德华是首次加盟《扫毒2》，除了演出，也兼任监制。此片将在香港和菲律宾取景，制作费将加码超过2亿港币（约3360万新元）。 配合下周举行的香港国际影视展2018，《扫毒2》和《拆弹2》的概念海报于日前相继曝光。除了华仔和首集的“古仔”古天乐，谁将是第三位加入阵容的重量级男星呢？另外，古天乐将以不同于首集的角色出现。
      </description>
      <link>http://www.zaobao.com/zentertainment/celebs/story20180317-843479</link>
      <pubDate>2018-04-09T00:00:00.0Z</pubDate>
      <enclosure url="http://3gimg.qq.com/trom_s/vrbrowser/audio_file/20015259_1f0c95aeabe06148a0c0f4ecfd5f6bf1.mp3"
        length="239868" type="audio/mpeg" />
        <source url="http://www.zaobao.com/sites/default/files/styles/article_large_full/public/images/201804/20180422/ZB_0420_CJ_doc6zrzjylboab1gpm40czn_19170932_chiangcf.jpg">Image</source>
     </item>
  </channel>
</rss>
```

+ Json格式

```json
{
    "guid": "da36194c07c640a9f1d59354e691b6620bbc80ab",
    "category": "娱乐",
    "pubDate": "2016-05-23T00:00:00.0Z",
    "title": "刘德华将拍《扫毒2》《拆弹2》",
    "description": "刘德华将接拍两部续集电影，6月先拍《扫毒2》，明年开拍《拆弹专家2》。自去年坠马受伤后，华仔休息了半年，伤愈复工后，最先公布年尾将在香港举办圣诞、跨年演唱。“寰宇娱乐”昨天（3月16日）宣布，将开拍《扫毒2》和《拆弹2》。 刘德华是首次加盟《扫毒2》，除了演出，也兼任监制。此片将在香港和菲律宾取景，制作费将加码超过2亿港币（约3360万新元）。 配合下周举行的香港国际影视展2018，《扫毒2》和《拆弹2》的概念海报于日前相继曝光。除了华仔和首集的“古仔”古天乐，谁将是第三位加入阵容的重量级男星呢？另外，古天乐将以不同于首集的角色出现。",
    "link": "http://www.zaobao.com/zentertainment/celebs/story20180317-843479",
    "streamUrl": "http://3gimg.qq.com/trom_s/vrbrowser/audio_file/20015259_1f0c95aeabe06148a0c0f4ecfd5f6bf1.mp3"
}
```

### 文本内容TTS播报

+ RSS格式

```sh
<?xml version="1.0" encoding="UTF-8" ?>
<rss version="2.0">
  <channel>
    <ttl>86400</ttl>
    <item>
      <guid>da36194c07c640a9f1d59354e691b6620bbc80ab</guid>
      <title>刘德华将拍《扫毒2》《拆弹2》</title>
      <category>娱乐</category>
      <description>
        刘德华将接拍两部续集电影，6月先拍《扫毒2》，明年开拍《拆弹专家2》。自去年坠马受伤后，华仔休息了半年，伤愈复工后，最先公布年尾将在香港举办圣诞、跨年演唱。“寰宇娱乐”昨天（3月16日）宣布，将开拍《扫毒2》和《拆弹2》。 刘德华是首次加盟《扫毒2》，除了演出，也兼任监制。此片将在香港和菲律宾取景，制作费将加码超过2亿港币（约3360万新元）。 配合下周举行的香港国际影视展2018，《扫毒2》和《拆弹2》的概念海报于日前相继曝光。除了华仔和首集的“古仔”古天乐，谁将是第三位加入阵容的重量级男星呢？另外，古天乐将以不同于首集的角色出现。
      </description>
      <link>http://www.zaobao.com/zentertainment/celebs/story20180317-843479</link>
      <pubDate>2018-04-09T00:00:00.0Z</pubDate>
     </item>
  </channel>
</rss>
```

+ Json格式

```json
{
  "guid": "da36194c07c640a9f1d59354e691b6620bbc80ab",
  "category":"娱乐",
  "pubDate": "2016-05-23T00:00:00.0Z",
  "title": "刘德华将拍《扫毒2》《拆弹2》",
  "description": "刘德华将接拍两部续集电影，6月先拍《扫毒2》，明年开拍《拆弹专家2》。自去年坠马受伤后，华仔休息了半年，伤愈复工后，最先公布年尾将在香港举办圣诞、跨年演唱。“寰宇娱乐”昨天（3月16日）宣布，将开拍《扫毒2》和《拆弹2》。 刘德华是首次加盟《扫毒2》，除了演出，也兼任监制。此片将在香港和菲律宾取景，制作费将加码超过2亿港币（约3360万新元）。 配合下周举行的香港国际影视展2018，《扫毒2》和《拆弹2》的概念海报于日前相继曝光。除了华仔和首集的“古仔”古天乐，谁将是第三位加入阵容的重量级男星呢？另外，古天乐将以不同于首集的角色出现。",
  "link": "http://www.zaobao.com/zentertainment/celebs/story20180317-843479",
  "imgUrl": "http://www.zaobao.com/sites/default/files/styles/article_large_full/public/images/201804/20180422/ZB_0420_CJ_doc6zrzjylboab1gpm40czn_19170932_chiangcf.jpg"
}
```


### 多个条目的例子

+ Json格式

```json
[
    {
        "guid": "40a9f94c07c640a9f1d59354e691b6620bb9354e",
        "category": "娱乐",
        "pubDate": "2018-04-22T00:00:00.0Z",
        "title": "与刘德华合演《拆弹专家》 姜皓文：比享受更享受",
        "description": "姜皓文今年有两部电影入围，结果《拆弹》里的火爆警察更吸睛，令他夺奖，他估计评审大概没见过他演绎那么正派的警察角色，加分不少，与监制兼男主角刘德华合作，姜皓文形容“比享受更享受”。他赞说：“跟刘先生拍戏，首先就有视觉享受，我曾趁他拍戏时偷拍他，刘先生给我很多信心，会跟我沟通，教我怎么拍会更得心应手。”他尊称华仔为“刘先生”。",
        "link": "http://www.zaobao.com/zentertainment/celebs/story20180422-852803",
        "imgUrl": "http://www.zaobao.com/sites/default/files/styles/article_large_full/public/images/201804/20180422/ZB_0420_CJ_doc6zrzjylboab1gpm40czn_19170932_chiangcf.jpg"
    },
    {
        "guid": "da36194c07c640a9f1d59354e691b6620bbc80ab",
        "category": "娱乐",
        "pubDate": "2016-05-23T00:00:00.0Z",
        "title": "刘德华将拍《扫毒2》《拆弹2》",
        "description": "刘德华将接拍两部续集电影，6月先拍《扫毒2》，明年开拍《拆弹专家2》。自去年坠马受伤后，华仔休息了半年，伤愈复工后，最先公布年尾将在香港举办圣诞、跨年演唱。“寰宇娱乐”昨天（3月16日）宣布，将开拍《扫毒2》和《拆弹2》。 刘德华是首次加盟《扫毒2》，除了演出，也兼任监制。此片将在香港和菲律宾取景，制作费将加码超过2亿港币（约3360万新元）。 配合下周举行的香港国际影视展2018，《扫毒2》和《拆弹2》的概念海报于日前相继曝光。除了华仔和首集的“古仔”古天乐，谁将是第三位加入阵容的重量级男星呢？另外，古天乐将以不同于首集的角色出现。",
        "link": "http://www.zaobao.com/zentertainment/celebs/story20180317-843479"
    }
]
```

+ RSS格式

```sh
<?xml version="1.0" encoding="UTF-8" ?>
<rss version="2.0">
  <channel>
    <ttl>86400</ttl>
    <item>
      <guid>da36194c07c640a9f1d59354e691b6620bbc80ab</guid>
      <title>刘德华将拍《扫毒2》《拆弹2》</title>
      <category>娱乐</category>
      <description>
        刘德华将接拍两部续集电影，6月先拍《扫毒2》，明年开拍《拆弹专家2》。自去年坠马受伤后，华仔休息了半年，伤愈复工后，最先公布年尾将在香港举办圣诞、跨年演唱。“寰宇娱乐”昨天（3月16日）宣布，将开拍《扫毒2》和《拆弹2》。 刘德华是首次加盟《扫毒2》，除了演出，也兼任监制。此片将在香港和菲律宾取景，制作费将加码超过2亿港币（约3360万新元）。 配合下周举行的香港国际影视展2018，《扫毒2》和《拆弹2》的概念海报于日前相继曝光。除了华仔和首集的“古仔”古天乐，谁将是第三位加入阵容的重量级男星呢？另外，古天乐将以不同于首集的角色出现。
      </description>
      <link>http://www.zaobao.com/zentertainment/celebs/story20180317-843479</link>
      <pubDate>2018-04-09T00:00:00.0Z</pubDate>
        <source url="http://www.zaobao.com/sites/default/files/styles/article_large_full/public/images/201804/20180422/ZB_0420_CJ_doc6zrzjylboab1gpm40czn_19170932_chiangcf.jpg">Image</source>
    </item>
    <item>
      <guid>40a9f94c07c640a9f1d59354e691b6620bb9354e</guid>
      <title>与刘德华合演《拆弹专家》 姜皓文：比享受更享受</title>
      <category>娱乐</category>
      <description>
        姜皓文今年有两部电影入围，结果《拆弹》里的火爆警察更吸睛，令他夺奖，他估计评审大概没见过他演绎那么正派的警察角色，加分不少，与监制兼男主角刘德华合作，姜皓文形容“比享受更享受”。他赞说：“跟刘先生拍戏，首先就有视觉享受，我曾趁他拍戏时偷拍他，刘先生给我很多信心，会跟我沟通，教我怎么拍会更得心应手。”他尊称华仔为“刘先生”。
      </description>
      <link>http://www.zaobao.com/zentertainment/celebs/story20180422-852803</link>
      <pubDate>2018-04-22T00:00:00.0Z</pubDate>
     </item>
  </channel>
</rss>
```