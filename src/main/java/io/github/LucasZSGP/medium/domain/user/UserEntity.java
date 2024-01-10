/* (C)2024 */
package io.github.LucasZSGP.medium.domain.user;

import io.github.LucasZSGP.medium.domain.article.ArticleEntity;
import io.github.LucasZSGP.medium.infra.utils.JwtUtils;
import jakarta.persistence.*;
import lombok.*;
import org.openapitools.model.Profile;
import org.openapitools.model.User;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;
    private String bio;
    private String image;

    @OneToMany
    private Set<ArticleEntity> authored;

    @ManyToMany
    private Set<ArticleEntity> favorites;

    @ManyToMany
    private Set<UserEntity> follows;

    public User toUserWithNewToken() {
        return User.builder()
                .email(this.getEmail())
                .username(this.getUsername())
                .token(JwtUtils.generateToken(this.getEmail()))
                .bio(this.getBio())
                .image(this.getImage())
                .build();
    }

    public Profile toProfile() {
        return Profile.builder()
                .username(this.getUsername())
                .bio(this.getBio())
                .image(this.getImage())
                .build();
    }
}
