package dev.friox.springbasics.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CircularDependencyTest {

    @Autowired private CircularA circularA;
    @Autowired private CircularB circularB;

    @Test
    public void circularDependencyTest() {
        // M: 순환 의존성 해결하기 - Test
        // springbasics/bean/CircularA.class 의 18번 라인을 수정하여
        // 순환참조 오류를 발생시킬 수 있습니다.
        circularA.setName("circularA");
        circularB.setName("circularB");
        assertThat(circularA.getName()).isEqualTo("circularA");
        assertThat(circularB.getName()).isEqualTo("circularB");
    }

}
