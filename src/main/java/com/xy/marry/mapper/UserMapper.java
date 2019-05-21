package com.xy.marry.mapper;

import com.xy.marry.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     */
    User selectByOpenidKey(String openid);
}