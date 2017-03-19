package com.hmoneoju.evalme.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hmoneoju.evalme.exception.EvaluationException;
import com.hmoneoju.evalme.model.Operation;
import com.hmoneoju.evalme.model.OperationError;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EvalmeServiceIT {

    private static final String EXPRESSION_PARAM_NAME = "expression";
    private static final String VALID_EXPRESSION = "2+2";
    private static final String RESULT = "4.0";
    private static final String SERVICE_URL = "/eval";
    private static final String INVALID_EXPRESSION = "2+2)";
    private static final String INVALID_EXPRESSION_MESSAGE = "[%s] is not a valid expression";

    @Value("${server.port}")
    private int port;

    @Test
    public void successJSONResponse() {
        Gson gson = new GsonBuilder().create();
        Operation operation = new Operation(VALID_EXPRESSION, RESULT);
        String expectedJSON = gson.toJson(operation);
        assertSuccess(expectedJSON, MediaType.APPLICATION_JSON, ContentType.JSON);
    }

    @Test
    public void successXMLResponse() throws JAXBException {
        Operation operation = new Operation(VALID_EXPRESSION, RESULT);
        JAXBContext jaxbContext = JAXBContext.newInstance(Operation.class);
        StringWriter writer = new StringWriter();
        jaxbContext.createMarshaller().marshal(operation, writer);
        String expectedXML = writer.toString();

        assertSuccess(expectedXML, MediaType.APPLICATION_XML, ContentType.XML);
    }

    @Test
    public void badRequestJSONResponse() {
        Gson gson = new GsonBuilder().create();
        OperationError error = buildOperationError();

        String expectedJSON = gson.toJson(error);
        assertBadRequest(expectedJSON, MediaType.APPLICATION_JSON, ContentType.JSON);
    }

    @Test
    public void badRequestXMLResponse() throws JAXBException {
        OperationError error = buildOperationError();
        JAXBContext jaxbContext = JAXBContext.newInstance(OperationError.class);
        StringWriter writer = new StringWriter();
        jaxbContext.createMarshaller().marshal(error, writer);
        String expectedXML = writer.toString();

        assertBadRequest(expectedXML, MediaType.APPLICATION_XML, ContentType.XML);
    }

    private OperationError buildOperationError() {
        OperationError error = new OperationError();
        error.setErrorCode(EvaluationException.ERROR_CODE);
        error.setMessage(String.format(INVALID_EXPRESSION_MESSAGE,INVALID_EXPRESSION));
        return error;
    }

    private void assertSuccess(String expectedResult, MediaType mediaType, ContentType contentType) {
        Response response =
            given()
                .port(port)
                .formParam(EXPRESSION_PARAM_NAME, VALID_EXPRESSION)
                .accept(mediaType.toString())
            .when()
                .post(SERVICE_URL)
            .then()
                .contentType(contentType)
                .statusCode(HttpStatus.OK.value())
            .extract().response();

        assertEquals( expectedResult, response.getBody().print());
    }

    private void assertBadRequest(String expectedError, MediaType mediaType, ContentType contentType) {
        Response response =
            given()
                .port(port)
                .formParam(EXPRESSION_PARAM_NAME, INVALID_EXPRESSION)
                .accept(mediaType.toString())
            .when()
                .post(SERVICE_URL)
            .then()
                .contentType(contentType)
                .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract().response();

        assertEquals( expectedError, response.getBody().print());
    }

}
