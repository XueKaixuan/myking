package com.web.mapper;

import com.web.entity.CityBean;
import com.web.entity.GradeBean;
import com.web.entity.Student;
import com.web.entity.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<UserBean> getUserList();

    //全查学生列表
    List<Student>findAll();
    //全查班级列表
    List<GradeBean>getGradeList();
    //省市县,三级联动
    List<CityBean> getCityListById(Long id);
    //新增学生
    void saveStuInfo(Student student);
    //新增学生班级
    void saveStudentGrade(@Param("sid") Long sid,@Param("gid") Long gid);
    //修改回显
    Student findOne(Long sid);
    //删除学生信息
    void deleteStudentBySid(Long sid);
    //删除学生班级表信息
    void deleteStudentGrade(Long sid);
}


