/* (C)2024 */
package io.github.LucasZSGP.medium.domain.user;

import io.github.LucasZSGP.medium.domain.article.ArticleEntity;
import io.github.LucasZSGP.medium.infra.utils.JwtUtils;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;
import lombok.*;
import org.openapitools.model.Profile;
import org.openapitools.model.User;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_entity")
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

    @OneToMany(mappedBy = "author")
    private Set<ArticleEntity> articles;

    @ManyToMany
    @JoinTable(
            name = "user_article_favorite_entity",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private Set<ArticleEntity> favorites;

    @ManyToMany
    @JoinTable(
            name = "user_relationship_entity",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "followee_id")})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
