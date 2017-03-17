package com.hmoneoju.evalme.resource;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EvalmeServiceIT {

    @Test
    public void jsonResponse() {
        given().
            accept(ContentType.JSON).formParam("expression","2+2").
        when().
            post("/eval").
        then().
            statusCode(HttpStatus.OK.value());
    }

    @Test
    public void xmlResponse() {
        given().
            accept(ContentType.XML).formParam("expression","2+2").
        when().
            post("/eval").
        then().
            statusCode(HttpStatus.OK.value());
    }

    @Test
    public void badRequest() {
        given().
            accept(ContentType.XML).formParam("expression","2+2)").
        when().
            post("/eval").
        then().
            statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
