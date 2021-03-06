package com.web.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBean implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Integer age;
}
