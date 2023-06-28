package com.sparta.springauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")   //메인페이지에 가기 위해서 만들어 놓은 콘트롤러
    public String home(Model model) {
        model.addAttribute("username", "username");  //키값과 벨류값
        return "index"; //메인페이지에 반환
    }
}