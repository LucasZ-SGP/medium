package io.github.LucasZSGP.medium.infra.utils;

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
        return slug;
    }
}
