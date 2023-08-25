package com.jangsuhyeon.security.domain.dto;

import com.jangsuhyeon.security.domain.entity.Authority;
import com.jangsuhyeon.security.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignResponse {
    private Long id;
    private String account;
    private String nickname;
    private String name;
    private String email;
    private List<Authority> roles;
    private String token;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.account = member.getAccount();
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.email = member.getEmail();
        this.roles = member.getRoles();
    }
}
