
/// 示例代码仅供参考
public static string HmacSha256(string appSecret, string body, string timestamp)
{
    string strOrgData = body + timestamp;
    var hmacsha256 = new HMACSHA256(Encoding.UTF8.GetBytes(appSecret));
    var dataBuffer = Encoding.UTF8.GetBytes(strOrgData);
    var hashBytes = hmacsha256.ComputeHash(dataBuffer);
    return BitConverter.ToString(hashBytes).Replace("-", "").ToLower();
}

Signature = HmacSha256(appSecret, body, timestamp);