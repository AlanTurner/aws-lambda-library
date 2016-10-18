package com.ft.lambda.config;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.util.NumberUtils;
import com.amazonaws.util.StringUtils;

public class Qualifier {

    public enum QualifierType {
        VERSION,
        ALIAS,
        UNKNOWN
    }

    private final String id;
    private final QualifierType qualifierType;
    private static final int QUALIFIER_LOCATION = 7;
    private static final int QUALIFIER_COUNT = 8;

    public Qualifier(final String id) {
        this.id = id;
        if (StringUtils.isNullOrEmpty(id)) {
            qualifierType = QualifierType.UNKNOWN;
        } else if (id.equals("$LATEST") || NumberUtils.tryParseInt(id) != null) {
            qualifierType = QualifierType.VERSION;
        } else {
            qualifierType = QualifierType.ALIAS;
        }
    }

    public String getId() {
        return id;
    }

    public QualifierType getQualifierType() {
        return qualifierType;
    }

    /**
     * Will attempt to extract the Version or Alias from the Invoked Function Arn.
     * @param context The lambda context.
     * @return A qualifier trying to identify the Qualifier name and type.
     */
    public static Qualifier get(final Context context) {

        String invokedFunctionArn = context.getInvokedFunctionArn();

        if (!StringUtils.isNullOrEmpty(invokedFunctionArn)) {
            String[] split = invokedFunctionArn.split(":");
            if (split.length == QUALIFIER_COUNT) {
                return new Qualifier(split[QUALIFIER_LOCATION]);
            }
        }

        return new Qualifier(null);
    }
}
