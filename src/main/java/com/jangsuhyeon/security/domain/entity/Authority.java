package com.jangsuhyeon.security.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // Todo API 응답을 구성할 때 필요하지 않은 필드여서..
    private Long id;

    private String name;

    @JoinColumn(name = "member")
    @ManyToOne(fetch = FetchType.LAZY) // Todo 왜 일대다??, 엔티티가 실제로 사용되기 전까지 로딩되지 않음
    @JsonIgnore
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

}
