package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRegistrationReq {
    @JsonProperty("currency_code")
    String currencyCode;
    String email;
    String name;
    @JsonProperty("password_change")
    String passwordChange;
    @JsonProperty("password_repeat")
    String passwordRepeat;
    String surname;
    String username;
}
