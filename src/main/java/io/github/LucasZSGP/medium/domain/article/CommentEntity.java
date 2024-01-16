/* (C)2024 */
package io.github.LucasZSGP.medium.domain.article;

import io.github.LucasZSGP.medium.domain.Auditable;
import io.github.LucasZSGP.medium.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.openapitools.model.Comment;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentEntity extends Auditable {
    private String body;

    @ManyToOne private UserEntity user;

    @ManyToOne private ArticleEntity articleEntity;

    public Comment toComment() {
        return Comment.builder().body(this.body).author(this.user.toProfile()).build();
    }
}
