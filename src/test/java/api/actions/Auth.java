package api.actions;

import api.pojo.LoginReq;
import api.pojo.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class Auth {
    private final static String URL = "https://testslotegrator.com";

    public static void authorize(String email, String password) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(URL)
                .addHeader("Authorization", "Bearer " + getToken(email, password))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static String getToken(String email, String password) {
        return new BaseController().post(new LoginReq().setEmail(email).setPassword(password),
                URL + "/api/tester/login", 201).as(LoginResponse.class).getAccessToken();
    }
}
