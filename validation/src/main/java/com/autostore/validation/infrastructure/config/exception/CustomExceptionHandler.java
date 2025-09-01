package com.autostore.validation.infrastructure.config.exception;


import com.autostore.validation.application.exception.ApplicationException;
import com.autostore.validation.domain.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

}
