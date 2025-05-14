package dev.friox.database.controller;

import dev.friox.database.model.dto.response.BookResponseDto;
import dev.friox.database.model.dto.request.BookUpdateRequestDto;
import dev.friox.database.persistence.jpa.entity.Book;
import dev.friox.database.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Book", description = "도서 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // CREATE
    @Operation(summary = "도서 생성", description = "새로운 도서를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "도서 생성 성공"),
            @ApiResponse(responseCode = "409", description = "도서 중복")
    })
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookResponseDto dto) {
        Book book = bookService.createBook(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(book);
    }

    // READ
    @Operation(summary = "도서 조회", description = "존재하는 도서를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "도서 조회 성공"),
            @ApiResponse(responseCode = "404", description = "도서를 찾을 수 없음")
    })
    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDto> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        BookResponseDto responseDto = BookResponseDto.fromEntity(book);
        return ResponseEntity.ok(responseDto);
    }

    // UPDATE
    @Operation(summary = "도서 정보 업데이트", description = "도서의 정보를 업데이트합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "도서 업데이트 성공"),
            @ApiResponse(responseCode = "404", description = "도서를 찾을 수 없음")
    })
    @PutMapping("/{isbn}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable String isbn,
            @RequestBody BookUpdateRequestDto dto
    ) {
        Book updatedBook = bookService.updateBook(isbn, dto);
        BookResponseDto responseDto = BookResponseDto.fromEntity(updatedBook);
        return ResponseEntity.ok(responseDto);
    }

    // DELETE
    @Operation(summary = "도서 삭제", description = "도서를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "도서 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "도서를 찾을 수 없음")
    })
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }

    // EXCEPTION HANDLER
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getReason());
    }

}
