package com.thanh.boot.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationErrorResponse extends BaseBadRequestResponse{

    private void setCode(){
        code = "VALIDATION_ERROR";
    }

    public ValidationErrorResponse() {
        setCode();
    }

    public ValidationErrorResponse(MethodArgumentNotValidException exception) {
        setCode();
        this.violations = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        int errorCount = bindingResult.getErrorCount();
        System.out.println("..............errorCount: " + errorCount);
        if (errorCount > fieldErrors.size()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors){

                System.out.println("Error class: " + error.getClass().getName() + ": " + error.getDefaultMessage());
            }
        } else {
            for (FieldError fe : fieldErrors) {
                this.violations.add(new Violation(fe));
            }
        }
    }


    private String description;

    private List<Violation> violations;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public class Violation {
        private String fieldName;
        private String errorMessage;

        Violation(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Violation(String fieldName, String errorMessage) {
            this.fieldName = fieldName;
            this.errorMessage = errorMessage;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
