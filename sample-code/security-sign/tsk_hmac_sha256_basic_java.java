// 示例代码仅供参考
//package com.qq.TSK.auth;

import java.nio.charset.Charset;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import java.util.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

public class TSKBasicSigner
{
    private static final String ALGORITHM_HMACSHA256 = "HmacSHA256";
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final DateTimeFormatter TIME_FORMATER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

    public static final String sign(String signingContent, String signingKey)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
		byte[] data = signingContent.getBytes(Charset.forName("UTF-8"));
		byte[] key = signingKey.getBytes(Charset.forName("UTF-8"));

        Mac mac = Mac.getInstance(ALGORITHM_HMACSHA256);
        mac.init(new SecretKeySpec(key, ALGORITHM_HMACSHA256));
        return bytesToHexString(mac.doFinal(data));
    }

    protected static final String bytesToHexString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars).toLowerCase();
    }

    public static void main(String args[]) {
    	String appKey = "app_key";
    	String appSecret = "app_secret";

		// ***** Task 1: 拼接请求数据和时间戳 *****

		//// 获取请求数据(也就是HTTP请求的Body)
		String postData = "123";
		//// 获得ISO8601时间戳
    	ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		String credentialDate = utc.format(TIME_FORMATER);

		//// 拼接数据
		String signingContent = postData + credentialDate;

		// ***** Task 2: 获取Signature签名 *****
		String signature = null;
    	try {
	    	signature = sign(signingContent, appSecret);
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }

		// ***** Task 3: 在HTTP请求头中带上签名信息
		String authorizationHeader = "TSK-HMAC-SHA256-BASIC" + " " + "Datetime=" + credentialDate + ", " + "Signature=" + signature;

		System.out.println("Authorization:" + authorizationHeader);

	}
}