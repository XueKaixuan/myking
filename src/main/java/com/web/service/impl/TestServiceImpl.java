package com.web.service.impl;

import com.web.entity.TestBean;
import com.web.mapper.TestMapper;
import com.web.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public TestBean findByName(TestBean testBean) {
        return testMapper.findByName(testBean);
    }

    @Override
    public void save(TestBean testBean) {

        testMapper.save(testBean);
    }
}
