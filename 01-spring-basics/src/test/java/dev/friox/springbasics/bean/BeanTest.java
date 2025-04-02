package dev.friox.springbasics.bean;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BeanTest {

    private final ParentObject xmlParentBean;
    private final ParentObject javaConfigParentObject;

    public BeanTest() {

        // M: 구성 파일에서 정의된 빈 간 관계 구현하기
        // M: JavaConfig를 사용한 빈 설정

        ApplicationContext xmlContext = new GenericXmlApplicationContext("bean/application-context.xml");
        xmlParentBean = (ParentObject)xmlContext.getBean("xmlParentBean");
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        javaConfigParentObject = (ParentObject)javaConfigContext.getBean("javaConfigParentBean");
    }

    @Test
    public void xmlBeanTest() {
        // M: 구성 파일에서 정의된 빈 간 관계 구현하기 - Test
        xmlParentBean.setName("xmlParent");
        xmlParentBean.setChildName("xmlChild");
        assertThat(xmlParentBean.getName()).isEqualTo("xmlParent");
        assertThat(xmlParentBean.getChildName()).isEqualTo("xmlChild");
    }

    @Test
    public void javaConfigBeanTest() {
        // M: JavaConfig를 사용한 빈 설정 - Test
        javaConfigParentObject.setName("javaConfigParent");
        javaConfigParentObject.setChildName("javaConfigChild");
        assertThat(javaConfigParentObject.getName()).isEqualTo("javaConfigParent");
        assertThat(javaConfigParentObject.getChildName()).isEqualTo("javaConfigChild");
    }

}
