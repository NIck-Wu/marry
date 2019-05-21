package com.xy.marry.server;

import com.xy.marry.entity.MerchantProject;

public interface MerchantProjectService {

	/**
	 * 通过appid查询 商户项目
	 * 
	 * @param appid
	 * @return
	 */
	public MerchantProject findByAppId(String appid);
}
