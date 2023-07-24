package api.actions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseController {
    public Response post(Object body, String path, int statusCode) {
        return given()
                .body(body)
                .contentType(ContentType.JSON)
                .when().log().all()
                .post(path)
                .then().log().all()
                .statusCode(statusCode)
                .extract().response();
    }

    public Response delete(String path, int statusCode) {
        return given()
                .when().log().all()
                .delete(path)
                .then().log().all()
                .statusCode(statusCode)
                .extract().response();
    }

    public Response get(String path, int statusCode) {
        return given()
                .when().log().all()
                .get(path)
                .then().log().all()
                .statusCode(statusCode)
                .extract().response();
    }
}
