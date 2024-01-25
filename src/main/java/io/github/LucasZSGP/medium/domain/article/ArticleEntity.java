/* (C)2024 */
package io.github.LucasZSGP.medium.domain.article;

import io.github.LucasZSGP.medium.domain.Auditable;
import io.github.LucasZSGP.medium.domain.user.UserEntity;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.*;
import org.openapitools.model.Article;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "article_entity")
public class ArticleEntity extends Auditable {

    private String slug;
    private String title;
    private String description;
    private String body;
    private int favoritesCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "article_tag_entity",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<TagEntity> tagList;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<CommentEntity> comments;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserEntity> favoritedBy;

    public Article toArticle() {
        return Article.builder()
                .slug(this.slug)
                .title(this.title)
                .description(this.description)
                .body(this.body)
                .favoritesCount(this.favoritesCount)
                .tagList(this.tagList.stream().map(TagEntity::getTag).toList())
                .author(author.toProfile())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleEntity that)) return false;
        return Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }
}
