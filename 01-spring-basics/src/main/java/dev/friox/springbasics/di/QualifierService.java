package dev.friox.springbasics.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QualifierService {

    // M: Qualifier를 사용하여 동일한 타입의 빈 주입 제어하기 - 사용

    // private: 캡슐화 원칙 준수. 외부로부터 접근 및 변경 방지
    // final: 불변성 보장. 객체가 의도치 않게 변경되는것을 방지
    private final CommonInterface commonInterface;

    // @Primary로 지정하지 않은 빈인 'FakeObject'를 선택.
    // @Qualifier 어노테이션은 @Primary 어노테이션보다 우선된다.
    public QualifierService(@Qualifier("fakeObject") CommonInterface commonInterface) {
        this.commonInterface = commonInterface;
    }

    public String getPropertyClassName() {
        return commonInterface.getClassName();
    }

}
