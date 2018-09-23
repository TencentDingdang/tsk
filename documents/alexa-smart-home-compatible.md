
# Alexa Smart Home Skill兼容方案
腾讯叮当支持原Alexa Smart Home技能接入，由于Alexa Smart Home的Skill Adapter通过[AWS Lambda](https://amazonaws-china.com/cn/lambda)进行接入，因此需要创建一个 [API Gateway](https://amazonaws-china.com/cn/api-gateway)作为腾讯叮当智能家居访问AWS Lambda的入口。

## Step 1: 创建API Gateway
进入[Amazon API Gateway](https://console.aws.amazon.com/apigateway/home)服务，点击“创建API”按钮：

1. API类型选择“__新建API__”；
2. “__设置__”中填写API名称，例如：“__SmartHomeAdapter__”；
3. 点击“__创建API__”进入下一步；

依次进入“__资源 >> 操作 >> 创建方法__”，为“__SmartHomeAdapter__”资源创建“__POST__”访问方法；

1. “__集成环境__”选择“__Lambda 函数__”
2. 选择Alexa Smart Home Skill对应的AWS Lambda函数所在的区域；
3. 填写Alexa Smart Home Skill对应的AWS Lambda函数的函数名名；

## Step 2: 创建IAM
为了API Gateway接口的安全，你需要在AWS上创建一个IAM用户，并分配相应的API Gateway访问策略给腾讯叮当智能家居API。

进入[AWS IAM](https://console.aws.amazon.com/iam/home#/users)服务，进入“__策略__”选项目录，点击“__创建策略__”：

1. 选择“__策略生成器__”，进入下一步；
2. 效果选择“**允许**”，AWS服务选择“**ExecuteAPI**”，操作选择“**Invoke**”，ARN填写[Step 1](#step-1-api-gateway)创建的API Gateway资源的ARN，进入下一步；
3. 填写策略名称，例如“__AccessSmartHomeAdapter__”，点击创建策略；

进入[AWS IAM](https://console.aws.amazon.com/iam/home#/users)服务“__用户__”选项目录，点击“__创建用户__”：

1. 填写用户名，例如“__Dingdang__”；
2. “__访问类型__”选择“__编程访问__”；
3. 进入下一步；

进入为“__Dingdang设定权限__”的页面，选择“__直接附加现有策略__”：

1. 选择新创建的“__AccessSmartHomeAdapter__”策略；
2. 点击“__下一步：审核__”，进入审核页面；
3. 点击创建用户，记录“__访问密钥ID__”和“__私有访问密钥__”（私有访问密钥只显示一次，因此可以保存下来），后续需要提供给腾讯叮当用于访问API Gateway；

## Step 3: 为API Gateway设置验证方式
进入[Step 1](#step-1-api-gateway)创建的的资源的POST方法，点击“__方法请求__”卡片的链接，进入该方法的设置页面，修改“__授权__”的值为“__AWS_IAM__”并保存；

## Step 4: 部署API
进入[Step 1](#step-1-api-gateway)创建的的资源，点击“__操作 >> 部署API__”，部署阶段选择已创建的阶段或者重新创建一个新阶段，比如“__release__”，点击“__部署__”后，获得“__调用URL__”；

至此，兼容Alexa Smart Home所需的配置工作已经完成。在配置过程中的一些信息需要在注册腾讯叮当智能家居技能的时候提供给腾讯叮当智能家居API：

1. API Gateway调用URL；
2. API Gateway所在的区域名称，比如“us-east-1”、“us-west-2”等；
3. API Gateway“访问密钥ID”和“私有访问密钥”；

需要注意的是，如果采用此兼容方式接入腾讯叮当智能家居，所提供用于OAuth 2.0验证的ClientID和ClientSecret应该区别于Alexa Smart Home，避免Access Token的刷新互相冲突。
