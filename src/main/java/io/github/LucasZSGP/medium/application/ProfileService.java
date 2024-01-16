/* (C)2024 */
package io.github.LucasZSGP.medium.application; /* (C)2024 */

import io.github.LucasZSGP.medium.domain.user.UserEntity;
import io.github.LucasZSGP.medium.domain.user.UserRepository;
import io.github.LucasZSGP.medium.infra.exception.UserException;
import lombok.AllArgsConstructor;
import org.openapitools.model.Profile;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserService userService;
    private final UserRepository userRepository;

    public Profile getProfileByUsername(String username) {
        // todo: input validation
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UserException("User does not exist");
        }
        Profile profile = userEntity.toProfile();

        UserEntity currentUserEntity = userService.getCurrentUserEntity();
        if (currentUserEntity != null) {
            if (currentUserEntity.getFollows().stream()
                    .anyMatch(userEntity1 -> userEntity1.getUsername().equals(username))) {
                profile.setFollowing(true);
            } else {
                profile.setFollowing(false);
            }
        }

        return profile;
    }

    public Profile followUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        UserEntity currentUserEntity = userService.getCurrentUserEntity();
        // will not throw error if already followed
        currentUserEntity.getFollows().add(userEntity);
        userRepository.save(currentUserEntity);

        Profile profile = userEntity.toProfile();
        profile.setFollowing(Boolean.TRUE);
        return profile;
    }

    public Profile unfollowUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        UserEntity currentUserEntity = userService.getCurrentUserEntity();

        // will not throw error if already followed
        currentUserEntity.getFollows().remove(userEntity);
        userRepository.save(currentUserEntity);

        Profile profile = userEntity.toProfile();
        profile.setFollowing(Boolean.FALSE);
        return profile;
    }
}
