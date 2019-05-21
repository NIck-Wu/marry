package com.xy.marry.server;

import com.xy.marry.entity.MerchantProject;
import com.xy.marry.entity.ProjectQjxcx;

public interface ProjectQjxcxService {

	/**
	 * 通过商户项目中商户id和商户项目id获取小程序项目信息
	 * @param merchantProject
	 * @return
	 */
	public ProjectQjxcx findByMerchantProject(MerchantProject merchantProject);
}
