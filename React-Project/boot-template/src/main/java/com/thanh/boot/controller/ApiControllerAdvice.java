package com.thanh.boot.controller;

import com.thanh.boot.controller.response.BadRequestResponse;
import com.thanh.boot.controller.response.BaseBadRequestResponse;
import com.thanh.boot.controller.response.ValidationErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Locale;

@RestControllerAdvice
public class ApiControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    private MessageSource messageSource;

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ValidationErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
        logger.error("", exception);
        return new ValidationErrorResponse();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseBadRequestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletResponse response) {
        response.setStatus(400);
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.getErrorCount() > bindingResult.getFieldErrorCount()){
            return new BadRequestResponse(exception);
        } else {
            return new ValidationErrorResponse(exception);
        }
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public BadRequestResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletResponse response) {
        //couldNotDelete
        response.setStatus(400);
        Locale locale = LocaleContextHolder.getLocale();
        return new BadRequestResponse(messageSource.getMessage("couldNotDelete", null, locale));
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
