/* (C)2024 */
package io.github.LucasZSGP.medium.user;

import io.github.LucasZSGP.medium.common.utils.JwtUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openapitools.model.User;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String username;
    private String password;
    private String bio;
    private String image;

    public User toUserWithNewToken() {
        return User.builder()
                .email(this.getEmail())
                .username(this.getUsername())
                .token(JwtUtils.generateToken(this.getEmail()))
                .bio(this.getBio())
                .image(this.getImage())
                .build();
    }
}
