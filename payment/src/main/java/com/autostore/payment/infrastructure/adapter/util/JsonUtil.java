package com.autostore.payment.infrastructure.adapter.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return "";
        }
    }

    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

}
