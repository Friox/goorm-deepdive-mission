package dev.friox.database.persistence.mybatis.mapper;

import dev.friox.database.model.dto.LoanInsertDto;
import dev.friox.database.model.dto.response.LoanResponseDto;
import dev.friox.database.persistence.jpa.entity.Loan;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface LoanMapper {

    Loan selectById(Long id);
    LoanResponseDto selectDetailById(Long id);
    List<LoanResponseDto> selectDetailsByUserRealname(String realname);
    List<LoanResponseDto> selectDetailsByBookTitle(String title);
    void insertLoan(LoanInsertDto dto);
    void updateEndDate(Long id, LocalDate newEndDate);

}
