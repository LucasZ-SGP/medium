/* (C)2023 */
package io.github.LucasZSGP.medium.user;

import io.github.LucasZSGP.medium.common.exception.UserException;
import io.github.LucasZSGP.medium.common.utils.JwtUtils;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.openapitools.model.NewUser;
import org.openapitools.model.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(NewUser newUser) {
        Optional<User> existingUser =
                userRepository.findAllByEmail(newUser.getEmail()).stream().findFirst();
        if (existingUser.isPresent()) {
            throw new UserException("User already exists");
        }
        User user =
                User.builder()
                        .email(newUser.getEmail())
                        .token(JwtUtils.generateToken(newUser))
                        .username(newUser.getUsername())
                        .bio("bio")
                        .image("image")
                        .build();
        return userRepository.save(user);
    }
}
