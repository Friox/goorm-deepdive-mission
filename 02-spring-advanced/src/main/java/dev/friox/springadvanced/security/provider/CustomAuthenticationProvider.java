package dev.friox.springadvanced.security.provider;

import dev.friox.springadvanced.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 특정 타입의 인증요청을 처리하는 프로바이더
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired private CustomUserDetailsService detailsService;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 실제 인증 로직을 처리하는 핵심 메서드
        // 이메일을 통해 유저를 찾고, 입력한 패스워드와 비교하여 자격증명을 수행한다.
        // 만약 일치하지 않으면 BadCredentialsException 예외를 발생시킨다.
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = detailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호 불일치");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        /*
        * 해당 Provider가 어떤 종류의 Authentication 객체를 처리할 수 있는지 판단하는 메서드
        * equals 대신 isAssignableFrom 을 사용하는것이 더 좋다고 한다.
        * 무엇보다 클래스 간의 상속 관계를 고려한 비교가 가능한것이 큰 장점이다.
        * Spring Security에서 권장하는 표준 구현 방식이기도 하다.
        * equals와 달리 인증의 유연성을 챙길 수 있다.
        * */
        // return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
