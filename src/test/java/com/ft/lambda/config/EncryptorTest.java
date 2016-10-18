package com.ft.lambda.config;

import com.ft.lambda.config.model.DecryptRequest;
import com.ft.lambda.config.model.EncryptRequest;
import com.ft.lambda.config.model.Encryption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EncryptorTest {

    @Test
    public void action() throws Exception {
        final Encryption action = Encryptor.action(new EncryptRequest("71b4b1e9-61f3-4d6e-a1ae-855d8a8eee56", "encrypt-me"));

        assertTrue(action.getValue().startsWith("CiBjAlyaUmDwthraHElNmJAnKubFyc56isanZ"));

    }

    @Test
    public void decrypt() throws Exception {
        final Encryption action = Encryptor.action(new DecryptRequest("71b4b1e9-61f3-4d6e-a1ae-855d8a8eee56", "CiBjAlyaUmDwthraHElNmJAnKubFyc56isanZcgHmKuysRKRAQEBAgB4YwJcmlJg8LYa2hxJTZiQJyrmxcnOeorGp2XIB5irsrEAAABoMGYGCSqGSIb3DQEHBqBZMFcCAQAwUgYJKoZIhvcNAQcBMB4GCWCGSAFlAwQBLjARBAxoOH9z7cfVTOc0mOACARCAJWsxzmWUOoeRGsRb4Kqlx4m9ecoqES/tM9xVK5BhS7q6x9s3XX4="));

        assertEquals("encrypt-me", action.getValue());

    }

}