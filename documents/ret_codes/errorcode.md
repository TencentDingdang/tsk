# 平台错误码

| 错误码 | 错误信息 | 类型 | 来源 | 更多说明 | 
| :-------- | :-------- | :-------- | :-------- | :-------- |
| -4 | 参数错误| ASR |  AIPM4   | appkey信息非法或服务返回参数错误；Appkey注册请到https://dingdang.qq.com/tvs申请设备接入。请检查sdk设置参数是否合理，sdk请参考https://dingdang.qq.com/doc/page/219 | 
| -7 | 调用超时| ASR |  AIPM7   | 代理层调用语义理解服务超时；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| 100 | 登录票据验证失败 | TSK | AIP100  | 票据验证失败一般情况为票据已过期，可能情况为：1、用户需要重新登录；2、票据在过期前没有做续期操作；其它情况请检查帐号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 |
| 101 | 帐号不存在 | ASR |  AIP101  | 登录帐号没有与设备绑定，请检查帐号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 
| 102 | 设备验证失败| ASR |  AIP102  | 设备验证失败；设备没有注册，请检查账号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 
| -1003 | 音频解码失败| ASR |  ASRM1003  | 音频解码失败，语音识别音频数据解码失败，请检查sdk接入逻辑，可参考：https://dingdang.qq.com/doc/page/322 | 
| -1005 | 语音识别参数错误 | ASR |  ASRM1005  | 代理层调用语音识别服务返回参数错误，请检查sdk设置语音识别参数是否合理，sdk请参考https://dingdang.qq.com/doc/page/219 | 
| -100 | 后端接口异常 | ASR |  TSKM100  | 调用接口出现异常，调用技能内容提供方接口异常，短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -13 | 设备未登记 | ASR |  TSKM13  | 设备未登记；设备没有注册，请检查设备授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/248 | 
| -12 | 协议解析失败 | ASR |  TSKM12  | 协议解析失败；调用技能返回数据解析异常，短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -9 | 调用es服务异常 | ASR |  TSKM9  | 调用es服务异常；调用搜索服务出现异常，短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -8 | 调用知识图谱服务异常 | ASR |  TSKM8  | 调用知识图谱服务异常；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -7 | 调用技能服务接口异常 | ASR |  TSKM7  | 调用技能服务接口异常；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -6 | 调用技能服务接口异常 | ASR |  TSKM6  | 调用技能服务接口异常；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -5 | 技能未上线 | ASR |  TSKM5  | 技能未上线；请反馈给腾讯云小微做进一步问题定位 | 
| -4 | 当前用户访问频率过大 | ASR |  TSKM4  | 当前用户访问频率过大；检查GUID是否按设备生成，或者接口调用逻辑是否出现异常 | 
| -3 | Cache访问异常 | ASR |  TSKM3  | Cache访问异常，短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -2 | DB访问异常 | ASR |  TSKM2  | DB访问异常；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -1 | 业务逻辑异常 | ASR |  TSKM1  | 业务逻辑异常；请检查技能接入流程，可参考：https://dingdang.qq.com/doc/page/294 | 
| 1 | 无数据返回 | ASR |  TSK1  | 列表无数据或资源库没有找到所需要的数据；比如播放XX歌手的歌，曲库里并没有这个歌手，可参考技能接入流程：https://dingdang.qq.com/doc/page/294 | 
| 2 | 无登录或登录已过期 | ASR |  TSK2  | 无登录或登录已过期；票据验证失败一般情况为票据已过期，可能情况为：1、用户需要重新登录；2、票据在过期前没有做续期操作；其它情况请检查帐号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 
| 3 | 参数非法 | ASR |  TSK3  | 参数非法；必要参数没有提取到，一般常见于UniAcess接口：https://dingdang.qq.com/doc/page/344 | 
| 4 | 功能暂未支持 | ASR |  TSK4  | 功能暂未支持；常见为未支持的意图或未支持的实体参数查询等，比如查询国外非主流城市的天气查询，具体可参考技能接入流程：https://dingdang.qq.com/doc/page/294 | 
| 5 | 没有权限访问 | ASR |  TSK5  | 没有权限访问；有验证权限要求，具体根据接口或技能文档说明来进一步定位 | 
| 6 | 请求内容需要付费 | ASR |  TSK6  | 请求内容需要付费；请求内容需要付费，可参考接入支付流程：https://docs.qq.com/doc/DQkZzaUxzbWFvaFhm | 
| 7 | 支付失败 | ASR |  TSK7  | 支付失败 | 
| 8 | 重定向 | ASR |  TSK8  | 重定向如需要技能拉取更多数据 | 
| 9 | 登录态失效 | ASR |  TSK9  | 票据验证失败一般情况为票据已过期，可能情况为：1、用户需要重新登录；2、票据在过期前没有做续期操作；其它情况请检查帐号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 
| 10 | 此操作需要CP授权 | ASR |  TSK10  | 此操作需要CP授权；此操作需要CP授权，请确认自定义技能接入：https://dingdang.qq.com/doc/page/88 | 
| 11 | 数据涉及敏感信息被过滤 | ASR |  TSK11  | 数据涉及敏感信息被过滤；涉敏感情况，如果是自建技能，请检查提供的内容是否合法合规 | 
| -7 | 语义接口调用异常 | ASR |  NLPM7  | 短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -7 | 语音识别接口调用异常 | ASR |  ASRM7  | 语音识别接口调用异常；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| -7 | 语音合成接口调用异常 | ASR |  TTSM7  | 语音合成接口调用异常；短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| 102 | 设备验证失败 | ASR |  TSKmusic102  | 设备没有注册，请检查账号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 
| 102 | 设备验证失败 | ASR |  asrm4  | 设备没有注册，请检查账号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 
| -4 | Appkey非法 | ASR |  asrm4  | Appkey没有注册或参数设置校验不通过；Appkey没有注册，请到https://dingdang.qq.com/tvs 申请设备接入 | 
| -4 | 主动拒绝服务 | ASR |  ttsm4  | 主动拒绝服务；系统出现过载情况，主动拒绝服务，短时间出现必现问题可反馈给腾讯云小微做进一步问题定位 | 
| 2 | 参数错误 | ASR |  tts2  | 参数错误；请检查sdk设置语音合成参数是否合理，sdk请参考https://dingdang.qq.com/doc/page/219 | 
| 1102 | 设备验证失败 | ASR |  aip1102  | 设备验证失败；设备没有注册，请检查账号授权接入流程是否合理，可参考：https://dingdang.qq.com/doc/page/296 | 


# 端错误码

| 错误码 | 错误信息 | 类型 | 来源 | 更多说明 | 
| :-------- | :-------- | :-------- | :-------- | :-------- | 
| -200 | 未初始化| sdk |  sdk | 需要先初始化 | 
| -201 | 设备已存在授权信息| sdk | sdk | 设备已存在授权信息，不能再次授权，先调用clear清除 | 
| -202 | 设置的clientId为空 | sdk | sdk | 检测代码逻辑是否正确 | 
| -203 | 传入的authRespInfoJson不为空，但解析json失败 | sdk | sdk | 检测代码逻辑是否正确 | 
| -204 | authRespInfoJson中的sessionId已失效 | sdk | sdk | 重新发起授权流程 | 

# TVS SDK错误码(Android) #

注：此错误码列表声学前端(RESULT_APL_XXX)部分只适用于TVS SDK提供的声学前端使用.接入第三方声学前端，请参考第三方定义的错误码。
| 返回码 | 名字                         | 备注                                                 |
| ------ | ---------------------------- | ---------------------------------------------------- |
| 0      | RESULT_OK                    | 正常返回    |

## 1. SDK初始化 ##
| 错误码 | 名字                         | 备注                                                 |
| ------ | ---------------------------- | ---------------------------------------------------- |
| -10000 | RESULT_SDK_INIT_ERROR        | SDK初始化失败-内部错误                               |
| -10001 | RESULT_SDK_INIT_CONTEXT_NULL | SDK初始化失败-application的Context为空               |
| -10002 | RESULT_SDK_INIT_ALREADY      | SDK初始化失败-重复初始化                             |
| -10003 | RESULT_SDK_INIT_PARAM_EMPTY  | SDK初始化失败-必传参数为空(appkey、accessToken、dsn) |

## 2. 账号 ##
| 错误码  | 名字                               | 备注                                                         |
| ------ | --------------------------------- | ------------------------------------------------------------ |
| -200   | RESULT_AUTH_NOT_INITED            | 账号模块没有初始化                                           |
| -201   | RESULT_AUTH_HAD_CLIENTID_ALREADY  | 设备已经存在clientId的情况下，再次setClientId，这时候需要先调用clear清除账号 |
| -202   | RESULT_AUTH_SET_CLIENTID_EMPTY    | 传入的clientId为空                                           |
| -203   | RESULT_AUTH_PARSE_AUTHCODE_FAILED | 解析传入的authCode json报错                                  |
| -204   | RESULT_AUTH_SESSIONID_INVALID     | sessionId对应的设备端授权信息已丢失，本次授权无效            |
## 3. 会话 ##

| 错误码 | 名字                                    | 备注                                                   |
| ------ | --------------------------------------- | ------------------------------------------------------ |
| -30000 | RESULT_DIALOG_INIT_FAIL                 | DialogManager初始化失败-识别模块初始化失败             |
| -31000 | RESULT_APL_INIT_NULL                    | DialogManager初始化失败-语音预处理模块初始化失败       |
| -31001 | RESULT_APL_NOT_INIT                     | DialogManager初始化失败-语音预处理模块未初始化         |
| -31002 | RESULT_APL_NO_VOICE_ENGINE              | DialogManager初始化失败-离线语音引擎异常               |
| -31003 | RESULT_APL_NO_SEMANTIC_ENGINE           | DialogManager初始化失败-离线语义引擎异常               |
| -31010 | RESULT_APL_RECO_START_ERR               | 语音预处理开始启动识别异常                             |
| -31011 | RESULT_APL_RECO_ENGINE_ERR              | 识别过程中，引擎出现异常                               |
| -31100 | RESULT_APL_SEMANTIC_PARSE_JSON_ERR      | 解析json异常错误                                       |
| -31101 | RESULT_APL_SEMANTIC_QUERY_NULL_ERR      | 请求语义的query为null                                  |
| -31102 | RESULT_APL_SEMANTIC_REQUEST_ERR         | 请求语义错误                                           |
| -31201 | RESULT_APL_WAKEUP_MODEL_ERR             | 模型异常，放置的模型版本号不对或者文件不全或者没有模型 |
| -31202 | RESULT_APL_WAKEUP_ENGINE_ERR            | 唤醒解码器引擎错误，创建解码器失败                     |
| -31203 | RESULT_APL_WAKEUP_PARSE_RESULT_ERR      | 解析唤醒初始化结果错误                                 |
| -31204 | RESULT_APL_WAKEUP_NOT_INIT_ERR          | 唤醒未初始化，调用start或者stop返回                    |
| -31900 | RESULT_APL_ERR_IN_DIALOG                | 会话过程中预处理模块报错                               |
| -32000 | RESULT_OFFLINE_RECO_FAILED              | 离线识别-内部错误                                      |
| -32001 | RESULT_OFFLINE_RECO_NOT_INIT            | 离线识别-未初始化                                      |
| -32002 | RESULT_OFFLINE_RECO_INVALID_PARAM       | 离线识别-传入错误参数，可能传入空的模型                |
| -32003 | RESULT_OFFLINE_RECO_MODEL_NOT_FOUND     | 离线识别-模型文件未找到                                |
| -32099 | RESULT_OFFLINE_RECO_UNKNOW_ERR          | 离线识别-未知问题，可能是引擎出现问题                  |
| -32200 | RESULT_OFFLINE_RECO_LIB_NOT_EXIST       | 离线库不存在                                           |
| -32201 | RESULT_OFFLINE_RECO_NOT_OPEN_COMMANDKWD | 没有打开自定义唤醒                                     |

## 4.通用错误码 ##

| 错误码 | 名字                           | 备注                         |
| ------ | ------------------------------ | ---------------------------- |
| -40000 | RESULT_START_RECO_FAILED       | 会话失败(内部错误)           |
| -40001 | RESULT_SEND_REQUEST_FAILED     | 终端发送请求失败             |
| -40002 | RESULT_SERVER_ERROR            | 服务器返回的错误码不正常     |
| -40003 | RESULT_AUTHORIZATION_FAILED    | 终端的授权信息为空           |
| -40004 | RESULT_SERVER_DATA_PARSE_ERROR | 服务器返回的json数据解析失败 |
| -40005 | RESULT_SERVER_CLOSED           | 服务器连接关闭               |
| -40006 | RESULT_CANCELED                | 被其他同类型请求取消         |
| -40007 | RESULT_INVALID_RESULT          | 返回无效结果                 |
| -40008 | RESULT_SERVER_NO_RESULT        | 服务器返回204（无数据返回）  |
| -40009 | RESULT_NETWORK_ERROR           | 网络请求回调错误             |
| -40100 | RESULT_ASR_SILIENT_TIMEOUT     | 终端的静音超时               |


## 5. 其他 ##

| 错误码 | 名字                            | 备注                      |
| ------ | ------------------------------- | ------------------------- |
| -51001 | RESULT_UNIACCESS_INVALID_RESULT | UniAccess返回无效结果     |
| -51002 | RESULT_UNIACCESS_NETWORK_ERROR  | UniAccess网络请求回调错误 |

