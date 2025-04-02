package dev.friox.springbasics.di;

import org.springframework.stereotype.Service;

@Service
public class PrimaryService {

    // M: @Primary를 사용하여 기본 빈 설정하기 - 실행

    private final CommonInterface commonInterface;

    // 단일 생성자일경우 @AutoWired 생략가능. (up to Spring 4.3)
    public PrimaryService(CommonInterface commonInterface) {
        this.commonInterface = commonInterface;
    }

    public String getPropertyClassName() {
        return commonInterface.getClassName();
    }

}
