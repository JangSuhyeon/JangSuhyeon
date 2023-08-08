package com.jangsuhyeon.comment.domain.dto;

import com.jangsuhyeon.comment.domain.entity.Comment;
import com.jangsuhyeon.shop.domain.dto.BrandResponseDto;
import com.jangsuhyeon.shop.domain.entity.Brand;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    public static List<CommentResponseDto> toDtoList(List<Comment> commentList) {

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = toDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }

}
