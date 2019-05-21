package com.xy.marry.common;

public class Constants {

	public enum SignType {
		MD5, HMACSHA256
	}

	public static final String FIELD_KEY = "key";
	public static final String FIELD_SIGN = "sign";
	public static final String FIELD_SIGN_TYPE = "sign_type";
	public static final String FIELD_APPID = "appid";
	public static final String FIELD_MCH_ID = "mch_id";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_STATE_SIGN = "state_sign";
	public static final String FIELD_DATA = "data";
	public static final String FIELD_DATAS = "datas";
	public static final String PROJECT_QJXCX = "project_qjxcx";
	
	public static final String HMACSHA256 = "HMAC-SHA256";
	public static final String MD5 = "MD5";
	
	
	public static final String SHAKE = "SHAKE";

	
	/**
	 * 接口状态码常量
	 */
	public static final String SUCCESS_CODE = "200"; // 请求成功
	public static final String NO_DATA = "204"; // 暂无数据
	public static final String FAIL_CODE = "400"; // 请求失败
	public static final String CHECKCODE_ERROR = "401"; // 校验码签名错误
	public static final String DATA_SIGN_ABNORMAL = "402"; // 数据签名状态异常
	public static final String DATA_INTERFACE_ABNORMAL = "403"; // 数据接口异常，或请求参数有误
	public static final String PARAMETER_ERROR = "411"; // 请求参数有误
	public static final String MCH_INFO_ERROR = "412"; // 商户信息错误
	public static final String MCH_INFO_ABNORMAL = "413"; // 商户信息异常,请联系管理员
	public static final String ABNORMAL_SERVER = "500"; // 服务器异常

	 /**
     * 魔法值
     */
    public interface MagicValue {
        Integer LENGTH_INTEGER__1 = -1;
        Integer LENGTH_INTEGER_0 = 0;
        Integer LENGTH_INTEGER_1 = 1;
        Integer LENGTH_INTEGER_2 = 2;
        Integer LENGTH_INTEGER_3 = 3;
        Integer LENGTH_INTEGER_4 = 4;
        Integer LENGTH_INTEGER_5 = 5;
        Integer LENGTH_INTEGER_6 = 6;
        Integer LENGTH_INTEGER_7 = 7;
        Integer LENGTH_INTEGER_8 = 8;
        Integer LENGTH_INTEGER_9 = 9;
        Integer LENGTH_INTEGER_10 = 10;
        Integer LENGTH_INTEGER_11 = 11;
        Integer LENGTH_INTEGER_12 = 12;
        Integer LENGTH_INTEGER_13 = 13;
        Integer LENGTH_INTEGER_14 = 14;
        Integer LENGTH_INTEGER_15 = 15;
        Integer LENGTH_INTEGER_16 = 16;
        Integer LENGTH_INTEGER_17 = 17;
        Integer LENGTH_INTEGER_18 = 18;
        Integer LENGTH_INTEGER_19 = 19;
        Integer LENGTH_INTEGER_20 = 20;

        String LENGTH_STR__1 = "-1";
        String LENGTH_STR_0 = "0";
        String LENGTH_STR_1 = "1";
        String LENGTH_STR_2 = "2";
        String LENGTH_STR_3 = "3";
        String LENGTH_STR_4 = "4";
        String LENGTH_STR_5 = "5";
        String LENGTH_STR_6 = "6";
        String LENGTH_STR_7 = "7";
        String LENGTH_STR_8 = "8";
        String LENGTH_STR_9 = "9";
        String LENGTH_STR_10 = "10";
        String LENGTH_STR_11 = "11";
        String LENGTH_STR_12 = "12";
        String LENGTH_STR_13 = "13";
        String LENGTH_STR_14 = "14";
        String LENGTH_STR_15 = "15";
        String LENGTH_STR_16 = "16";
        String LENGTH_STR_17 = "17";
        String LENGTH_STR_18 = "18";
        String LENGTH_STR_19 = "19";
        String LENGTH_STR_20 = "20";

        String FLAG_YES = "Y";
        String FLAG_NO = "N";
    }
}
