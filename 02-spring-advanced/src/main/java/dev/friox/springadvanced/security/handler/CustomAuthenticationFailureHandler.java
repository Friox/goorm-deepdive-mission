package dev.friox.springadvanced.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * 인증 실패 시 사용할 핸들러
 * */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 예외 타입에 따라 적절한 메시지를 세션 속성으로 지정하여 넘긴다
        HttpSession session = request.getSession();
        if (exception instanceof UsernameNotFoundException) {
            session.setAttribute("signinMessage", "계정이 존재하지 않습니다");
        } else if (exception instanceof BadCredentialsException) {
            session.setAttribute("signinMessage", "비밀번호가 일치하지 않습니다");
        } else {
            session.setAttribute("signinMessage", "로그인 할 수 없습니다");
        }
        response.sendRedirect("/signin");
    }

}
