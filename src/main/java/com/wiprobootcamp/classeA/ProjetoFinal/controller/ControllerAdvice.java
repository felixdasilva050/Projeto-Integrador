package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleEmptyImput(BusinessException businessException) {
        return new ResponseEntity<String>(businessException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
