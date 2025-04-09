package dev.friox.springadvanced.service;

import dev.friox.springadvanced.dto.UserSignUpDTO;
import dev.friox.springadvanced.entity.User;
import dev.friox.springadvanced.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자와 관련한 작업을 수행하는 서비스
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 입력 정보를 이용하여 회원가입
     * @param dto 회원가입 정보 DTO
     */
    public void signUpUser(UserSignUpDTO dto) throws Exception {
        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            throw new Exception();
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User.UserBuilder()
                .userEmail(dto.getEmail())
                .userPass(encodedPassword)
                .build();
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

}
