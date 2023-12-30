package io.github.LucasZSGP.medium.user;

import lombok.AllArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.api.UsersApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
@AllArgsConstructor
@RestController
public class UserController implements UsersApi, UserApi {
    private final UserService userService;
    @Override
    public ResponseEntity<Login200Response> getCurrentUser() {
        return UserApi.super.getCurrentUser();
    }

    @Override
    public ResponseEntity<Login200Response> updateCurrentUser(UpdateCurrentUserRequest body) {
        return UserApi.super.updateCurrentUser(body);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Login200Response> createUser(CreateUserRequest request) {
        User user = userService.createUser(request.getUser());
        Login200Response response = new Login200Response(user);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<Login200Response> login(LoginRequest body) {
        return UsersApi.super.login(body);
    }
}
