package dev.friox.springbasics.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    // M: JavaConfig를 사용한 빈 설정 - 정의

    @Bean(name = "javaConfigChildBean")
    public ChildObject childObject() {
        return new ChildObject();
    }

    @Bean(name = "javaConfigParentBean")
    public ParentObject parentObject(ChildObject childObject) {
        return new ParentObject(childObject);
    }

}
