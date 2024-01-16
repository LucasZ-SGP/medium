/* (C)2024 */
package io.github.LucasZSGP.medium.infra.utils;

import java.util.UUID;
import org.springframework.util.StringUtils;

public class SlugUtils {
    public static String generateSlug(String input) {
        if (!StringUtils.hasText(input)) {
            return "";
        }
        String slug = input.toLowerCase().replaceAll("\\s+", "-");
        slug = slug.replaceAll("[^a-z0-9-]", "");

        if (!StringUtils.hasText(slug)) {
            slug = "article";
        }
        String uniqueIdentifier = generateUniqueIdentifier();
        slug = slug + "-" + uniqueIdentifier;
        return slug;
    }

    private static String generateUniqueIdentifier() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replaceAll("-", "").substring(0, 12);
        return uuidString;
    }
}
