package com.xy.marry.server;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxAttend;

public interface ProjectQjxcxAttendService {

	/**
	 * 通过请柬小程序id获取小程序人员出席信息
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxAttend> findByQjxcxId(Integer qjxcxId);
	
	/**
	 * 通过用户id、请柬小程序id获取小程序人员出席信息
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxAttend> findByUserIdAndQjxcxId(Integer userId,Integer qjxcxId);
	
	/**
	 * 添加出席信息
	 * @param record
	 * @return
	 */
	public int insertSelective(ProjectQjxcxAttend record);
}
