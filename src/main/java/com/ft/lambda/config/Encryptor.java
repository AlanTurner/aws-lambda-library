package com.ft.lambda.config;

import com.amazonaws.regions.Regions;
import com.ft.lambda.config.model.Encryption;
import com.ft.lambda.config.model.EncryptorRequest;

import java.io.IOException;

public final class Encryptor {

    public static final String LAMBDA_NAME = "konencryptapi_LambdaEncryptService";
    private static final String DEFAULT_QUALIFIER = "PROD";

    private Encryptor() {
    }

    public static Encryption action(final EncryptorRequest request) throws IOException {
        return action(request, DEFAULT_QUALIFIER, Regions.EU_WEST_1);
    }

    public static Encryption action(final EncryptorRequest request, final String qualifier) throws IOException {
        return action(request, qualifier, Regions.EU_WEST_1);
    }

    public static Encryption action(final EncryptorRequest request, final String qualifier, final Regions region) throws IOException {
        return Shared.getFromLambda(request, Encryption.class, LAMBDA_NAME, qualifier, region);
    }


}