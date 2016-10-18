package com.ft.lambda.config;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

public final class Shared {

    private Shared() {
    }

    public static <T> T stringToModel(final String json, final Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, valueType);
    }

    public static String modelToString(final Object model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(model);
    }

    public static <T> T getFromLambda(final Object request, final Class<T> responseType, final String lambdaName, final String qualifier, final Regions region) throws IOException {

        AWSLambdaClient client = new AWSLambdaClient();
        client.setRegion(Region.getRegion(region));

        final InvokeResult result = client.invoke(
                new InvokeRequest()
                        .withFunctionName(lambdaName)
                        .withQualifier(qualifier)
                        .withPayload(Shared.modelToString(request)));

        return decodePayload(result.getPayload(), responseType);

    }

    public static ListenableFuture<InvokeResult> getFromLambdaAsync(final Object request, final String lambdaName, final String qualifier, final Regions region) throws IOException {

        AWSLambdaAsyncClient client = new AWSLambdaAsyncClient();
        client.setRegion(Region.getRegion(region));

        final Future<InvokeResult> invokeResultFuture = client.invokeAsync(
                new InvokeRequest()
                        .withFunctionName(lambdaName)
                        .withQualifier(qualifier)
                        .withPayload(Shared.modelToString(request)));

        return JdkFutureAdapters.listenInPoolThread(invokeResultFuture);

    }

    public static <T> T decodePayload(final ByteBuffer payload, final Class<T> responseType) throws IOException {
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(payload);
        return Shared.stringToModel(charBuffer.toString(), responseType);
    }
}
