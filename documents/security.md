# 安全签名

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [如何校验请求的合法性](#如何校验请求的合法性)
- [TSK-HMAC-SHA256-BASIC签名方法](#tsk-hmac-sha256-basic签名方法)
	- [Task 1: 拼接请求数据和时间戳得到`SigningContent`](#task-1-拼接请求数据和时间戳得到signingcontent)
	- [Task 2: 获取`Signature`签名](#task-2-获取signature签名)
	- [Task 3: 在请求中带上签名信息](#task-3-在请求中带上签名信息)
	- [算法实现示例](#算法实现示例)
- [TSK-RSA2签名方法](#tsk-rsa2签名方法)
	- [签名方法](#签名方法)

<!-- /TOC -->

## 如何校验请求的合法性
由于技能服务是公网可访问的，为了保护技能不被其他人攻击，技能服务应当验证请求的合法性，拒绝未签名或签名不合法的请求。验证请求合法性的大致流程如下：
1. 校验签名的合法性，使用[签名算法](#TSK-HMAC-SHA256-BASIC签名方法)对请求内容进行签名，并与请求头的`Authorization`进行比对，若发现请求不一致则为非法请求；
  - 请求的时间戳可以从请求结构体中的`timestamp`字段中获取，需要注意的是，腾讯云叮当的请求时间戳取的是UTC+0时区的时间；
  - SkillSecret可以从技能开放平台的服务配置中获取。
2. 校验请求中时间戳，一般对于超过3分钟的请求可以拒绝其请求。

## TSK-HMAC-SHA256-BASIC签名方法

### Task 1: 拼接请求数据和时间戳得到`SigningContent`
请求数据指的是HTTP Body数据，时间戳取当前的UTC+0时间，并以ISO8601格式为标准（'YYYYMMDD'T'HHMMSS'Z）。假设当前时间戳为20170701T235959Z，得到的`SigningContent`为：
```json
{
    ...
    "request": {
    ...
}20170701T235959Z
```

### Task 2: 获取`Signature`签名
腾讯叮当API要求使用平台分配的`SkillSecret`作为签名的密钥，`SkillSecret`应该存放在服务器的安全位置，不应该以任何形式暴露给终端。签名使用的算法是`HMAC-SHA256`：

```
Signature = HMAC_SHA256(SigningContent, SkillSecret);
```

### Task 3: 在请求中带上签名信息
计算得到请求内容的签名之后，需要在HTTP Header的`Authorization`中带上签名信息。`Authorization`的结构如下所示：

```http
Authorization: [Algorithm] Datetime=[Timestamp], Signature=[Signature]
```

以下Demo展示了一个完整的`Authorization`：
```http
Authorization: TSK-HMAC-SHA256-BASIC Datetime=20170720T193559Z, Signature=d8612ab1ff0301e1016d817c02350a2b76ea62e0
```

### 算法实现示例
我们为该签名算法提供了多个语言版本的签名示例，供开发者实现时进行参考：

+ [TSK-HMAC-SHA256-BASIC C#实现示例](https://github.com/TencentDingdang/tsk/blob/master/sample-code/security-sign/tsk_hmac_sha256_basic_csharp.cs)
+ [TSK-HMAC-SHA256-BASIC Java实现示例](https://github.com/TencentDingdang/tsk/blob/master/sample-code/security-sign/tsk_hmac_sha256_basic_java.java)
+ [TSK-HMAC-SHA256-BASIC Python实现示例](https://github.com/TencentDingdang/tsk/blob/master/sample-code/security-sign/tsk_hmac_sha256_basic_python.py)

## TSK-RSA2签名方法

TSK-RSA2签名方法为非对称加密，会用到TSKPublicKey，SkillPrivateKey， SkillPublicKey ：


| 名词            | 描述                   | 获取方式|
| --------------- | ---- | ---------------------- |
| `TSKPublicKey` | TSK平台分发的验证签名公钥。 | 合作时邮件分配或开放平台分配。 |
| `SkillPrivateKey`      | 技能开发者请求TSK平台时，签名使用的私钥。 | 技能开发者生成，并妥善保管 |
| `SkillPublicKey` | `SkillPrivateKey`对应的公钥，需要提供给TSK平台，用于验证签名。 | 技能开发者生成，并提供给TSK平台 |

以下为openssl工具生成密钥示例：

```
openssl genrsa -out private_key.pem 2048  #生成私钥
openssl rsa -in private_key.pem -pubout -out public_key.pem #生成公钥，将public_key.pem去除begin和end标签，以及换行符，然后提供给TSK平台
```

### 签名方法

TSK-RSA2签名方法和TSK-HMAC-SHA256-BASIC签名方法待签名内容拼接规则一致，但签名算法使用的是SHA256WithRSA。

```
Signature = SHA256WithRSA(SigningContent, SkillPrivateKey);
```

Signature为Base64编码，签名内容同样放置于HTTP Header的`Authorization`，`Authorization`的结构和TSK-HMAC-SHA256-BASIC签名方法保持一致：

```http
Authorization: [Algorithm] Datetime=[Timestamp], Signature=[Signature]
```
此处，Algorithm应填写TSK-RSA2，以下Demo展示了一个完整的`Authorization`：
```http
Authorization: TSK-RSA2 Datetime=20170720T193559Z, Signature=ZDg2MTJhYjFmZjAzMDFlMTAxNmQ4MTd....jMDIzNTBhMmI3NmVhNjJlMA==
```
