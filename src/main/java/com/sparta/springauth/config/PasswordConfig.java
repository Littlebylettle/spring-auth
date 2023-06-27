package com.sparta.springauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// 비밀번호를 암호화 하기 위하여 수동으로 빈 등록하기
@Configuration  //매서드가 포함한 클래스 위에 어노테이션 -? Ioc 컨테이너에 빈으로 저장됨 소문자로 변하며 passwordConfig
public class PasswordConfig {

    @Bean     // 빈으로 등록하고자 하는 객체를 반환하는 매서드를 선언 후 위에 어노테이션
    public PasswordEncoder passwordEncoder() { //passwordEncoder로 등록됨 인터페이스라 구현체를 넣어줘야함
        return new BCryptPasswordEncoder(); //구현체를 선택한 것 --> BCrypt 비밀번호를 암호화 해주는 Hash함수임
    }
}