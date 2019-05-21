package com.xy.marry.controller.project.qjxcx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.common.Constants;
import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.ProjectQjxcx;
import com.xy.marry.entity.ProjectQjxcxWords;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.ProjectQjxcxWordsService;

@RestController
@RequestMapping("/api/project/qjxcx/words")
public class QjxcxWordsController {
	@Autowired
	private ProjectQjxcxWordsService projectQjxcxWordsService;

	/**
	 * 查询请柬留言信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public ResponseResult<List<ProjectQjxcxWords>> find(
			@RequestBody Map<String, Object> reqMap,HttpServletRequest request) {
		ProjectQjxcx projectQjxcx=(ProjectQjxcx) request.getAttribute(Constants.PROJECT_QJXCX);
		List<ProjectQjxcxWords> projectQjxcxWordsList = projectQjxcxWordsService
				.findByQjxcxId(projectQjxcx.getId());
		return new ResponseResult<List<ProjectQjxcxWords>>(
				ErrorCodeEnum.SUCCESS, projectQjxcxWordsList);
	}

	/**
	 * 添加请柬留言信息
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult<ErrorCodeEnum> add(
			@RequestBody ProjectQjxcxWords record) {
		projectQjxcxWordsService.insertSelective(record);
		return new ResponseResult<ErrorCodeEnum>(ErrorCodeEnum.SUCCESS.getCode(),ErrorCodeEnum.SUCCESS.getDesc());
	}

}
