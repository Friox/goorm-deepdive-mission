package dev.friox.springadvanced.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "USER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PASS")
    private String userPass;

    protected User() {}

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

}
