//package io.github.LucasZSGP.medium.article;
//
//import io.github.LucasZSGP.medium.profile.ProfileService;
//import io.github.LucasZSGP.medium.application.UserService;
//import io.github.LucasZSGP.medium.domain.user.UserEntity;
//import lombok.AllArgsConstructor;
//import org.openapitools.model.CreateArticle201Response;
//import org.openapitools.model.NewArticle;
//import org.openapitools.model.Profile;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ArticleService {
//    private final UserService userService;
//    private final ProfileService profileService;
//
//    public CreateArticle201Response createArticle(NewArticle newArticle){
//        // todo: input validation
//        UserEntity currentUserEntity = userService.getCurrentUserEntity();
//        Profile profile = profileService.getProfileByUsername(currentUserEntity.getUsername());
//        return null;
//    }
//}
