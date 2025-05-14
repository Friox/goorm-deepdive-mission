package dev.friox.database.model.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateRequestDto {

    private String password;
    private String email;
    private String realname;

}
