package dev.friox.springbasics.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PropertyTest {

    @Autowired
    private Property property;

    @Test
    public void getProperty() {
        // M: 프로퍼티 파일을 이용한 환경 설정 주입하기 - Test
        // application.yml의 spring.application.name 속성값인 'spring-basics'를 기준으로 합니다.
        assertThat(property.getApplicationName()).isEqualTo("spring-basics");
        assertThat(property.getCustomProperty()).isEqualTo("customproperty");
    }
}