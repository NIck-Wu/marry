package com.xy.marry.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.ProjectQjxcxGiveZan;
import com.xy.marry.mapper.ProjectQjxcxGiveZanMapper;
import com.xy.marry.server.ProjectQjxcxGiveZanService;


@Service
@Transactional
public class ProjectQjxcxGiveZanServiceImpl implements ProjectQjxcxGiveZanService {

	@Autowired
	private ProjectQjxcxGiveZanMapper projectQjxcxGiveZanMapper;

	@Override
	public List<ProjectQjxcxGiveZan> findByQjxcxId(Integer qjxcxId) {
		return projectQjxcxGiveZanMapper.selectByQjxcxIdKey(qjxcxId);
	}

	@Override
	public List<ProjectQjxcxGiveZan> findByUserIdAndQjxcxId(Integer userId,Integer qjxcxId) {
		return projectQjxcxGiveZanMapper.selectByUserIdAndQjxcxIdKey(userId,qjxcxId);
	}

	@Override
	public int insertSelective(ProjectQjxcxGiveZan record) {
		// TODO Auto-generated method stub
		return projectQjxcxGiveZanMapper.insertSelective(record);
	}

}
