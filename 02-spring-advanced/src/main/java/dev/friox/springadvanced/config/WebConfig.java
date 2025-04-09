package dev.friox.springadvanced.config;

import dev.friox.springadvanced.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring WebMvc 설정에 관여하는 클래스.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    public WebConfig(LogInterceptor logInterceptor) {
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 등록
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**") // 적용할 경로, 모든 경로로 설정
                .excludePathPatterns(); // 제외할 경로, 일단 비워둠
    }

}
