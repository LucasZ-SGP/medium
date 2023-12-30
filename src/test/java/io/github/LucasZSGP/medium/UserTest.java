package io.github.LucasZSGP.medium;

import org.junit.jupiter.api.Test;
import org.openapitools.model.NewUser;

public class UserTest {
    @Test
    void create_user_success(){
        NewUser newUser = new NewUser("username", "email@mail.com","password_123");

    }
}
