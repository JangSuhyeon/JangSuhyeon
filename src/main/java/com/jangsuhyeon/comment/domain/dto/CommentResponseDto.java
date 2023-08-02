package com.jangsuhyeon.comment.domain.dto;

import com.jangsuhyeon.comment.domain.entity.Comment;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private String cmtName;
    private String cmtContent;
    private String cmtGb;

    public static CommentResponseDto toDto(Comment comment) {
        return CommentResponseDto.builder()
                .cmtName(comment.getCmtName())
                .cmtContent(comment.getCmtContent())
                .cmtGb(comment.getCmtGb())
                .build();
    }

}
