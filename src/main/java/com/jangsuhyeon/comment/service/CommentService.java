package com.jangsuhyeon.comment.service;

import com.jangsuhyeon.comment.CommentRepository;
import com.jangsuhyeon.comment.domain.dto.CommentRequestDto;
import com.jangsuhyeon.comment.domain.dto.CommentResponseDto;
import com.jangsuhyeon.comment.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 저장
    public void save(CommentRequestDto commentRequestDto) {

        // DTO -> Entity
        Comment comment = Comment.toEntity(commentRequestDto);

        commentRepository.save(comment);
    }

    // 댓글 전체 조회
    public List<CommentResponseDto> findAll() {

        // 최신등록순
        Sort sort = Sort.by(Sort.Direction.DESC, "regDt");
        List<Comment> commentList = commentRepository.findAll(sort);

        // Entity -> DTO
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = CommentResponseDto.toDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }
}
