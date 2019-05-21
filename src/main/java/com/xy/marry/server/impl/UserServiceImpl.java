package com.xy.marry.server.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xy.marry.entity.ProjectQjxcxWords;
import com.xy.marry.entity.User;
import com.xy.marry.mapper.ProjectQjxcxWordsMapper;
import com.xy.marry.mapper.UserMapper;
import com.xy.marry.server.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProjectQjxcxWordsMapper projectQjxcxWordsMapper;

	@Override
	public User findById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User findByOpenid(String openid) {
		return userMapper.selectByOpenidKey(openid);
	}

	@Override
	public User insertSelective(User record) {
		int relust=userMapper.insertSelective(record);
		if(relust==1&&record.getId()!=null) {
			User user=this.findById(record.getId());
			return user;
		}
		return null;
	}

	@Override
	public User insertSelective(Map<String, Object> reqMap) {
		// TODO Auto-generated method stub
		if(reqMap.containsKey("openid")&&reqMap.containsKey("merchant_id")) {
			User user=new User();
			user.setMerchantId(reqMap.containsKey("merchant_id")&&reqMap.get("merchant_id")!=null?Integer.parseInt(reqMap.get("merchant_id").toString()):null);
			user.setNickName(reqMap.containsKey("nick_name")&&reqMap.get("nick_name")!=null?reqMap.get("nick_name").toString():null);
			user.setUnionid(reqMap.containsKey("unionid")&&reqMap.get("unionid")!=null?reqMap.get("unionid").toString():null);
			user.setOpenid(reqMap.containsKey("openid")&&reqMap.get("openid")!=null?reqMap.get("openid").toString():null);
			user.setAvatarUrl(reqMap.containsKey("avatar_url")&&reqMap.get("avatar_url")!=null?reqMap.get("avatar_url").toString():null);
			user.setGender(reqMap.containsKey("gender")&&reqMap.get("gender")!=null?reqMap.get("gender").toString():null);
			user.setCity(reqMap.containsKey("city")&&reqMap.get("city")!=null?reqMap.get("city").toString():null);
			user.setProvince(reqMap.containsKey("province")&&reqMap.get("province")!=null?reqMap.get("province").toString():null);
			user.setCountry(reqMap.containsKey("country")&&reqMap.get("country")!=null?reqMap.get("country").toString():null);
			user.setLanguage(reqMap.containsKey("language")&&reqMap.get("language")!=null?reqMap.get("language").toString():null);
			user.setAddTime(new Date());
			return this.insertSelective(user);
		}
		return null;
	}

	
	@Override
	public User insertOrSelect(Map<String, Object> reqMap) {
		// TODO Auto-generated method stub
		if(reqMap.containsKey("openid")) {
			User user=this.findByOpenid(reqMap.get("openid").toString());
			if(user!=null) {
				return user;
			}else {
				return this.insertSelective(reqMap);
			}
		}
		return null;
	}

	/**
	 * 抽奖
	 */
	@Override
	public User luckDraw() {
		List<ProjectQjxcxWords> list = projectQjxcxWordsMapper.listQueue();
		if (null == list || list.isEmpty()) {
			return null;
		}
		/** 随机获取一条留言记录*/
		ProjectQjxcxWords p = this.getLuckDraw(list);
		/** 获取用户信息*/
		User userQuery = this.queryUser(p.getUserId());
		
		return userQuery;
	}
	
	/**
	 * 查詢用戶信息
	 * @param userID
	 * @return
	 */
	private User queryUser(Integer userID) {
		if (null == userID)
			return null;
		return userMapper.selectByPrimaryKey(userID);
	}
	
	/**
	 * 随机获取一条中奖记录
	 * @param userID
	 * @return
	 */
	private ProjectQjxcxWords getLuckDraw(List<ProjectQjxcxWords> list) {
		Random r = new Random();
		int luckNum = r.nextInt(list.size()); // 生成[0,list.size]区间的整数
		ProjectQjxcxWords p = projectQjxcxWordsMapper.selectByPrimaryKey(list
				.get(luckNum).getId());
		if (null == p){
			return this.getLuckDraw(list);
		}
		return p;
	}
}
