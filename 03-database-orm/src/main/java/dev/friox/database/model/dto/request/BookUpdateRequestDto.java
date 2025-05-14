package dev.friox.database.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookUpdateRequestDto {

    private String title;
    private String author;
    private LocalDate publishDate;

    @Override
    public String toString() {
        return "UpdateBookRequestDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
