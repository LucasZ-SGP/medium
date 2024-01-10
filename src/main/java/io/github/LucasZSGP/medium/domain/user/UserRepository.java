/* (C)2024 */
package io.github.LucasZSGP.medium.domain.user;

import io.github.LucasZSGP.medium.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
