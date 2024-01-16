/* (C)2024 */
package io.github.LucasZSGP.medium.domain.article;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByTag(String tag);
}
