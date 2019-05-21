package com.xy.marry.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.ProjectQjxcxAttend;
import com.xy.marry.mapper.ProjectQjxcxAttendMapper;
import com.xy.marry.server.ProjectQjxcxAttendService;

@Service
@Transactional
public class ProjectQjxcxAttendServiceImpl implements ProjectQjxcxAttendService {

	@Autowired
	private ProjectQjxcxAttendMapper projectQjxcxAttendMapper;

	@Override
	public List<ProjectQjxcxAttend> findByQjxcxId(Integer qjxcxId) {
		return projectQjxcxAttendMapper.selectByQjxcxIdKey(qjxcxId);
	}
	
	@Override
	public List<ProjectQjxcxAttend> findByUserIdAndQjxcxId(Integer userId,Integer qjxcxId) {
		return projectQjxcxAttendMapper.selectByUserIdAndQjxcxIdKey(userId,qjxcxId);
	}

	@Override
	public int insertSelective(ProjectQjxcxAttend record) {
		// TODO Auto-generated method stub
		return projectQjxcxAttendMapper.insertSelective(record);
	}

}
