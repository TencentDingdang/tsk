# 处理腾讯云叮当的请求

完成了技能的创建，并且配置好服务配置部分之后，你需要在服务中处理以下逻辑：

+ 验证并解析腾讯云叮当发来的请求；
+ 按照协议定义返回响应内容；

这里我们将详细介绍几个重要的流程的处理细节，并给出示例。
## 多轮会话过程中发起参数询问流程
技能在于用户交互过程中，可以通过设置`shouldEndSession`为`false`来保持会话，并且能够让终端自动打开麦克风跟用户进行连续的对话。对于在对话过程中需要收集更多槽位的技能，可以返回`Dialog.ElicitSlot`指令并且将`shouldEndSession`设置为`false`来收集指定的槽位。
以打车技能为例，当用户未提供行程终点时，技能可以提问引导用户说出要去的地点：

> **用户**：叮当叮当，帮我打个车  
>   *Roud1: 技能返回`Dialog.ElicitSlot`指令，设置`shouldEndSession`为`false`，指明`slotToElicit`为`destination`*  
> **叮当**：请问你要去哪里？  
> **用户**：公司  
>  *Round2: 技能收到`IntentRequest`，并且`destination`槽位被设置为“公司”，技能知道*  
> **叮当**：正在为您叫车，请等待司机接单。  

### Round1请求示例
```json
{
  "version": "1.0",
  "session": {
    "new": true,
    "sessionId": "sample-taxi-session"
  },
  "context": {
    "System": {
      "device": {
        "deviceId": "abc1234567890de",
        "supportedInterfaces": {
          "AudioPlayer": {
            "Play": true,
            "Stop": false,
            "ClearQueue": false
          }
        }
      },
      "application": {
        "applicationId": "taxi-skill-id"
      },
      "user": {
        "userId": "uuuuuuuu....id"
      }
    }
  },
  "request": {
    "type": "IntentRequest",
    "requestId": "sample-taxi-session-round1",
    "timestamp": "20190520T193559Z",
    "dialogState": "STARTED",
    "queryText": "帮我打个车",
    "intent": {
      "name": "order",
      "confirmationStatus": "NONE",
      "slots": {
        }
      }
    }
  }
}
```
### Round1技能响应示例
```json
{
  "version": "1.0",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "请问你要去哪里"
    },
    "directives": [
      {
        "type": "Dialog.ElicitSlot",
        "slotToElicit": "destination"
      }
    ],
    "shouldEndSession": false
  }
}
```
### Round2请求示例
```json
{
  "version": "1.0",
  "session": {
    "new": true,
    "sessionId": "sample-taxi-session"
  },
  "context": {
    "System": {
      "device": {
        "deviceId": "abc1234567890de",
        "supportedInterfaces": {
          "AudioPlayer": {
            "Play": true,
            "Stop": false,
            "ClearQueue": false
          }
        }
      },
      "application": {
        "applicationId": "taxi-skill-id"
      },
      "user": {
        "userId": "uuuuuuuu....id"
      }
    }
  },
  "request": {
    "type": "IntentRequest",
    "requestId": "sample-taxi-session-round2",
    "timestamp": "20190520T193605Z",
    "dialogState": "IN_PROGRESS",
    "queryText": "公司",
    "intent": {
      "name": "order",
      "confirmationStatus": "NONE",
      "slots": {
        "destination": {
          "name": "destination",
          "confirmationStatus": "NONE",
          "values": [{
            "value": {
              "type": "text",
              "value": "公司",
              "origin": "公司"
            }
          }]
        }
      }
    }
  }
}
```

### Round2技能响应示例
```json
{
  "version": "1.0",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "正在为您叫车，请等待司机接单。"
    },
    "shouldEndSession": true
  }
}
```
## 在有屏设备上展示图形界面
技能可以通过`Display.RenderTemplate`指令在有屏终端上渲染图形界面，目前腾讯云叮当支持多种类型的模板，通过选择合适的模板并填充相应的内容，可以实现在有屏终端上展示自定义内容。目前腾讯云叮当支持的模板类型可以参照[Display.RenderTemplate指令](./custom_skill.md#Display.RenderTemplate指令)，下面给出一个示例的响应：

### RenderTemplate响应示例
```json
{
  "version": "1.0",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "以下是郭德纲的相声合集"
    },
    "directives": [
      {
        "type": "Display.RenderTemplate",
        "token": "some-template-token",
        "template": {
          "type": "HorizontialListTemplate ",
          "listItems":[{
            "token": "list-item-token-1",
            "textContent": {
              "title": "西河大鼓灞桥挑袍",
              "description": "德云社相声2017"
            },
            "image": {
              "contentDescription": "西河大鼓灞桥挑袍封面",
              "source": {
                "url": "http://imgcache.qq.com/fm/photo/album/rmid_album_360/U/x/004CufAm1kOuUx.jpg?time=1506354775"
              }
            }
          }, {
            "token": "list-item-token-2",
            "textContent": {
              "title": "一笑缘",
              "description": "郭德纲单口相声"
            },
            "image": {
              "contentDescription": "一笑缘封面",
              "source": {
                "url": "http://imgcache.qq.com/fm/photo/album/rmid_album_360/Z/F/0024QwYM2dLAZF.jpg?time=1517543669"
              }
            }
          }]
        }
      }
    ],
    "shouldEndSession": true
  }
}
```

## 了解如何使用账号连接
对于开启了账号连接的技能，若发现当前用户未进行账号关联或者用户当前操作需要账号信息，可以返回账号连接卡片，引导用户进行账号关联操作。
以阅文听书为例，用户听书时需要提前确认用户是否已经在阅文APP中购买当前的付费内容，如果已经已经登录且已购买则直接播放，若未登录或`accessToken`过期则提示用户登录：

> **用户**：叮当叮当，播放万古神帝第35集  
>   *技能发现当前用户未登录，返回`LinkAccount`卡片，引导用户进行登录*  
> **叮当**：这是付费内容，需要登录购买才能播放哦  

### LinkAccount卡片响应示例

```json
{
  "version": "1.0",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "这是付费内容，需要登录购买才能播放哦"
    },
    "card": {
      "type": "LinkAccount"
    },
    "shouldEndSession": true
  }
}
```

## 发起付费流程
若技能登记开启了付费功能，可以在响应中返回`Payment.Pay`指令发起一次付费请求，若用户使用有屏设备则会在有屏设备上展示付费二维码和商品信息。
以阅文听书为例，用户进行了账号连接之后，发给技能的请求会带上`accessToken`，技能可以根据该信息获取到用户是否购买当前资源的信息，若未购买则发起付费请求。


> **用户**：叮当叮当，播放万古神帝第35集  
>   *Roud1: 技能返回`Payment.Pay`指令，设置`shouldEndSession`为`true`*  
> **叮当**：这是付费内容，需要购买才能播放哦  
> **用户**：(手机微信扫描二维码付费)  
>  *Round2: 付费成功后技能收到`RetryIntentRequest`，并且`retryMeta`中有该笔订单的信息*  
> **叮当**：现在就为你播放万古神帝第35集  

### Round1请求示例
```json
{
  "version": "1.0",
  "session": {
    "new": true,
    "sessionId": "sample-payment-session"
  },
  "context": {
    "System": {
      "device": {
        "deviceId": "abc1234567890de",
        "supportedInterfaces": {
          "AudioPlayer": {
            "Play": true,
            "Stop": false,
            "ClearQueue": false
          }
        }
      },
      "application": {
        "applicationId": "yuewen-skill-id"
      },
      "user": {
        "userId": "uuuuuuuu....id",
        "accessToken": "aBcdEthisisaccesstoKeNhh"
      }
    }
  },
  "request": {
    "type": "IntentRequest",
    "requestId": "sample-Payment-session-round1",
    "timestamp": "20190520T193505Z",
    "dialogState": "IN_PROGRESS",
    "queryText": "播放万古神帝第35集",
    "intent": {
      "name": "play",
      "confirmationStatus": "NONE",
      "slots": {
        "album": {
          "name": "album",
          "confirmationStatus": "NONE",
          "values": [{
            "value": {
              "type": "text",
              "value": "万古神帝",
              "origin": "万古神帝"
            }
          }]
        },
        "index": {
          "name": "index",
          "confirmationStatus": "NONE",
          "values": [{
            "value": {
              "type": "text",
              "value": "35",
              "origin": "35"
            }
          }]
        }
      }
    }
  }
}
```
### Round1技能响应示例
```json
{
  "version": "1.0",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "这是付费内容，需要购买才能播放哦"
    },
    "directives": [
      {
        "type": "Payment.Pay",
        "order": {
            "name": "万古神帝",
            "description": "请购买后继续播放",
            "userId": "user-id-of-yuewen",
            "items": [{
                "itemId": "id-of-current-goods",
                "itemName": "万古神帝-35集",
                "price": 200,
                "totalFee": 200,
                "count": 1
            }]
        }
      }
    ],
    "shouldEndSession": true
  }
}
```


### Round2请求示例
```json
{
  "version": "1.0",
  "session": {
    "new": true,
    "sessionId": "sample-payment-session"
  },
  "context": {
    "System": {
      "device": {
        "deviceId": "abc1234567890de",
        "supportedInterfaces": {
          "AudioPlayer": {
            "Play": true,
            "Stop": false,
            "ClearQueue": false
          }
        }
      },
      "application": {
        "applicationId": "yuewen-skill-id"
      },
      "user": {
        "userId": "uuuuuuuu....id",
        "accessToken": "aBcdEthisisaccesstoKeNhh"
      }
    }
  },
  "request": {
    "type": "IntentRequest",
    "requestId": "sample-Payment-session-round2",
    "timestamp": "20190520T193605Z",
    "dialogState": "IN_PROGRESS",
    "queryText": "播放万古神帝第35集",
    "sourceIntent": {
      "name": "play",
      "confirmationStatus": "NONE",
      "slots": {
        "album": {
          "name": "album",
          "confirmationStatus": "NONE",
          "values": [{
            "value": {
              "type": "text",
              "value": "万古神帝",
              "origin": "万古神帝"
            }
          }]
        },
        "index": {
          "name": "index",
          "confirmationStatus": "NONE",
          "values": [{
            "value": {
              "type": "text",
              "value": "35",
              "origin": "35"
            }
          }]
        }
      }
    },
    "retryMeta": {
      "type": "PaymentMeta",
      "partnerOrderId": "order-id-generated-by-yuewen",
      "dingdangOrderId": "order-id-generated-by-dingdang"
    }
  }
}
```
### Round2技能响应示例
```json
{
  "version": "1.0",
  "response": {
    "outputSpeech": {
      "type": "PlainText",
      "text": "：现在就为你播放万古神帝第35集"
    },
    "directives": [
      {
        "type": "AudioPlayer.Play",
        "playlist": [
          {
            "stream": {
              "url": "https://xxx.yuewen.com/path1/subpath2/xxxx.mp3",
              "token": "wangushendi-35"
            },
            "info": {
              "title": "第35集_万古神帝",
              "subtitle": "万古神帝",
              "art": {
                "source": {
                  "url": "http://imgcache.qq.com/fm/photo/album/rmid_album_360/7/3/003GN9272hep73.jpg?time=1508857997"
                }
              }
            }
          }
        ]
      }
    ],
    "shouldEndSession": true
  }
}
```
