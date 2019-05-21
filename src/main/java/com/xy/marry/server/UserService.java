package com.xy.marry.server;

import java.util.Map;

import com.xy.marry.entity.User;

public interface UserService {

	/**
	 * 通过userid 获得用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public User findById(Integer userId);

	/**
	 * 通过openid 获取用户信息
	 * 
	 * @param openid
	 * @return
	 */
	public User findByOpenid(String openid);

	/**
	 * 添加用户信息,返回user
	 * 
	 * @param record
	 * @return
	 */
	public User insertSelective(User record);

	/**
	 * 添加用户信息，返回用户信息
	 * 
	 * @param reqMap
	 * @return
	 */
	public User insertSelective(Map<String, Object> reqMap);

	/**
	 * 添加或查询用户信息，如用户存在则查询，反之添加
	 * 
	 * @param record
	 * @return
	 */
	public User insertOrSelect(Map<String, Object> reqMap);
	
	/**
	 *  抽奖
	 * @return
	 */
	public User luckDraw();
}
