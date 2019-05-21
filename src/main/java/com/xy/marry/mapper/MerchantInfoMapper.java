package com.xy.marry.mapper;

import com.xy.marry.entity.MerchantInfo;

public interface MerchantInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MerchantInfo record);

    int insertSelective(MerchantInfo record);

    MerchantInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantInfo record);

    int updateByPrimaryKey(MerchantInfo record);
}