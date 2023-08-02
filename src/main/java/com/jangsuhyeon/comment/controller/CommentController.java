package com.jangsuhyeon.comment.controller;

import com.jangsuhyeon.comment.domain.dto.CommentRequestDto;
import com.jangsuhyeon.comment.domain.dto.CommentResponseDto;
import com.jangsuhyeon.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity saveComment(CommentRequestDto commentRequestDto) {

        commentService.save(commentRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("Data saved!");
    }

    // 댓글 전체 조회 JSON
    @GetMapping("")
    @ResponseBody
    public ResponseEntity findAll() {

        List<CommentResponseDto> commentList = commentService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

}
