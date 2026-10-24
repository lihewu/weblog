package com.lihewu.weblogweb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j 接口文档配置，统一管理文档信息和接口分组。
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI weblogOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Weblog 接口文档")
                .description("Weblog 前台与管理后台接口文档")
                .version("1.0"));
    }

    @Bean
    public GroupedOpenApi webApi() {
        return GroupedOpenApi.builder()
                .group("weblog-web")
                .packagesToScan("com.lihewu.weblogweb.controller")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("weblog-admin")
                .packagesToScan("com.lihewu.weblogmoduleadmin.controller")
                .pathsToMatch("/**")
                .build();
    }
}
