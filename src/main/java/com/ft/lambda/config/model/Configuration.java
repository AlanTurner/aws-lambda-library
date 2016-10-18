package com.ft.lambda.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * INPORTANT - ENSURE THE LAMBDA CONFIG SERVICE  RESPONSEMODEL IS MATCHED TO THIS!
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    private String contents;
    private String bucket;
    private String errorMessage;

    public String getBucket() {
        return bucket;
    }

    public String getContents() {
        return contents;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
