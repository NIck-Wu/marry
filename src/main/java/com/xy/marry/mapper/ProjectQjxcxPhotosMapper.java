package com.xy.marry.mapper;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxPhotos;

public interface ProjectQjxcxPhotosMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProjectQjxcxPhotos record);

	int insertSelective(ProjectQjxcxPhotos record);

	ProjectQjxcxPhotos selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProjectQjxcxPhotos record);

	int updateByPrimaryKey(ProjectQjxcxPhotos record);

	/**
	 * 通过请柬小程序id查出相册信息
	 * 
	 * @param qjxcxId
	 * @return
	 */
	List<ProjectQjxcxPhotos> selectByQjxcxIdKey(Integer qjxcxId);
}