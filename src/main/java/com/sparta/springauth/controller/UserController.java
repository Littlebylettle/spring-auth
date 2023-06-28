package com.sparta.springauth.controller;

import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    public UserController(UserService userSrvice) {
        this.userSrvice = userSrvice;
    }

    private final UserService userSrvice;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";   //로그인 페이지 반환
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup"; //회원가입 페이지 반환
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto) {
        userSrvice.signup(requestDto);
        return "redirect:/api/user/login-page";
    }


}