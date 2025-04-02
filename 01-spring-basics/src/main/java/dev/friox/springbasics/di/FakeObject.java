package dev.friox.springbasics.di;

import org.springframework.stereotype.Component;

@Component()
public class FakeObject implements CommonInterface {

    // M: 인터페이스를 사용하여 의존성 주입하기 - 사용
    // M: Qualifier를 사용하여 동일한 타입의 빈 주입 제어하기 - 정의

    @Override
    public String getClassName() {
        return "FakeObject";
    }

}
