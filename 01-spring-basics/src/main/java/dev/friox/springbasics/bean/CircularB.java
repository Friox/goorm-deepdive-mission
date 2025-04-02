package dev.friox.springbasics.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularB {

    // M: 어노테이션을 사용하여 빈 주입하기
    // M: 순환 의존성 해결하기
    // 미션에서 사용할 순환 객체 B

    private final CircularA circularA;
    private String name;

    @Autowired
    public CircularB(CircularA circularA) {
        this.circularA = circularA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
