# 技能创建
平台支持开发者定制自定义技能。现介绍自定义技能的创建、开发、体验、发布的一整套流程。首先，进入“我的技能”页面：
 ![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%88%91%E7%9A%84%E6%8A%80%E8%83%BD.png?raw=true)
 
点击创建技能，选择技能类型为“自定义”：
 ![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E6%8A%80%E8%83%BD.png?raw=true)

点击下一步，打开技能信息填写页面，填写技能分类、名称、标识等信息：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E9%85%8D%E7%BD%AE.png?raw=true)
 
比如，创建了一个"查天气"技能如下：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%9F%A5%E5%A4%A9%E6%B0%94.png?raw=true)
 
点击技能，进入具体技能的各个配置页面，包括技能基本信息、我的意图、服务配置、技能训练、技能发布。

## 基本信息
在基本信息页上，可以修改技能的分类、名称、调用名、描述、图标等：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%8A%80%E8%83%BD%E4%BF%A1%E6%81%AF.png?raw=true)
 技能标识一旦填写，不能再被修改。因此对技能标识的命名需要谨慎。
 
## 我的意图
我的意图页面上，可以为该技能创建意图、引用系统意图，以及往下进行配置意图。页面如下：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%88%91%E7%9A%84%E6%84%8F%E5%9B%BE.png?raw=true)

点击创建意图，弹出创建意图窗口，填写意图基本信息，包括意图名称、意图标识、意图描述、查询示例：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E6%84%8F%E5%9B%BE.png?raw=true)

点击保存后，可以看到自己创建的意图：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%88%91%E7%9A%84%E6%84%8F%E5%9B%BE%E6%9F%A5%E5%A4%A9%E6%B0%94.png?raw=true)
 
创建了一个意图后，需要进行意图的语料和槽位配置。
点击“查天气温度”的意图，进入意图页面：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%99%AE%E9%80%9A%E8%AF%AD%E6%96%99.png?raw=true)

一个意图主要包括槽位、以及所需的语料。为了意图识别，需要创建槽位、添加语料。

1. 创建槽位

![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%84%8F%E5%9B%BE%E6%A7%BD%E4%BD%8D.png?raw=true)
在意图槽位页面下，点击创建槽位，输入相关信息：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E6%A7%BD%E4%BD%8D.png?raw=true)

创建槽位时，需要必需填写槽位名称、标识、实体库。实体库可以选择来自平台提供的系统实体库(sys开头)，也可以是自定义的实体库(usr开头)。

2. 自定义实体库

当现有的实体库仍无法满足需求时，可以点击”创建实体库”，进行创建一个新的自定义实体库。
例如，点击“创建实体库“，在弹出的窗口中创建一个个性化地址实体库，用于支持老王家的天气查询：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E5%AE%9E%E4%BD%93%E5%BA%93.png?raw=true)

实体库的创建需要至少输入名称、标识。点击创建后显示实体列表页面：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E8%87%AA%E5%BB%BA%E5%AE%9E%E4%BD%93.png?raw=true)
添加实体时，可以输入一个或多个别名：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%B7%BB%E5%8A%A0%E5%AE%9E%E4%BD%93.png?raw=true)

点击添加，保存新的实体到实体库中：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E8%87%AA%E5%BB%BA%E5%AE%9E%E4%BD%93.png?raw=true)

然后，重新打开槽位页面，在实体库选择列表处点击”刷新”，选择刚自定义的实体库usr.special_place：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E7%BC%96%E8%BE%91%E6%A7%BD%E4%BD%8D.png?raw=true)
 
保存后，该槽位将会与一个系统实体库和一个自定义的实体库相关联。即可以满足通用的地名叫法识别（比如”北京”），也可以满足个性化的地名叫法识别（比如”老王家”）。

除了槽位定义，平台同时支持槽位引用。当某个意图所需的槽位在该技能的其它意图下已有创建时，可以不用重复创建，直接引用。具体槽位引用的操作，与槽位创建页面相似。

3. 添加语料

当创建完槽位和添加实体后，需要我们添加一些宽泛的用户常用说法，我们称之为语料。这些语料会被用于模版识别和模型训练，帮助识别该意图的更多的说法。
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%B7%BB%E5%8A%A0%E8%AF%AD%E6%96%99.png?raw=true)

点击添加语料，打开窗口输入语料：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%B7%BB%E5%8A%A0%E8%AF%AD%E6%96%99%E7%AA%97%E5%8F%A3.png?raw=true)
 
同时，可以按住鼠标滑动文字，对实体进行标注。标注的语料会被用于模版识别。由于模版不需训练，建议开发者尽量把语料标注为模版，这样可以保证对满足该类模版的语料识别快速生效。
例如，按住鼠标滑动文字”北京”，会弹出槽位列表供选择：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%B7%BB%E5%8A%A0%E8%AF%AD%E6%96%992.png?raw=true)
 
由于”北京”是要查询的天气地名，我们选择location槽位。标记后，平台自动展示标注的槽位信息：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%A7%BD%E4%BD%8D%E6%A0%87%E8%AE%B0.png?raw=true)
 
最后点击添加，将该语料保存起来：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E4%BF%9D%E5%AD%98%E8%AF%AD%E6%96%99.png?raw=true)
 
以上为保存后的语料展示页面。

4. 快速体验

快速体验，是帮助开发者在添加了语料和实体后，可以快速验证效果，方便迭代优化意图问法设计。

在语料页面的右侧，点击”快速体验”，打开快速体验页面：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%BF%AB%E9%80%9F%E4%BD%93%E9%AA%8C.png?raw=true) 
 
输入想要体验的问法”帮我查下北京的天气状况”，可以看到被识别到了查天气意图，说明识别成功：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%BF%AB%E9%80%9F%E4%BD%93%E9%AA%8C%E8%AF%A6%E6%83%85.png?raw=true)
 
通过这种方式，开发者可以不断完善意图和语料检测，加快开发效率。需要注意的是，快速体验并不是在正式环境生效，而是后台自动生成一个沙箱环境供开发者使用。要在正式环境生效，需要开发者到”技能发布”页面上提交技能发布。

## 服务配置

平台支持为技能开发者提供服务配置，开发者可以通过web service和腾讯云SCF两种方式配置服务的部署地址。目前这部分正在联调中，后续开放。

## 技能训练

除了满足模版识别，我们还支持开发者调用机器学习算法进行技能训练。
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%8A%80%E8%83%BD%E8%AE%AD%E7%BB%83.png?raw=true)
 
开发者只需要在页面上创建一个训练任务，后台会自动对语料和实体扽数据进行预处理，通过大数据技术学习技能的特征和模型分类器，让技能识别更聪明。
点击创建任务按钮，输入任务说明：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%88%9B%E5%BB%BA%E8%AE%AD%E7%BB%83%E4%BB%BB%E5%8A%A1.png?raw=true)
 
点击创建后，平台开始进行技能训练，并实时同步当前训练的进度：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%8A%80%E8%83%BD%E8%AE%AD%E7%BB%83%E5%88%97%E8%A1%A8.png?raw=true)
 
技能训练完成后，可以到快速体验窗口，进行二次体验。

## 技能发布

当一个技能满足要发布的状态时，可以到技能发布页面上提交发布任务：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E6%8A%80%E8%83%BD%E5%8F%91%E5%B8%83.png?raw=true) 
 
点击创建任务，输入要发布任务的说明：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%8F%91%E5%B8%83%E4%BB%BB%E5%8A%A1.png?raw=true) 
 
当点击创建后，平台开始一个新的发布任务，开发者同时可以看到发布状态：
![image](https://github.com/tidewang/MyPostImage/blob/master/%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B5%81%E7%A8%8B-%E5%8F%91%E5%B8%83%E7%8A%B6%E6%80%81.png?raw=true)
 
成功发布的技能会到技能商店，让更多的终端用户使用。在发布流程中，技能发布有一套严格的审核流程，我们有专门的后台审核人员对技能的内容和质量进行把控。技能只有通过审核后才能发布。

