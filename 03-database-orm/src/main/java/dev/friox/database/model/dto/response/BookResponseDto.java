package dev.friox.database.model.dto.response;

import dev.friox.database.persistence.jpa.entity.Book;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookResponseDto {

    private String isbn;
    private String title;
    private String author;
    private LocalDate publishDate;

    public static BookResponseDto fromEntity(Book book) {
        return BookResponseDto.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishDate(book.getPublishDate())
                .build();
    }

}
