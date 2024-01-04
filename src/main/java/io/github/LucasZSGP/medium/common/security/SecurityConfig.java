/* (C)2024 */
package io.github.LucasZSGP.medium.common.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests(
                (authz) ->
                        authz.requestMatchers(
                                        "/test",
                                        "/users",
                                        "/users/login",
                                        "/tags",
                                        "/profiles/{username}")
                                .permitAll()
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/articles/{slug}/comments",
                                        "/articles/{slug}",
                                        "/articles")
                                .permitAll()
                                .requestMatchers(
                                        "/test2",
                                        "/user",
                                        "/articles/{slug}/comments/{id}",
                                        "/profiles/{username}/follow",
                                        "/articles/feed",
                                        "/articles/{slug}/favorite")
                                .authenticated()
                                .requestMatchers(
                                        HttpMethod.POST, "/articles", "/articles/{slug}/comments")
                                .authenticated()
                                .requestMatchers(HttpMethod.PUT, "/articles/{slug}")
                                .authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/articles/{slug}")
                                .authenticated());
        http.addFilterBefore(
                new JWTTokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //    @Bean
    //    public InMemoryUserDetailsManager userDetailsManager() {
    //        UserDetails admin =
    //                User.withUsername("admin").password("12345").authorities("admin").build();
    //        return new InMemoryUserDetailsManager(admin);
    //    }

    //    @Bean
    //    public PasswordEncoder passwordEncoder() {
    //        return NoOpPasswordEncoder.getInstance();
    //    }
}
