package com.example.baitest2.service;



import java.util.Map;
public interface JwtService {

    String generateToken(String subject);

    String generateToken(String subject, Map<String, Object> extraClaims);

    String generateRefreshToken(String subject);

    String extractSubject(String token);


}
