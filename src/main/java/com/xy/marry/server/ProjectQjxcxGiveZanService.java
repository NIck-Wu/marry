package com.xy.marry.server;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxGiveZan;

public interface ProjectQjxcxGiveZanService {

	/**
	 * 通过请柬小程序id获取小程序人员点赞信息
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxGiveZan> findByQjxcxId(Integer qjxcxId);
	
	/**
	 * 通过用户id、请柬小程序id获取小程序人员点赞信息
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxGiveZan> findByUserIdAndQjxcxId(Integer userId,Integer qjxcxId);
	
	/**
	 * 添加点赞信息
	 * @param record
	 * @return
	 */
	public int insertSelective(ProjectQjxcxGiveZan record);
}
