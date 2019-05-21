package com.xy.marry.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.ProjectQjxcxPhotos;
import com.xy.marry.mapper.ProjectQjxcxPhotosMapper;
import com.xy.marry.server.ProjectQjxcxPhotosService;

@Service
@Transactional
public class ProjectQjxcxPhotosServiceImpl implements ProjectQjxcxPhotosService {

	@Autowired
	private ProjectQjxcxPhotosMapper projectQjxcxPhotosMapper;

	@Override
	public List<ProjectQjxcxPhotos> findByQjxcxId(Integer qjxcxId) {
		return projectQjxcxPhotosMapper.selectByQjxcxIdKey(qjxcxId);
	}

	@Override
	public int insertSelective(ProjectQjxcxPhotos record) {
		// TODO Auto-generated method stub
		return projectQjxcxPhotosMapper.insertSelective(record);
	}

}
