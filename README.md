
## Introduction

A math evaluator service

## System requirements

You need to have maven 3.x as well as java 8 in order to build and run the service

## Running the service

The artifact produced by is an executable jar with an embedded tomcat. To run the service you will need to execute:

*hector@Iscariot ~/projects/personal/eval-me $ java -jar ./target/eval-me-1.0.jar &*

Application and access logs are located in the *logs* directory under the main project location

*hector@Iscariot ~/projects/personal/eval-me/logs*

## Technical considerations
The service has been built with SpringBoot. It provides a REST endpoint ( POST /eval ) that expects "expression" as the parameter containing
the expression to evaluate.

The service uses the Open Source JEval to parse and produce the result back to the caller.
The service supports XML and JSON format, if no accept header is specified JSON format will be used as default.

## Testing

To manually test the service you can use curl command, see an example below:

```
curl -v -F "expression=-1+3-10" -H "Accept: application/xml" -X POST http://localhost:8090/eval
<?xml version="1.0" encoding="UTF-8" standalone="yes"?><operation><expression>-1+3-10</expression><result>-8.0</result></operation>
```