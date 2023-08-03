package com.jangsuhyeon.main.controller;

import com.jangsuhyeon.comment.domain.dto.CommentResponseDto;
import com.jangsuhyeon.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("")
public class MainController {

    private final CommentService commentService;

    // 메인 화면으로
    @GetMapping("")
    public String goToIndex(Model model) {

        // 전체 댓글 조회
        List<CommentResponseDto> commentList = commentService.findAll();
        model.addAttribute("commentList", commentList);

        return "pages/index";
    }
}
