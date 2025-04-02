package dev.friox.springbasics.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Property {

    // M: 프로퍼티 파일을 이용한 환경 설정 주입하기

    // @Value 어노테이션 같은 경우 필드 주입으로 충분하다.
    // 스프링 컨텍스트가 초기화될때 프로퍼티값을 필드에 할당하는 방식으로,
    // 일반적으로 불변데이터를 쓰는 경우가 많아 괜찮음.
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${custom.property}")
    private String customProperty;

    public String getApplicationName() {
        return applicationName;
    }

    public String getCustomProperty() {
        return customProperty;
    }
}
