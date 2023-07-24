package api;

import api.actions.Auth;
import api.actions.TestController;
import api.pojo.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SlotegratorTest {
    private final static String email = "novikov.av.4949@gmail.com";
    private final static String pass = "5E9pioqZdYpF";

    @BeforeClass
    public static void preconditions() {
        Auth.authorize(email, pass);
    }

    @Test
    public void verifyUserRegistration() {
        String name = "Andrey";
        String surname = "Novikov";
        String username = "andreyQA";
        String currency = "dollar";
        String password = "5E9pioqZdYpF";
        String emailTemplate = "novikov.av.4949+%s@gmail.com";

        TestController testController = new TestController();

        // Create 12 players
        for(int i = 1; i <= 12; i++) {
            String email = String.format(emailTemplate, i);
            UserDataResponse registration = testController.createUser(createUserObject(email, name, surname, username, currency, password));
            assertThat(registration).isNotNull();
        }

        // Get info about a certain player
        String emailToGet = String.format(emailTemplate, 1);
        UserDataResponse getOne = testController.getUser(emailToGet);
        SoftAssertions.assertSoftly(a -> {
            a.assertThat(getOne.getId()).isNotNull();
            a.assertThat(getOne.getName()).isEqualTo(name);
            a.assertThat(getOne.getSurname()).isEqualTo(surname);
            a.assertThat(getOne.getUsername()).isEqualTo(username);
            a.assertThat(getOne.getEmail()).isEqualTo(emailToGet);
        });

        // Get info about all players and sort them by name
        List<UserDataResponse> getAll = testController.getAllUsers();
        List<String> sortedGetAll = getAll.stream().map(UserDataResponse::getName).sorted().collect(Collectors.toList());
        System.out.println(sortedGetAll);

        // Delete all users and verify that they are deleted (no users in the system)
        List<String> allId = getAll.stream().map(UserDataResponse::getId).collect(Collectors.toList());
        for (String id : allId) {
            testController.deleteUser(id);
        }
        assertThat(testController.getAllUsers()).isEmpty();
    }

    private UserRegistrationReq createUserObject(String email, String name, String surname,
                                                 String username, String currency, String password) {
        return new UserRegistrationReq()
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setUsername(username)
                .setPasswordChange(password)
                .setPasswordRepeat(password)
                .setCurrencyCode(currency);
    }
}
