package com.xy.marry.server;

import java.util.List;

import com.xy.marry.entity.ProjectQjxcxPhotos;

public interface ProjectQjxcxPhotosService {

	/**
	 * 通过请柬小程序id获取小程序请柬相册
	 * @param merchantProject
	 * @return
	 */
	public List<ProjectQjxcxPhotos> findByQjxcxId(Integer qjxcxId);
	
	/**
	 * 添加请柬相册
	 * @param record
	 * @return
	 */
	public int insertSelective(ProjectQjxcxPhotos record);
}
