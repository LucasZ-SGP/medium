///* (C)2024 */
//package io.github.LucasZSGP.medium.profile;
//
//import io.github.LucasZSGP.medium.common.exception.UserException;
//import io.github.LucasZSGP.medium.application.UserService;
//import io.github.LucasZSGP.medium.domain.user.UserEntity;
//import io.github.LucasZSGP.medium.user.repositories.UserRelationshipRepository;
//import io.github.LucasZSGP.medium.domain.user.UserRepository;
//import lombok.AllArgsConstructor;
//import org.openapitools.model.Profile;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ProfileService {
//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final UserRelationshipRepository userRelationshipRepository;
//
//    public Profile getProfileByUsername(String username) {
//        // todo: input validation
//        UserEntity userEntity = userRepository.findByUsername(username);
//        if (userEntity == null) {
//            throw new UserException("User does not exist");
//        }
//        Profile profile = userEntity.toProfile();
//
//        UserEntity currentUserEntity = userService.getCurrentUserEntity();
//        if (currentUserEntity != null
//                && (userRelationshipRepository.findAllByFollower(currentUserEntity.getId()).stream()
//                        .anyMatch(entity -> entity.getFollowee() == userEntity.getId()))) {
//            profile.setFollowing(Boolean.TRUE);
//        }
//        return profile;
//    }
//
//    public Profile followUserByUsername(String username) {
//        UserEntity userEntity = userRepository.findByUsername(username);
//        UserEntity currentUserEntity = userService.getCurrentUserEntity();
//        userRelationshipRepository.save(
//                UserRelationshipEntity.builder()
//                        .follower(currentUserEntity.getId())
//                        .followee(userEntity.getId())
//                        .build());
//        Profile profile = userEntity.toProfile();
//        profile.setFollowing(Boolean.TRUE);
//        return profile;
//    }
//
//    public Profile unfollowUserByUsername(String username) {
//        UserEntity userEntity = userRepository.findByUsername(username);
//        UserEntity currentUserEntity = userService.getCurrentUserEntity();
//        userRelationshipRepository.deleteByFollowerAndFollowee(
//                currentUserEntity.getId(), userEntity.getId());
//        Profile profile = userEntity.toProfile();
//        profile.setFollowing(Boolean.FALSE);
//        return profile;
//    }
//}
