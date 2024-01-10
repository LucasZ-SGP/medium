package io.github.LucasZSGP.medium.web;

import org.openapitools.api.ArticlesApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
public class ArticleController implements ArticlesApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ArticlesApi.super.getRequest();
    }


    @Override
    public ResponseEntity<CreateArticle201Response> createArticle(CreateArticleRequest article) {
        return ArticlesApi.super.createArticle(article);
    }

    @Override
    public ResponseEntity<CreateArticleComment200Response> createArticleComment(String slug, CreateArticleCommentRequest comment) {
        return ArticlesApi.super.createArticleComment(slug, comment);
    }

    @Override
    public ResponseEntity<CreateArticle201Response> createArticleFavorite(String slug) {
        return ArticlesApi.super.createArticleFavorite(slug);
    }

    @Override
    public ResponseEntity<Void> deleteArticle(String slug) {
        return ArticlesApi.super.deleteArticle(slug);
    }

    @Override
    public ResponseEntity<Void> deleteArticleComment(String slug, Integer id) {
        return ArticlesApi.super.deleteArticleComment(slug, id);
    }

    @Override
    public ResponseEntity<CreateArticle201Response> deleteArticleFavorite(String slug) {
        return ArticlesApi.super.deleteArticleFavorite(slug);
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
    public ResponseEntity<GetArticlesFeed200Response> getArticles(String tag, String author, String favorited, Integer offset, Integer limit) {
        return ArticlesApi.super.getArticles(tag, author, favorited, offset, limit);
    }

    @Override
    public ResponseEntity<GetArticlesFeed200Response> getArticlesFeed(Integer offset, Integer limit) {
        return ArticlesApi.super.getArticlesFeed(offset, limit);
    }

    @Override
    public ResponseEntity<CreateArticle201Response> updateArticle(String slug, UpdateArticleRequest article) {
        return ArticlesApi.super.updateArticle(slug, article);
    }
}
