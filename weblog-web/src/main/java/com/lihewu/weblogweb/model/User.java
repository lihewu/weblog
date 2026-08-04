package com.lihewu.weblogweb.model;

import lombok.Data;
/**
 * @description: model
 * @author: lihewu
 * @date: 2026/8/4 14:45
 * @project:weblog-springboot
 */
@Data
public class User {
    //用户名
    private String username;
    //性别
    private Integer sex;
}
