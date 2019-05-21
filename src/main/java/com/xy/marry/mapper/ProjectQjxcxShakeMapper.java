package com.xy.marry.mapper;

import com.xy.marry.entity.ProjectQjxcxShake;

public interface ProjectQjxcxShakeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQjxcxShake record);

    int insertSelective(ProjectQjxcxShake record);

    ProjectQjxcxShake selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectQjxcxShake record);

    int updateByPrimaryKey(ProjectQjxcxShake record);
    
    /**
     * 获取一个当前正在进行中的游戏
     * @return
     */
    ProjectQjxcxShake getOngoingShakeGame();
}