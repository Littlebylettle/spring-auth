package com.sparta.springauth.filter;

import com.sparta.springauth.entity.User;
import com.sparta.springauth.jwt.JwtUtil;
import com.sparta.springauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
//인증 인가 관련된 필터
@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/api/user") || url.startsWith("/css") || url.startsWith("/js"))
                //null인지 확인 && api로 시작을 하냐 안하냐 확인
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행  --> 인증하려고 로그인하는 거기 때문에 검증할 필요없
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest); //제일 첫 요청이라 따로 매서드가 필

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);  //배어럴 떼어냄

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByUsername(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("Not Found User")
                );

                request.setAttribute("user", user);
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }

}