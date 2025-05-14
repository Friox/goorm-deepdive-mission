package dev.friox.database.model.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoanResponseDto {

    private Long loanId;
    private Long userId;
    private String userRealname;
    private Long bookId;
    private String bookTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;

    @Override
    public String toString() {
        return "LoanResponseDto{" +
                "loanId=" + loanId +
                ", userId=" + userId +
                ", userRealname='" + userRealname + '\'' +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
