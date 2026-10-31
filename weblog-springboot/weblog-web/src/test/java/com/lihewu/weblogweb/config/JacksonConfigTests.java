package com.lihewu.weblogweb.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lihewu.weblogmodulecommon.config.JacksonConfig;
import com.lihewu.weblogmodulecommon.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Import;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@Import(JacksonConfig.class)
class JacksonConfigTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void serializesJavaTimeValuesAsFormattedStrings() throws Exception {
        JsonNode json = objectMapper.readTree(objectMapper.writeValueAsString(sample()));

        assertThat(json.get("dateTime").asText()).isEqualTo("2026-10-24 15:04:05");
        assertThat(json.get("date").asText()).isEqualTo("2026-10-24");
        assertThat(json.get("time").asText()).isEqualTo("15:04:05");
        assertThat(json.get("instant").asText()).isEqualTo("2026-10-24T07:04:05Z");
    }

    @Test
    void deserializesFormattedStringsIntoJavaTimeValues() throws Exception {
        DateTimePayload value = objectMapper.readValue("{"
                + "\"dateTime\":\"2026-10-24 15:04:05\","
                + "\"date\":\"2026-10-24\","
                + "\"time\":\"15:04:05\","
                + "\"instant\":\"2026-10-24T07:04:05Z\"}", DateTimePayload.class);

        DateTimePayload expected = sample();
        assertThat(value).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void preservesNullDateTimeFields() throws Exception {
        String json = objectMapper.writeValueAsString(new DateTimePayload());
        assertThat(objectMapper.readTree(json).get("dateTime").isNull()).isTrue();
        assertThat(objectMapper.readValue(json, DateTimePayload.class))
                .usingRecursiveComparison().isEqualTo(new DateTimePayload());
    }

    @Test
    void logJsonUsesTheSameDateTimeFormats() throws Exception {
        assertThat(objectMapper.readTree(JsonUtil.toJsonString(sample())))
                .isEqualTo(objectMapper.readTree(objectMapper.writeValueAsString(sample())));
    }

    private DateTimePayload sample() {
        DateTimePayload value = new DateTimePayload();
        value.dateTime = LocalDateTime.of(2026, 10, 24, 15, 4, 5);
        value.date = LocalDate.of(2026, 10, 24);
        value.time = LocalTime.of(15, 4, 5);
        value.instant = Instant.parse("2026-10-24T07:04:05Z");
        return value;
    }

    public static class DateTimePayload {
        public LocalDateTime dateTime;
        public LocalDate date;
        public LocalTime time;
        public Instant instant;
    }
}
