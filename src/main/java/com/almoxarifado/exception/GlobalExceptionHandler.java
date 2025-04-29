package com.almoxarifado.exception;

import com.almoxarifado.exception.exceptioncustom.NotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handlerProductNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not Found Exception, Check Documentation")
                        .details(notFoundException.getMessage())
                        .developerMessage(notFoundException.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }

}
