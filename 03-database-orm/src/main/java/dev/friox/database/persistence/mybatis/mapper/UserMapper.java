package dev.friox.database.persistence.mybatis.mapper;

import dev.friox.database.persistence.jpa.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByUsername(String username);
    Boolean existsByUsername(String username);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(String username);

}
