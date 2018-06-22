# 安全签名

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
