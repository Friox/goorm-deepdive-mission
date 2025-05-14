package dev.friox.database.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoanInsertDto {

    @Setter private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate startDate;
    private LocalDate endDate;

}
