package dev.friox.database.model.dto.response;

import dev.friox.database.persistence.jpa.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateResponseDto {

    private String username;
    private String email;
    private String realname;

    public static UserUpdateResponseDto fromEntity(User user) {
        return UserUpdateResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .realname(user.getRealname())
                .build();
    }

}
