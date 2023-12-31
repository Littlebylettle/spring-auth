package com.sparta.springauth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("수동 등록한 passwordEncoder를 주입 받아와 문자열 암호화")
    void test1() {
        String password = "Robbie's password";  //현재 비밀번호

        // 암호화
        String encodePassword = passwordEncoder.encode(password);  //encode라는 매서드에 주입
        System.out.println("encodePassword = " + encodePassword);

        String inputPassword = "Robbie";   //로그인했다고 가정을하고 다른 비밀번호와 비교해보기

        // 복호화를 통해 암호화된 비밀번호와 비교
        boolean matches = passwordEncoder.matches(inputPassword, encodePassword);  //matches - 비교하는 매서드 (입력받은 평문, 압호화된 비밀번호)
        System.out.println("matches = " + matches); // 암호화할 때 사용된 값과 다른 문자열과 비교했기 때문에 false
    }
}