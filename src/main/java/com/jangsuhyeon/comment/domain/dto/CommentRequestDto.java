package com.jangsuhyeon.comment.domain.dto;

import com.jangsuhyeon.comment.domain.entity.Comment;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    private String cmtFrmName;
    private String cmtFrmContent;
    private String cmtFrmGb;

    public static CommentRequestDto toDto(Comment comment) {
        return CommentRequestDto.builder()
                .cmtFrmName(comment.getCmtName())
                .cmtFrmContent(comment.getCmtContent())
                .cmtFrmGb(comment.getCmtGb())
                .build();
    }

}
