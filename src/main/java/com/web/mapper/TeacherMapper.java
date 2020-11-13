package com.web.mapper;

import com.web.entity.GradeBean;
import com.web.entity.TeacherBean;
import com.web.entity.TestBean;

import java.util.List;

public interface TeacherMapper {

    List<TestBean> getDuliTestList(Long tid);

    List<GradeBean> getGradeListByTid(Long tid);

    TeacherBean getTname(Long tid);
}
