package com.xy.marry.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.common.Constants;
import com.xy.marry.entity.ProjectQjxcxShakeUserRanking;
import com.xy.marry.redis.RedisUtil;
import com.xy.marry.server.ProjectQjxcxShakeUserRankingService;

@Service
@Transactional
public class ProjectQjxcxShakeUserRankingServiceImpl implements ProjectQjxcxShakeUserRankingService {
	@Autowired
	RedisUtil redisUtil;

	@Override
	public void addShakeToRedis(ProjectQjxcxShakeUserRanking shake) {
		// redis找
		if (null == redisUtil.get(Constants.SHAKE + shake.getUserId().toString())) {
			shake.setNumber(shake.getNumber() == null ? 1 : shake.getNumber());
			redisUtil.set(Constants.SHAKE + shake.getUserId().toString(), shake);
		} else {
			ProjectQjxcxShakeUserRanking newShake = new ProjectQjxcxShakeUserRanking();

			ProjectQjxcxShakeUserRanking shakeByRedis = (ProjectQjxcxShakeUserRanking) redisUtil
					.get(Constants.SHAKE + shake.getUserId().toString());
			newShake.setUserId(shakeByRedis.getUserId());
			newShake.setNumber(shakeByRedis.getNumber() == null ? shake.getNumber() + 1 : shakeByRedis.getNumber() + shake.getNumber());

			// redis更新 ： 先刪除后添加
			redisUtil.del(Constants.SHAKE + shakeByRedis.getUserId().toString());
			redisUtil.set(Constants.SHAKE + newShake.getUserId().toString(), newShake);
		}
	}
}
