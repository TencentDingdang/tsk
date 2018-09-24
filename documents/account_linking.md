# 账号连接

账号连接功能能够让技能识别出腾讯叮当的用户与技能自身的账号系统的用户的映射关系，进而提供更加具有针对性的服务。腾讯叮当使用OAuth 2.0授权框架进行账号连接，关于OAuth 2.0授权框架的详细介绍可以参见[RFC6749](https://tools.ietf.org/html/rfc6749)。目前在腾讯叮当的技能开放平台的自定义技能和智能家居技能支持账号连接功能，其中智能家居技能必须使用账号连接功能。

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [如何进行账号连接](#如何进行账号连接)
	- [Authorization Code授权流程](#authorization-code授权流程)
	- [开放平台相关信息配置](#开放平台相关信息配置)
- [授权请求](#授权请求)
	- [code获取流程](#code获取流程)
		- [授权请求](#授权请求)
		- [授权响应](#授权响应)
	- [AccessToken获取流程](#accesstoken获取流程)
		- [AccessToken请求](#accesstoken请求)
		- [AccessToken响应](#accesstoken响应)
	- [AccessToken刷新流程](#accesstoken刷新流程)
		- [刷新AccessToken请求](#刷新accesstoken请求)
		- [刷新AccessToken响应](#刷新accesstoken响应)

<!-- /TOC -->

## 如何进行账号连接

OAuth 2.0定义了四种角色：资源所有者、资源服务、终端和授权服务。在腾讯叮当技能交互的场景中，用户就是资源所有者，技能扮演资源服务和授权服务的角色，腾讯叮当则作为请求资源的终端。对于需要授权的技能，叮当会在向技能发起请求时带上AccessToken，技能可根据AccessToken判定用户的身份和权限。
OAuth 2.0四种授权方式，目前腾讯叮当只支持其中的Authorization Code Grant授权方式：

+ Authorization Code Grant
+ Implicit Grant
+ Resource Owner Password Credentials Grant
+ Client Credentials Grant

### Authorization Code授权流程
Authorization Code Grant的授权流程如下图所示：
```
    +----------+
    | Resource |
    |   Owner  |
    |          |
    +----------+
         ^
         |
        (B)
    +----|-----+          Client Identifier      +---------------+
    |         -+----(A)-- & Redirection URI ---->|               |
    |  User-   |                                 | Authorization |
    |  Agent  -+----(B)-- User authenticates --->|     Server    |
    |          |                                 |               |
    |         -+----(C)-- Authorization Code ---<|               |
    +-|----|---+                                 +---------------+
      |    |                                         ^      v
     (A)  (C)                                        |      |
      |    |                                         |      |
      ^    v                                         |      |
    +---------+                                      |      |
    |         |>---(D)-- Authorization Code ---------'      |
    |  Client |          & Redirection URI                  |
    |         |                                             |
    |         |<---(E)----- Access Token -------------------'
    +---------+       (w/ Optional Refresh Token)
```

### 开放平台相关信息配置
为了使用账号连接功能，技能开发者需要在腾讯叮当技能开放平台上配置OAuth 2.0的相关信息：

+ response_code：授权类型，固定为`code`；
+ 授权地址：用户登陆授权页面地址，建议该页面支持不同尺寸设备的显示；
+ client_id：客户端ID，授权服务分配给腾讯叮当的应用ID；
+ client_secret：授权服务分配给腾讯叮当的应用密钥；
+ scope：授权权限范围，多个权限以英文半角字符`;`分隔开；
+ 回调地址：用户确认授权后重定向的URL，由开放平台生成；
+ Token地址：授权服务的地址，用于code换取AccessToken/RefreshToken及刷新AccessToken；
+ Token请求方式：腾讯叮当传递client_id/client_secret的方式：
    - HTTP Basic：HTTP头的方式传递，将client_id:client_secret拼接后使用Base64编码后放到Authorization头中，即`Authorization: Basic {Base64(client_id:client_secret)}`，示例如下：
    ```http
    POST /token HTTP/1.1
    Host: server.example.com
    Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
    Content-Type: application/x-www-form-urlencoded

    grant_type=authorization_code&code=SplxlOBeZQQYbYS6WxSbIA&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
    ```
    - 请求体参数：client_id/client_secret将在携带在HTTP的请求体中，如：
    ```http
    POST /token HTTP/1.1
    Host: server.example.com
    Content-Type: application/x-www-form-urlencoded

    client_id=s6BhdRkqt3&client_secret=7Fjfp0ZBr1KtDRbnfVdmIw&grant_type=authorization_code&code=SplxlOBeZQQYbYS6WxSbIA&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
    ```
+ HTTP Method：腾讯叮当在进行账号连接时，按照RFC6749给出的示例，分别以不同的HTTP方法请求授权服务：
    - 用户登陆授权过程，由浏览器请求技能配置的授权地址，使用`GET`方式；
    - code换取AccessToken过程，由叮当后台发起`POST`请求；
    - 刷新AccessToken过程，由叮当后台发起`POST`请求；

## 授权请求
授权服务需要处理由腾讯叮当的终端、服务器发起的三种请求，以下给出这三种请求的示例及参数说明：
### code获取流程
#### 授权请求
```http
GET /authorize?response_type=code&client_id=s6BhdRkqt3&state=xyz&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb HTTP/1.1
Host: server.example.com
```

+ 参数说明
| 参数名         | 描述                 | 必需 |
|----------------|---------------------|------|
| response_type | 固定值`code`          | 是 |
| client_id     | 由开发者在开放平台配置 | 是 |
| redirect_uri  | 重定向URL             | 是 |

#### 授权响应
**这里指的是用户同意授权后发起的重定向请求，而非授权请求对应的HTTP Response**，重定向请求使用`application/x-www-form-urlencoded`格式，示例响应数据：
```http
HTTP/1.1 302 Found
Location: https://client.example.com/cb?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz
```

+ 参数说明
| 参数名         | 描述                 | 必需 |
|----------------|---------------------|------|
| code          | 授权服务生成的授权码，将用于后续请求AccessToken的流程。建议code有效期不超过10分钟，并只能消费一次   | 是 |
| state         | 在叮当发起的授权请求中，重定向时回传给叮当的重定向地址 | 是 |

### AccessToken获取流程
#### AccessToken请求
```http
POST /token HTTP/1.1
Host: server.example.com
Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
Content-Type: application/x-www-form-urlencoded

grant_type=authorization_code&code=SplxlOBeZQQYbYS6WxSbIA&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
```

+ 参数说明
| 参数名         | 描述                 | 必需 |
|----------------|---------------------|------|
| grant_type     | 固定值`authorization_code`   | 是 |
| code           | 授权服务生成的授权码 | 是 |
| redirect_uri   | 重定向URL，需要与[授权请求](#授权请求)中的`redirect_uri`一致  | 是 |
| client_id      | 由开发者在开放平台配置 | 是 |
| client_secret  | 由开发者在开放平台配置 | 是 |

#### AccessToken响应
```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "access_token":"2YotnFZFEjr1zCsicMWpAA",
  "token_type":"Bearer",
  "expires_in":3600,
  "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA",
  "refresh_token_expires_in":2592000
}
```

+ 参数说明
| 参数名         | 描述                 | 必需 |
|----------------|---------------------|------|
| access_token   | 访问令牌，**建议在有效期之后或者在AccessToken被刷新之后保持5秒左右有效** | 是 |
| expires_in     | AccessToken有效时间，单位为秒 | 是 |
| refresh_token  | 用于刷新AccessToken  | 是 |
| token_type     | token类型，固定值`Bearer` | 否 |
| refresh_token_expires_in  | 刷新Token的有效时间 | 否 |


### AccessToken刷新流程
#### 刷新AccessToken请求
```http
POST /token HTTP/1.1
Host: server.example.com
Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
Content-Type: application/x-www-form-urlencoded

grant_type=refresh_token&refresh_token=tGzv3JOkF0XG5Qx2TlKWIA
```

+ 参数说明
| 参数名         | 描述                 | 必需 |
|----------------|---------------------|------|
| grant_type     | 固定值`refresh_token`   | 是 |
| refresh_token  | 刷新Token            | 是 |
| client_id      | 由开发者在开放平台配置 | 是 |
| client_secret  | 由开发者在开放平台配置 | 是 |

#### 刷新AccessToken响应
```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "access_token":"2YotnFZFEjr1zCsicMWpAA",
  "token_type":"Bearer",
  "expires_in":3600,
  "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA",
  "refresh_token_expires_in":2592000
}
```

+ 参数说明
| 参数名         | 描述                 | 必需 |
|----------------|---------------------|------|
| access_token   | AccessToken         | 是 |
| expires_in     | AccessToken有效时间，单位为秒 | 是 |
| refresh_token  | 用于刷新AccessToken  | 是 |
| token_type     | token类型，固定值`Bearer` | 否 |
| refresh_token_expires_in  | 刷新Token的有效时间 | 否 |
