package com.example.SpringProject.Advice;


import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@RestController
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserErrorException.class})
    public ResponseEntity<?> handleExceptions(UserErrorException e) {
        UserErrorException errorMessage = new UserErrorException(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CouponStockException.class})
    public ResponseEntity<?> handleExceptions(CouponStockException e) {
        CouponStockException errorMessage = new CouponStockException(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}