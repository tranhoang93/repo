package com.thanh.boot.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thanh.boot.util.Constants;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessResponse {
    private String code;
    private String description;

    public SuccessResponse() {
    }

    public SuccessResponse(String description) {
        this.code = Constants.SUCCESS;
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
