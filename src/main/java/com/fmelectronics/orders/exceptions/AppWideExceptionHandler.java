package com.fmelectronics.orders.exceptions;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Exception e) {
//        return new ResponseEntity(new StandardResponse("500", "Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException(NotFoundException e) {
//        return new ResponseEntity(new StandardResponse("404", "Error", e.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(ValidateException.class)
//    public ResponseEntity handleValidationException(ValidateException e) {
//        return new ResponseEntity(new StandardResponse("400", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
//    }
}
