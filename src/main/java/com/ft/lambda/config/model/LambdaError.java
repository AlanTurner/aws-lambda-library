package com.ft.lambda.config.model;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Lambda error
 */
public class LambdaError extends RuntimeException {

    public LambdaError(final LambdaLogger logger, final String message, final String awsId, final Throwable e) {
        super(String.format("[Error][%s] %s [Root: %s]", awsId, message, ExceptionUtils.getStackTrace(e)), e);
        logger.log(String.format("[Error][%s] %s [Root: %s]", awsId, message, ExceptionUtils.getStackTrace(e)));
    }
}
