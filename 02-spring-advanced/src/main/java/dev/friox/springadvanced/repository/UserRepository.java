package dev.friox.springadvanced.repository;

import dev.friox.springadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 유저관련 JPARepository 자동구현을 위한 인터페이스
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String username); // 이메일(유저네임)을 사용하여 유저를 검색
}