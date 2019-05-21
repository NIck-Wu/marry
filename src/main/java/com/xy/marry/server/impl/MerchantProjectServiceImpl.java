package com.xy.marry.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.MerchantProject;
import com.xy.marry.mapper.MerchantProjectMapper;
import com.xy.marry.server.MerchantProjectService;


@Service
@Transactional
public class MerchantProjectServiceImpl implements MerchantProjectService {

	@Autowired
	private MerchantProjectMapper merchantProjectMapper;


	@Override
	public MerchantProject findByAppId(String appid) {
		return merchantProjectMapper.selectByAppidKey(appid);
	}

}
