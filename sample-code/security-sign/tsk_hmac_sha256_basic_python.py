# -*- coding: UTF-8 -*-
import datetime, hashlib, hmac
import requests # Command to install: `pip install request`

# 示例代码仅供参考
# 腾讯叮当提供的APP Key/Secret
appKey = 'app_key'
appSecret = 'app_secret'

# ***** Task 1: 拼接请求数据和时间戳 *****

## 获取请求数据(也就是HTTP请求的Body)
postData = 'skill-request-data'
## 获得ISO8601时间戳
credentialDate = datetime.datetime.utcnow().strftime('%Y%m%dT%H%M%SZ')

## 拼接数据
signingContent = postData + credentialDate

# ***** Task 2: 获取Signature签名 *****
signature = hmac.new(appSecret, signingContent, hashlib.sha256).hexdigest()

# ***** Task 3: 在HTTP请求头中带上签名信息
authorizationHeader = 'TSK-HMAC-SHA256-BASIC' + ' Datetime=' + credentialDate + ', ' + 'Signature=' + signature

print 'Authorization header:' + authorizationHeader