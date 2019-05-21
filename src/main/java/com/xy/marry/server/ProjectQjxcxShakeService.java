package com.xy.marry.server;

import com.xy.marry.entity.ProjectQjxcxShake;
import com.xy.marry.exception.ResponseResult;

public interface ProjectQjxcxShakeService {

	/**
	 * 根据ID查找活动信息
	 * 
	 * @param shakeID
	 * @return
	 */
	public ProjectQjxcxShake findById(Integer shakeID);

	/**
	 * 获取一个当前正在进行中的游戏
	 * 
	 * @return
	 */
	public ProjectQjxcxShake getOngoingShakeGame();

	/**
	 * 创建一个新的摇一摇活动
	 * 
	 * @param projectQjxcxShake
	 */
	public void createShakeGame(ProjectQjxcxShake projectQjxcxShake);

	/**
	 * 更新摇一摇游戏状态
	 * 
	 * @param projectQjxcxShake
	 * @return
	 */
	public ResponseResult<ProjectQjxcxShake> updateShakeGame(
			ProjectQjxcxShake projectQjxcxShake);

	/**
	 * 开始摇一摇游戏
	 * 
	 * @param projectQjxcxShake
	 * @return
	 */
	public ResponseResult<ProjectQjxcxShake> startShakeGame(
			ProjectQjxcxShake projectQjxcxShake);

}
