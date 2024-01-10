/* (C)2024 */
package io.github.LucasZSGP.medium.domain.article;

import io.github.LucasZSGP.medium.domain.Auditable;
import io.github.LucasZSGP.medium.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleEntity extends Auditable {


    private String slug;
    private String title;
    private String description;
    private String body;

    @ManyToOne
    private UserEntity author;


    @ManyToMany
    private Set<TagEntity> tags;

}
