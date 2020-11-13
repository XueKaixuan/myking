package com.web.controller;

import com.web.comm.ResultInfo;
import com.web.entity.*;
import com.web.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/getUserList")
    @ResponseBody
    public List<UserBean> getUserList() {

        return userService.getUserList();
    }

    /**
     * 全查班级, VUE测试
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Student> findAll() {
        List<Student> list = userService.findAll();
        return list;
    }

    /**
     * 全查班级列表
     *
     * @return
     */
    @RequestMapping("/getGradeList")
    @ResponseBody
    public List<GradeBean> getGradeList() {
        List<GradeBean> list = userService.getGradeList();
        return list;
    }

    @ResponseBody
    @RequestMapping("/getCityListById")
    public List<CityBean> getCityListById(Long id) {
        return userService.getCityListById(id);
    }

    @ResponseBody
    @RequestMapping("/saveStuInfo")
    public ResultInfo saveStuInfo(@RequestBody Student student) {
        try {
            userService.saveStuInfo(student);
            return new ResultInfo(true, "更新成功");
        } catch (Exception e) {
            return new ResultInfo(false, "更新失败");
        }
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Student findOne(Long sid){

        Student student = userService.findOne(sid);
        return student;
    }
    @ResponseBody
    @RequestMapping("/deleteStudentBatch")
    public ResultInfo deleteStudentBatch(@RequestBody Long[] sids){
        try {
            userService.deleteStudentBatch(sids);
            return new ResultInfo(true, "删除成功");
        }catch (Exception e){
            return new ResultInfo(false, "删除失败");
        }
    }
    @ResponseBody
    @RequestMapping("/deleteStu")
    public ResultInfo deleteStudentBatch(Long sid){
        try {
            userService.deleteStu(sid);
            return new ResultInfo(true, "删除成功");
        }catch (Exception e){
            return new ResultInfo(false, "删除失败");
        }
    }
}

