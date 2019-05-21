package com.xy.marry.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.ProjectQjxcxWords;
import com.xy.marry.mapper.ProjectQjxcxWordsMapper;
import com.xy.marry.server.ProjectQjxcxWordsService;


@Service
@Transactional
public class ProjectQjxcxWordsServiceImpl implements ProjectQjxcxWordsService {

	@Autowired
	private ProjectQjxcxWordsMapper projectQjxcxWordsMapper;

	@Override
	public List<ProjectQjxcxWords> findByQjxcxId(Integer qjxcxId) {
		return projectQjxcxWordsMapper.selectByQjxcxIdKey(qjxcxId);
	}

	@Override
	public List<ProjectQjxcxWords> findByUserIdAndQjxcxId(Integer userId,Integer qjxcxId) {
		return projectQjxcxWordsMapper.selectByUserIdAndQjxcxIdKey(userId,qjxcxId);
	}
	
	@Override
	public int insertSelective(ProjectQjxcxWords record) {
		// TODO Auto-generated method stub
		return projectQjxcxWordsMapper.insertSelective(record);
	}

}
