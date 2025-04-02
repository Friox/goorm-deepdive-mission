package dev.friox.springbasics.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QualifierServiceTest {

    @Autowired
    QualifierService qualifierService;

    @Test
    void getPropertyClassName() {
        // M: Qualifier를 사용하여 동일한 타입의 빈 주입 제어하기 - Test
        // @Qualifier로 지정한 FakeObject를 대상으로 합니다.
        assertThat(qualifierService.getPropertyClassName()).isEqualTo("FakeObject");
    }
}