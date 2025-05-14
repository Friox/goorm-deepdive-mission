package dev.friox.database.model.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoanCreateRequestDto {

    private String username;
    private String isbn;

}
