package com.xy.marry.mapper;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxWords;
import com.xy.marry.entity.User;

public interface ProjectQjxcxWordsMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProjectQjxcxWords record);

	int insertSelective(ProjectQjxcxWords record);

	ProjectQjxcxWords selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProjectQjxcxWords record);

	int updateByPrimaryKey(ProjectQjxcxWords record);

	/**
	 * 通过请柬小程序id查出留言信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxWords> selectByQjxcxIdKey(Integer qjxcxId);
	
	/**
	 * 通过用户id、请柬小程序id查出留言信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxWords> selectByUserIdAndQjxcxIdKey(Integer userId,Integer qjxcxId);
	
	/**
	 * 获取最后100条信息
	 * @param userId
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxWords> listQueue();

}