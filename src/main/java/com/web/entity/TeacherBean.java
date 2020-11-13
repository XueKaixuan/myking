package com.web.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class TeacherBean implements Serializable {
    private Long tid;
    private String tname;
    private String taccount;
    private String telphone;
    private String email;
    private String pwd;

}
