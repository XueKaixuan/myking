package com.web.service.impl;

import com.web.entity.CityBean;
import com.web.entity.GradeBean;
import com.web.entity.Student;
import com.web.entity.UserBean;
import com.web.mapper.UserMapper;
import com.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public List<UserBean> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public List<Student> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<GradeBean> getGradeList() {
        return userMapper.getGradeList();
    }

    @Override
    public List<CityBean> getCityListById(Long id) {
        return userMapper.getCityListById(id);
    }

    @Override
    public void saveStuInfo(Student student) {
        if(student.getSid()!=null){
            //修改
        }else{
            //新增学生
            userMapper.saveStuInfo(student);
            //新增学生班级
            userMapper.saveStudentGrade(student.getSid(),student.getGb().getGid());
        }
    }

    @Override
    public Student findOne(Long sid) {
        return userMapper.findOne(sid);
    }

    @Override
    public void deleteStudentBatch(Long[] sids) {
       if(sids!=null&&sids.length>=1){
            for (Long sid : sids) {
                userMapper.deleteStudentBySid(sid);
                userMapper.deleteStudentGrade(sid);
            }
        }
    }
    @Override
    public void deleteStu(Long sid) {
        userMapper.deleteStudentBySid(sid);
        userMapper.deleteStudentGrade(sid);
    }


}
