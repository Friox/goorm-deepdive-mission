package dev.friox.springbasics.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularA {

    // M: 어노테이션을 사용하여 빈 주입하기
    // M: 순환 의존성 해결하기
    // 미션에서 사용할 순환 객체 A

    private final CircularB circularB;
    private String name;

    @Autowired
    // public CircularA(CircularB circularB) {
    public CircularA(@Lazy CircularB circularB) {
        this.circularB = circularB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
