package com.lihewu.weblogweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lihewu")
@ComponentScan({"com.lihewu.weblogmodulecommon.*"})// 多模块项目中，必需手动指定扫描 com.lihewu.weblogmodulecommon 包下面的所有类
public class WeblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeblogWebApplication.class, args);
    }

}
