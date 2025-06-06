package dev.friox.springbasics.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonObject {

    // M: 싱글톤 빈 스코프와 프로토타입 빈 스코프 구현하기
    // 싱글톤 빈 정의

    private String prop;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

}
