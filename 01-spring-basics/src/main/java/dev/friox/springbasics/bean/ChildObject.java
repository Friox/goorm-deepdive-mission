package dev.friox.springbasics.bean;

public class ChildObject {

    // M: 구성 파일에서 정의된 빈 간 관계 구현하기
    // M: JavaConfig를 사용한 빈 설정
    // 미션에서 사용할 자식 객체

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
