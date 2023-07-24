package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.Request;

public class Spec {

    public static RequestSpecification requestSpec (String URL, String authToken) {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .addHeader("Authorization", "Bearer " + authToken)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOk201 () {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static void installSpecification (RequestSpecification request) {
        RestAssured.requestSpecification = request;
//        RestAssured.responseSpecification = response;
    }
}
