package com.xy.marry.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.xy.marry.common.ErrorCodeEnum;

/**
 * @auther: chenbowen
 * @date: 2019/2/24 15:00
 * @description:捕获全局异常
 */

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {
	/**
	 * 只捕获 IllegalArgumentException 异常
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private ResponseResult<JSONObject> illegalArgumentExceptionHandler(HttpServletRequest request, Exception e) {
		JSONObject json = new JSONObject();
		json.put("requestUrl", request.getRequestURI());
		json.put("requestName", e.getClass().getName());
		return new ResponseResult<>(ErrorCodeEnum.PARAM_ERROR.getCode(),
				ErrorCodeEnum.PARAM_ERROR.getDesc(),json);
//		System.out.println("IllegalArgumentException 异常: " + e.getClass().getName());
//		return Result.error(ResultTypeEnum.PARAM_ERROR, "请求地址：" + request.getRequestURI());
	}

	/**
	 * 只捕获 MyException 异常
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private ResponseResult myExceptionHandler(HttpServletRequest request, MyException e) {
		JSONObject json = new JSONObject();
		json.put("requestUrl", request.getRequestURI());
		json.put("exceptionMSg", e.getMsg());
		return new ResponseResult<>(ErrorCodeEnum.FAIL.getCode(),
				ErrorCodeEnum.FAIL.getDesc(),json);
	}

	/**
	 * 默认异常捕获，上面两个异常没走的话走我。
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private ResponseResult exceptionHandler(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		System.out.println("异常 ：" + e.getClass().getName());
		JSONObject json = new JSONObject();
		json.put("exceptionMsg",e.getClass().getName());
		request.getMethod();
		return new ResponseResult<>(ErrorCodeEnum.SERVICE_ERROR.getCode(),
				ErrorCodeEnum.SERVICE_ERROR.getDesc(),json);
//		return Result.error(ResultTypeEnum.SERVICE_ERROR, "不符合上述异常的默认走我这");
	}
}
