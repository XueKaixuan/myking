package com.web.service;

import com.web.entity.ExamBean;
import com.web.entity.GradeBean;
import com.web.entity.TestBean;

import java.util.List;

public interface TeacherService {

    List<TestBean> getDuliTestList(Long tid);

    List<GradeBean> getGradeListByTid(Long tid);

    void saveTestMark(Long tid, List<ExamBean> list, TestBean testBean, Long[] gids);
}
