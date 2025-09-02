package com.autostore.bff.infrastructure.config.exception;


import com.autostore.bff.application.exception.ApplicationException;
import com.autostore.bff.application.exception.ValidationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException ex) {
        String errorMessage = extractErrorMessage(ex.contentUTF8());
        final ErrorMessage error = new ErrorMessage(HttpStatus.valueOf(ex.status()), errorMessage);
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }

    private String extractErrorMessage(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            if (rootNode.has("errors") && rootNode.get("errors").isArray() && rootNode.get("errors").size() > 0)
                return rootNode.get("errors").get(0).asText();
            return responseBody;
        } catch (Exception e) {
            return extractErrorMessageFromString(responseBody);
        }
    }

    private String extractErrorMessageFromString(String responseBody) {
        Pattern pattern = Pattern.compile("\\[\\{\".*?\"errors\":\\[\"(.*?)\"\\]");
        Matcher matcher = pattern.matcher(responseBody);
        if (matcher.find()) return matcher.group(1);
        return "Error processing request";
    }

}
