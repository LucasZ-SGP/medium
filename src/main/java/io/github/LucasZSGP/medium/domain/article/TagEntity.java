/* (C)2024 */
package io.github.LucasZSGP.medium.domain.article;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String tag;

    public TagEntity(String tag) {
        this.tag = tag;
    }
}
