package com.stockmanager.infrastructure.controllers.exceptionhandling;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final ErrorResponse badCredentials = new ErrorResponse(List.of("Authentication failed"));

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validatorHandler(
            MethodArgumentNotValidException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(getErrors(exception))
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsHandler(
            BadCredentialsException exception
    ){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                .body(badCredentials);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> wrongInputHandler(
            HttpMessageNotReadableException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(List.of(exception.getMessage())));
    }
    private List<String> getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
   }

    private record ErrorResponse(
            List<String> errors
    ){}
}
