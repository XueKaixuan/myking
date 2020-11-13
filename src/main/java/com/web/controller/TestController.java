package com.web.controller;

import com.web.entity.TestBean;
import com.web.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("test")
@Controller
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("findByName")
    @ResponseBody
    public String findByName(TestBean testBean){
        TestBean ABC = testService.findByName(testBean);
        if (ABC == null){
            return "ok";
        }
        return "no";
    }
}
