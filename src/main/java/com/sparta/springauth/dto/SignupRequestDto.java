package com.sparta.springauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private boolean admin = false;     //관리자 확인
    private String adminToken = "";    //관리자 토큰
}