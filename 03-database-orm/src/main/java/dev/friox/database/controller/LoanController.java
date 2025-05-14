package dev.friox.database.controller;

import dev.friox.database.model.dto.request.LoanCreateRequestDto;
import dev.friox.database.model.dto.response.LoanResponseDto;
import dev.friox.database.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Loan", description = "대출 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    // CREATE
    @Operation(summary = "대출 이력 생성", description = "새로운 대출을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "대출 생성 성공"),
            @ApiResponse(responseCode = "404", description = "유저 또는 도서 찾을 수 없음.")
    })
    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@RequestBody LoanCreateRequestDto dto) {
        LoanResponseDto responseDto = loanService.createLoan(dto);
        System.out.println(responseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    // READ
    @Operation(summary = "대출 조회", description = "대출 기록을 유저 실명 또는 도서 제목으로 검색합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대출 기록 조회 성공"),
            @ApiResponse(responseCode = "400", description = "유저 실명 또는 도서 제목 제공되지 않음"),
            @ApiResponse(responseCode = "404", description = "조건에 맞는 대출 기록 없음")
    })
    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> searchLoanBy(
            @RequestParam(required = false) String realname,
            @RequestParam(required = false) String title
    ) {
        if (realname == null && title == null) {
            return ResponseEntity.badRequest().build();
        }
        List<LoanResponseDto> responseDtoList;
        if (realname != null) {
            responseDtoList = loanService.getLoansByUserRealname(realname);
        } else {
            responseDtoList = loanService.getLoansByBookTitle(title);
        }
        return ResponseEntity.ok(responseDtoList);
    }

    // READ
    @Operation(summary = "대출 ID로 조회", description = "대출 ID로 대출 이력을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대출 기록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "대출 기록 존재하지 않음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDto> getLoanById(@PathVariable Long id) {
        LoanResponseDto responseDto = loanService.getLoanById(id);
        return ResponseEntity.ok(responseDto);
    }

    // UPDATE
    @Operation(summary = "대출 반납일 연장", description = "지정한 날만큼 반납일 연장.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "연장 성공"),
            @ApiResponse(responseCode = "404", description = "대출 이력을 찾을 수 없음")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LoanResponseDto> updateLoanById(@PathVariable Long id, @RequestParam Long day) {
        LoanResponseDto responseDto = loanService.extendEndDate(id, day);
        return ResponseEntity.ok(responseDto);
    }

    // DELETE
    @Operation(summary = "대출 이력 삭제", description = "대출 ID를 이용하여 대출 이력을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대출 이력 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "대출 이력 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanById(@PathVariable Long id) {
        loanService.deleteLoan(id);
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
