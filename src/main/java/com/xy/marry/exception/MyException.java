package com.xy.marry.exception;

import com.xy.marry.common.ErrorCodeEnum;

/**
 * @auther: chenbowen
 * @date: 2019/2/24 15:00
 * @description:自定义异常错误
 */

@SuppressWarnings("serial")
public class MyException extends RuntimeException {

	private String code;

	private String msg;

	public MyException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public MyException(String msg) {
		this.code = ErrorCodeEnum.SERVICE_ERROR.getCode();
		this.msg = msg;
	}

	public MyException(ErrorCodeEnum errorCodeEnum) {
		this.code = errorCodeEnum.getCode();
		this.msg = errorCodeEnum.getDesc();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
