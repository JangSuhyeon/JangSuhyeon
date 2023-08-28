package com.jangsuhyeon.security.controller;

import com.jangsuhyeon.security.domain.dto.SignRequest;
import com.jangsuhyeon.security.domain.dto.SignResponse;
import com.jangsuhyeon.security.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    // 로그인 화면으로
    @GetMapping("/login")
    public String goToLogin() {
        return "pages/login";
    }

    // 로그인
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<SignResponse> signin(@RequestBody SignRequest request) {
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    // 회원가입 화면으로
    @GetMapping("/register")
    public String goToRegister() {
        return "pages/register";
    }

    // 회원가입
    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<Boolean> signup(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }

    // 회원조회
    @ResponseBody
    @GetMapping("/user")
    public ResponseEntity<SignResponse> getUser(@RequestParam String account) throws Exception {
        return new ResponseEntity<>(signService.getMember(account), HttpStatus.OK);
    }
}
