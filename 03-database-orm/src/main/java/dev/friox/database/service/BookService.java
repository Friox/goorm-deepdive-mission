package dev.friox.database.service;

import dev.friox.database.model.dto.response.BookResponseDto;
import dev.friox.database.model.dto.request.BookUpdateRequestDto;
import dev.friox.database.persistence.jpa.entity.Book;
import dev.friox.database.persistence.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // CREATE
    public Book createBook(BookResponseDto dto) {
        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        Book book = Book.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publishDate(dto.getPublishDate())
                .build();
        return bookRepository.save(book);
    }

    // READ
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    public Book updateBook(String isbn, BookUpdateRequestDto dto) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Book.BookBuilder builder = book.toBuilder();
        if (dto.getTitle() != null) builder.title(dto.getTitle());
        if (dto.getAuthor() != null) builder.author(dto.getAuthor());
        if (dto.getPublishDate() != null) builder.publishDate(dto.getPublishDate());
        Book updatedBook = builder.build();
        bookRepository.save(updatedBook);
        return updatedBook;
    }

    // DELETE
    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }

}
