package com.example.baitest2.service.impl;


import com.example.baitest2.component.DateUtils;
import com.example.baitest2.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expires-in}")
    private Long expiresIn;
    @Value("${jwt.refresh-expires-in}")
    private Long refreshExpiresIn;

    @Override
    public String generateToken(String subject) {
        return generateToken(subject, new LinkedHashMap<>());
    }

    @Override
    public String generateToken(String subject, Map<String, Object> extraClaims) {
        return buildToken(subject, extraClaims, expiresIn);
    }

    @Override
    public String generateRefreshToken(String subject) {
        return buildToken(subject, new HashMap<>(), refreshExpiresIn);
    }

    @Override
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

    private String buildToken(String subject, Map<String, Object> extraClaims, long expiresIn) {
        return Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(subject)
                .claims(extraClaims)
                .issuedAt(DateUtils.getCurrentDateTime())
                .expiration(Date.from(Instant.now().plusMillis(expiresIn)))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        var keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
