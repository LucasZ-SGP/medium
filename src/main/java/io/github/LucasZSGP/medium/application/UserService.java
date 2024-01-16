/* (C)2023-2024 */
package io.github.LucasZSGP.medium.application;

import io.github.LucasZSGP.medium.domain.user.UserEntity;
import io.github.LucasZSGP.medium.domain.user.UserRepository;
import io.github.LucasZSGP.medium.infra.exception.UserException;
import lombok.AllArgsConstructor;
import org.openapitools.model.LoginUser;
import org.openapitools.model.NewUser;
import org.openapitools.model.UpdateCurrentUserRequest;
import org.openapitools.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(NewUser newUser) {
        // todo: input validation
        UserEntity existingUserEntity = userRepository.findByEmail(newUser.getEmail());
        if (existingUserEntity != null) {
            throw new UserException("User already exists");
        }
        UserEntity userEntity =
                UserEntity.builder()
                        .email(newUser.getEmail())
                        .password(newUser.getPassword())
                        .username(newUser.getUsername())
                        .bio("bio")
                        .image("image")
                        .build();
        userRepository.save(userEntity);
        return userEntity.toUserWithNewToken();
    }

    public User login(LoginUser loginUser) {
        // todo: input validation
        UserEntity userEntity = userRepository.findByEmail(loginUser.getEmail());
        if (userEntity != null && userEntity.getPassword().equals(loginUser.getPassword())) {
            return userEntity.toUserWithNewToken();
        }
        throw new UserException("Invalid Credentials");
    }

    public UserEntity getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        String email = (String) authentication.getPrincipal();
        return userRepository.findByEmail(email);
    }

    public User updateCurrentUser(UpdateCurrentUserRequest request) {
        // todo: input validation
        UserEntity currentUserEntity = getCurrentUserEntity();
        UserEntity newUserEntity =
                UserEntity.builder()
                        .id(currentUserEntity.getId())
                        .bio(
                                request.getUser().getBio() == null
                                        ? currentUserEntity.getBio()
                                        : request.getUser().getBio())
                        .password(
                                request.getUser().getPassword() == null
                                        ? currentUserEntity.getPassword()
                                        : request.getUser().getPassword())
                        .username(
                                request.getUser().getUsername() == null
                                        ? currentUserEntity.getUsername()
                                        : request.getUser().getUsername())
                        .image(
                                request.getUser().getImage() == null
                                        ? currentUserEntity.getImage()
                                        : request.getUser().getImage())
                        .build();
        userRepository.save(newUserEntity);
        return newUserEntity.toUserWithNewToken();
    }
}
