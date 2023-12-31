/* (C)2023 */
package io.github.LucasZSGP.medium.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.util.Map;
import org.openapitools.model.NewUser;

public class JwtUtils {

    private static final String SECRET_KEY = "secert";
    private static final long EXPIRATION_SECONDS = 86400;

    public static String generateToken(NewUser newUser) {

        return JWT.create()
                .withHeader(Map.of("alg", "HS512", "typ", "JWT"))
                .withClaim("email", newUser.getEmail())
                .withClaim("password", newUser.getPassword())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(EXPIRATION_SECONDS))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public static boolean validateToken(String token) {

        try {
            JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            // Handle JWT verification exception
            return false;
        }
    }
}
