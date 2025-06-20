package com.project.capstone.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.project.capstone.constant.Constant.ACCESS_TOKEN_TIME;
import static com.project.capstone.constant.Constant.REFRESH_TOKEN_TIME;

@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +  ACCESS_TOKEN_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String userId) {
       return Jwts.builder()
               .setSubject(userId)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
               .signWith(secretKey, SignatureAlgorithm.HS256)
               .compact();
    }

    //userId를 추출
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (MalformedJwtException | SecurityException e) {
            System.out.println("잘못된 토큰 서명입니다.");
        } catch(ExpiredJwtException e) {
            System.out.println("기간이 만료된 토큰입니다.");
        } catch(UnsupportedJwtException e) {
            System.out.println("지원하지 않는 토큰입니다.");
        } catch(IllegalArgumentException e) {
            System.out.println("잘못된 토큰입니다.");
        }

        return false;
    }
}
