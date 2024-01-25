/* (C)2024 */
package io.github.LucasZSGP.medium.web;

import io.github.LucasZSGP.medium.application.ArticleService;
import io.github.LucasZSGP.medium.domain.article.CommentEntity;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.openapitools.api.ArticlesApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@AllArgsConstructor
public class ArticleController implements ArticlesApi {
    private final ArticleService articleService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ArticlesApi.super.getRequest();
    }

    @Override
    public ResponseEntity<CreateArticle201Response> createArticle(CreateArticleRequest article) {
        CreateArticle201Response createArticle201Response =
                new CreateArticle201Response(articleService.createArticle(article.getArticle()));
        return ResponseEntity.status(201).body(createArticle201Response);
    }

    @Override
    public ResponseEntity<CreateArticleComment200Response> createArticleComment(
            String slug, CreateArticleCommentRequest comment) {
        CommentEntity commentEntity =
                articleService.createArticleComment(slug, comment.getComment());
        return ResponseEntity.status(201)
                .body(new CreateArticleComment200Response(commentEntity.toComment()));
    }

    @Override
    public ResponseEntity<CreateArticle201Response> createArticleFavorite(String slug) {
        Article article = articleService.createArticleFavorite(slug);
        return ResponseEntity.status(200).body(new CreateArticle201Response(article));
    }

    @Override
    public ResponseEntity<CreateArticle201Response> deleteArticleFavorite(String slug) {
        Article article = articleService.deleteArticleFavorite(slug);
        return ResponseEntity.status(200).body(new CreateArticle201Response(article));
    }

    @Override
    public ResponseEntity<Void> deleteArticle(String slug) {
        articleService.deleteArticle(slug);
        return ResponseEntity.status(200).build();
    }

    @Override
    public ResponseEntity<Void> deleteArticleComment(String slug, Integer id) {
        CommentEntity deletedArticleComment = articleService.deleteArticleComment(slug, id);
        return ResponseEntity.status(200).build();
    }

    @Override
    public ResponseEntity<CreateArticle201Response> getArticle(String slug) {
        return ArticlesApi.super.getArticle(slug);
    }

    @Override
    public ResponseEntity<GetArticleComments200Response> getArticleComments(String slug) {
        return ArticlesApi.super.getArticleComments(slug);
    }

    @Override
    public ResponseEntity<GetArticlesFeed200Response> getArticles(
            String tag, String author, String favorited, Integer offset, Integer limit) {
        return ArticlesApi.super.getArticles(tag, author, favorited, offset, limit);
    }

    @Override
    public ResponseEntity<GetArticlesFeed200Response> getArticlesFeed(
            Integer offset, Integer limit) {
        return ArticlesApi.super.getArticlesFeed(offset, limit);
    }

    @Override
    public ResponseEntity<CreateArticle201Response> updateArticle(
            String slug, UpdateArticleRequest article) {
        CreateArticle201Response createArticle201Response =
                new CreateArticle201Response(
                        articleService.updateArticle(slug, article.getArticle()).toArticle());
        return ResponseEntity.status(201).body(createArticle201Response);
    }
}
