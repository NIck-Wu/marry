package com.xy.marry.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.MerchantProject;
import com.xy.marry.entity.ProjectQjxcx;
import com.xy.marry.mapper.ProjectQjxcxMapper;
import com.xy.marry.server.ProjectQjxcxService;

@Service
@Transactional
public class ProjectQjxcxServiceImpl implements ProjectQjxcxService {

	@Autowired
	private ProjectQjxcxMapper projectQjxcxMapper;

	@Override
	public ProjectQjxcx findByMerchantProject(MerchantProject merchantProject) {
		if (merchantProject != null) {
			return projectQjxcxMapper.selectByMerchantKey(merchantProject.getMerchantId(), merchantProject.getId());
		}
		return null;
	}

}
