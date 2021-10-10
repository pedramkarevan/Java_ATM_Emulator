package com.atm.emulator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;


@ControllerAdvice
@RestControllerAdvice
public class ExceptionController extends DefaultResponseErrorHandler {



    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> baseExceptionHandler(BaseException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(LocalDateTime.now(), ex.getErrorCode(), ex.getMessage(), null);
        return ResponseEntity.status(ex.getErrorCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler( Exception ex,  WebRequest request)  {
        ex.printStackTrace();

        if (ex instanceof NullPointerException)
        return new ResponseEntity(new ErrorDetail(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "NullPointer ",null), HttpStatus.BAD_REQUEST);
        if (ex instanceof IllegalAccessException)
        return new ResponseEntity(new ErrorDetail(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "IllegalAccess ",null), HttpStatus.BAD_REQUEST);

        return new ResponseEntity(new ErrorDetail(LocalDateTime.now(), 500, "Internal Error",null), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
