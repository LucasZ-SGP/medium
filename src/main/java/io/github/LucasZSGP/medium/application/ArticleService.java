/* (C)2024 */
package io.github.LucasZSGP.medium.application;

import io.github.LucasZSGP.medium.domain.article.*;
import io.github.LucasZSGP.medium.domain.user.UserEntity;
import io.github.LucasZSGP.medium.domain.user.UserRepository;
import io.github.LucasZSGP.medium.infra.exception.UserException;
import io.github.LucasZSGP.medium.infra.utils.SlugUtils;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.openapitools.model.Article;
import org.openapitools.model.NewArticle;
import org.openapitools.model.NewComment;
import org.openapitools.model.UpdateArticle;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleService {
    private final UserService userService;
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public Article createArticle(NewArticle newArticle) {
        // todo: input validation
        UserEntity currentUserEntity = userService.getCurrentUserEntity().orElseThrow();
        Set<TagEntity> tagEntitySet = new HashSet<>();
        for (String tag : newArticle.getTagList()) {
            Optional<TagEntity> existingTagEntity = tagRepository.findByTag(tag);
            TagEntity tagEntity = existingTagEntity.orElse(new TagEntity(tag));
            tagEntitySet.add(tagEntity);
        }

        ArticleEntity articleEntity =
                ArticleEntity.builder()
                        .title(newArticle.getTitle())
                        .body(newArticle.getBody())
                        .description(newArticle.getDescription())
                        .slug(SlugUtils.generateSlug(newArticle.getTitle()))
                        .author(currentUserEntity)
                        .tagList(tagEntitySet)
                        .build();
        articleRepository.save(articleEntity);
        return articleEntity.toArticle();
    }

    public void deleteArticle(String slug) {
        UserEntity currentUserEntity = userService.getCurrentUserEntity().orElseThrow();
        ArticleEntity articleEntity = getArticle(slug);

        if (articleEntity != null
                && currentUserEntity.getId() == articleEntity.getAuthor().getId()) {
            articleRepository.delete(articleEntity);
        } else {
            throw new UserException("Not permitted");
        }
    }

    public ArticleEntity getArticle(String slug) {
        ArticleEntity articleEntity = articleRepository.findBySlug(slug).orElseThrow();
        return articleEntity;
    }

    public ArticleEntity updateArticle(String slug, UpdateArticle updateArticle) {
        ArticleEntity articleEntity = getArticle(slug);
        articleEntity.setBody(updateArticle.getBody());
        articleEntity.setDescription(updateArticle.getDescription());
        articleEntity.setTitle(updateArticle.getTitle());
        return articleRepository.save(articleEntity);
    }

    public CommentEntity createArticleComment(String slug, NewComment newComment) {
        ArticleEntity articleEntity = getArticle(slug);
        UserEntity currentUserEntity = userService.getCurrentUserEntity().orElseThrow();
        CommentEntity commentEntity =
                CommentEntity.builder()
                        .body(newComment.getBody())
                        .user(currentUserEntity)
                        .articleEntity(articleEntity)
                        .build();
        articleEntity.getComments().add(commentEntity);
        articleRepository.save(articleEntity);
        return commentEntity;
    }

    public CommentEntity deleteArticleComment(String slug, Integer id) {
        ArticleEntity articleEntity = getArticle(slug);
        UserEntity currentUserEntity = userService.getCurrentUserEntity().orElseThrow();
        if (currentUserEntity.getId() != articleEntity.getAuthor().getId()) {
            throw new UserException("You are not permitted to delete other's comment");
        }
        Optional<CommentEntity> commentEntity =
                articleEntity.getComments().stream()
                        .filter(entity -> entity.getId() == id)
                        .findFirst();
        if (commentEntity.isPresent()) {
            articleEntity.getComments().remove(commentEntity);
            return commentEntity.get();
        }
        throw new UserException("Comment does not exist");
    }

    public Article createArticleFavorite(String slug) {
        UserEntity currentUserEntity = userService.getCurrentUserEntity().orElseThrow();
        ArticleEntity articleEntity = articleRepository.findBySlug(slug).orElseThrow();

        Set<ArticleEntity> favoritedArticles = currentUserEntity.getFavorites();
        // Override equal method, set remain unchanged if two articles are the same.
        favoritedArticles.add(articleEntity);
        userRepository.save(currentUserEntity);

        Article article = articleEntity.toArticle();
        article.favorited(true);
        return articleEntity.toArticle();
    }

    public Article deleteArticleFavorite(String slug) {
        UserEntity currentUserEntity = userService.getCurrentUserEntity().orElseThrow();
        ArticleEntity articleEntity = articleRepository.findBySlug(slug).orElseThrow();

        Set<ArticleEntity> favoritedArticles = currentUserEntity.getFavorites();
        // Override equal method, set remain unchanged if two articles are the same.
        favoritedArticles.remove(articleEntity);
        userRepository.save(currentUserEntity);

        Article article = articleEntity.toArticle();
        article.favorited(false);
        return articleEntity.toArticle();
    }
}
