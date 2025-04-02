package dev.friox.springbasics.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ScopeTest {

    // M: 싱글톤 빈 스코프와 프로토타입 빈 스코프 구현하기

    @Autowired private PrototypeObject prototypeObjectA;
    @Autowired private PrototypeObject prototypeObjectB;
    @Autowired private SingletonObject singletonObjectA;
    @Autowired private SingletonObject singletonObjectB;

    @Test
    public void prototypeScopeTest() {
        // M: 싱글톤 빈 스코프와 프로토타입 빈 스코프 구현하기 - Prototype Test
        // A, B 각각 다른이름을 설정하여 모두 개별의 인스턴스임을 확인할 수 있습니다.
        prototypeObjectA.setProp("Prototype A");
        prototypeObjectB.setProp("Prototype B");
        assertThat(prototypeObjectA.getProp()).isEqualTo("Prototype A");
        assertThat(prototypeObjectB.getProp()).isEqualTo("Prototype B");
    }

    @Test
    public void singletonScopeTest() {
        // M: 싱글톤 빈 스코프와 프로토타입 빈 스코프 구현하기 - Singleton Test
        // A, B 각각 다른이름을 설정했지만, 싱글톤 빈이라 같은 값을 가지는 것을 확인할 수 있습니다.
        singletonObjectA.setProp("Prototype A");
        singletonObjectB.setProp("Prototype B");
        assertThat(singletonObjectA.getProp()).isEqualTo("Prototype B");
        assertThat(singletonObjectB.getProp()).isEqualTo("Prototype B");
    }

}
