package com.ft.lambda.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Encryption {

    private String value;
    private String errorMessage;

    public String getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
