package com.xy.marry.mapper;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxAttend;

public interface ProjectQjxcxAttendMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProjectQjxcxAttend record);

	int insertSelective(ProjectQjxcxAttend record);

	ProjectQjxcxAttend selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProjectQjxcxAttend record);

	int updateByPrimaryKey(ProjectQjxcxAttend record);

	/**
	 * 通过请柬小程序id查出出席人员信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxAttend> selectByQjxcxIdKey(Integer qjxcxId);
	
	/**
	 * 通过用户id、请柬小程序id查出出席人员信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxAttend> selectByUserIdAndQjxcxIdKey(Integer userId,Integer qjxcxId);
}