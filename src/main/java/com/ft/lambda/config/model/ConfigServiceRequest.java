package com.ft.lambda.config.model;

public class ConfigServiceRequest {

    private String configName;

    public ConfigServiceRequest() {
    }

    public ConfigServiceRequest(final String configName) {
        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }

    public ConfigServiceRequest setConfigName(final String configName) {
        this.configName = configName;
        return this;
    }
}
