package api.actions;

import api.pojo.GetOnePlayer;
import api.pojo.UserDataResponse;
import api.pojo.UserRegistrationReq;

import java.util.Arrays;
import java.util.List;

public class TestController extends BaseController {

    public UserDataResponse createUser(UserRegistrationReq user) {
        return post(user, "/api/automationTask/create", 201).as(UserDataResponse.class);
    }

    public void deleteUser(String id) {
        delete("/api/automationTask/deleteOne/" + id, 200);
    }

    public List<UserDataResponse> getAllUsers() {
        return Arrays.stream(get("/api/automationTask/getAll", 200).as(UserDataResponse[].class)).toList();
    }

    public UserDataResponse getUser(String email) {
        return post(new GetOnePlayer().setEmail(email), "/api/automationTask/getOne", 201).as(UserDataResponse.class);
    }
}
