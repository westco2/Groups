package com.project.groups.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String message = "해당 페이지에 접근 할 수 없습니다.";

        String alertScript = "<script>alert('" + message + "'); history.back();</script>";

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(alertScript);
    }


}
