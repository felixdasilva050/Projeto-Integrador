package com.wiprobootcamp.classeA.ProjetoFinal.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Object Not Found")
public class BusinessException extends Exception{
    private static final long serialVersionUID = 1L;

    public BusinessException (String message) {
        super(message);
    }
}
