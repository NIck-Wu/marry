package com.xy.marry.exception;

import java.util.Map;

/**
 * @auther: chenbowen
 * @date: 2019/2/24 15:00
 * @description: 校验返回
 */

public class ValidationResult {

	// 校验结果是否有错
	private boolean hasErrors;

	// 校验错误信息
	private Map<String, String> errorMsg;

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}

}
