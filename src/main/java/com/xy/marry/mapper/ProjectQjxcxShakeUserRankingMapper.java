package com.xy.marry.mapper;

import com.xy.marry.entity.ProjectQjxcxShakeUserRanking;

public interface ProjectQjxcxShakeUserRankingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQjxcxShakeUserRanking record);

    int insertSelective(ProjectQjxcxShakeUserRanking record);

    ProjectQjxcxShakeUserRanking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectQjxcxShakeUserRanking record);

    int updateByPrimaryKey(ProjectQjxcxShakeUserRanking record);
}