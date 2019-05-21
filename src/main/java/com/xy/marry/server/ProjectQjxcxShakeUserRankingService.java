package com.xy.marry.server;

import com.xy.marry.entity.ProjectQjxcxShakeUserRanking;


public interface ProjectQjxcxShakeUserRankingService {

	/**
	 * 新增搖一搖记录到redis
	 */
	public void addShakeToRedis(ProjectQjxcxShakeUserRanking shake);


}
