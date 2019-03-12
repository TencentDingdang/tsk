# 腾讯叮当智能家居开放平台接入文档

> 文档更新于2019/03/12，点击查看[更新日志](#更新日志)。

腾讯叮当智能家居开放平台提供了一套与智能硬件厂商的云端进行数据交互的规范，能够让接入腾讯叮当智能家居开放平台的智能设备具备接受语音进行控制的能力。腾讯叮当智能家居技能是通过给智能硬件厂商的云端发送指令的方式，通过厂商的云端进行控制智能硬件的，因此，接入腾讯叮当智能家居开放平台的设备首先需要具备通过云端进行设备管理的能力。交互流程如下图所示：
![](./pic/smarthome-skill-interaction-flow.png)

如果你的设备已经接入了Alexa Smart Home，腾讯叮当提供了一套兼容原有Alexa Smart Home技能的方案，用于快速将Alexa的技能接入到腾讯叮当智能家居中，具体请参照[Alexa Smart Home Skill兼容方案](./alexa-smart-home-compatible.md)。

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [权限验证](#权限验证)
- [处理腾讯叮当智能家居请求](#处理腾讯叮当智能家居请求)
	- [消息头(header)](#消息头header)
	- [消息体(payload)](#消息体payload)
	- [设备发现消息](#设备发现消息)
		- [DiscoverAppliancesRequest](#discoverappliancesrequest)
		- [DiscoverAppliancesResponse](#discoverappliancesresponse)
	- [打开/关闭消息](#打开关闭消息)
		- [TurnOnRequest](#turnonrequest)
		- [TurnOnConfirmation](#turnonconfirmation)
		- [TurnOffRequest](#turnoffrequest)
		- [TurnOffConfirmation](#turnoffconfirmation)
	- [灯光调节消息](#灯光调节消息)
		- [SetColorRequest](#setcolorrequest)
		- [SetColorConfirmation](#setcolorconfirmation)
		- [IncrementBrightnessPercentageRequest](#incrementbrightnesspercentagerequest)
		- [IncrementBrightnessPercentageConfirmation](#incrementbrightnesspercentageconfirmation)
		- [DecrementBrightnessPercentageRequest](#decrementbrightnesspercentagerequest)
		- [DecrementBrightnessPercentageConfirmation](#decrementbrightnesspercentageconfirmation)
		- [SetBrightnessPercentageRequest](#setbrightnesspercentagerequest)
		- [SetBrightnessPercentageConfirmation](#setbrightnesspercentageconfirmation)
		- [IncrementColorTemperatureRequest](#incrementcolortemperaturerequest)
		- [IncrementColorTemperatureConfirmation](#incrementcolortemperatureconfirmation)
		- [DecrementColorTemperatureRequest](#decrementcolortemperaturerequest)
		- [DecrementColorTemperatureConfirmation](#decrementcolortemperatureconfirmation)
		- [SetColorTemperatureRequest](#setcolortemperaturerequest)
		- [SetColorTemperatureConfirmation](#setcolortemperatureconfirmation)
	- [温度调节类消息](#温度调节类消息)
		- [SetTemperatureRequest](#settemperaturerequest)
		- [SetTemperatureConfirmation](#settemperatureconfirmation)
		- [IncrementTemperatureRequest](#incrementtemperaturerequest)
		- [IncrementTemperatureConfirmation](#incrementtemperatureconfirmation)
		- [DecrementTemperatureRequest](#decrementtemperaturerequest)
		- [DecrementTemperatureConfirmation](#decrementtemperatureconfirmation)
	- [风速调节类消息](#风速调节类消息)
		- [SetFanSpeedRequest](#setfanspeedrequest)
		- [SetFanSpeedConfirmation](#setfanspeedconfirmation)
		- [IncrementFanSpeedRequest](#incrementfanspeedrequest)
		- [IncrementFanSpeedConfirmation](#incrementfanspeedconfirmation)
		- [DecrementFanSpeedRequest](#decrementfanspeedrequest)
		- [DecrementFanSpeedConfirmation](#decrementfanspeedconfirmation)
	- [电量控制类消息](#电量控制类消息)
		- [ChargeRequest](#chargerequest)
		- [ChargeConfirmation](#chargeconfirmation)
	- [模式控制类消息](#模式控制类消息)
		- [SetModeRequest](#setmoderequest)
		- [SetModeConfirmation](#setmodeconfirmation)
	- [电视频道控制类消息](#电视频道控制类消息)
		- [SetTVChannelRequest](#settvchannelrequest)
		- [SetTVChannelConfirmation](#settvchannelconfirmation)
		- [IncrementTVChannelRequest](#incrementtvchannelrequest)
		- [IncrementTVChannelConfirmation](#incrementtvchannelconfirmation)
		- [DecrementTVChannelRequest](#decrementtvchannelrequest)
		- [DecrementTVChannelConfirmation](#decrementtvchannelconfirmation)
		- [RetrunTVChannelRequest](#retruntvchannelrequest)
		- [ReturnTVChannelConfirmation](#returntvchannelconfirmation)
	- [音量控制类消息](#音量控制类消息)
		- [SetVolumeRequest](#setvolumerequest)
		- [SetVolumeConfirmation](#setvolumeconfirmation)
		- [IncrementVolumeRequest](#incrementvolumerequest)
		- [IncrementVolumeConfirmation](#incrementvolumeconfirmation)
		- [DecrementVolumeRequest](#decrementvolumerequest)
		- [DecrementVolumeConfirmation](#decrementvolumeconfirmation)
		- [SetVolumeMuteRequest](#setvolumemuterequest)
		- [SetVolumeMuteConfirmation](#setvolumemuteconfirmation)
	- [设备信息查询类消息](#设备信息查询类消息)
		- [GetTemperatureReadingRequest](#gettemperaturereadingrequest)
		- [GetTemperatureReadingResponse](#gettemperaturereadingresponse)
		- [GetAirPM25Request](#getairpm25request)
		- [GetAirPM25Response](#getairpm25response)
		- [GetHumidityRequest](#gethumidityrequest)
		- [GetHumidityResponse](#gethumidityresponse)
	- [错误消息](#错误消息)
		- [OperationNotAllowedForUserError](#operationnotallowedforusererror)
		- [TargetNotProvisionedError](#targetnotprovisionederror)
		- [TargetOfflineError](#targetofflineerror)
		- [NoSuchTargetError](#nosuchtargeterror)
		- [BridgeOfflineError](#bridgeofflineerror)
		- [ValueOutOfRangeError](#valueoutofrangeerror)
		- [BatteryLevelTooLowError](#batteryleveltoolowerror)
		- [DriverInternalError](#driverinternalerror)
		- [DependentServiceUnavailableError](#dependentserviceunavailableerror)
		- [TargetConnectivityUnstableError](#targetconnectivityunstableerror)
		- [TargetBridgeConnectivityUnstableError](#targetbridgeconnectivityunstableerror)
		- [TargetFirmwareOutdatedError](#targetfirmwareoutdatederror)
		- [TargetBridgeFirmwareOutdatedError](#targetbridgefirmwareoutdatederror)
		- [TargetHardwareMalfunctionError](#targethardwaremalfunctionerror)
		- [TargetBridgeHardwareMalfunctionError](#targetbridgehardwaremalfunctionerror)
		- [UnableToGetValueError](#unabletogetvalueerror)
		- [UnableToSetValueError](#unabletosetvalueerror)
		- [UnwillingToSetValueError](#unwillingtosetvalueerror)
		- [RateLimitExceededError](#ratelimitexceedederror)
		- [NotSupportedInCurrentModeError](#notsupportedincurrentmodeerror)
		- [ExpiredAccessTokenError](#expiredaccesstokenerror)
		- [InvalidAccessTokenError](#invalidaccesstokenerror)
		- [UnsupportedTargetError](#unsupportedtargeterror)
		- [UnsupportedOperationError](#unsupportedoperationerror)
		- [UnsupportedTargetSettingError](#unsupportedtargetsettingerror)
		- [UnexpectedInformationReceivedError](#unexpectedinformationreceivederror)
- [更新日志](#更新日志)
	- [2019/03/12 更新](#20190312-更新)
	- [2018/09/23 更新](#20180923-更新)

<!-- /TOC -->

## 权限验证
为了保证用户设备安全，腾讯叮当智能家居API要求接入的设备厂商需提供HTTPs服务，并以[OAuth2.0](https://tools.ietf.org/html/rfc6749)标准进行权限验证，对于智能家居技能，要求使用[Authorization Code](https://tools.ietf.org/html/rfc6749#section-1.3.1)的授权类型。
设备的控制服务需要对腾讯叮当的每次请求中所携带的Access Token进行校验，关于账号连接的详细描述见[账号连接文档](./account_linking.md)。

## 处理腾讯叮当智能家居请求
腾讯叮当会将用户的请求转换成指令，转而将指令传给设备控制服务，由设备控制服务来执行具体的操作。**设备控制服务只提供一个服务地址，具体指令类型通过消息体来区分**，以下是腾讯叮当智能家居指令的示例：

```json
{
  "header": {
    "version": "1",
    "namespace": "Dingdang.ConnectedHome.Control",
    "name": "SetColorRequest"
  },
  "payload": {
    "accessToken": "{{OAuth Token}}",
    "appliance": {
      "applianceId": "{{Device ID}}",
      "additionalApplianceDetails": {}
    },
    "color": {
      "hue": 0.0100,
      "saturation": 1.0000,
      "brightness": 1.0000
    }
  }
}
```

腾讯叮当智能家居的指令消息分为两个部分：

+ `header`：消息头包含了指令的版本信息、命名空间和指令名称；
+ `payload`：消息体的内容是用于告知控制服务需要执行的具体动作。消息体的内容根据消息头的指令类型及指令名称不同，内容也不尽相同；

下面给出消息体和消息头字段的说明：

### 消息头(header)

| 参数名         | 参数说明                                     | 参数类型   |
| ----------- | ---------------------------------------- | ------ |
| `version`   | 协议的版本                                    | string |
| `namespace` | 指令的命名空间，腾讯叮当智能家居的指令分为了三个类型：<br><br />• `Dingdang.ConnectedHome.Discovery`<br />• `Dingdang.ConnectedHome.Control`<br />• `Dingdang.ConnectedHome.Query` | string |
| `name`      | 指令名称，比如`SetColorRequest`、`SetColorConfirmation`等，具体的指令名称列表参照[消息体](#payload)的内容 | string |

### 消息体(payload)
消息体的内容将根据指令的不同而变化，下表给出了腾讯叮当支持的不同的命名空间下的指令集合。

| 命名空间                             | 功能说明                      | 指令名称列表                                   |
| -------------------------------- | ------------------------- | ---------------------------------------- |
| Dingdang.ConnectedHome.Discovery | 设备的发现和连接                  | • [DiscoverAppliancesRequest](#discoverappliancesrequest)<br />• [DiscoverAppliancesResponse](#discoverappliancesresponse) |
| Dingdang.ConnectedHome.Control     | 已连接设备的控制，比如打开关闭、调高调低设备属性等 | • [TurnOnRequest](#turnonrequest)<br />• [TurnOnConfirmation](#turnonconfirmation)<br />• [TurnOffRequest](#turnoffrequest)<br />• [TurnOffConfirmation](#turnoffconfirmation)<br />• [SetColorRequest](#setcolorrequest)<br />• [SetColorConfirmation](#setcolorconfirmation)<br />• [IncrementBrightnessPercentageRequest](#incrementbrightnesspercentagerequest)<br />• [IncrementBrightnessPercentageConfirmation](#incrementbrightnesspercentageconfirmation)<br />• [DecrementBrightnessPercentageRequest](#decrementbrightnesspercentagerequest)<br />• [DecrementBrightnessPercentageConfirmation](#decrementbrightnesspercentageconfirmation)<br />• [SetBrightnessPercentageRequest](#setbrightnesspercentagerequest)<br />• [SetBrightnessPercentageConfirmation](#setbrightnesspercentageconfirmation)<br /> • [SetTemperatureRequest](#settemperaturerequest)<br /> • [SetTemperatureConfirmation](#settemperatureconfirmation)<br /> • [IncrementTemperatureRequest](#incrementtemperaturerequest)<br /> • [IncrementTemperatureConfirmation](#incrementtemperatureconfirmation)<br /> • [DecrementTemperatureRequest](#decrementtemperaturerequest)<br /> • [DecrementTemperatureConfirmation](#decrementtemperatureconfirmation)<br /> • [SetFanSpeedRequest](#setfanspeedrequest)<br /> • [SetFanSpeedConfirmation](#setfanspeedconfirmation)<br /> • [IncrementFanSpeedRequest](#incrementfanspeedrequest)<br /> • [IncrementFanSpeedConfirmation](#incrementfanspeedconfirmation)<br /> • [DecrementFanSpeedRequest](#decrementfanspeedrequest)<br /> • [DecrementFanSpeedConfirmation](#decrementfanspeedconfirmation)<br /> • [ChargeRequest](#chargerequest)<br /> • [ChargeConfirmation](#chargeconfirmation)<br /> • [SetModeRequest](#setmoderequest)<br /> • [SetModeConfirmation](#setmodeconfirmation)<br />• [IncrementColorTemperatureRequest](#incrementcolortemperaturerequest)<br />• [IncrementColorTemperatureConfirmation](#incrementcolortemperatureconfirmation)<br />• [DecrementColorTemperatureRequest](#decrementcolortemperaturerequest)<br />• [DecrementColorTemperatureConfirmation](#decrementcolortemperatureconfirmation)<br />• [SetColorTemperatureRequest](#setcolortemperaturerequest)<br />• [SetColorTemperatureConfirmation](#setcolortemperatureconfirmation)<br />• [SetTVChannelRequest](#settvchannelrequest)<br />• [SetTVChannelConfirmation](#settvchannelconfirmation)<br />• [IncrementTVChannelRequest](#incrementtvchannelrequest)<br />• [IncrementTVChannelConfirmation](#incrementtvchannelconfirmation)<br />• [DecrementTVChannelRequest](#decrementtvchannelrequest)<br />• [DecrementTVChannelConfirmation](#decrementtvchannelconfirmation)<br />• [RetrunTVChannelRequest](#retruntvchannelrequest)<br />• [ReturnTVChannelConfirmation](#returntvchannelconfirmation)<br />• [SetVolumeRequest](#setvolumerequest)<br />• [SetVolumeConfirmation](#setvolumeconfirmation)<br />• [IncrementVolumeRequest](#incrementvolumerequest)<br />• [IncrementVolumeConfirmation](#incrementvolumeconfirmation)<br />• [DecrementVolumeRequest](#decrementvolumerequest)<br />• [DecrementVolumeConfirmation](#decrementvolumeconfirmation)<br />• [SetVolumeMuteRequest](#setvolumemuterequest)<br />• [SetVolumeMuteConfirmation](#setvolumemuteconfirmation) |
| Dingdang.ConnectedHome.Query        | 查询设备的状态或传感器检测的数值 |  • [GetTemperatureReadingRequest](#gettemperaturereadingrequest)<br /> • [GetTemperatureReadingResponse](#gettemperaturereadingresponse)<br /> • [GetAirPM25Request](#getairpm25request)<br /> • [GetAirPM25Response](#getairpm25response)<br /> • [GetHumidityRequest](#gethumidityrequest)<br /> • [GetHumidityResponse](#gethumidityresponse) |

### 设备发现消息
这类消息用于标识该技能可用的设备、场景，及其对应支持的功能。

#### DiscoverAppliancesRequest

+ Header

| 参数          | 值                                |
| ----------- | -------------------------------- |
| `namespace` | Dingdang.ConnectedHome.Discovery |
| `name`      | DiscoverAppliancesRequest        |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |


+ 示例
```json
{
    "header": {
        "name": "DiscoverAppliancesRequest",
        "namespace": "Dingdang.ConnectedHome.Discovery",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}"
    }
}
```

#### DiscoverAppliancesResponse

+ Header

| 参数          | 值                                |
| ----------- | -------------------------------- |
| `namespace` | Dingdang.ConnectedHome.Discovery |
| `name`      | DiscoverAppliancesResponse       |


+ Payload

| 参数                       | 参数功能                                     | 参数类型  | 必需   |
| ------------------------ | ---------------------------------------- | ----- | ---- |
| `discoveredAppliances[]` | 用户账号所关联的设备列表，设备信息的字段具体含义参照[discoveredAppliance](#discoveredappliance) | array | 是    |


+ discoveredAppliance

| 参数                           | 参数功能                                     | 参数类型   | 必需   |
| ---------------------------- | ---------------------------------------- | ------ | ---- |
| `applianceTypes[]`           | 设备所属分类列表，设备分类列表参照[设备分类]()                | array  | 是    |
| `applianceId`                | 设备在控制服务的唯一标识，可以是英文、数字或以下任意字符` _-=#;:?@&`，最长不超过256个英文字符 | string | 是    |
| `manufacturerName`           | 设备制造商的名称，最长不超过128个英文字符                   | string | 是    |
| `modelName`                  | 设备型号，最长不超过128个英文字符                       | string | 是    |
| `version`                    | 设备版本号，最长不超过128个英文字符                      | string | 是    |
| `friendlyName`               | 设备名称，最长不超过128个英文字符                       | string | 是    |
| `friendlyDescription`        | 设备描述，最长不超过128个英文字符                       | string | 否    |
| `isReachable`                | 设备是否在线                                   | bool   | 是    |
| `actions[]`                  | 设备支持的控制动作，全量控制列表如下 | array  | 是    |
| `additionalApplianceDetails` | 附加的键值对，腾讯智能家居会将这些信息透传给设备控制服务，数据最长不超过5000字节 | object | 否    |


+ 设备分类

具体操作的设备分类。对于红外遥控器这类特殊的设备，可以将红外遥控器映射成具体控制的设备（如空调、电视机、机顶盒等）。**场景（包括有序场景和无序场景）在腾讯叮当智能家居中，是作为一种特殊的设备存在的**，场景通过声明其支持的动作达到被触发的目的。

| 设备类型             | 类型描述                            |
| ---------------- | ------------------------------- |
| LIGHT            | 光源或者灯具                         |
| SWITCH           | 墙壁开关                             |
| SMARTPLUG        | 智能插线板                           |
| AIR_CONDITION    | 空调类设备                           |
| AIR_PURIFIER     | 空气净化器                           |
| WATER_PURIFIER   | 净水器                               |
| FAN              | 风扇                                 |
| SWEEPING_ROBOT   | 扫地机器人                           |
| CURTAIN          | 窗帘类设备                           |
| AIR_MONITOR      | PM2.5检测仪等空气监测器类设备         |
| TV_SET           | 电视机                               |
| SET_TOP_BOX      | 机顶盒                               |
| WEBCAM           | 网络摄像头                           |
| HUMIDIFIER       | 加湿器                               |
| KETTLE           | 热水壶                               |
| WATER_HEATER     | 电热水器                             |
| HEATER           | 电暖气                               |
| WASHING_MACHINE  | 洗衣机                               |
| CLOTHES_RACK     | 晾衣架                               |
| RANGE_HOOD       | 油烟机                               |
| OVEN             | 烤箱                                 |
| MICROWAVE_OVEN   | 微波炉                               |
| PRESSURE_COOKER  | 压力锅                               |
| RICE_COOKER      | 电饭煲                               |
| INDUCTION_COOKER | 电磁炉                               |
| HIGH_SPEED_BLENDER | 破壁机                            |
| FRIDGE           | 冰箱                                 |
| PRINTER          | 打印机                               |
| AIR_FRESHER      | 新风机                               |
| WINDOW_OPENER    | 开窗器                               |
| ACTIVITY_TRIGGER | 表示一系列设备控制指令的集合，并且要求这些控制指令被按顺序执行 |
| SCENE_TRIGGER    | 表示一些列设备控制指令的集合，指令的执行顺序并不重要      |

+ 设备支持的操作类型

用于声明设备支持的操作类型，该类型的声明将作为腾讯叮当是否执行对应操作的依据。

|设备操作名称      | 设备操作类型   |
|-----------------|---------------|
|turnOn       |打开     |
|turnOff      |关闭     |
|timingTurnOn     |定时打开   |
|timingTurnOff    |定时关闭   |
|pause        |暂停     |
|continue     |继续     |
|setBrightnessPercentage    |设置亮度百分比       |
|incrementBrightnessPercentage  |调高亮度百分比   |
|decrementBrightnessPercentage  |调低亮度百分比   |
|getBrightnessPercentage        |查询亮度百分比   |
|incrementTemperature   |调高温度 |
|decrementTemperature   |调低温度 |
|setTemperature   |设置温度     |
|getTemperatureReading    |查询温度     |
|getTargetTemperature   |查询目标温度     |
|incrementFanSpeed  |调高风速     |
|decrementFanSpeed  |调低风速     |
|setFanSpeed    |设置风速     |
|getFanSpeed    |查询风速     |
|setMode        |设置模式     |
|unSetMode      |取消设置的模式    |
|timingSetMode    |定时设置模式     |
|timingUnsetMode  |定时取消设置的模式  |
|setColor       |设置颜色     |
|getAirQualityIndex |查询空气质量     |
|getAirPM25     |查询PM2.5      |
|getAirCO2      |查询二氧化碳含量     |
|getAirPM10     |查询PM10       |
|getWaterQuality  |查询水质     |
|getFilterUsagePercentage     |查询滤芯使用百分比    |
|getFilterLeftLife     |查询滤芯剩余寿命    |
|getHumidity    |查询湿度     |
|setHumidity    |设置湿度模式     |
|getTimeLeft    |查询剩余时间     |
|getRunningTime   |查询运行时间     |
|getRunningStatus |查询运行状态     |
|setLockState     |上锁解锁     |
|getLockState     |查询锁状态      |
|incrementPower   |增大功率     |
|decrementPower   |减小功率     |
|incrementVolume  |调高音量     |
|decrementVolume  |调低音量     |
|setVolume        |设置音量     |
|setVolumeMute    |设置设备静音状态   |
|returnTVChannel  |返回上个频道     |
|decrementTVChannel |上一个频道      |
|incrementTVChannel |下一个频道      |
|setTVChannel     |设置频道     |
|decrementHeight  |降低高度     |
|incrementHeight  |升高高度     |
|chargeTurnOn     |开始充电     |
|chargeTurnOff    |停止充电     |
|submitPrint      |打印         |
|getTurnOnState   |查询设备打开状态   |
|setSuction     |设置吸力     |
|setDirection   |设置移动方向     |
|getElectricityCapacity   |查询电量 |
|getOilCapacity   |查询油量     |

+ 示例
```json
{
   "header":{
      "name":"DiscoverAppliancesResponse",
      "namespace":"Dingdang.ConnectedHome.Discovery",
      "version":"1"
   },
   "payload":{
      "discoveredAppliances":[
         {
            "actions":[
               "turnOn",
               "turnOff",
               "setColor",
               "setPercentage",
               "incrementPercentage",
               "decrementPercentage"
            ],
            "applianceTypes":[
               "LIGHT"
            ],
            "additionalApplianceDetails":{
               "extraDetail1":"optionalDetailForSkillAdapterToReferenceThisDevice",
               "extraDetail2":"There can be multiple entries",
               "extraDetail3":"but they should only be used for reference purposes.",
               "extraDetail4":"This is not a suitable place to maintain current device state"
            },
            "applianceId":"unique-light-device-id",
            "friendlyDescription":"颜色随心变",
            "friendlyName":"卧室的灯",
            "isReachable":true,
            "manufacturerName":"飞利浦",
            "modelName":"Hue",
            "version":"your software version number here."
         }
      ]
   }
}
```

### 打开/关闭消息
这类消息用于打开或关闭指定设备或者场景。

#### TurnOnRequest
当用户发起打开设备请求时，叮当将发送该消息到技能。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | TurnOnRequest                  |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要打开的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，打开卧室的灯”
```json
{
    "header": {
        "name": "TurnOnRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
            "additionalApplianceDetails": {},
            "applianceId": "{{Device ID}}"
        }
    }
}
```

#### TurnOnConfirmation
打开设备后，技能返回该消息给叮当。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | TurnOnConfirmation             |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| -    | -    | -    | -    |


+ 示例
```json
{
    "header": {
        "name": "TurnOnConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {}
}
```

#### TurnOffRequest
当用户发起关闭设备请求时，叮当将该消息发送给技能。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | TurnOffRequest                 |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要打开的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，关灯”
```json
{
    "header": {
        "name": "TurnOffRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
            "additionalApplianceDetails": {},
            "applianceId": "{{Device ID}}"
        }
    }
}
```

#### TurnOffConfirmation
设备被成功关闭后，技能返回该消息给叮当。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | TurnOffConfirmation            |

+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| -    | -    | -    | -    |

+ 示例
```json
{
    "header": {
        "name": "TurnOffConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {}
}
```

### 灯光调节消息
这类消息用于调节灯光的颜色等属性。

#### SetColorRequest
请求调整设备的灯光颜色。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetColorRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `color`       | 灯光的颜色，以HSB的颜色空间表示   | object | 是    |
| `color.hue`        | 颜色色相，变化区间为\[0.00, 360.00\]    | double | 是    |
| `color.saturation` | 颜色饱和度，变化区间为\[0.0000, 1.0000\] | double | 是    |
| `color.brightness` | 颜色亮度，变化区间为\[0.0000, 1.0000\]  | double | 是    |


+ 示例
用户问法：“叮当叮当，把卧室的灯设为粉色”
```json
{
    "header": {
        "name": "SetColorRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "color": {
          "hue": 0.00,
          "saturation": 1.0000,
          "brightness": 1.0000
        }
      }
    }
}
```

#### SetColorConfirmation
设备颜色调整成功后，技能返回该消息给叮当。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetColorConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `achievedState`       | 设备调节后的状态 | object | 是    |
| `achievedState.color` | 设备调节后的颜色 | object | 是    |


+ 示例
```json
{
    "header": {
        "name": "SetColorConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "achievedState": {
            "color": {
                "hue": 0.00,
                "saturation": 1.0000,
                "brightness": 1.0000
            }
        }
    }
}
```

#### IncrementBrightnessPercentageRequest
用户请求调高亮度时，叮当将发送该消息到技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementBrightnessPercentageRequest     |


+ Payload

| 参数                      | 参数功能                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `accessToken`           | 用户账号对应的Access Token                      | string | 是    |
| `appliance`             | 需要调节的设备                                  | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaPercentage.value` | 上浮的百分比，属性的百分比应该在\[0, 100\]间变化，若超过该区间应该返回错误 | double | 是    |


+ 示例
用户问法：“叮当叮当，把台灯调亮点”
```json
{
    "header": {
        "name": "IncrementBrightnessPercentageRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaPercentage": {
            "value": 1.0
        }
      }
    }
}
```

#### IncrementBrightnessPercentageConfirmation
亮度值调低成功确认消息

+ Header

| 参数          | 值                               |
| ----------- | ------------------------------- |
| `namespace` | Dingdang.ConnectedHome.Control  |
| `name`      | IncrementBrightnessPercentageConfirmation |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| `brightness.value` | 设置后的亮度值    | double    | 是    |
| `previousState.brightness.value` | 设置前的亮度值 | double    | 是    |


+ 示例
```json
{
    "header": {
        "name": "IncrementBrightnessPercentageConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "brightness": {
            "value": 10.0,
        },
        "previousState": {
            "brightness": {
                "value": 9.0
            }
        }
    }
}
```

#### DecrementBrightnessPercentageRequest
用户请求调低设备亮度时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementBrightnessPercentageRequest     |


+ Payload

| 参数                      | 参数功能                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `accessToken`           | 用户账号对应的Access Token                      | string | 是    |
| `appliance`             | 需要调节的设备                                  | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaPercentage.value` | 下调的百分比，属性的百分比应该在\[0, 100\]间变化，若超过该区间应该返回错误 | double | 是    |


+ 示例
用户问法：“叮当叮当，台灯调暗一点”。
```json
{
    "header": {
        "name": "DecrementBrightnessPercentageRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaPercentage": {
            "value": 1.0
        }
      }
    }
}
```

#### DecrementBrightnessPercentageConfirmation
设备亮度成功调低时，技能返回该消息给叮当。
+ Header

| 参数          | 值                               |
| ----------- | ------------------------------- |
| `namespace` | Dingdang.ConnectedHome.Control  |
| `name`      | DecrementBrightnessPercentageConfirmation |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| `brightness.value` | 设置后的亮度值    | double    | 是    |
| `previousState.brightness.value` | 设置前的亮度值 | double    | 是    |


+ 示例
```json
{
    "header": {
        "name": "DecrementBrightnessPercentageConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "brightness": {
            "value": 10.0,
        },
        "previousState": {
            "brightness": {
                "value": 11.0
            }
        }
    }
}
```

#### SetBrightnessPercentageRequest
用户请求设置设备的亮度值时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetBrightnessPercentageRequest           |


+ Payload

| 参数                      | 参数功能                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `accessToken`           | 用户账号对应的Access Token                      | string | 是    |
| `appliance`             | 需要调节的设备                                  | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `brightness.value` | 目标百分比，属性的百分比应该在\[0, 100\]间变化，若超过该区间应该返回错误 | double | 是    |


+ 示例
用户问法：“叮当叮当，把客厅的灯亮度设为30”
```json
{
    "header": {
        "name": "SetBrightnessPercentageRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "brightness": {
            "value": 10.0
        }
      }
    }
}
```

#### SetBrightnessPercentageConfirmation
技能设置亮度值成功后，返回该消息。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetBrightnessPercentageConfirmation      |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| `brightness.value` | 设置后的亮度值    | double    | 是    |
| `previousState.brightness.value` | 设置前的亮度值 | double    | 是    |


+ 示例
```json
{
    "header": {
        "name": "SetBrightnessPercentageConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "brightness": {
            "value": 10.0,
        },
        "previousState": {
            "brightness": {
                "value": 9.0
            }
        }
    }
}
```

#### IncrementColorTemperatureRequest
用户请求调高设备的色温值时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementColorTemperatureRequest           |


+ Payload

| 参数                      | 参数功能                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `accessToken`           | 用户账号对应的Access Token                      | string | 是    |
| `appliance`             | 需要调节的设备                                  | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，把客厅的灯色温调冷”
```json
{
    "header": {
        "name": "IncrementColorTemperatureRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### IncrementColorTemperatureConfirmation
技能调高色温值成功后，返回该消息。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementColorTemperatureConfirmation      |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| `achievedState.colorTemperature.value` | 设置后的色温值，单位为开尔文 | int    | 是    |


+ 示例
```json
{
    "header": {
        "name": "IncrementColorTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "achievedState": {
            "colorTemperature": {
                "value": 2700
            }
        }
    }
}
```

#### DecrementColorTemperatureRequest
用户请求调低设备的色温值时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementColorTemperatureRequest           |


+ Payload

| 参数                      | 参数功能                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `accessToken`           | 用户账号对应的Access Token                      | string | 是    |
| `appliance`             | 需要调节的设备                                  | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，把客厅的灯色温调暖”
```json
{
    "header": {
        "name": "DecrementColorTemperatureRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### DecrementColorTemperatureConfirmation
技能调低色温值成功后，返回该消息。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementColorTemperatureConfirmation      |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| `achievedState.colorTemperature.value` | 设置后的色温值，单位为开尔文 | int    | 是    |


+ 示例
```json
{
    "header": {
        "name": "DecrementColorTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "achievedState": {
            "colorTemperature": {
                "value": 2700
            }
        }
    }
}
```


#### SetColorTemperatureRequest
用户请求设置设备的色温值时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetColorTemperatureRequest           |


+ Payload

| 参数                      | 参数功能                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `accessToken`           | 用户账号对应的Access Token                      | string | 是    |
| `appliance`             | 需要调节的设备                                  | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `colorTemperature.value` | 目标色温，单位为开尔文 | int | 是    |


+ 示例
用户问法：“叮当叮当，把客厅的灯色温调到3000”
```json
{
    "header": {
        "name": "SetColorTemperatureRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
		},
		"colorTemperature": {
			"value": 3000
		}
      }
    }
}
```

#### SetColorTemperatureConfirmation
技能设置色温值成功后，返回该消息。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetColorTemperatureConfirmation      |


+ Payload

| 参数   | 参数功能 | 参数类型 | 必需   |
| ---- | ---- | ---- | ---- |
| `achievedState.colorTemperature.value` | 设置后的色温值，单位为开尔文 | int    | 是    |


+ 示例
```json
{
    "header": {
        "name": "SetColorTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "achievedState": {
            "colorTemperature": {
                "value": 3000
            }
        }
    }
}
```

### 温度调节类消息
这类消息用于调节设备的温度。

#### SetTemperatureRequest
用户请求调整设备的温度是，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetTemperatureRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `targetTemperature`       | 目标温度   | object | 是    |
| `targetTemperature.value` | 目标温度值，使用摄氏度表示 | double | 是    |


+ 示例
用户问法：“叮当叮当，把空调温度设为27度”
```json
{
    "header": {
        "name": "SetTemperatureRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "targetTemperature": {
          "value": 27.00
        }
      }
    }
}
```

#### SetTemperatureConfirmation
设备温度设置成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetTemperatureConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `temperature.value`   | 设置成功后的目标温度 | double | 是    |
| `mode`                | 设置成功后的设备模式，可选值有`AUTO`、`COOL`、`HEAT` | string | 是    |
| `previousState.mode`  | 设置之前的设备模式 | string | 是    |
| `previousState.temperature.value`  | 设置之前的设备目标温度，单位为摄氏度 | double | 是    |


+ 示例
```json
{
    "header": {
        "name": "SetTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "mode": "AUTO",
        "temperature": {
          "value": 10.00
        },
        "previousState": {
            "mode": "AUTO",
            "temperature": {
              "value": 10.00
            }
        }
    }
}
```

#### IncrementTemperatureRequest
用户请求调高设备温度时，技能返回该消息给叮当。

+ Header

| 参数          | 值                           |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementTemperatureRequest    |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value` | 调高的温度值，单位为摄氏度 | double | 是    |


+ 示例
用户问法：“叮当叮当，空调的温度调高一点”
```json
{
    "header": {
        "name": "IncrementTemperatureRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 1.00
        }
      }
    }
}
```

#### IncrementTemperatureConfirmation
设备温度成功调高后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementTemperatureConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `temperature.value`   | 设置成功后的目标温度 | double | 是    |
| `mode`                | 设置成功后的设备模式，可选值有`AUTO`、`COOL`、`HEAT` | string | 是    |
| `previousState.mode`  | 设置之前的设备模式 | string | 是    |
| `previousState.temperature.value`  | 设置之前的设备目标温度，单位为摄氏度 | double | 是    |


+ 示例
```json
{
    "header": {
        "name": "SetTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "mode": "AUTO",
        "temperature": {
          "value": 10.00
        },
        "previousState": {
            "mode": "AUTO",
            "temperature": {
              "value": 10.00
            }
        }
    }
}
```

#### DecrementTemperatureRequest
用户请求调低设备温度时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementTemperatureRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value` | 调低的温度值，使用摄氏度表示 | double | 是    |


+ 示例
用户问法：“叮当叮当，空调的温度调低一点”

```json
{
    "header": {
        "name": "DecrementTemperatureRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 10.00
        }
      }
    }
}
```

#### DecrementTemperatureConfirmation
设备温度成功调低后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementTemperatureConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `temperature.value`   | 设置成功后的目标温度 | double | 是    |
| `mode`                | 设置成功后的设备模式，可选值有`AUTO`、`COOL`、`HEAT` | string | 是    |
| `previousState.mode`  | 设置之前的设备模式 | string | 是    |
| `previousState.temperature.value`  | 设置之前的设备目标温度，单位为摄氏度 | double | 是    |


+ 示例
```json
{
    "header": {
        "name": "DecrementTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "mode": "AUTO",
        "temperature": {
          "value": 10.00
        },
        "previousState": {
            "mode": "AUTO",
            "temperature": {
              "value": 10.00
            }
        }
    }
}
```

### 风速调节类消息
这类消息用于调节设备的温度。

#### SetFanSpeedRequest
用户请求调整设备风速值时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetFanSpeedRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `fanSpeed.value` | 目标风速值，取值范围为[1, 10]，当用户表达具体的风速值时使用该字段 | int | 否    |
| `fanSpeed.level` | 目标风速档位，可选值有：`MIN`、`LOW`、`MIDDLE`、`HIGH`、`MAX`、`AUTO`，当用户表达风速档位时使用该字段 | string | 否    |


+ 示例
用户问法：“叮当叮当，把风扇的风速设为9”

```json
{
    "header": {
        "name": "SetFanSpeedRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "fanSpeed": {
          "value": 9
        }
      }
    }
}
```

#### SetFanSpeedConfirmation
设备风速值调整成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetFanSpeedConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `fanSpeed.value`      | 设置成功后的风速值 | int | 是    |
| `fanSpeed.value` | 设置后的风速值 | int | 否    |
| `fanSpeed.level` | 设置后的风速档位 | string | 否    |
| `previousState.fanSpeed.value` | 设置前的风速值 | int | 否    |
| `previousState.fanSpeed.level` | 设置前的风速档位 | string | 否    |


+ 示例
```json
{
    "header": {
        "name": "SetTemperatureConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "fanSpeed": {
          "value": 9
        },
        "previousState": {
            "fanSpeed": {
              "level": "AUTO"
            }
        }
    }
}
```

#### IncrementFanSpeedRequest
当用户请求提高设备风速时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                           |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementFanSpeedRequest    |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value` | 调高的风速值 | int | 是    |


+ 示例
用户问法：“叮当叮当，把风扇的风速调高”
```json
{
    "header": {
        "name": "IncrementFanSpeedRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 1
        }
      }
    }
}
```

#### IncrementFanSpeedConfirmation
设备风速调高成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementFanSpeedConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `fanSpeed.value`      | 设置成功后的目标风速值 | int | 是    |
| `fanSpeed.value` | 设置后的风速值 | int | 否    |
| `fanSpeed.level` | 设置后的风速档位 | string | 否    |
| `previousState.fanSpeed.value` | 设置前的风速值 | int | 否    |
| `previousState.fanSpeed.level` | 设置前的风速档位 | string | 否    |

+ 示例
```json
{
    "header": {
        "name": "IncrementFanSpeedConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "fanSpeed": {
          "value": 2
        },
        "previousState": {
            "fanSpeed": {
              "value": 1
            }
        }
    }
}
```

#### DecrementFanSpeedRequest
当用户请求调低设备风速时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementFanSpeedRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value` | 调低的风速值 | int | 是    |


+ 示例
用户问法：“叮当叮当，把风扇的风速调低”
```json
{
    "header": {
        "name": "DecrementFanSpeedRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 1
        }
      }
    }
}
```

#### DecrementFanSpeedConfirmation
当设备的风力成功调低时，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementFanSpeedConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `fanSpeed.value`   | 设置成功后的目标风速值 | int | 是    |
| `fanSpeed.value` | 设置后的风速值 | int | 否    |
| `fanSpeed.level` | 设置后的风速档位 | string | 否    |
| `previousState.fanSpeed.value` | 设置前的风速值 | int | 否    |
| `previousState.fanSpeed.level` | 设置前的风速档位 | string | 否    |


+ 示例
```json
{
    "header": {
        "name": "DecrementFanSpeedConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "fanSpeed": {
          "value": 1
        },
        "fanSpeed": {
            "fanSpeed": {
              "value": 2
            }
        }
    }
}
```


### 电量控制类消息
这类消息用于查询、控制电量相关的操作。

#### ChargeRequest
当用户请求给设备充电时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | ChargeRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，扫地机器人回去充电”
```json
{
    "header": {
        "name": "ChargeRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### ChargeConfirmation
成功执行充电请求后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | ChargeConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| -       | - | - | -    |


+ 示例
```json
{
    "header": {
        "name": "ChargeConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {}
}
```

### 模式控制类消息
这类消息用于查询、控制设备模式相关的操作。

#### SetModeRequest
用户请求设置设备的模式时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetModeRequest                |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `mode.deviceType`                | 设备类型，可选值见设备模式表   | string | 是    |
| `mode.value`                | 设备模式，可选值见设备模式表   | string | 是    |

+ 设备模式表
不同类型的设备及支持的模式列表如下表：

| 设备类型  | 设备模式 |
| -------  | -------- |
| LIGHT | READING：阅读<br />SLEEP：睡眠<br />ALARM：报警<br />NIGHT_LIGHT：夜灯<br />ROMANTIC：浪漫<br />SUNDOWN：日落<br />SUNRISE：日出<br />RELAX ：休闲/放松<br />LIGHTING ：照明<br />SUN ：太阳<br />STAR ：星星<br />ENERGY_SAVING：节能<br />MOON：月亮<br />JUDI：蹦迪 |
| AIR_CONDITION | COOL：制冷<br />HEAT：制热<br />AUTO：自动<br />FAN：送风<br />DEHUMIDIFICATION：除湿<br />SLEEP：睡眠<br />CLEAN：净化<br />NAI：负离子 |
| AIR_PURIFIER  | SLEEP：睡眠<br />HOME：回家<br />OUT：离家<br />AUTO：自动<br />MANUAL：手动<br />MUTE：静音<br />INTELLIGENT：智能<br />HIGHSPEED：急速<br />DUST_REMOVE：除尘<br />HCHO_FREE：除甲醛<br />NAI：负离子 |
| INDUCTION_COOKER  | QUICK_FIRE：快速火<br />SLOW：温火<br />FRY：煎炸<br />STEWING：蒸煮/炖煮<br />SOUP：汤粥/煲汤<br />HOT_POT：火锅 |
| HEATER  | HIGH_HEAT：高热<br />LOW_HEAT：低热<br />COOL：冷风 |
| MICROWAVE_OVEN  | MICROWAVE：微波<br />BARBECUE：烧烤<br />THAW：解冻 |
| OVEN  | FERMENTATION：发酵<br />DOUBLE_TUBE：双管<br />THAW：解冻 |
| FAN | NIGHT：夜间<br />SWING：摆动/摆风<br />SINGLE：单人<br />MULTI：多人<br />SPURT：喷射<br />SPREAD：扩散<br />QUIET：安静<br />NORMAL：正常风速/适中风速/一般风速<br />POWERFUL：强效<br />MUTE：静音风速<br />NATURAL：自然风速<br />BABY：无感风速/轻微风速<br />COMFORTABLE：舒适风速<br />FEEL：人感风速 |
| RICE_COOKER | HOTTING：加热/热饭/再加热<br />INSULATED：保温<br />SOUP：煮粥/煲汤/杂粮粥<br />FAST_SOUP：快速粥<br />STEWING：蒸煮/炖煮/美味蒸<br />NORMAL_RICE：蒸米饭<br />MIXED_RICE：杂粮饭<br />SMALL_RICE：小米饭<br />GERMINATED_RICE：发芽饭 |
| AIR_FRESHER | NORMAL：普通模式<br />AUTO：自动模式<br />SLEEP：睡眠模式<br />HEAT：加热模式 |
| WASHING_MACHINE | STANDARD：标准<br />DRY：干洗<br />WASH_DRY：洗烘<br />FAST_WASH：快洗<br />DOWN_JACKET：羽绒服 |
| RANGE_HOOD  | HIGH：高速<br />MIDDLE：中速<br />LOW：低速<br />STIR_FRY：爆炒 |
| FRIDGE  | INTELLIGENT：智能<br />FAST_FREEZE：速冻<br />FAST_COOL：速冷<br />MANUAL：手动设定<br />ICE_DRINK：冰饮功能<br />HOLIDY：假日 |
| WATER_PURIFIER  | CLEAN：冲洗<br />ADD_WATER：取水<br />SMALL_CUP：小杯<br />MIDDLE_CUP：中杯<br />BIG_CUP：大杯<br />NORMAL：常温<br />BOILING：开水<br />TEA：泡茶<br />MILK：冲奶 |
| SWEEPING_ROBOT  | AUTO：自动(清扫/拖地)<br />FOCUS：定点(清扫/拖地)<br />ALONG_EDGE：延边(清扫/拖地)<br />ANTI_DISTURB：防打扰<br />蛇形：S_STYLE<br />Z形：Z_STYLE<br />精细：FINE<br />回充：CHARGE |
| CLOTHES_RACK  | DRYING：烘干<br />AIR_DRY：风干<br />DISINFECT：消毒 |

+ 示例
用户问法：“叮当叮当，把空调设为制冷模式”
```json
{
    "header": {
        "name": "SetModeRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "mode": {
          "deviceType": "AIR_CONDITION",
          "value": "COOL"
        }
      }
    }
}
```

#### SetModeConfirmation
当设备成功设置为指定模式后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetModeConfirmation           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `mode.deviceType`       | 设备类型         | string | 是    |
| `mode.value`            | 设置后的设备模式  | string | 是    |
| `previousState.mode.deviceType`       | 设备类型         | string | 是    |
| `previousState.mode.value`            | 设置前的设备模式  | string | 是    |


+ 示例
```json
{
    "header": {
        "name": "ChargeConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "mode": {
          "deviceType": "AIR_CONDITION",
          "value": "COOL"
        },
        "previousState": {
          "mode": {
            "deviceType": "AIR_CONDITION",
            "value": "AUTO"
          }
        }
    }
}
```

### 电视频道控制类消息

#### SetTVChannelRequest
当用户请求设置为指定的电视频道时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetTVChannelRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `targetChannel.value`                | 目标频道，当值为`int`型表示目标的频道排序；当值为`string`类型，表示频道名称   | string/int | 是    |


+ 示例
用户问法：“叮当叮当，电视频道调到5”
```json
{
    "header": {
        "name": "SetTVChannelRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "targetChannel": {
          "value": 5
        }
      }
    }
}
```

#### SetTVChannelConfirmation
设置频道成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetTVChannelConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `channel.value` | 当前频道 | string/int | 否  |
| `previousState.channel.value` | 之前的频道 | string/int | 否  |


+ 示例
```json
{
    "header": {
        "name": "SetTVChannelConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"channel": {
			"value": 5
		},
		"previousState": {
			"channel": {
				"value": 8
			}
		}
    }
}
```

#### IncrementTVChannelRequest
当用户请求调高电视频道时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementTVChannelRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value`   | 调高的频道值   | int | 是   |


+ 示例
用户问法：“叮当叮当，电视调到下一个频道”
```json
{
    "header": {
        "name": "IncrementTVChannelRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 1
        }
      }
    }
}
```

#### IncrementTVChannelConfirmation
设置频道成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementTVChannelConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `channel.value` | 当前频道 | string/int | 否  |
| `previousState.channel.value` | 之前的频道 | string/int | 否  |


+ 示例
```json
{
    "header": {
        "name": "IncrementTVChannelConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"channel": {
			"value": 6
		},
		"previousState": {
			"channel": {
				"value": 5
			}
		}
    }
}
```

#### DecrementTVChannelRequest
当用户请求调低电视频道时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementTVChannelRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value`   | 调低的频道值   | int | 是   |


+ 示例
用户问法：“叮当叮当，电视调到下一个频道”
```json
{
    "header": {
        "name": "DecrementTVChannelRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 1
        }
      }
    }
}
```

#### DecrementTVChannelConfirmation
设置频道成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementTVChannelConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `channel.value` | 当前频道 | string/int | 否  |
| `previousState.channel.value` | 之前的频道 | string/int | 否  |


+ 示例
```json
{
    "header": {
        "name": "DecrementTVChannelConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"channel": {
			"value": 5
		},
		"previousState": {
			"channel": {
				"value": 6
			}
		}
    }
}
```

#### RetrunTVChannelRequest
当用户返回上一个电视频道时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | RetrunTVChannelRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，电视返回上一个频道”
```json
{
    "header": {
        "name": "RetrunTVChannelRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### ReturnTVChannelConfirmation
设置频道成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | ReturnTVChannelConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `channel.value` | 当前频道 | string/int | 否  |
| `previousState.channel.value` | 之前的频道 | string/int | 否  |


+ 示例
```json
{
    "header": {
        "name": "ReturnTVChannelConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"channel": {
			"value": 5
		},
		"previousState": {
			"channel": {
				"value": 8
			}
		}
    }
}
```


### 音量控制类消息

#### SetVolumeRequest
当用户请求设置为指定的音量时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetVolumeRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `targetVolume.value`                | 目标音量，取值范围是[0, 100]  | int | 是    |


+ 示例
用户问法：“叮当叮当，电视音量调到15”
```json
{
    "header": {
        "name": "SetVolumeRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "targetVolume": {
          "value": 15
        }
      }
    }
}
```

#### SetVolumeConfirmation
设置音量成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetVolumeConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `volume.value` | 当前音量 | int | 否  |
| `previousState.volume.value` | 之前音量 | int | 否  |


+ 示例
```json
{
    "header": {
        "name": "SetVolumeConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"volume": {
			"value": 15
		},
		"previousState": {
			"volume": {
				"value": 10
			}
		}
    }
}
```

#### IncrementVolumeRequest
当用户请求调大音量时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementVolumeRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value`                | 调高的音量值  | int | 是    |


+ 示例
用户问法：“叮当叮当，电视音量调高”
```json
{
    "header": {
        "name": "IncrementVolumeRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 5
        }
      }
    }
}
```

#### IncrementVolumeConfirmation
设置音量成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | IncrementVolumeConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `volume.value` | 当前音量 | int | 否  |
| `previousState.volume.value` | 之前音量 | int | 否  |


+ 示例
```json
{
    "header": {
        "name": "IncrementVolumeConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"volume": {
			"value": 15
		},
		"previousState": {
			"volume": {
				"value": 10
			}
		}
    }
}
```


#### DecrementVolumeRequest
当用户请求调小音量时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                           |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementVolumeRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `deltaValue.value`                | 调高的音量值  | int | 是    |


+ 示例
用户问法：“叮当叮当，电视音量调低”
```json
{
    "header": {
        "name": "DecrementVolumeRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "deltaValue": {
          "value": 5
        }
      }
    }
}
```

#### DecrementVolumeConfirmation
设置音量成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementVolumeConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `volume.value` | 当前音量 | int | 否  |
| `previousState.volume.value` | 之前音量 | int | 否  |


+ 示例
```json
{
    "header": {
        "name": "DecrementVolumeConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"volume": {
			"value": 15
		},
		"previousState": {
			"volume": {
				"value": 20
			}
		}
    }
}
```


#### SetVolumeMuteRequest
当用户请求静音或取消静音时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                           |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | SetVolumeMuteRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |
| `muteState.value`  | 静音状态，`ON`：静音，`OFF`：取消静音  | string | 是    |


+ 示例
用户问法：“叮当叮当，电视静音”
```json
{
    "header": {
        "name": "SetVolumeMuteRequest",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        },
        "muteState": {
          "value": "ON"
        }
      }
    }
}
```

#### SetVolumeMuteConfirmation
设置静音状态成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Control |
| `name`      | DecrementVolumeConfirmation     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `muteState.value` | 当前静音状态 | string | 否  |
| `previousState.muteState.value` | 之前静音状态 | string | 否  |


+ 示例
```json
{
    "header": {
        "name": "DecrementVolumeConfirmation",
        "namespace": "Dingdang.ConnectedHome.Control",
        "version": "1"
    },
    "payload": {
		"muteState": {
			"value": "ON"
		},
		"previousState": {
			"muteState": {
				"value": "OFF"
			}
		}
    }
}
```

### 设备信息查询类消息

#### GetTemperatureReadingRequest
当用户询问设备显示的温度值时，叮当将该消息发送给技能。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Query |
| `name`      | GetTemperatureReadingRequest      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，热水器的温度是多少”
```json
{
    "header": {
        "name": "GetTemperatureReadingRequest",
        "namespace": "Dingdang.ConnectedHome.Query",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### GetTemperatureReadingResponse
查询设备显示温度成功后，技能返回该消息给叮当。

+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Query |
| `name`      | GetTemperatureReadingResponse     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `temperatureReading.value` | 温度值 | double | 是  |
| `applianceResponseTimestamp` | 最近更新该温度值的时间，精确到1秒。该数值使用UTC时间，ISO8601格式，推荐使用RFC3399变体，不使用负偏移量 | string | 否  |


+ 示例
```json
{
    "header": {
        "name": "GetTemperatureReadingResponse",
        "namespace": "Dingdang.ConnectedHome.Query",
        "version": "1"
    },
    "payload": {
      "temperatureReading": {
        "value": 0.61
      },
      "applianceResponseTimestamp": "2018-09-21T00:04:11.15Z"
    }
}
```

#### GetAirPM25Request
用户询问设备的PM2.5数值时，叮当将该消息发送给技能。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Query |
| `name`      | GetAirPM25Request      |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，让空气净化器查一下PM2.5”
```json
{
    "header": {
        "name": "GetAirPM25Request",
        "namespace": "Dingdang.ConnectedHome.Query",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### GetAirPM25Response
当设备查询PM2.5成功后，技能返回该消息给叮当。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Query |
| `name`      | GetAirPM25Response     |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| `PM25.value` | PM2.5含量，单位为单位微课每立方米 | double | 是  |
| `applianceResponseTimestamp` | 最近更新该温度值的时间，精确到1秒。该数值使用UTC时间，ISO8601格式，推荐使用RFC3399变体，不使用负偏移量 | string | 否  |


+ 示例
```json
{
    "header": {
        "name": "GetAirPM25Response",
        "namespace": "Dingdang.ConnectedHome.Query",
        "version": "1"
    },
    "payload": {
      "PM25": {
        "value": 0.61
      },
      "applianceResponseTimestamp": "2018-09-21T00:04:11.15Z"
    }
}
```

#### GetHumidityRequest
当用户询问设备检测的想对湿度时，叮当将该消息发送给技能。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Query |
| `name`      | GetHumidityRequest             |


+ Payload

| 参数            | 参数功能                | 参数类型   | 必需   |
| ------------- | ------------------- | ------ | ---- |
| `accessToken` | 用户账号对应的Access Token | string | 是    |
| `appliance`   | 需要调节的设备             | object | 是    |
| `appliance.applianceId`                | 设备ID   | string | 是    |
| `appliance.additionalApplianceDetails` | 设备附属信息 | map | 是    |


+ 示例
用户问法：“叮当叮当，让温湿度传感器查一下湿度时多少”
```json
{
    "header": {
        "name": "GetHumidityRequest",
        "namespace": "Dingdang.ConnectedHome.Query",
        "version": "1"
    },
    "payload": {
        "accessToken": "{{OAuth Token}}",
        "appliance": {
          "applianceId": "{{Device ID}}",
          "additionalApplianceDetails": {}
        }
      }
    }
}
```

#### GetHumidityResponse
当设备查询相对湿度成功后，技能返回该消息给叮当。
+ Header

| 参数          | 值                              |
| ----------- | ------------------------------ |
| `namespace` | Dingdang.ConnectedHome.Query |
| `name`      | GetHumidityResponse           |


+ Payload

| 参数                    | 参数功能     | 参数类型   | 必需   |
| --------------------- | -------- | ------ | ---- |
| humidity              | 相对湿度值，取值范围是[0.0000, 1.0000] | double | 是  |
| `applianceResponseTimestamp` | 最近更新该温度值的时间，精确到1秒。该数值使用UTC时间，ISO8601格式，推荐使用RFC3399变体，不使用负偏移量 | string | 否  |


+ 示例
```json
{
    "header": {
        "name": "GetHumidityResponse",
        "namespace": "Dingdang.ConnectedHome.Query",
        "version": "1"
    },
    "payload": {
      "humidity": {
        "value": 0.61
      },
      "applianceResponseTimestamp": "2018-09-21T00:04:11.15Z"
    }
}
```


### 错误消息
#### OperationNotAllowedForUserError
当前用户无该操作权限。

+ 示例
```json
{
    "header": {
        "namespace": "Dingdang.ConnectedHome.Query",
        "name": " OperationNotAllowedForUserError",
        "version":"1",
    },
        "payload": {
    }
}
```

#### TargetNotProvisionedError
设备已找到，但是尚未被配置。

+ 示例
```json
{
  "header": {
    "namespace": "Dingdang.ConnectedHome.Query",
    "name": " TargetNotProvisionedError",
    "version":"1",
  },
  "payload": {
  }
}
```

#### TargetOfflineError
目标设备当前处于离线状态。


+ 示例
```json
{
    "header":{
        "namespace":"Dingdang.ConnectedHome.Control",
        "name":"TargetOfflineError",
        "version":"1",
    },
    "payload":{
    }
}
```

#### NoSuchTargetError
目标设备不存在。


+ 示例
```json
{
    "header":{
        "namespace":"Dingdang.ConnectedHome.Control",
        "name":"NoSuchTargetError",
        "version":"1",
    },
    "payload":{
    }
}
```

#### BridgeOfflineError
设备的桥接器离线。

+ 示例
```json
    {
        "header":{
            "namespace":"Dingdang.ConnectedHome.Control",
            "name":"BridgeOfflineError",
            "version":"1",
        },
        "payload":{
        }
    }
```

#### ValueOutOfRangeError
数值超出设备可处理的范围


+ Payload

| 参数名            | 参数说明        | 参数类型   | 必需   |
| -------------- | ----------- | ------ | ---- |
| `minimumValue` | 设备所能处理的最低数值 | double | 是    |
| `maximumValue` | 设备所能处理的最高数值 | double | 是    |


+ 示例
```json
    {
      "header":{
        "namespace":"Dingdang.ConnectedHome.Control",
        "name":" ValueOutOfRangeError",
        "version":"1",
      },
      "payload":{
        "minimumValue":15.0,
        "maximumValue":30.0
      }
    }
```

#### BatteryLevelTooLowError
当前设备电量低，无法执行该指令。

+ Payload

| 参数名               | 参数说明    | 参数类型   | 必需   |
| ----------------- | ------- | ------ | ---- |
| `percentageState` | 当前电量百分比 | double | 是    |


+ 示例
```json
{
    "header": {
        "namespace": "Dingdang.ConnectedHome.Query",
        "name": " BatteryLevelTooLowError",
        "version":"1",
    },
    "payload": {
        "percentageState": 3
    }
}
```

#### DriverInternalError
设备执行指令出错


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"DriverInternalError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### DependentServiceUnavailableError
设备控制服务不可用


+ Payload

| 参数名                    | 参数说明            | 参数类型   | 必需   |
| ---------------------- | --------------- | ------ | ---- |
| `dependentServiceName` | 依赖的服务名称，最长256字符 | string | 是    |


+ 示例
```
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"DependentServiceUnavailableError",
    "version":"1",
  },
  "payload":{
    "dependentServiceName":"Customer Credential Database"
  }
}
```

#### TargetConnectivityUnstableError
设备网络连接不稳定


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"TargetConnectivityUnstableError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### TargetBridgeConnectivityUnstableError
桥接器网络连接不稳定


+ 示例
```json
    {
      "header":{
        "namespace":"Dingdang.ConnectedHome.Control",
        "name":"TargetBridgeConnectivityUnstableError",
        "version":"1",
      },
      "payload":{
      }
    }
```

#### TargetFirmwareOutdatedError
设备固件版本已过期

| 参数名                      | 参数说明      | 参数类型   | 必需   |
| ------------------------ | --------- | ------ | ---- |
| `minimumFirmwareVersion` | 支持的最低固件版本 | string | 是    |
| `currentFirmwareVersion` | 当前固件版本    | string | 是    |


+ 示例
```json
    {
      "header":{
        "namespace":"Dingdang.ConnectedHome.Control",
        "name":"TargetFirmwareOutdatedError",
        "version":"1",
      },
      "payload":{
        "minimumFirmwareVersion":"17",
        "currentFirmwareVersion":"6"
      }
    }
```

#### TargetBridgeFirmwareOutdatedError
桥接器固件版本已过期

| 参数名                      | 参数说明      | 参数类型   | 必需   |
| ------------------------ | --------- | ------ | ---- |
| `minimumFirmwareVersion` | 支持的最低固件版本 | string | 是    |
| `currentFirmwareVersion` | 当前固件版本    | string | 是    |


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"TargetBridgeFirmwareOutdatedError",
    "version":"1",
  },
  "payload":{
    "minimumFirmwareVersion":"17",
    "currentFirmwareVersion":"6"
  }
}
```

#### TargetHardwareMalfunctionError
硬件故障


+ 示例
```json
    {
      "header":{
        "namespace":"Dingdang.ConnectedHome.Control",
        "name":"TargetHardwareMalfunctionError",
        "version":"1",
      },
      "payload":{}
    }
```

#### TargetBridgeHardwareMalfunctionError
桥接器硬件故障


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"TargetBridgeHardwareMalfunctionError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### UnableToGetValueError
无法读取设备属性值


+ Payload

| 参数名                     | 参数说明                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `errorInfo`             | 错误信息                                     | object | 是    |
| `errorInfo.code`        | 错误码，错误码类型有：<br />• DEVICE_AJAR: 设备状态原因导致属性无法读取<br />• DEVICE_BUSY: 设备忙<br />• DEVICE_JAMMED: 设备被卡住了<br />• DEVICE_OVERHEATED: 设备过热<br />• HARDWARE_FAILURE: 由于硬件错误的原因导致请求无法完成<br />• LOW_BATTERY: 电池电量低<br />• NOT_CALIBRATED: 设备未校准 | string | 是    |
| `errorInfo.Description` | 错误描述                                     | string | 否    |


+ 示例
```json
    {
      "header":{
        "namespace":"Dingdang.ConnectedHome.Query",
        "name":"UnableToGetValueError",
        "version":"1",
      },
      "payload":{
        "errorInfo":{
          "code":"DEVICE_JAMMED",
          "description":"A custom description of the error.."
        }
      }
    }
```

#### UnableToSetValueError
设置属性值失败


+ Payload

| 参数名                     | 参数说明                                     | 参数类型   | 必需   |
| ----------------------- | ---------------------------------------- | ------ | ---- |
| `errorInfo`             | 错误信息                                     | object | 是    |
| `errorInfo.code`        | 错误码，错误码类型有：<br />• DEVICE_AJAR: 设备状态原因导致属性无法读取<br />• DEVICE_BUSY: 设备忙<br />• DEVICE_JAMMED: 设备被卡住了<br />• DEVICE_OVERHEATED: 设备过热<br />• HARDWARE_FAILURE: 由于硬件错误的原因导致请求无法完成<br />• LOW_BATTERY: 电池电量低<br />• NOT_CALIBRATED: 设备未校准 | string | 是    |
| `errorInfo.Description` | 错误描述                                     | string | 否    |


+ 示例
```json
{
    "header":{
        "name":"UnableToSetValueError",
        "namespace":"Dingdang.ConnectedHome.Control",
        "version":"1"
    },
    "payload":{
        "errorInfo":{
            "code":"DEVICE_BUSY",
            "description":"A custom description of the error"
        }
    }
}
```

#### UnwillingToSetValueError
设备不建议执行该操作


+ Payload

| 参数名                     | 参数说明                       | 参数类型   | 必需   |
| ----------------------- | -------------------------- | ------ | ---- |
| `errorInfo`             | 错误信息                       | object | 是    |
| `errorInfo.code`        | 错误码<br />• ThermostatIsOff | string | 是    |
| `errorInfo.description` | 错误信息描述                     | string | 是    |


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"UnwillingToSetValueError",
    "version":"1",
  },
  "payload":{
    "errorInfo":{
      "code":"ThermostatIsOff",
      "description":"The requested operation is unsafe because it requires changing the mode."
    }
  }
}
```

#### RateLimitExceededError
请求频率过快


+ Payload

| 参数名         | 参数说明                          | 参数类型   | 必需   |
| ----------- | ----------------------------- | ------ | ---- |
| `rateLimit` | 频率上限                          | int    | 是    |
| `timeUnit`  | 时间单位，比如：`MINUTE`、`HOUR`、`DAY` | string | 是    |


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"RateLimitExceededError",
    "version":"1",
  },
  "payload":{
    "rateLimit":"10",
    "timeUnit":"HOUR"
  }
}
```

#### NotSupportedInCurrentModeError
设备当前模式不支持该操作。


+ Payload

| 参数名                 | 参数说明                                     | 参数类型   | 必需   |
| ------------------- | ---------------------------------------- | ------ | ---- |
| `currentDeviceMode` | 当前设备状态：AUTO, AWAY, COLOR, COOL, HEAT, and OTHER | string | 是    |


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"NotSupportedInCurrentModeError",
    "version":"1",
  },
  "payload":{
    "currentDeviceMode":"AWAY"
  }
}
```

#### ExpiredAccessTokenError
当前Access Token已过期


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"ExpiredAccessTokenError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### InvalidAccessTokenError
Access Token由于非过期的原因失效


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"InvalidAccessTokenError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### UnsupportedTargetError
当前技能不支持控制该设备


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"UnsupportedTargetError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### UnsupportedOperationError
设备不支持该操作


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"UnsupportedOperationError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### UnsupportedTargetSettingError
设备不支持当前设置


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"UnsupportedTargetSettingError",
    "version":"1",
  },
  "payload":{
  }
}
```

#### UnexpectedInformationReceivedError
技能不能处理腾讯叮当的请求数据


+ Payload

| 参数名                 | 参数说明  | 参数类型   | 必需   |
| ------------------- | ----- | ------ | ---- |
| `faultingParameter` | 错误的字段 | string | 是    |


+ 示例
```json
{
  "header":{
    "namespace":"Dingdang.ConnectedHome.Control",
    "name":"UnexpectedInformationReceivedError",
    "version":"1",
  },
  "payload":{
    "faultingParameter": "value"
  }
}
```

## 更新日志
### 2019/03/12 更新
+ 新增控制类指令：
	- [IncrementColorTemperatureRequest](#incrementcolortemperaturerequest)
	- [IncrementColorTemperatureConfirmation](#incrementcolortemperatureconfirmation)
	- [DecrementColorTemperatureRequest](#decrementcolortemperaturerequest)
	- [DecrementColorTemperatureConfirmation](#decrementcolortemperatureconfirmation)
	- [SetColorTemperatureRequest](#setcolortemperaturerequest)
	- [SetColorTemperatureConfirmation](#setcolortemperatureconfirmation)
	- [SetTVChannelRequest](#settvchannelrequest)
	- [SetTVChannelConfirmation](#settvchannelconfirmation)
	- [IncrementTVChannelRequest](#incrementtvchannelrequest)
	- [IncrementTVChannelConfirmation](#incrementtvchannelconfirmation)
	- [DecrementTVChannelRequest](#decrementtvchannelrequest)
	- [DecrementTVChannelConfirmation](#decrementtvchannelconfirmation)
	- [RetrunTVChannelRequest](#retruntvchannelrequest)
	- [ReturnTVChannelConfirmation](#returntvchannelconfirmation)
	- [SetVolumeRequest](#setvolumerequest)
	- [SetVolumeConfirmation](#setvolumeconfirmation)
	- [IncrementVolumeRequest](#incrementvolumerequest)
	- [IncrementVolumeConfirmation](#incrementvolumeconfirmation)
	- [DecrementVolumeRequest](#decrementvolumerequest)
	- [DecrementVolumeConfirmation](#decrementvolumeconfirmation)
	- [SetVolumeMuteRequest](#setvolumemuterequest)
	- [SetVolumeMuteConfirmation](#setvolumemuteconfirmation)

### 2018/09/23 更新

+ 修改指令IncrementPercentageRequest、IncrementPercentageConfirmation、DecrementPercentageRequest、DecrementPercentageConfirmation、SetPercentageRequest、 SetPercentageConfirmation为意义更加明确的[IncrementBrightnessPercentageRequest](#incrementbrightnesspercentagerequest)、[IncrementBrightnessPercentageConfirmation](#incrementbrightnesspercentageconfirmation)、[DecrementBrightnessPercentageRequest](#decrementbrightnesspercentagerequest)、[DecrementBrightnessPercentageConfirmation](#decrementbrightnesspercentageconfirmation)、[SetBrightnessPercentageRequest](#setbrightnesspercentagerequest)、 [SetBrightnessPercentageConfirmation](#setbrightnesspercentageconfirmation)；
+ 新增控制类指令：
    - [SetTemperatureRequest](#settemperaturerequest)
    - [SetTemperatureConfirmation](#settemperatureconfirmation)
    - [IncrementTemperatureRequest](#incrementtemperaturerequest)
    - [IncrementTemperatureConfirmation](#incrementtemperatureconfirmation)
    - [DecrementTemperatureRequest](#decrementtemperaturerequest)
    - [DecrementTemperatureConfirmation](#decrementtemperatureconfirmation)
    - [SetFanSpeedRequest](#setfanspeedrequest)
    - [SetFanSpeedConfirmation](#setfanspeedconfirmation)
    - [IncrementFanSpeedRequest](#incrementfanspeedrequest)
    - [IncrementFanSpeedConfirmation](#incrementfanspeedconfirmation)
    - [DecrementFanSpeedRequest](#decrementfanspeedrequest)
    - [DecrementFanSpeedConfirmation](#decrementfanspeedconfirmation)
    - [ChargeRequest](#chargerequest)
    - [ChargeConfirmation](#chargeconfirmation)
    - [SetModeRequest](#setmoderequest)
    - [SetModeConfirmation](#setmodeconfirmation)
+ 新增查询类指令：
    - [GetTemperatureReadingRequest](#gettemperaturereadingrequest)
    - [GetTemperatureReadingResponse](#gettemperaturereadingresponse)
    - [GetAirPM25Request](#getairpm25request)
    - [GetAirPM25Response](#getairpm25response)
    - [GetHumidityRequest](#gethumidityrequest)
    - [GetHumidityResponse](#gethumidityresponse)
