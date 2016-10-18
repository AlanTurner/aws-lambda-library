package com.ft.lambda.config;

import com.amazonaws.regions.Regions;
import com.ft.lambda.config.model.ConfigServiceRequest;
import com.ft.lambda.config.model.Configuration;

import java.io.IOException;

public final class Configurator {
    public static final String CONFIG_LAMBDA_NAME = "konconfigapi_LambdaConfigService";
    private static final String DEFAULT_QUALIFIER = "PROD";

    private Configurator() {
    }

    public static Configuration info() throws IOException {
        return get(new ConfigServiceRequest("info"));
    }

    /**
     * The default way to call for a configuration file.
     *
     * It will call [ConfigServiceLambda]:PROD
     *
     * @param request The config request
     * @return A response model with the contents and the bucket name it was loaded from.
     * @throws IOException
     */
    public static Configuration get(final ConfigServiceRequest request) throws IOException {
        return get(request, DEFAULT_QUALIFIER, Regions.EU_WEST_1);
    }

    /**
     * Allows the qualifier to be overridden.
     *
     * It will call [ConfigServiceLambda]:[QUALIFIER]
     *
     * @param request The config request
     * @param qualifier the qualifier (aka Alias or Version) name.
     * @return A response model with the contents and the bucket name it was loaded from.
     * @throws IOException
     */
    public static Configuration get(final ConfigServiceRequest request, final String qualifier) throws IOException {
        return get(request, qualifier, Regions.EU_WEST_1);
    }

    /**
     * Allows the qualifier and AWS region to be overridden.
     *
     * @param request The config request
     * @param qualifier the qualifier (aka Alias or Version) name.
     * @param region The AWS region.
     * @return A response model with the contents and the bucket name it was loaded from.
     * @throws IOException
     */
    public static Configuration get(final ConfigServiceRequest request, final String qualifier, final Regions region) throws IOException {
        return Shared.getFromLambda(request, Configuration.class, CONFIG_LAMBDA_NAME, qualifier, region);
    }






}