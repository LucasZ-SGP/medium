/* (C)2023 */
package io.github.LucasZSGP.medium;

import io.github.LucasZSGP.medium.common.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.openapitools.model.NewUser;

public class UserTest {
    @Test
    void create_user_success() {
        NewUser newUser = new NewUser("username", "email@mail.com", "password_123");
        System.out.println(
                JwtUtils.validateToken(
                        "eyJhbGciOiJIUzUxMiIsInR5cDIiOiJKV1QiLCJ0eXAiOiJKV1QifQ.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwiaWF0IjoxNzA0MDE2ODEwLCJleHAiOjE3MDQxMDMyMTB9.Yq85hlOOqYo02Xctu9O9iWbyt8DK8uRZSqzUrOSQAnr5cqvKlKmXisKtvtWW9xHRFQwdGmd4QCi252Crur-TVg"));
    }
}
