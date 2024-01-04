/* (C)2024 */
package io.github.LucasZSGP.medium.user;

import java.util.Optional;
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
        //        Authentication token = new
        // UsernamePasswordAuthenticationToken(body.getUser().getEmail(),
        // body.getUser().getPassword());
        //        Authentication authentication = authenticationManager.authenticate(token);
        //        SecurityContextHolder.getContext().setAuthentication(authentication);

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
