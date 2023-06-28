package com.sparta.springauth.controller;

import com.sparta.springauth.dto.LoginRequestDto;
import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res) {  //res -> 검증이 끝나고 jwt 토큰 쿠키에 넣고 쿠키 담아 반환하기 위하여
        try {  //로그인에 오류가 생겼을때를 위해
            userSrvice.login(requestDto, res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error";  // 다시 로그인 페이지로 // ?error ->에러 표시
        }

        return "redirect:/"; //메인으로 반환
    }


}