package com.project.groups.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // Alert 창을 띄우고 메시지를 출력
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>alert('로그인 실패\\n아이디 또는 비밀번호를 확인하세요'); window.location.href='/login';</script>");
    }
}
