package dev.friox.springadvanced.entity;

import jakarta.persistence.*;

/*
* 시행착오 2
* 현상 : 애플리케이션 시작 시 테이블을 생성하지 못함
* 원인 : 테이블이름이 SQL 예약어 'USER' 와 겹쳐 생성하지 못함
* 해결 : 테이블이름을 예약어가 아닌 단어로 명시하여 해결
* 기본적으로 @Table(name)이 정의되지 않을경우 클래스 이름으로 테이블을 생성한다.
* User 키워드는 SQL에서 예약어로 지정되어있어 테이블 이름으로 사용이 불가하다.
* 애플리케이션 시작시 JPA가 DB를 생성하는데 오류가 발생했다.
* 테이블 이름을 별도로 지정하여 오류를 해결했다.
* */

/**
 * JPA에서 사용할 유저 Entity 정의.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "USER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PASS")
    private String userPass;

    // protected 접근자를 설정함으로써 불필요한 생성을 방지
    protected User() {}

    // 불변성을 지키기 위해 Setter 사용 지양.
    // Builder 패턴 수동 구현
    public UserBuilder toBuilder() {
        return new UserBuilder()
                .userNo(this.userNo)
                .userEmail(this.userEmail)
                .userPass(this.userPass);
    }

    public static class UserBuilder {

        private int userNo;
        private String userEmail;
        private String userPass;

        public UserBuilder userNo(int userNo) {
            this.userNo = userNo;
            return this;
        }

        public UserBuilder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public UserBuilder userPass(String userPass) {
            this.userPass = userPass;
            return this;
        }

        public User build() {
            User user = new User();
            user.userNo = this.userNo;
            user.userEmail = this.userEmail;
            user.userPass = this.userPass;
            return user;
        }

    }

    public int getUserNo() {
        return userNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

}
