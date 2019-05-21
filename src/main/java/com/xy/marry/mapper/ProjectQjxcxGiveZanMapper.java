package com.xy.marry.mapper;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxGiveZan;

public interface ProjectQjxcxGiveZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQjxcxGiveZan record);

    int insertSelective(ProjectQjxcxGiveZan record);

    ProjectQjxcxGiveZan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectQjxcxGiveZan record);

    int updateByPrimaryKey(ProjectQjxcxGiveZan record);
    
	/**
	 * 通过请柬小程序id查出点赞人员信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxGiveZan> selectByQjxcxIdKey(Integer qjxcxId);
	
    
	/**
	 * 通过用户id、请柬小程序id查出点赞人员信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxGiveZan> selectByUserIdAndQjxcxIdKey(Integer userId,Integer qjxcxId);
}