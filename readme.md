## AWS Lambda Library

A collection of useful classes to ease your Java development.

Provides:

    - Configuration utilities
    - Encryption utilities
    - Qualifier class for aliases.
    - 'Shared' class for common operations.
    

Current AWS doesn't support Lambda configuration.  To help overcome this a AWS Config Lambda has been created.

In addition this library also supports encryption to secure your sensitive information.


Configuration Usage
-------------------

The Configurator class has several methods **get(...)**. These will call the Configuration Service Lambda directly and NOT via an API.  

You must have the correct permissions for this to work.

        {
           "Effect": "Allow",
           "Action": [
               "lambda:InvokeFunction"
           ],
           "Resource": [
               "arn:aws:lambda:*:*:function:konconfigapi_*"
           ]
        },
        

Note: If you use the AWS Policy Generator API with "lambdConfig" set to true - this will be automatically set for you.

General use
-----------

By default the library will attempt to load from the PROD configuration lambda.  You can override this (see methods)

It's as simple as:

    Configuration configuration = Configurator.get(configName);

How to work with a Qualifier
----------------------------

To allow a single Lambda to be deployed and pushed through 'environments' you can use Lambda's ALIAS and VERSIONING functions.

Once you have setup your Lambda's to use such an environment you can then perform some environment specific logic.

**Note: the term 'environment' here is nothing more than a label.**

You can then write code

    Qualifier qualifier = Qualifier.get(context);
    if(Qualifier.QualifierType.ALIAS.equals(qualifier.getQualifierType()){
    
        String configName = String.format("myfolder/%s.json", qualifier.getId().toLowerCase());
    
        Configuration configuration = Configurator.get(configName);

    } else ...


Encryptor usage
---------------

You are able to encrypt and decrypt values using this library but you will need to obtain an Encryption ID first before usage.

Please see https://konstructor.in.ft.com and view the Encryption Service API.  There will be an option to create an ID.

Once done you can use this in your process:

    final Encryption action = Encryptor.action(new EncryptRequest("71b4b1e9-61f3-MY-ID-KEY-855d8a8eee56", "encrypt-me"));

and

    final Encryption action = Encryptor.action(new DecryptRequest("71b4b1e9-61f3-MY-ID-KEY-855d8a8eee56", "encrypted-value"));

This ID should be stored in your configuration file and not in your code.

For security purposes you should ensure your logging levels are set so that the response is not shown in the logs:

log4j.logger.httpclient.wire=INFO

Shared
------

There is a very useful class to simplify your codebase.  This class provides:

- json to model
- model to json
- Lambda invoke
- Lambda sync invoke 



Build
-----

To build this library you will need to set your AWS ID/SECRET as either system or environment variables.