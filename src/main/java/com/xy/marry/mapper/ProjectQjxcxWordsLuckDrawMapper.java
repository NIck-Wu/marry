package com.xy.marry.mapper;

import com.xy.marry.entity.ProjectQjxcxWordsLuckDraw;

public interface ProjectQjxcxWordsLuckDrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectQjxcxWordsLuckDraw record);

    int insertSelective(ProjectQjxcxWordsLuckDraw record);

    ProjectQjxcxWordsLuckDraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectQjxcxWordsLuckDraw record);

    int updateByPrimaryKey(ProjectQjxcxWordsLuckDraw record);
}