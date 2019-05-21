package com.xy.marry.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xy.marry.common.Constants;
import com.xy.marry.common.Constants.SignType;

public class XYSDKUtil {

	/**
	 * 获取请求参数值
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static String getRequestValue(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		String input = null;
		StringBuffer requestBody = new StringBuffer();
		while ((input = reader.readLine()) != null) {
			requestBody.append(input);
		}
		return requestBody.toString();
	}

	/**
	 * 生成带有 sign 的 json 格式字符串
	 *
	 * @param data map类型数据
	 * @param key  API密钥
	 * @return 含有sign字段的XML
	 */
	public static String generateSignedJson(final Map<String, Object> data, String key) throws Exception {
		return generateSignedJson(data, key, SignType.MD5);
	}

	/**
	 * 生成带有 sign 的 json 格式字符串
	 *
	 * @param data     map类型数据
	 * @param key      API密钥
	 * @param signType 签名类型
	 * @return 含有sign字段的XML
	 */
	public static String generateSignedJson(final Map<String, Object> data, String key, SignType signType)
			throws Exception {
		String sign = generateSignature(data, key, signType);
		data.put(Constants.FIELD_SIGN, sign);
		return data.toString();
	}

	/**
	 * 判断签名是否正确
	 *
	 * @param jsonStr JSON格式数据
	 * @param key     API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(String jsonStr, String key) throws Exception {
		JSONObject data = JSONObject.parseObject(jsonStr);
		if (!data.containsKey(Constants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.getString(Constants.FIELD_SIGN);
		return generateSignature(data, key).equals(sign);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
	 *
	 * @param data map类型数据
	 * @param key  API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(final Map<String, Object> data, String key) throws Exception {
		return isSignatureValid(data, key, SignType.MD5);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。
	 *
	 * @param data     map类型数据
	 * @param key      API密钥
	 * @param signType 签名方式
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(final Map<String, Object> data, String key, SignType signType)
			throws Exception {
		if (!data.containsKey(Constants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.get(Constants.FIELD_SIGN).toString();
		return generateSignature(data, key, signType).equals(sign);
	}

	/**
	 * 生成签名
	 *
	 * @param data 待签名数据
	 * @param key  API密钥
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, Object> data, String key) throws Exception {
		return generateSignature(data, key, SignType.MD5);
	}

	/**
	 * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
	 *
	 * @param data     待签名数据
	 * @param key      API密钥
	 * @param signType 签名方式
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, Object> data, String key, SignType signType)
			throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (k.equals(Constants.FIELD_SIGN)) {
				continue;
			}
			if (data.get(k).toString().trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(handleDatas(data, k, key, signType)).append("&");
		}
		sb.append("key=").append(key);
		if (SignType.MD5.equals(signType)) {
			return MD5(sb.toString()).toUpperCase();
		} else if (SignType.HMACSHA256.equals(signType)) {
			return HMACSHA256(sb.toString(), key);
		} else {
			throw new Exception(String.format("Invalid sign_type: %s", signType));
		}
	}

	/**
	 * 处理返回数据中含有数组的数据
	 * 
	 * @param data
	 * @param key
	 * @param signType
	 * @return
	 * @throws Exception
	 */
	public static String handleDatas(final Map<String, Object> data, String k, String key, SignType signType)
			throws Exception {
		String handleString = data.get(k).toString().trim();
		if (k.equals(Constants.FIELD_DATA)) {
			JSONObject jsonData = JSONObject.parseObject(data.get(k).toString());
			if (jsonData != null && !jsonData.isEmpty() && !jsonData.containsKey(Constants.FIELD_DATAS)) {
				handleString = generateSignature(jsonData, key, signType);
			} else if (jsonData != null && !jsonData.isEmpty()) {
				JSONArray jsonArray = JSONArray.parseArray(jsonData.get(Constants.FIELD_DATAS).toString());
				if (jsonArray != null && !jsonArray.isEmpty()) {
					handleString = "";
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonDataOne = jsonArray.getJSONObject(i);
						handleString += generateSignature(jsonDataOne, key, signType);
					}
				}
			}
		}
		return handleString;
	}

	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String generateNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

	/**
	 * 生成 MD5
	 *
	 * @param data 待处理数据
	 * @return MD5结果
	 */
	public static String MD5(String data) throws Exception {
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成 HMACSHA256
	 * 
	 * @param data 待处理数据
	 * @param key  密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String HMACSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 日志
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		Logger logger = LoggerFactory.getLogger("xysdk java sdk v1.0 ---mango");
		return logger;
	}

	/**
	 * 获取当前时间戳，单位秒
	 * 
	 * @return
	 */
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取当前时间戳，单位毫秒
	 * 
	 * @return
	 */
	public static long getCurrentTimestampMs() {
		return System.currentTimeMillis();
	}

	/**
	 * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
	 * 
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

	public static void main(String[] args) {
		
		try {
			System.out.println("md5="+MD5("123"));
			System.out.println("md5=202cb962ac59075b964b07152d234b70");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("appid", "wx533f7d32f0cab12f");
		data.put("sign_type", SignType.MD5);
		try {
			System.out.println("data" + generateSignedJson(data, "123456", SignType.MD5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
