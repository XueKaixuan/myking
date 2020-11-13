package com.web.service;

import com.web.entity.TestBean;

public interface TestService {

    TestBean findByName(TestBean testBean);

    void save(TestBean testBean);
}
