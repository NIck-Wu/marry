package com.xy.marry.controller.project;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.common.Constants;
import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.ProjectQjxcx;
import com.xy.marry.exception.ResponseResult;

@RestController
@RequestMapping("/api/project/qjxcx")
public class QjxcxController {

	/**
	 * 查询请柬信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcx> find(
			@RequestBody Map<String, Object> reqMap,HttpServletRequest request) {
		ProjectQjxcx projectQjxcx=(ProjectQjxcx) request.getAttribute(Constants.PROJECT_QJXCX);
		return new ResponseResult<ProjectQjxcx>(
				ErrorCodeEnum.SUCCESS.getCode(),
				ErrorCodeEnum.SUCCESS.getDesc(), projectQjxcx);
	}

}
