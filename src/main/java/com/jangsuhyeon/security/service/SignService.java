package com.jangsuhyeon.security.service;

import com.jangsuhyeon.security.config.JwtProvider;
import com.jangsuhyeon.security.domain.dto.SignRequest;
import com.jangsuhyeon.security.domain.dto.SignResponse;
import com.jangsuhyeon.security.domain.entity.Authority;
import com.jangsuhyeon.security.domain.entity.Member;
import com.jangsuhyeon.security.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 호그인
    public SignResponse login(SignRequest request) {

        // 요청 정보로 회원이 조회되지 않을 경우 예외 처리
        Member member = memberRepository.findByAccount(request.getAccount()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        // 비밀번호가 일치하지 않을 경우 예외 처리
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return SignResponse.builder()
                .id(member.getId())
                .account(member.getAccount())
                .name(member.getName())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getAccount(), member.getRoles())) // 토큰생성
                .build();
    }

    // 회원가입
    public boolean register(SignRequest request) throws Exception {
        try {
            Member member = Member.builder()
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .build();

            // 하나의 요소를 가진 불변 리스트를 생성
            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            memberRepository.save(member);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }

        return true;
    }

    // account로 회원 조회
    public SignResponse getMember(String account) throws Exception {
        Member member = memberRepository.findByAccount(account).orElseThrow(() -> (
            new Exception("계정을 찾을 수 없습니다."))
        );
        return new SignResponse(member);
    }
}
