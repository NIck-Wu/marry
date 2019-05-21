package com.xy.marry.mapper;

import com.xy.marry.entity.ProjectQjxcx;

public interface ProjectQjxcxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQjxcx record);

    int insertSelective(ProjectQjxcx record);

    ProjectQjxcx selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectQjxcx record);

    int updateByPrimaryKey(ProjectQjxcx record);
    
    
    /**
     * 通过商户项目，商户id和商户项目id获取单条小程序项目
     * @param record
     * @return
     */
    ProjectQjxcx selectByMerchantKey(Integer merchantId ,Integer merchantProjectId);
    
}