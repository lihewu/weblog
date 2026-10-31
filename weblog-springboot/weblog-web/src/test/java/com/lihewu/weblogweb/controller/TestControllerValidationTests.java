package com.lihewu.weblogweb.controller;

import com.lihewu.weblogmodulecommon.enums.ResponseCodeEnum;
import com.lihewu.weblogmodulecommon.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TestControllerValidationTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void invalidRequestReturnsAllValidationMessages() throws Exception {
        mockMvc.perform(post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode()))
                .andExpect(jsonPath("$.message").value(containsString("用户名不能为空")))
                .andExpect(jsonPath("$.message").value(containsString("性别不能为空")))
                .andExpect(jsonPath("$.message").value(containsString("年龄不能为空")))
                .andExpect(jsonPath("$.message").value(containsString("邮箱不能为空")));
    }

    @Test
    void invalidValuesReturnConstraintMessages() throws Exception {
        mockMvc.perform(post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"sex\":1,\"age\":17,\"email\":\"invalid\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode()))
                .andExpect(jsonPath("$.message").value(containsString("年龄必须大于或等于 18")))
                .andExpect(jsonPath("$.message").value(containsString("邮箱格式不正确")));
    }

    @Test
    void validRequestKeepsSuccessResponse() throws Exception {
        mockMvc.perform(post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"sex\":1,\"age\":18,\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("参数没有任何问题"));
    }
}
