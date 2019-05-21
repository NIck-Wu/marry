package com.xy.marry.server.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xy.marry.common.Constants.MagicValue;
import com.xy.marry.common.ErrorCodeEnum;
import com.xy.marry.entity.ProjectQjxcxShake;
import com.xy.marry.exception.ResponseResult;
import com.xy.marry.mapper.ProjectQjxcxShakeMapper;
import com.xy.marry.server.ProjectQjxcxShakeService;
import com.xy.marry.server.WebSocketService;

@Service
@Transactional
public class ProjectQjxcxShakeServiceImpl implements ProjectQjxcxShakeService {

	@Autowired
	private ProjectQjxcxShakeMapper projectQjxcxShakeMapper;

	@Autowired
	WebSocketService webSocketService;

	/**
	 * 根据ID查找活动信息
	 */
	@Override
	public ProjectQjxcxShake findById(Integer shakeID) {

		return projectQjxcxShakeMapper.selectByPrimaryKey(shakeID);
	}

	/**
	 * 获取一个当前正在进行中的游戏
	 */
	@Override
	public ProjectQjxcxShake getOngoingShakeGame() {

		return projectQjxcxShakeMapper.getOngoingShakeGame();

	}

	/**
	 * 创建一个新的摇一摇活动
	 */
	@Override
	public void createShakeGame(ProjectQjxcxShake projectQjxcxShake) {

		projectQjxcxShake.setAddTime(new Date());
		projectQjxcxShake.setUpdateTime(new Date());
		projectQjxcxShake.setIsDelete(new Byte(MagicValue.LENGTH_STR_1));
		projectQjxcxShake.setState(new Byte(MagicValue.LENGTH_STR_1));

		projectQjxcxShakeMapper.insertSelective(projectQjxcxShake);

	}

	/**
	 * 更新摇一摇游戏状态
	 */
	@Override
	public ResponseResult<ProjectQjxcxShake> updateShakeGame(
			ProjectQjxcxShake projectQjxcxShake) {

		ProjectQjxcxShake projectQjxcxShakeQuery = this
				.findById(projectQjxcxShake.getId());
		if (null == projectQjxcxShakeQuery) {
			return new ResponseResult<>(
					ErrorCodeEnum.SHAKE_GAME_NOT_FOUND.getCode(),
					ErrorCodeEnum.SHAKE_GAME_NOT_FOUND.getDesc());
		}

		int intShakeGameState = (int) projectQjxcxShakeQuery.getState();
		if (MagicValue.LENGTH_INTEGER_1 == intShakeGameState) {
			if (MagicValue.LENGTH_INTEGER_1 == (int) projectQjxcxShake
					.getState()
					|| MagicValue.LENGTH_INTEGER_3 == (int) projectQjxcxShake
							.getState()) {
				return new ResponseResult<>(
						ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getCode(),
						ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getDesc());
			}
		} else if (MagicValue.LENGTH_INTEGER_2 == intShakeGameState) {
			if (MagicValue.LENGTH_INTEGER_1 == (int) projectQjxcxShake
					.getState()
					|| MagicValue.LENGTH_INTEGER_3 == (int) projectQjxcxShake
							.getState()) {
				return new ResponseResult<>(
						ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getCode(),
						ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getDesc());
			}
		} else {
			return new ResponseResult<>(
					ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getCode(),
					ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getDesc());
		}
		projectQjxcxShake.setUpdateTime(new Date());
		projectQjxcxShakeMapper.updateByPrimaryKeySelective(projectQjxcxShake);

		return new ResponseResult<>();
	}

	/**
	 * 开始摇一摇游戏
	 */
	@Override
	public ResponseResult<ProjectQjxcxShake> startShakeGame(
			ProjectQjxcxShake projectQjxcxShake) {

		ProjectQjxcxShake shakeGame = this.findById(projectQjxcxShake.getId());
		if (null == shakeGame) {
			return new ResponseResult<>(
					ErrorCodeEnum.SHAKE_GAME_NOT_FOUND.getCode(),
					ErrorCodeEnum.SHAKE_GAME_NOT_FOUND.getDesc());
		}

		if (MagicValue.LENGTH_INTEGER_1 != (int) shakeGame.getState()) {
			return new ResponseResult<>(
					ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getCode(),
					ErrorCodeEnum.SHAKE_GAME_STATUS_ERROR.getDesc());
		}

		webSocketService.pushAll(ErrorCodeEnum.SHAKE_GAME_IS_REALLY_GO
				.getDesc());

		/** 倒计时 */
		Integer countDownReallyTime = 3; // 活动开始前的倒计时
		Integer countDown = shakeGame.getCountDown(); // 倒计时

		while (countDownReallyTime != 0) {
			JSONObject jsonObject = new JSONObject();
			JSONObject msgJsonObject = new JSONObject();
			msgJsonObject.put("shakeId", shakeGame.getId());
			msgJsonObject.put("shake_status", (int) shakeGame.getState());
			msgJsonObject.put("countDown", countDownReallyTime);
			jsonObject.put("type", MagicValue.LENGTH_INTEGER_1);
			jsonObject.put("operation", MagicValue.LENGTH_INTEGER_2);
			jsonObject.put("message", msgJsonObject.toString());
			webSocketService.pushAll(jsonObject.toString());

			try {
				Thread.sleep(1000);
				countDownReallyTime--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		webSocketService.pushAll(ErrorCodeEnum.SHAKE_GAME_IS_STARTED.getDesc());

		while (countDown != 0) {
			JSONObject jsonObject = new JSONObject();
			JSONObject msgJsonObject = new JSONObject();
			msgJsonObject.put("shakeId", shakeGame.getId());
			msgJsonObject.put("shake_status", (int) shakeGame.getState());
			msgJsonObject.put("countDown", countDown);
			jsonObject.put("type", MagicValue.LENGTH_INTEGER_1);
			jsonObject.put("operation", MagicValue.LENGTH_INTEGER_3);
			jsonObject.put("message", msgJsonObject.toString());

			webSocketService.pushAll(jsonObject.toString());
			try {
				Thread.sleep(1000);
				countDown--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 更新当前游戏的状态为已经结束
		shakeGame.setState(new Byte(MagicValue.LENGTH_STR_2));
		this.updateShakeGame(shakeGame);

		webSocketService.pushAll(ErrorCodeEnum.SHAKE_GAME_IS_END.getDesc());
		
		// 推送消息给所有用户游戏已经结束
		JSONObject jsonObject = new JSONObject();
		JSONObject msgJsonObject = new JSONObject();
		msgJsonObject.put("shakeId", shakeGame.getId());
		msgJsonObject.put("shake_status", (int) shakeGame.getState());
		jsonObject.put("type", MagicValue.LENGTH_INTEGER_1);
		jsonObject.put("operation", MagicValue.LENGTH_INTEGER_5);
		jsonObject.put("message", msgJsonObject.toString());
		webSocketService.pushAll(jsonObject.toString());

		return new ResponseResult<ProjectQjxcxShake>();
	}
}
