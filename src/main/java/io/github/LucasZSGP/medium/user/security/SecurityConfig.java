/* (C)2024 */
package io.github.LucasZSGP.medium.user.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests(
                (requests) ->
                        requests.requestMatchers("/test", "/users", "/users/login")
                                .permitAll()
                                .requestMatchers("/test2")
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
