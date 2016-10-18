package com.ft.lambda.config.model;

public class EncryptRequest implements EncryptorRequest {

    private String value;
    private String key;
    private final String type = "ENCRYPT";

    public EncryptRequest() {
    }

    public EncryptRequest(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String getType() {
        return type;
    }

}
