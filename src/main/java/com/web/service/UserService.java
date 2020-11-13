package com.web.service;

import com.web.entity.CityBean;
import com.web.entity.GradeBean;
import com.web.entity.Student;
import com.web.entity.UserBean;

import java.util.List;

public interface UserService {

    List<UserBean> getUserList();

    //全查学生列表
    List<Student>findAll();
    //全查班级列表
    List<GradeBean>getGradeList();

    List<CityBean> getCityListById(Long id);

    void saveStuInfo(Student student);

    Student findOne(Long sid);

    void deleteStudentBatch(Long[] sids);

    void deleteStu(Long sid);
}
