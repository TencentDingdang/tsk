# 技能创建

为了简化内容开发者的技能创建流程，平台专门定制了这类技能的开发页面，将一些通用的意图槽位和语料配置封装起来。开发者需要关心的事情是，只需要简单的配置下内容播报源（url地址），即可完成整个技能的构建，操作非常简单。

首先，在”我的技能”页面下，选择创建技能：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E6%8A%80%E8%83%BD.png?raw=true) 

点击创建技能后，在弹出的类型选择页面中选择”内容播报”：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E9%80%89%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5.png?raw=true) 
 
例如，创建一个刘德华资讯的播报技能，技能调用名称叫”刘德华资讯”，专门播放刘德华的最新新闻：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E5%88%98%E5%BE%B7%E5%8D%8E%E8%B5%84%E8%AE%AF.png?raw=true) 
 
点击创建，即完成技能的创建过程：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E5%AE%8C%E6%88%90%E5%88%9B%E5%BB%BA.png?raw=true) 
 
如上图，在技能列表中可以看到刚才创建好的技能。

## 服务配置

进一步进行技能的服务配置，需要打开技能的子页面：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E6%9C%8D%E5%8A%A1%E9%85%8D%E7%BD%AE.png?raw=true) 
 
可以看到有一个专门的服务配置页面供开发者填写。在该页面上我们填写刘德华资讯的内容播报源：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E6%9C%8D%E5%8A%A1%E9%85%8D%E7%BD%AE2.png?raw=true) 
 
点击保存，一个用于查询刘德华最新资讯的技能就构建完毕。当用户说”打开刘德华资讯帮我来点娱乐消息”时，技能会根据内容源地址找到刘德华的有关新闻，然后返回给用户。

为了验证是否配置正确，我们可以打开右侧的快速体验框，输入”打开刘德华资讯帮我来点娱乐消息”，可以看到返回了正确的语义识别结果，其中liudehua_news就是当前技能标识：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E5%86%85%E5%AE%B9%E6%92%AD%E6%8A%A5%E6%B5%81%E7%A8%8B-%E5%BF%AB%E9%80%9F%E4%BD%93%E9%AA%8C.png?raw=true) 
 
## 技能发布

以上完成了一个技能的简单配置，当需要把这个技能让终端用户在正式环境使用时，需要对技能进行发布。具体发布方法与自定义技能->技能发布相同。此处不重复累述.