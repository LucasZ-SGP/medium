/* (C)2024 */
package io.github.LucasZSGP.medium.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate private OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate private OffsetDateTime updatedAt = OffsetDateTime.now();
    ;
}
