/* (C)2023-2024 */
package io.github.LucasZSGP.medium.user;

import io.github.LucasZSGP.medium.common.exception.UserException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.openapitools.model.LoginUser;
import org.openapitools.model.NewUser;
import org.openapitools.model.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(NewUser newUser) {
        // todo: input validation
        Optional<UserEntity> existingUserEntity =
                userRepository.findByEmail(newUser.getEmail()).stream().findFirst();
        if (existingUserEntity.isPresent()) {
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
        List<UserEntity> userEntity = userRepository.findByEmail(loginUser.getEmail());
        if (!userEntity.isEmpty()
                && userEntity.get(0).getPassword().equals(loginUser.getPassword())) {
            return userEntity.get(0).toUserWithNewToken();
        }
        throw new UserException("Invalid Credentials");
    }

    //    @Override
    //    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //        List<UserEntity> userEntityList = userRepository.findByEmail(email);
    //        if (userEntityList.isEmpty()) {
    //            throw new UsernameNotFoundException(
    //                    "User details not found for user with email: " + email);
    //        }
    //        return new org.springframework.security.core.userdetails.User(
    //                userEntityList.get(0).getEmail(),
    //                userEntityList.get(0).getPassword(),
    //                List.of(new SimpleGrantedAuthority("User")));
    //    }
}
