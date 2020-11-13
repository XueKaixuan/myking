package com.web.mapper;

import org.apache.ibatis.annotations.Param;

public interface TestGradeMapper {

    void save(@Param("gid") Long gid,@Param("testid") Long testid);
}
