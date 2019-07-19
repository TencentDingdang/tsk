# 知识问答技能服务开发协议

知识问答技能同时支持平台服务和自定义服务两种模式：平台服务模式下，开发者以问答对的方式直接创建技能，无需提供Web服务；在自定义服务模式下，开发者需额外提供Web服务供平台调用，服务可根据情况动态返回答案。

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [请求数据格式](#请求数据格式)
	- [HTTP Header](#http-header)
	- [HTTP Body](#http-body)
	- [请求参数说明](#请求参数说明)
	- [Context Object 参数说明](#context-object-参数说明)
	- [System Object 参数说明](#system-object-参数说明)
	- [Request Object 参数说明](#request-object-参数说明)
- [响应数据格式](#响应数据格式)
	- [HTTP Header](#http-header)
	- [HTTP Body](#http-body)
	- [响应参数说明](#响应参数说明)
	- [Response Object 参数说明](#response-object-参数说明)
- [更新日志](#更新日志)
	- [2019/07/19 更新](#20190719-更新)

<!-- /TOC -->

## 请求数据格式

### HTTP Header

```http
POST / HTTP/1.1
Content-Type : application/json;charset=UTF-8
Host : your.application.endpoint
Accept : application/json
Accept-Charset : utf-8
Authorization: TSK-HMAC-SHA256-BASIC Datetime=20180101T203559Z, Signature=d8612ab1ff0301e1016d817c02350a2b76ea62e0
```

其中，`Authorization`中的签名格式见[安全签名文档](./security.md#tsk-hmac-sha256-basic签名方法)。

### HTTP Body

请求数据将以JSON格式发送到技能所在的服务地址上，以下是一个正常的云小微请求的例子：

```json
{
  "version": "1.0",
  "context": {
    "System": {
      "device": {
        "deviceId": "string"
      },
      "application": {
        "applicationId": "sssssskill.......id"
      },
      "user": {
        "userId": "uuuuuuuu....id"
      }
    }
  },
  "request": {
    "requestId": "rrrrrr.....id",
    "timestamp": "20170720T193559Z",
    "queryText": "string",
    "question": {
        "questionId": "{{STRING}}"
    }
  }
}
```

### 请求参数说明

| 参数        | 描述                                       | 类型       |
| --------- | ---------------------------------------- | -------- |
| `version` | 协议的版本标识，当前版本为`1.0`                       | `string` |
| `context` | 包含了当前腾讯云小微服务和设备的状态信息，该信息会包含在所有对技能的请求中。详细说明见[Context Object](#context-object-参数说明) | `object` |
| `request` | 用户的详细请求内容，包含了用户命中的问答ID，详细说明见[Request Object](#request-object-参数说明) | `object` |

### Context Object 参数说明

| 参数            | 描述                                       | 类型       |
| ------------- | ---------------------------------------- | -------- |
| `System`      | 腾讯云小微当前的服务和设备信息，详细说明见[System](#system-object-参数说明) | `object` |

### System Object 参数说明

| 参数            | 描述                                       | 类型       |
| ------------- | ---------------------------------------- | -------- |
| `application` | 当前技能的信息，用于验证该请求正确指向你的服务，其中：<br>+ ` applicationId`：技能ID，你可以在腾讯云小微技能平台上找到该ID | `object` |
| `device`      | 当前与技能交互的设备信息：+ `deviceId`：转换的设备ID，唯一标识一台设备 | `object` |
| `user`        | 当前与技能交互的用户信息：<br>+ `userId`：转换的用户ID，唯一标识一个云小微用户，**用户重新关注技能后可能产生新的用户ID**；<br>+ `accessToken`：用户在第三方系统的账号标识，仅在账号连接成功后才会提供，查阅[账号连接](./account_linking.md)了解更多相关信息。 | `object` |

### Request Object 参数说明

当用户与云小微终端的交互命中了你的问答技能，发往技能服务的请求会带上该问答对的问题标识。

| 参数          | 描述                         | 类型       |
| ----------- | ---------------------------- | -------- |
| `requestId` | 当前请求的ID，用于唯一标识一次请求       | `string` |
| `timestamp` | 用户请求时间戳，ISO 8601格式的UTC+0时间 | `string` |
| `queryText` | 用户的说话内容                         | `string` |
| `question`  | 命中的问答对                           | `object` |
| `question.questionId`  | 命中的问答对ID，该ID由开发者在创建技能时提供   | `string` |


## 响应数据格式

### HTTP Header

```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
```

### HTTP Body

响应数据是技能服务收到叮当请求后的返回，用于朗读TTS或者播放音视频、展示图形界面等。问答技能的响应协议主要沿用自定义技能的响应协议，剔除了部分问答技能不支持的指令（如：Dialog类型指令、Connections.SendRequest类型指令等），以下是一个技能响应数据的例子：

```json
{
  "version": "string",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "Plain text string to speak"
    },
    "directives": [
      {
        "type": "InterfaceName.Directive"
        ...
      }
    ]
  }
}
```

### 响应参数说明

| 参数         | 描述                                       | 类型       | 必需   |
| ---------- | ------------------------------------------- | -------- | ---- |
| `version`  | 协议的版本标识，当前版本为`1.0`                | `string` | 是    |
| `response` | 技能响应内容，详细说明见[Response Object](#response-object-参数说明) | `object` | 是    |

### Response Object 参数说明

| 参数                  | 描述                                       | 类型        | 必需                      |
| ------------------- | ---------------------------------------- | --------- | ----------------------- |
| `outputSpeech`      | 回答用户的语音内容，将以TTS技术朗读出来         | `object`  | 是                       |
| `outputSpeech.type` | 当前只支持`PlainText`                        | `string`  | 是                       |
| `outputSpeech.text` | 回答用户的语音内容                            | `string`  | 是（当`type`为`PlainText`时） |
| `directives`        | 指令列表，支持的类型有：<br>+ [audioPlayer 类型的指令](./custom_skill.md#audioplayer类型的指令)<br>+ [VideoPlayer 类型的指令](./custom_skill.md#videoplayer类型的指令)<br>+ [Display 类型的指令](./custom_skill.md#display类型的指令) | `array`   | 否          |

## 更新日志

### 2019/07/19 更新

+ 知识问答技能调用自定义服务文档初始化；
