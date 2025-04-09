package dev.friox.springadvanced.security.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 인증 요청에 대해 처리하는 필터
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 입력받은 값과 함께 AuthenticationManager 에게 인증을 위임한다.
        // AuthenticationManager 는 해당 유형의 인증을 처리할 수 있는 AuthenticationProvider를 찾아 인증을 위임한다.
        UsernamePasswordAuthenticationToken authRequest;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        authRequest = new UsernamePasswordAuthenticationToken(email, password);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}