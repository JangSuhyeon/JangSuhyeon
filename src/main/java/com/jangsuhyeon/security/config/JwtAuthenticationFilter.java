package com.jangsuhyeon.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// servlet에 도달하기 전에 filter에서 검증 진행
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 토큰 추출
        String token = jwtProvider.resolveToken(request);

        if (token != null && jwtProvider.validateToken(token)) { // 토큰 만료 확인
            token = token.split(" ")[1].trim();
            Authentication auth = jwtProvider.getAuthentication(token);

            // 추출한 인증 정보(auth)를 Spring Security의 SecurityContextHolder에 설정하여, 현재 사용자가 보호된 리소스에 접근할 수 있도록 인증된 상태로 만듬.
            // 이렇게 하면 이후에 보호된 리소스에 접근할 때 SecurityContextHolder에서 인증 정보를 확인하고, 접근 권한을 부여하거나 거부하는 데 사용됨.
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
