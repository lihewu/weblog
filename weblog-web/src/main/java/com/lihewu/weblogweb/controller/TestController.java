package com.lihewu.weblogweb.controller;

import com.lihewu.weblogmodulecommon.aspect.ApiOperationLog;
import com.lihewu.weblogmodulecommon.utils.Response;
import com.lihewu.weblogweb.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "测试接口", description = "参数校验测试")
public class TestController {

    @PostMapping("/test")
    @Operation(summary = "测试接口", description = "校验用户参数，校验失败时统一返回错误信息")
    @ApiOperationLog(description = "测试接口")
    public ResponseEntity<Response<String>> test(@RequestBody @Validated User user) {
        // 返参
        return ResponseEntity.ok(Response.success("参数没有任何问题"));
    }

}

