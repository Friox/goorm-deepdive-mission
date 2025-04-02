package dev.friox.springbasics.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RealObject implements CommonInterface {

    // M: 인터페이스를 사용하여 의존성 주입하기 - 사용
    // M: @Primary를 사용하여 기본 빈 설정하기 - 정의

    @Override
    public String getClassName() {
        return "RealObject";
    }

}