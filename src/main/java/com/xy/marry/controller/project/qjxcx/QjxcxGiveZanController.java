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
import com.xy.marry.entity.ProjectQjxcxGiveZan;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.ProjectQjxcxGiveZanService;

@RestController
@RequestMapping("/api/project/qjxcx/givezan")
public class QjxcxGiveZanController {
	@Autowired
	private ProjectQjxcxGiveZanService projectQjxcxGiveZanService;

	/**
	 * 查询请柬点赞信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public ResponseResult<List<ProjectQjxcxGiveZan>> find(
			@RequestBody Map<String, Object> reqMap,HttpServletRequest request) {
		ProjectQjxcx projectQjxcx=(ProjectQjxcx) request.getAttribute(Constants.PROJECT_QJXCX);
		List<ProjectQjxcxGiveZan> projectQjxcxGiveZansList = projectQjxcxGiveZanService
				.findByQjxcxId(projectQjxcx.getId());
		return new ResponseResult<List<ProjectQjxcxGiveZan>>(
				ErrorCodeEnum.SUCCESS, projectQjxcxGiveZansList);
	}

	/**
	 * 添加请柬点赞信息
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult<ErrorCodeEnum> add(
			@RequestBody ProjectQjxcxGiveZan record) {
		if(null==record.getUserId()||null==record.getQjxcxId()){
			return new ResponseResult<>(ErrorCodeEnum.USER_ID_OR_QJXCXID_IS_NULL.getCode(),ErrorCodeEnum.USER_ID_OR_QJXCXID_IS_NULL.getDesc());
		}
		projectQjxcxGiveZanService.insertSelective(record);
		return new ResponseResult<>(ErrorCodeEnum.SUCCESS.getCode(),ErrorCodeEnum.SUCCESS.getDesc());
	}
}
