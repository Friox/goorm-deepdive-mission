package dev.friox.springadvanced.repository;

import dev.friox.springadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}