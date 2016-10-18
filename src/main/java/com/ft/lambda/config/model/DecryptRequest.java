package com.ft.lambda.config.model;

public class DecryptRequest extends EncryptRequest {

    private final String type = "DECRYPT";

    public DecryptRequest(final String key, final String value) {
        super(key, value);
    }

    public DecryptRequest() {
    }

    public String getType() {
        return this.type;
    }

}
