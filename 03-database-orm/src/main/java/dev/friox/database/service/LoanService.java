package dev.friox.database.service;

import dev.friox.database.model.dto.LoanInsertDto;
import dev.friox.database.model.dto.request.LoanCreateRequestDto;
import dev.friox.database.model.dto.response.LoanResponseDto;
import dev.friox.database.persistence.jpa.entity.Book;
import dev.friox.database.persistence.jpa.entity.Loan;
import dev.friox.database.persistence.jpa.entity.User;
import dev.friox.database.persistence.jpa.repository.BookRepository;
import dev.friox.database.persistence.jpa.repository.LoanRepository;
import dev.friox.database.persistence.mybatis.mapper.LoanMapper;
import dev.friox.database.persistence.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final UserMapper userMapper;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;

    // CREATE
    public LoanResponseDto createLoan(LoanCreateRequestDto dto) {
        LocalDate now = LocalDate.now();
        User user = userMapper.findByUsername(dto.getUsername());
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Book book = bookRepository.findByIsbn(dto.getIsbn())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LoanInsertDto insertDto = LoanInsertDto.builder()
                .userId(user.getId())
                .bookId(book.getId())
                .startDate(now)
                .endDate(now.plusDays(7))
                .build();
        loanMapper.insertLoan(insertDto);
        return loanMapper.selectDetailById(insertDto.getId());
    }

    // READ
    public LoanResponseDto getLoanById(Long id) {
        LoanResponseDto loan = loanMapper.selectDetailById(id);
        if (loan == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return loan;
    }

    // READ
    public List<LoanResponseDto> getLoansByUserRealname(String realname) {
        List<LoanResponseDto> loans = loanMapper.selectDetailsByUserRealname(realname);
        if (loans.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return loans;
    }

    // READ
    public List<LoanResponseDto> getLoansByBookTitle(String title) {
        List<LoanResponseDto> loans = loanMapper.selectDetailsByBookTitle(title);
        if (loans.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return loans;
    }

    // UPDATE
    public LoanResponseDto extendEndDate(Long id, Long day) {
        Loan loan = loanMapper.selectById(id);
        if (loan == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        LocalDate newEndDate = loan.getEndDate().plusDays(day);
        loanMapper.updateEndDate(id, newEndDate);
        return loanMapper.selectDetailById(id);
    }

    // DELETE
    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        loanRepository.delete(loan);
    }

}