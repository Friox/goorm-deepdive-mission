package dev.friox.database.service;

import dev.friox.database.model.dto.request.UserCreateRequestDto;
import dev.friox.database.model.dto.request.UserUpdateRequestDto;
import dev.friox.database.persistence.jpa.entity.User;
import dev.friox.database.persistence.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // CREATE
    public User createUser(UserCreateRequestDto dto) {
        if (userMapper.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .realname(dto.getRealname())
                .build();
        userMapper.insertUser(user);
        return user;
    }

    // READ
    public User getUserByUserName(String username) {
        if (!userMapper.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userMapper.findByUsername(username);
    }

    // UPDATE
    public User updateUser(String username, UserUpdateRequestDto dto) {
        if (!userMapper.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User.UserBuilder builder = userMapper.findByUsername(username).toBuilder();
        if (dto.getPassword() != null) builder.password(dto.getPassword());
        if (dto.getEmail() != null) builder.email(dto.getEmail());
        if (dto.getRealname() != null) builder.realname(dto.getRealname());
        User updatedUser = builder.build();
        userMapper.updateUser(updatedUser);
        return updatedUser;
    }

    // DELETE
    public void deleteUser(String username) {
        if (!userMapper.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userMapper.deleteUser(username);
    }

}
