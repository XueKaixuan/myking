package com.web.mapper;

import com.web.entity.TestBean;

public interface TestMapper {
    TestBean findByName(TestBean testBean);

    void save(TestBean testBean);
}
