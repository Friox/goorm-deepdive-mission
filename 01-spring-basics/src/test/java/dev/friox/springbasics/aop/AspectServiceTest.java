package dev.friox.springbasics.aop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

@SpringBootTest
class AspectServiceTest {

    // M: 어노테이션을 사용하여 빈 주입하기
    // 'javax.inject:javax.inject:1' 라이브러리를 사용합니다.
    @Inject
    private AspectService aspectService;

    @Test
    void joinPointMethod() {
        // M: AOP를 사용하여 Aspect 구현하기 - Test
        aspectService.joinPointMethod();
    }

}