package dev.friox.springbasics.aop;

import org.springframework.stereotype.Service;

@Service
public class AspectService {

    // M: AOP를 사용하여 Aspect 구현하기 - 실행부

    public void joinPointMethod() {
        System.out.println("실제 작업 실행");
    }

}
