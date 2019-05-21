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
import com.xy.marry.entity.ProjectQjxcxAttend;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.ProjectQjxcxAttendService;

@RestController
@RequestMapping("/api/project/qjxcx/attend")
public class QjxcxAttendController {
	@Autowired
	private ProjectQjxcxAttendService projectQjxcxAttendService;

	/**
	 * 查询请柬出席信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public ResponseResult<List<ProjectQjxcxAttend>> find(
			@RequestBody Map<String, Object> reqMap,HttpServletRequest request) {
		ProjectQjxcx projectQjxcx=(ProjectQjxcx) request.getAttribute(Constants.PROJECT_QJXCX);
		List<ProjectQjxcxAttend> projectQjxcxAttendsList = projectQjxcxAttendService
				.findByQjxcxId(projectQjxcx.getId());
		return new ResponseResult<List<ProjectQjxcxAttend>>(
				ErrorCodeEnum.SUCCESS, projectQjxcxAttendsList);
	}

	/**
	 * 查询请柬出席信息
	 * 
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxAttend> get(
			@RequestBody Map<String, Object> reqMap) {
		if (!reqMap.containsKey("qjxcxId") || reqMap.get("qjxcxId") == null
				|| !reqMap.containsKey("userId") || reqMap.get("userId") == null)
			return new ResponseResult<ProjectQjxcxAttend>(
					ErrorCodeEnum.USER_ID_OR_QJXCXID_IS_NULL);

		List<ProjectQjxcxAttend> projectQjxcxAttendsList = projectQjxcxAttendService
				.findByUserIdAndQjxcxId(
						Integer.parseInt(reqMap.get("userId").toString()),
						Integer.parseInt(reqMap.get("qjxcxId").toString()));
		if (projectQjxcxAttendsList == null
				|| projectQjxcxAttendsList.isEmpty())
			return new ResponseResult<ProjectQjxcxAttend>(
					ErrorCodeEnum.FAIL);

		return new ResponseResult<ProjectQjxcxAttend>(
				ErrorCodeEnum.SUCCESS, projectQjxcxAttendsList.get(0));
	}

	/**
	 * 添加请柬出席信息
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult<ErrorCodeEnum> add(
			@RequestBody ProjectQjxcxAttend record) {
		if (null == record.getQjxcxId() || null == record.getUserId())
			return new ResponseResult<ErrorCodeEnum>(ErrorCodeEnum.SUCCESS.getCode(),ErrorCodeEnum.SUCCESS.getDesc());

		List<ProjectQjxcxAttend> list = projectQjxcxAttendService
				.findByUserIdAndQjxcxId(record.getUserId(), record.getQjxcxId());

		if (list == null || list.isEmpty()) {
			projectQjxcxAttendService.insertSelective(record);
			return new ResponseResult<ErrorCodeEnum>(ErrorCodeEnum.SUCCESS.getCode(),ErrorCodeEnum.SUCCESS.getDesc());
		} else
			return new ResponseResult<>(
					ErrorCodeEnum.YOU_ALREADY_INPUT_IN_INOF.getCode(),ErrorCodeEnum.YOU_ALREADY_INPUT_IN_INOF.getDesc());
	}
}
