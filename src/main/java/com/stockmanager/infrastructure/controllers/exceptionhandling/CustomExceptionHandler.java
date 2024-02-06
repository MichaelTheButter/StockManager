package com.stockmanager.infrastructure.controllers.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.BindErrorUtils;
import org.springframework.validation.BindException;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> wrongInputHandler(
            MethodArgumentNotValidException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(e.getMessage())
        );
    }
//    private List<String> getErrors(MethodArgumentNotValidException exception) {
//        return exception.getBody().getInstance();
//    }

    private record ErrorResponse(
            String message
    ){}
}
