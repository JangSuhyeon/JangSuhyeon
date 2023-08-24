package com.jangsuhyeon.security.config;

import com.jangsuhyeon.security.domain.entity.Authority;
import com.jangsuhyeon.security.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String salt;

    private Key secretKey;

    private final long exp = 1000L * 60 * 60; // 만료시간

    private final CustomUserDetailsService userDetailsService;

    // jwt.secret.key -> HMAC 키
    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String createToken(String account, List<Authority> roles) {
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(account); // 토큰 내에 사용자의 정보를 저장하기 위함
        claims.put("roles", roles); // 권한

        return Jwts.builder()
                .setClaims(claims)  // 사용자정보
                .setIssuedAt(now)   // 토큰발급시간
                .setExpiration(new Date(now.getTime() + exp))   // 토큰만료시간
                .signWith(secretKey, SignatureAlgorithm.HS256)  // 토큰서명
                .compact();
    }

    // 권한정보 획득 (Spring Security 인증과정에서 권한확인을 위함)
    public Authentication getAuthentication(String token) {
        // account로 member 조회
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰 내에 account 획득
    public String getAccount(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody().getSubject();
    }

    // request header에서 token 추출
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰 검증 (만료여부)
    public boolean validateToken(String token) {
        try {
            // token이 Bearer 로 시작하는지 검증
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                // Bearer 뒤 부터 추출
                token = token.split(" ")[1].trim();
            }

            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); // 만료되었으면 false
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
