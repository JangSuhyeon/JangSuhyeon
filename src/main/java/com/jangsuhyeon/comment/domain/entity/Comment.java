package com.jangsuhyeon.comment.domain.entity;

import com.jangsuhyeon.comment.domain.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtId;

    @Column
    private String cmtName;

    @Column
    private String cmtContent;

    @Column
    private String cmtGb;

    @Column
    private LocalDateTime regDt;

    @PrePersist
    public void setRegDt() {
        this.regDt = LocalDateTime.now();
    }

    public static Comment toEntity(CommentRequestDto dto) {
        return Comment.builder()
                .cmtName(dto.getCmtFrmName())
                .cmtContent(dto.getCmtFrmContent())
                .cmtGb(dto.getCmtFrmGb())
                .build();
    }
}


