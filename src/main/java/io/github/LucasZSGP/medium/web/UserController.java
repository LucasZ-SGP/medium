/* (C)2024 */
package io.github.LucasZSGP.medium.web;

import java.util.Optional;

import io.github.LucasZSGP.medium.application.UserService;
import lombok.AllArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.api.UsersApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@AllArgsConstructor
@RestController
public class UserController implements UsersApi, UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<Login200Response> getCurrentUser() {
        User user = userService.getCurrentUserEntity().toUserWithNewToken();
        Login200Response response = new Login200Response(user);
        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<Login200Response> updateCurrentUser(UpdateCurrentUserRequest request) {
        User user = userService.updateCurrentUser(request);
        Login200Response response = new Login200Response(user);
        return ResponseEntity.status(200).body(response);
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
        Login200Response response = new Login200Response(userService.login(body.getUser()));
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("hello world");
    }

    @GetMapping(value = "/test2")
    public ResponseEntity<String> test2() {
        return ResponseEntity.ok("hello world2");
    }

    @GetMapping(value = "/test3")
    public ResponseEntity<String> test3() {
        return ResponseEntity.ok("hello world3");
    }
}
