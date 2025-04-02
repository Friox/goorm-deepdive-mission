package dev.friox.springbasics.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifeCycle {

    // M: 빈 라이프사이클 메서드 활용하기 - 구현

    @PostConstruct
    public void postConstructMethod() {
        System.out.println("LifeCycle 빈 초기화");
    }

    public void func() {
        System.out.println("LifeCycle 확인");
    }

    @PreDestroy
    public void preDestroyMethod() {
        System.out.println("LifeCycle 빈 소멸");
    }

}
