package com.ytrue.security.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 定义用户表实体
 */
@Data
@AllArgsConstructor
public class UserPojo implements Serializable {

    private Integer id;
    private String name;
    private String mobile;
    private String mail;
    private String pwd;

    public UserPojo() {
    }
}
