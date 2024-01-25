/* (C)2024 */
package io.github.LucasZSGP.medium.domain.article;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity save(ArticleEntity articleEntity);

    Optional<ArticleEntity> findBySlug(String slug);
}
