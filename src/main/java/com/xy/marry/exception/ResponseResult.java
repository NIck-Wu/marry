package com.xy.marry.exception;

import java.io.Serializable;
import java.util.UUID;

import com.xy.marry.common.ErrorCodeEnum;



/**
 * 统一响应结果
 * @author lenovo
 *
 */
public class ResponseResult<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String requestId = UUID.randomUUID().toString();
	
	private String code = ErrorCodeEnum.SUCCESS.getCode();
	
	private String msg = ErrorCodeEnum.SUCCESS.getDesc();
	
	private T data;
	

	public ResponseResult() {
		
	}

	public ResponseResult(T data) {
		this.data = data;
	}
	
	public ResponseResult(ErrorCodeEnum errorCodeEnum) {
		super();
		this.code = errorCodeEnum.getCode();
		this.msg = errorCodeEnum.getDesc();
	}
	
	public ResponseResult(ErrorCodeEnum errorCodeEnum,T data) {
		super();
		this.code = errorCodeEnum.getCode();
		this.msg = errorCodeEnum.getDesc();
		this.data = data;
	}


	public ResponseResult(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = UUID.randomUUID().toString();
	}
	
	
	public boolean success(){
		return ErrorCodeEnum.SUCCESS.getCode().equals(this.code);
	}
}
