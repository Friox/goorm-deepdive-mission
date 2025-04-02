package dev.friox.springbasics.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrimaryServiceTest {

    @Autowired
    PrimaryService primaryService;

    @Test
    void getPropertyClassName() {
        // M: @Primary를 사용하여 기본 빈 설정하기 - Test
        // @Primary로 지정한 RealObject를 대상으로 합니다.
        assertThat(primaryService.getPropertyClassName()).isEqualTo("RealObject");
    }

}