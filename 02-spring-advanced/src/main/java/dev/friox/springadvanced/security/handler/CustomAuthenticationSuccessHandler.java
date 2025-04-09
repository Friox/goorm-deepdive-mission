package dev.friox.springadvanced.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;

/**
 * 인증 성공 시 사용할 핸들러
 * */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 세션 사용 시 인증정보를 계속 사용할 수 있도록, 인증 성공 시 Authentication 객체를 세션에 저장한다.
        // 이미 이 시점에서는 Spring Security의 인증필터가 요청을 처리하고 SecurityContextHolder에 저장했기때문에
        // 별도로 SecurityContextHolder에 인증정보를 저장하는 코드를 작성하지 않아도 된다.
        HttpSessionSecurityContextRepository securityContextRepository;
        securityContextRepository = new HttpSessionSecurityContextRepository();
        SecurityContext context = SecurityContextHolder.getContext();
        securityContextRepository.saveContext(context, request, response);
        response.sendRedirect("/");
    }

}
