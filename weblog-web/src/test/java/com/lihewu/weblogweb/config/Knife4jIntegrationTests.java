package com.lihewu.weblogweb.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Knife4jIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void knife4jPageIsAvailable() throws Exception {
        mockMvc.perform(get("/doc.html"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/html"));
    }

    @Test
    void webDocumentationContainsTestEndpoint() throws Exception {
        mockMvc.perform(get("/v3/api-docs/weblog-web"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title").value("Weblog 接口文档"))
                .andExpect(jsonPath("$.paths['/test'].post").exists());
    }

    @Test
    void bothDocumentationGroupsAreRegistered() throws Exception {
        mockMvc.perform(get("/v3/api-docs/swagger-config"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.urls[*].name").value(hasItems("weblog-web", "weblog-admin")));
    }
}
