package dev.friox.springadvanced.security.config;

import dev.friox.springadvanced.security.filter.CustomAuthenticationFilter;
import dev.friox.springadvanced.security.handler.CustomAuthenticationFailureHandler;
import dev.friox.springadvanced.security.handler.CustomAuthenticationSuccessHandler;
import dev.friox.springadvanced.security.provider.CustomAuthenticationProvider;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security 관련 설정을 담당하는 클래스.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 요청을 구분하여 인증여부에 따라 제어
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 정적리소스들 허가
                        .requestMatchers( "/", "/signin", "/signup", "/h2-console/**", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        // H2 Console 사용을 위한 헤더설정
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                // 커스텀 인증 필터 설정
                .addFilterBefore(customAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        // 로그아웃 시 동작을 설정, 성공 시 메인 페이지로 이동하고 세션정보를 삭제한다
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .exceptionHandling(exception -> exception
                        // 이외의 처리되지 않은 요청에 대해 핸들링
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 인증이 필요한 페이지는 로그인이 필요하다는 정보를 제공하면서
                            // 로그인 페이지로 리다이렉션
                            HttpSession session = request.getSession();
                            session.setAttribute("signinMessage", "로그인이 필요합니다");
                            response.sendRedirect("/signin");
                        })
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider());
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(AuthenticationManager authenticationManager) {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        /*
        * 시행착오 3
        * 현상 : /signin(로그인 페이지)으로 GET 요청 시 /signin으로 무한 리다이렉션
        * 원인 : filter 인증 요청 실패 시 /signin 으로 리다이렉션하도록 했는데,
        * filter가 /signin에 대한 GET 요청도 잡아버려 실패했다고 판단 후 리다이렉션함
        * 해결 : POST 방식만 잡도록 제한함
        * Filter의 setFilterProcessesUrl은 GET, POST 방식 모두 처리한다.
        * /signin GET 요청도 잡아버려 예외를 발생시킨 후 필터체인에 설정된 핸들러에 의해 처리되어
        * 로그인 페이지에 접근하고 로그인 페이지로 리다이렉션되는 이상한 상황이 발생했다.
        * setFilterProcessesUrl 대신 setRequiresAuthenticationRequestMatcher 를 사용하여
        * POST 요청으로 한정하는 방법으로 해결했다.
        * */
        // customAuthenticationFilter.setFilterProcessesUrl("/signin");
        customAuthenticationFilter.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/signin", "POST")
        );
        customAuthenticationFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        customAuthenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

}
