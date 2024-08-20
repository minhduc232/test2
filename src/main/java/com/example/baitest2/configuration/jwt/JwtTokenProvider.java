package com.example.baitest2.configuration.jwt;


import com.example.baitest2.configuration.CustomUserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;

@Component
/*Đây là class giúp extract thông tin từ jwt hoặc generate thông tin ra jwt*/
public class JwtTokenProvider {
    private String JWT_SECRET = "dunglamsamsungdunghaydilungtungdunglamsamsungdunghaydilungtungdunglamsamsungdunghaydilungtung";
    private final Long JWT_EXPIRATION = 604800000L;
    public String generateToken(CustomUserDetails userDetails)
    {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ JWT_EXPIRATION);
        //Tao json web token
        return Jwts.builder()
                .setSubject(userDetails.getUser().getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateToken(String authToken)
    {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public String getUserNameFromJWT(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); //return username
    }
}
