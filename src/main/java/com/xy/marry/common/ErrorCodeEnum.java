package com.xy.marry.common;

/**
 * 错误代码枚举类
 * 
 * @author NIck
 */
public enum ErrorCodeEnum {
	/** 系統变量*/
	SUCCESS("200","成功"),
	FAIL("400","失敗"),
	PARAM_ERROR("501","入参异常"),
	SERVICE_ERROR("502", "服务异常"),
	
	/** 验签相关*/
	SIGN_IS_NULL("3001","sign为空"),
	SIGN_TYPE_IS_NULL("3002","sign_type为空"),
	APP_ID_IS_NULL("3003","appid为空"),
	KEY_IS_NULL("3004","key为空"),
	SIGN_ERROR("3005","sign错误"),
	//验签下小程序项目
	MERCHANT_PROJECT_IS_NULL("3111","此appid商户项目为空"),
	PROJECT_QJXCX_IS_NULL("3112","此appid请柬小程序为空"),
	
	/** 用戶枚举相关  1000-1100*/
	USER_ID_OR_QJXCXID_IS_NULL("1002","userId或qjxcxId为空"),
	YOU_ALREADY_INPUT_IN_INOF("1003","您已经填写了出席信息"),
	YOU_ALREADY_SEND_BLESS("1004","	您已经送出祝福了"),

	
	/** 摇一摇游戏相关   2000-2100*/
	SHAKE_GAME_NOT_FOUND("2001","摇一摇活动未找到"),
	SHAKE_GAME_STATUS_ERROR("2002","摇一摇活动状态错误,无法操作"),
	SHAKE_GAME_IS_REALLY_GO("2003","活动准备开始啦~ ,抓紧时间准备 ！"),
	SHAKE_GAME_IS_STARTED("2004","活动开始 ！"),
	SHAKE_GAME_IS_END("2005","活动结束 ！"),
	;

	private String code;

	private String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private ErrorCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
