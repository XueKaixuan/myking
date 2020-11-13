package com.web.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityBean implements Serializable {
    private Long id;
    private Long pid;
    private String cname;
}

