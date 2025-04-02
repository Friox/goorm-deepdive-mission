package dev.friox.springbasics.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LifeCycleTest {

    @Autowired
    private LifeCycle lifeCycle;

    @Test
    public void checkLifeCycle() {
        // M: 빈 라이프사이클 메서드 활용하기 - Test
        lifeCycle.func();
    }

}