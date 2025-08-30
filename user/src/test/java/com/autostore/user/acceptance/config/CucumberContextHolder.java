package com.autostore.user.acceptance.config;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class CucumberContextHolder {

    private ResponseEntity<String> response;
    private Map<String, Object> requestData;

    public Object getRequestData() {
        return requestData;
    }

    public void setRequestData(Map<String, Object> requestData) {
        this.requestData = requestData;
    }


    public ResponseEntity<String> getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity<String> response) {
        this.response = response;
    }

}
