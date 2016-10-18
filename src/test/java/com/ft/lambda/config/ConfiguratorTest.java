package com.ft.lambda.config;

import com.amazonaws.services.lambda.runtime.Context;
import com.ft.lambda.config.model.ConfigServiceRequest;
import com.ft.lambda.config.model.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfiguratorTest {

    @Mock
    Context context;

    @Test
    public void getQualifierA() throws Exception {
        when(context.getInvokedFunctionArn()).thenReturn("arn:aws:lambda:eu-west-1:810381234814:function:konconfigapi_Config_Service");
        Qualifier qualifier = Qualifier.get(context);
        assertEquals(Qualifier.QualifierType.UNKNOWN, qualifier.getQualifierType());
        assertNull(qualifier.getId());
    }

    @Test
    public void getQualifierB() throws Exception {
        when(context.getInvokedFunctionArn()).thenReturn("arn:aws:lambda:eu-west-1:810381234814:function:konconfigapi_Config_Service:$LATEST");
        Qualifier qualifier = Qualifier.get(context);
        assertEquals(Qualifier.QualifierType.VERSION, qualifier.getQualifierType());
        assertEquals("$LATEST", qualifier.getId());
    }

    @Test
    public void getQualifierC() throws Exception {
        when(context.getInvokedFunctionArn()).thenReturn("arn:aws:lambda:eu-west-1:810381234814:function:konconfigapi_Config_Service:1");
        Qualifier qualifier = Qualifier.get(context);
        assertEquals(Qualifier.QualifierType.VERSION, qualifier.getQualifierType());
        assertEquals("1", qualifier.getId());
    }

    @Test
    public void getQualifierD() throws Exception {
        when(context.getInvokedFunctionArn()).thenReturn("arn:aws:lambda:eu-west-1:810381234814:function:konconfigapi_Config_Service:PROD");
        Qualifier qualifier = Qualifier.get(context);
        assertEquals(Qualifier.QualifierType.ALIAS, qualifier.getQualifierType());
        assertEquals("PROD", qualifier.getId());
    }

    @Test
    public void lambdaTest() throws IOException {

        final Configuration result = Configurator.get(new ConfigServiceRequest("konconfigapi/config.json"));
        assertEquals("com.ft.lambda.konstructor.config", result.getBucket());
        final TestConfig testConfig = Shared.stringToModel(result.getContents(), TestConfig.class);
        assertEquals("passed", testConfig.getTest());

    }

}