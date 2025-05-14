package dev.friox.database.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column(nullable = false)
    private String realname;

}
