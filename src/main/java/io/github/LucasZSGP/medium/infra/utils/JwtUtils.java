/* (C)2023-2024 */
package io.github.LucasZSGP.medium.infra.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.LucasZSGP.medium.infra.exception.UserException;
import java.time.Instant;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET_KEY = "secert";
    private static final long EXPIRATION_SECONDS = 86400;

    public static String generateToken(String email) {

        return JWT.create()
                .withHeader(Map.of("alg", "HS512", "typ", "JWT"))
                .withClaim("email", email)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(EXPIRATION_SECONDS))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public static String validateToken(String token) {

        try {
            DecodedJWT decodedJWT =
                    JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
            return decodedJWT.getClaim("email").asString();
        } catch (JWTVerificationException ex) {
            // Handle JWT verification exception
            throw new UserException("Authentication Failed");
        }
    }
}
