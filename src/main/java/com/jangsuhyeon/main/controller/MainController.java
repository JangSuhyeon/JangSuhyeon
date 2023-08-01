package com.jangsuhyeon.main.controller;

import com.jangsuhyeon.main.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class MainController {

    // 메인 화면으로
    @GetMapping("")
    public String goToIndex() {
        return "pages/index";
    }

    // 댓글 저장
    @PostMapping(value = "/comment")
    @ResponseBody
    public ResponseEntity saveComment(CommentDto commentDto) {
        System.out.println(commentDto.toString());
        return ResponseEntity.ok("댓글 저장 성공");
    }
}
