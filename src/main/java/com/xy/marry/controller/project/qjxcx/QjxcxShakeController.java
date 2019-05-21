package com.xy.marry.controller.project.qjxcx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xy.marry.entity.ProjectQjxcxShake;
import com.xy.marry.entity.ProjectQjxcxShakeUserRanking;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.server.ProjectQjxcxShakeService;
import com.xy.marry.server.ProjectQjxcxShakeUserRankingService;

@RestController
@RequestMapping("/api/shake/shake/")
public class QjxcxShakeController {

	@Autowired
	ProjectQjxcxShakeUserRankingService projectQjxcxShakeUserRankingService;

	@Autowired
	ProjectQjxcxShakeService projectQjxcxShakeService;

	/**
	 * 创建一个新的摇一摇游戏
	 * 
	 * @param projectQjxcxShake
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "createShakeGame", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxShake> createShakeGame(@RequestBody ProjectQjxcxShake projectQjxcxShake,
			HttpServletRequest request) {

		projectQjxcxShakeService.createShakeGame(projectQjxcxShake);

		return new ResponseResult<ProjectQjxcxShake>();
	}

	/**
	 * 开始摇一摇游戏
	 * 
	 * @param projectQjxcxShake
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "startShakeGame", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxShake> startShakeGame(@RequestBody ProjectQjxcxShake projectQjxcxShake,
			HttpServletRequest request) {

		return projectQjxcxShakeService.startShakeGame(projectQjxcxShake);

	}

	/**
	 * 根据ID查找活动信息
	 * 
	 * @param projectQjxcxShake
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getShakeGame", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxShake> getShakeGame(@RequestBody ProjectQjxcxShake projectQjxcxShake,
			HttpServletRequest request) {

		ProjectQjxcxShake shakeQuery = projectQjxcxShakeService.findById(projectQjxcxShake.getId());

		return new ResponseResult<ProjectQjxcxShake>(shakeQuery);
	}

	/**
	 * 获取一个当前正在进行的摇一摇活动
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOngoingShakeGame", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxShake> getOngoingShakeGame(HttpServletRequest request) {

		ProjectQjxcxShake shakeQuery = projectQjxcxShakeService.getOngoingShakeGame();

		return new ResponseResult<ProjectQjxcxShake>(shakeQuery);
	}

	/**
	 * 更新摇一摇游戏状态
	 * 
	 * @param projectQjxcxShake
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateShakeGame", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxShake> updateShakeGame(@RequestBody ProjectQjxcxShake projectQjxcxShake,
			HttpServletRequest request) {

		projectQjxcxShakeService.updateShakeGame(projectQjxcxShake);

		return new ResponseResult<ProjectQjxcxShake>();
	}

	/**
	 * 新增摇一摇记录到redis
	 * 
	 * @param shakeUserRanking
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "shake", method = RequestMethod.POST)
	public ResponseResult<ProjectQjxcxShakeUserRanking> shake(
			@RequestBody ProjectQjxcxShakeUserRanking shakeUserRanking, HttpServletRequest request) {

		projectQjxcxShakeUserRankingService.addShakeToRedis(shakeUserRanking);

		return new ResponseResult<ProjectQjxcxShakeUserRanking>();
	}

}
