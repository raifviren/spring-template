package com.example.demo.commons.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.commons.enums.JwtAlgorithm;
import org.apache.commons.collections4.MapUtils;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;


/**
 * @author Virender Bhargav
 */
public class JwtUtil {

    /**
     * Create a JWT token
     *
     * @param claims
     * @param base64key
     * @param jwtAlgorithm
     * @return
     */
    public static String createJwtToken(Map<String, ?> claims, String base64key, JwtAlgorithm jwtAlgorithm) {
        byte[] key = Base64.getDecoder().decode(base64key);
        return createJwtToken(claims, key, jwtAlgorithm);
    }

    /**
     * Create a JWT token
     *
     * @param claims
     * @param secretKey
     * @return
     */
    public static String createJwtToken(Map<String, ?> claims, byte[] key, JwtAlgorithm jwtAlgorithm) {
        Algorithm algorithm = null;
        switch (jwtAlgorithm) {
            case HS256:
                algorithm = Algorithm.HMAC256(key);
                break;
            case HS512:
                algorithm = Algorithm.HMAC512(key);
                break;
        }

        Builder jwtBuilder = JWT.create();

        setClaimsFromMap(jwtBuilder, claims);

        return jwtBuilder.sign(algorithm);
    }

    private static void setClaimsFromMap(Builder builder, Map<String, ?> claims) {
        if (MapUtils.isNotEmpty(claims)) {
            claims.forEach((k, v) -> {
                if (v instanceof Long) {
                    builder.withClaim(k, (Long) v);
                } else if (v instanceof Boolean) {
                    builder.withClaim(k, (Boolean) v);
                } else if (v instanceof Date) {
                    builder.withClaim(k, (Date) v);
                } else if (v instanceof Double) {
                    builder.withClaim(k, (Double) v);
                } else if (v instanceof Integer) {
                    builder.withClaim(k, (Integer) v);
                } else {
                    builder.withClaim(k, Objects.toString(v, null));
                }
            });
        }
    }
}
