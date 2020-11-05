package com.thanh.boot.controller.response;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class BadRequestResponse extends BaseBadRequestResponse{

    private String description;

    public BadRequestResponse() {
        code = "BAD_REQUEST";
    }

    public BadRequestResponse(MethodArgumentNotValidException exception) {

        code = "BAD_REQUEST";

        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        if (errors.size() > 0){
            ObjectError e0 = errors.get(0);
            this.description = e0.getDefaultMessage();
        }
    }

    public BadRequestResponse(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
