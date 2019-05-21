package com.xy.marry.mapper;

import com.xy.marry.entity.MerchantProject;

public interface MerchantProjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MerchantProject record);

	int insertSelective(MerchantProject record);

	MerchantProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MerchantProject record);

	int updateByPrimaryKey(MerchantProject record);

	/**
	 * 根据appid查询
	 * 
	 * @param appid
	 * @return
	 */
	MerchantProject selectByAppidKey(String appid);
}