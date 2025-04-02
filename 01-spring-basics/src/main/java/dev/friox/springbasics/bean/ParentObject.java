package dev.friox.springbasics.bean;

public class ParentObject {

    // M: 구성 파일에서 정의된 빈 간 관계 구현하기
    // M: JavaConfig를 사용한 빈 설정
    // 미션에서 사용할 부모 객체

    private final ChildObject childObject;
    private String name;

    public ParentObject(ChildObject childObject) {
        this.childObject = childObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildName() {
        return childObject.getName();
    }

    public void setChildName(String name) {
        childObject.setName(name);
    }

}
