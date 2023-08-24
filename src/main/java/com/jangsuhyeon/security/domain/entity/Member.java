package com.jangsuhyeon.security.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String account; // 아이디

    private String password;

    private String nickname;

    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // 연관 엔티티를 즉시 로딩, 모든 상태 변화(예: 저장, 업데이트, 삭제 등)를 연관된 엔티티에도 적용
    @Builder.Default // 빌더를 통해 초기값이 지정된 상태로 시작
    private List<Authority> roles = new ArrayList<>();

    @Column(unique = true)
    private String email;

}
