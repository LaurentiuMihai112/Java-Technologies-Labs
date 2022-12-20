package com.javatech.lab8.tokens;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenHandler {

    private static final String ISSUER = "DocumentsApp";
    private static final Long EXP_TIME = (long) (7200 * (10 ^ 4));
    private static final String SECRET_KEY = "MTA1NDQ2NDYyMjkxODQ3NjI0NjM4NjUxNTYxZGZnMTU2MTQ4ZGY5NDE4MTk0OTg=";


    public static String issue(Long id) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> accountData = new HashMap<>();
        accountData.put("id", id);

        Map<String, Object> claims = new HashMap<>(accountData);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, signingKey);

        if (EXP_TIME > 0) {
            long expMillis = nowMillis + EXP_TIME;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static Long validate(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.get("id", Long.class);
    }
}
