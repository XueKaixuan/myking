package com.web.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuBean implements Serializable {
    private Long mid;
    private String mname;
    private Long pid;
    private String url;
    private String target;
    private String icon;

}