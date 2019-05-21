package com.xy.marry.server;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxWords;

public interface ProjectQjxcxWordsService {

	/**
	 * 通请柬小程序id获取小程序人员留言信息
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxWords> findByQjxcxId(Integer qjxcxId);
	
	/**
	 * 通过用户id、请柬小程序id获取小程序人员留言信息
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxWords> findByUserIdAndQjxcxId(Integer userId,Integer qjxcxId);
	
	/**
	 * 添加留言信息
	 * @param record
	 * @return
	 */
	public int insertSelective(ProjectQjxcxWords record);
}
