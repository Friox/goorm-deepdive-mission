package dev.friox.database.persistence.jpa.repository;

import dev.friox.database.persistence.jpa.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Optional<Loan> findById(Long id);

}
