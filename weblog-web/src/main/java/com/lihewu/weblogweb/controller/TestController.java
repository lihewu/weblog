package com.lihewu.weblogweb.controller;

import com.lihewu.weblogmodulecommon.aspect.ApiOperationLog;
import com.lihewu.weblogmodulecommon.utils.Response;
import com.lihewu.weblogweb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * @description: controller
 * @author: lihewu
 * @date: 2026/8/4 14:44
 * @project:weblog-springboot
 */

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public ResponseEntity<Response<String>> test(@RequestBody @Validated User user) {
        // 返参
        return ResponseEntity.ok(Response.success("参数没有任何问题"));
    }

}

