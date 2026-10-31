package com.lihewu.weblogmodulecommon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lihewu.weblogmodulecommon.config.JacksonConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: com.lihewu.weblogmodulecommon.utils
 * @author: 26690
 * @date: 2026/7/23 16:22
 * @project:weblog-springboot
 */


@Slf4j
public class JsonUtil {
    private static final ObjectMapper INSTANCE = new ObjectMapper()
            .registerModule(JacksonConfig.javaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}

