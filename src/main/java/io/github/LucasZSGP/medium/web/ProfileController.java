///* (C)2024 */
//package io.github.LucasZSGP.medium.profile;
//
//import java.util.Optional;
//import lombok.AllArgsConstructor;
//import org.openapitools.api.ProfilesApi;
//import org.openapitools.model.GetProfileByUsername200Response;
//import org.openapitools.model.Profile;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.NativeWebRequest;
//
//@RestController
//@AllArgsConstructor
//public class ProfileController implements ProfilesApi {
//    private final ProfileService profileService;
//
//    @Override
//    public Optional<NativeWebRequest> getRequest() {
//        return ProfilesApi.super.getRequest();
//    }
//
//    @Override
//    public ResponseEntity<GetProfileByUsername200Response> followUserByUsername(String username) {
//        return buildResponse(profileService.followUserByUsername(username));
//    }
//
//    @Override
//    public ResponseEntity<GetProfileByUsername200Response> unfollowUserByUsername(String username) {
//        return buildResponse(profileService.unfollowUserByUsername(username));
//    }
//
//    @Override
//    public ResponseEntity<GetProfileByUsername200Response> getProfileByUsername(String username) {
//        return buildResponse(profileService.getProfileByUsername(username));
//    }
//
//    private ResponseEntity<GetProfileByUsername200Response> buildResponse(Profile profile){
//        GetProfileByUsername200Response response = new GetProfileByUsername200Response(profile);
//        return ResponseEntity.status(200).body(response);
//    }
//
//
//}
